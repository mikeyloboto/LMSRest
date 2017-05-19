package com.gcit.library.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gcit.library.entity.Book;
import com.gcit.library.service.AdminService;

//@Controller
public class AdminController {
//	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//	
//	@Autowired
//	AdminService adminService;
//	
//	@RequestMapping(value = "/adminAuthorManage", method = RequestMethod.GET)
//	public String manageAuthors(Locale locale, Model model) {
//		return "adminAuthorManage";
//	}
//	
//	@RequestMapping(value = "/adminBookManage", method = RequestMethod.GET)
//	public String manageBooks(Locale locale, Model model) {
////		try {
////			//Integer bookCount = adminService.getBookCount();
////			//List<Book> books = adminService.getAllBooks(null);
////			//model.addAttribute("bookCount", bookCount);
////			//model.addAttribute("books", books);
////		} catch (SQLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		return "adminBookManage";
//	}
//	
//	@RequestMapping(value = "/adminBorrowerManage", method = RequestMethod.GET)
//	public String manageBorrowers(Locale locale, Model model) {
//		return "adminBorrowerManage";
//	}
//	
//	@RequestMapping(value = "/adminBranchManage", method = RequestMethod.GET)
//	public String manageBranches(Locale locale, Model model) {
//		return "adminBranchManage";
//	}
//	
//	@RequestMapping(value = "/adminGenreManage", method = RequestMethod.GET)
//	public String manageGenres(Locale locale, Model model) {
//		return "adminGenreManage";
//	}
//	
//	@RequestMapping(value = "/adminLoanManage", method = RequestMethod.GET)
//	public String manageLoans(Locale locale, Model model) {
//		return "adminLoanManage";
//	}
//	
//	@RequestMapping(value = "/adminPublisherManage", method = RequestMethod.GET)
//	public String managePublisher(Locale locale, Model model) {
//		return "adminPublisherManage";
//	}
}