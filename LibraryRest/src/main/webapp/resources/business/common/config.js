lmsApp.config(["$routeProvider", function($routeProvider){
	return $routeProvider.when("/", {
		redirectTo: "/home"
	}).when("/home",{
		templateUrl: "home.html"
	}).when("/admin",{
		templateUrl: "admin.html"
	}).when("/admin/authors",{
		templateUrl: "adminAuthorManage.html"
	}).when("/admin/books",{
		templateUrl: "adminBookManage.html"
	}).when("/admin/borrowers",{
		templateUrl: "adminBorrowerManage.html"
	}).when("/admin/branches",{
		templateUrl: "adminBranchManage.html"
	}).when("/admin/genres",{
		templateUrl: "adminGenreManage.html"
	}).when("/admin/publishers",{
		templateUrl: "adminPublisherManage.html"
	}).when("/admin/loans",{
		templateUrl: "adminLoanManage.html"
	})
}])