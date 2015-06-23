<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html ng-app="gemStore">
  <head>
    <link rel="stylesheet" type="text/css" href="/css/lib/bootstrap.min.css" />
    <script type="text/javascript" src="/js/lib/angular.min.js"></script>
    <script type="text/javascript" src="/js/ngtest/ngtest1App.js"></script>
  </head>
  <body class="list-group" ng-controller="StoreController as store">
    <!--  Store Header  -->
    <header>
      <h1 class="text-center">Flatlander Crafted Gems</h1>
      <h2 class="text-center">– an Angular store –</h2>
    </header>
    <div class="list-group-item" ng-repeat="product in store.products">
      <h3>
        {{product.name}}
        <em class="pull-right">{{product.price | currency}}</em>
      </h3>
      <!-- Image Gallery  -->
      <section ng-show="product.images.length">
        <img ng-src="{{product.images[0]}}" />
        <ul class="list-inline thumbs">
          <li class="thumbnail" ng-repeat="image in product.images">
            <img ng-src="{{image}}" />
          </li>
        </ul>
      </section>

      <!-- Product Tabs -->
      <section class="tab" ng-controller="TabController as tab">
        <ul class="nav nav-pills">
          <li ng-class="{ active: tab.isSet(1) }">
            <a href ng-click="tab.setTab(1)">Description</a></li>
          <li ng-class="{ active: tab.isSet(2) }">
            <a href ng-click="tab.setTab(2)">Specs</a></li>
          <li ng-class="{ active: tab.isSet(3) }">
            <a href ng-click="tab.setTab(3)">Reviews</a></li>
        </ul>
        <div ng-show="tab.isSet(1)">
          <h4>Description</h4>
          <blockquote>{{product.description}}</blockquote>
        </div>
        <div ng-show="tab.isSet(2)">
          <h4>Specs</h4>
          <blockquote>Shine: {{product.shine}}</blockquote>
        </div>
        <div ng-show="tab.isSet(3)">
          <h4>Reviews</h4>
        </div>
      </section>

    </div>
  </body>
</html>
