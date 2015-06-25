(function() {
	var app = angular.module('todos', []);

	app.controller('TodosController', ["$scope", function($scope) {
		$scope.todos = []; // todo목록
		$scope.todoInput = ''; // todo입력필드값

		
		// 초기화
		$scope.todos = [
	         {id : 1, name : 'todo1', completed : false},
	         {id : 2, name : 'todo2', completed : false}
		];
		
		// 입력필드에 값입력 처리
		$scope.onTodoFormSubmit = function () {
			// 입력필드에 값이 있는 경우만 처리
			if (!$scope.todoInput.length) {
				return;
			}

			// 입력필드값을 todos 리스트에 저장
			var newTodo = {id:null,  name: $scope.todoInput ,completed: false};

			// todos 목록에 추가
			$scope.todos.push(newTodo); // false=Active, true=Completed

			// 입력필드 초기화 
			$scope.todoInput = ''; 
		};

		// 미완료목록 수 Count
		$scope.getActiveCount = function () {
			var count = 0;
			for (var i = 0, leng = $scope.todos.length; i < leng; i++) {
				if (!$scope.todos[i].completed) {
					count++;
				}
			}

			return count;
		};

		// completed 값 DB 수정
		$scope.onClickTodoCheckBox = function (todoArg) {
			// DB에 수정
			var newTodo = {id:todoArg.id,  completed: todoArg.completed};
		};
		
		// 선택목록 삭제
		$scope.deleteTodo = function(id) {
			if (confirm('삭제하시겠습니까!!!')) {
				for (var i = $scope.todos.length-1; i >= 0; i--) {
					if ($scope.todos[i].id === id) {
						$scope.todos.splice(i, 1);
					}
				}
			}
		};

	}]);

})();
