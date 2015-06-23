(function() {
	var app = angular.module('todoServiceModule', []);
	app.service('todoService', ["$http", function($http){
			// todos 조회
			this.getAllTodos = 
				function(todoController) {
					var url = '/ngtest/getAllTodos.nhn';
					$http.get(url).
						success(function(data, status, headers, config) {
							console.log('getAllTodos data: ' + data);
							if (data && data.header && data.header.isSuccessful === true) {
								todoController.todos =  data.todos;
							}
						});
			};
				
			this.addTodo = 
				function (todo) {
					var url = '/ngtest/addTodo.nhn';
					$http.get(url, {params:todo}).
						success(function(data, status, headers, config) {
							if (data && data.header && data.header.isSuccessful === true) {
								todo.id = data.id;
							}
						});
			};

			this.removeTodo = 
				function (id) {
					var url = '/ngtest/removeTodo.nhn';
					$http.get(url, {params:{'id':id}}).
						success(function(data, status, headers, config) {
							if (data && data.header && data.header.isSuccessful === true) {
								// do nothing
							}
						});
			};

			this.updateTodo = 
				function (todo) {
					var url = '/ngtest/updateTodo.nhn';
					$http.get(url, {params:todo}).
						success(function(data, status, headers, config) {
							if (data && data.header && data.header.isSuccessful === true) {
								// do nothing
							}
						});
			};
	
	}]);
	
})();
