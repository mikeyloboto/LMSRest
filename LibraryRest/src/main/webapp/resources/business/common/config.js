lmsApp.config(["$routeProvider", function($routeProvider){
	return $routeProvider.when("/", {
		redirectTo: "/home"
	}).when("/home",{
		templateUrl: "home.html"
	}).when("/admin",{
		templateUrl: "admin.html"
	}).when("/admin/authors",{
		templateUrl: "adminAuthorManage.html"
	}).when("/viewauthors", {
		redirectTo: "/admin/authors"
	}).when("/admin/books",{
		templateUrl: "adminBookManage.html"
	}).when("/viewbooks", {
		redirectTo: "/admin/books"
	})
}])