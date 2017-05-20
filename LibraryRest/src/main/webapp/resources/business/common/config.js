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
	}).when("/admin/borrowers",{
		templateUrl: "adminBorrowerManage.html"
	}).when("/viewborrowers", {
		redirectTo: "/admin/borrowers"
	}).when("/admin/branches",{
		templateUrl: "adminBranchManage.html"
	}).when("/viewbranches", {
		redirectTo: "/admin/branches"
	}).when("/admin/genres",{
		templateUrl: "adminGenreManage.html"
	}).when("/viewgenres", {
		redirectTo: "/admin/genres"
	}).when("/admin/publishers",{
		templateUrl: "adminPublisherManage.html"
	}).when("/viewpublishers", {
		redirectTo: "/admin/publishers"
	})
}])