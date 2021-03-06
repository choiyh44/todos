<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="todo">
<head>
<link rel="stylesheet" type="text/css" href="/css/lib/bootstrap.min.css" />
<script type="text/javascript" src="/js/lib/angular.min.js"></script>
<script type="text/javascript" src="/js/ngtest/todoService.js"></script>
<script type="text/javascript" src="/js/ngtest/ngtestApp.js"></script>
</head>

<body ng-controller="TodoController as todoCtrl">

	<div class="panel panel-default">
		<div class="panel-body">
			<!-- title -->
			<div class="page-header">
				<h1>todos</h1>
			</div>
			<!--// title -->

			<!-- input row -->
			<div class="input-group">
				<span class="input-group-addon">ㅁ</span>
				<form ng-submit="todoCtrl.onTodoFormSubmit()">
					<input type="text" class="form-control" aria-label="..."
						placeholder="What need to be done?"
						ng-model="todoCtrl.todoInput">
				</form>
			</div>
			<!-- /input-group -->

			<!-- input row -->
			<div class="input-group"
				ng-repeat="todo in todoCtrl.todos | filter:todoCtrl.itemViewFilter">
				<span class="input-group-addon">
					<input type="checkbox" aria-label="..." ng-model="todo.completed" ng-click="todoCtrl.onClickTodoCheckBox(todo)" />
				</span> 
				<span class="form-control" ng-hide="todoCtrl.isTodoEditing(todo.id)" ng-dblclick="todoCtrl.editTodo(todo)">{{todo.name}}</span> 
				<span class="form-control" ng-show="todoCtrl.isTodoEditing(todo.id)">
					<form ng-submit="todoCtrl.onTodoEditFormSubmit(todo)">
						<input type="text" class="form-control" aria-label="..." ng-model="todoCtrl.todoEdit">
					</form>
				</span> 
				<span class="input-group-addon">
					<a ng-click="todoCtrl.deleteTodo(todo.id)">X</a>
				</span>
			</div>
			<!-- /input-group -->

			<div class="input-group">
				<span>{{todoCtrl.getActiveCount()}} items left.</span>
				<button type="button" class="btn btn-default navbar-btn"
					ng-click="todoCtrl.onButtonClick('All')">All</button>
				<button type="button" class="btn btn-default navbar-btn"
					ng-click="todoCtrl.onButtonClick('Active')">Active</button>
				<button type="button" class="btn btn-default navbar-btn"
					ng-click="todoCtrl.onButtonClick('Completed')">Completed</button>
				<a href="#" ng-click="todoCtrl.clearCompleted()"
					ng-show="todoCtrl.getCompletedCount()">Clear completed</a>
			</div>
			<!-- // input-group -->
		</div>
		<!--// panel-body -->
	</div>
	<!-- // panel -->

</body>
</html>
