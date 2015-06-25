(function() {
	var app = angular.module('todo', ['todoServiceModule']);

	app.controller('TodoController', ["$http", "todoService", function($http, todoService) {
		this.todos = []; // todo목록
		this.todoInput = ''; // todo입력필드값
		this.todoEdit = ''; // todo수정필드값
		this.editingTodoId = 0; // 수정상태인 todo id 값. 0=수정중이 아님. 
		this.buttonStatus = { // 버튼(전체/미완료/완료) 상태. toggle버튼 구현을 위해..TODO 정리 필요.
				All: true,
				Active: false,
				Completed: false
		};
		this.itemViewCondition = 'All'; // 현재의 눌려진버튼 정보. toggle버튼(라디오버튼) 구현을 위해..TODO 정리 필요.
		this.itemViewFilter = {}; // todo목록 필터변수

		// 초기화: todos 테이블 DB조회하여 로드
		todoService.getAllTodos(this);

		// 입력필드에 값입력 처리
		this.onTodoFormSubmit = function () {
			// 입력필드에 값이 있는 경우만 처리
			if (!this.todoInput.length) {
				return;
			}

			// 입력필드값을 todos 리스트에 저장
			var newTodo = {id:null,  name: this.todoInput ,completed: false};

			// DB에 등록
			todoService.addTodo(newTodo);

			// todos 목록에 추가
			this.todos.push(newTodo); // false=Active, true=Completed

			// 입력필드 초기화 
			this.todoInput = ''; 
		};

		// 버튼클릭시(전체/미완료/완료) 처리
		// 필터를 설정하고 버튼 상태를 갱신한다. TODO 정리 필요.
		this.onButtonClick = function (buttonType) {
			this.itemViewCondition = buttonType;
			this.setItemViewFilter();
			for (var i = 0, leng = this.buttonStatus.length; i < leng; i++) {
				this.buttonStatus[i] = false;
			}
			this.buttonStatus[buttonType] = true;
		};

		// 목록 필터 설정: 전체/미완료/완료
		this.setItemViewFilter = function () {
			if (this.itemViewCondition === 'Active') {
				this.itemViewFilter.completed = false;
			}
			else if (this.itemViewCondition === 'Completed') {
				this.itemViewFilter.completed = true;
			}
			else { // this.itemViewCondition === 'All'
				this.itemViewFilter = {};
			}
		};

		// 미완료목록 수 Count
		this.getActiveCount = function () {
			var count = 0;
			for (var i = 0, leng = this.todos.length; i < leng; i++) {
				if (this.todos[i].completed === false) {
					count++;
				}
			}

			return count;
		};

		// 완료목록 수 Count
		this.getCompletedCount = function () {
			var count = 0;
			for (var i = 0, leng = this.todos.length; i < leng; i++) {
				if (this.todos[i].completed === true) {
					count++;
				}
			}

			return count;
		};
		
		// 수정중인지 체크. 화면의 필드 show/hide 제어 바인딩값
		this.isTodoEditing = function (id) {
			if (this.editingTodoId === id) {
				return true;
			}
			else {
				return false;
			}
		};

		// 수정시작. 수정폼필드에 현재값 세팅.
		this.editTodo = function (todo){
			this.editingTodoId = todo.id;
			this.todoEdit = todo.name;
		};
		
		// 수정폼 값입력 DB수정
		this.onTodoEditFormSubmit = function (todoArg) {
			// 입력필드에 값이 있는 경우만 처리
			if (!this.todoEdit.length || this.editingTodoId === 0) {
				return;
			}

			// DB에 수정
			var newTodo = {id:this.editingTodoId,  name: this.todoEdit};
			todoService.updateTodo(newTodo);

			// todo목록의 todo명 수정
			todoArg.name = this.todoEdit;
			
			// 입력필드 초기화 
			this.editingTodoId = 0;
			this.todoEdit = ''; 
		};
		
		// completed 값 DB 수정
		this.onClickTodoCheckBox = function (todoArg) {
			// DB에 수정
			var newTodo = {id:todoArg.id,  completed: todoArg.completed};
			todoService.updateTodo(newTodo);
		};
		
		// 완료목록 삭제
		this.clearCompleted = function() {
			for (var i = this.todos.length-1; i >= 0; i--) {
				if (this.todos[i].completed === true) {
					todoService.removeTodo(this.todos[i].id);
					this.todos.splice(i, 1);
				}
			}
		};

		// 선택목록 삭제
		this.deleteTodo = function(id) {
			if (confirm('삭제하시겠습니까!!!')) {
				for (var i = this.todos.length-1; i >= 0; i--) {
					if (this.todos[i].id === id) {
						todoService.removeTodo(this.todos[i].id);
						this.todos.splice(i, 1);
					}
				}
			}
		};

	}]);

})();
