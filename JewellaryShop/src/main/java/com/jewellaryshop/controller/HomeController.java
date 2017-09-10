package com.jewellaryshop.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.backend.spring.configuration.AppConfig;
import com.backend.spring.model.Product;
import com.backend.spring.service.ProductService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
public class HomeController
{
           
	public HomeController(){
		System.out.println("HomeController()");   	
	}
	
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView listProduct(ModelAndView model) throws IOException
	{ 
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ProductService service = (ProductService) context.getBean("productService");
	    List<Product> li= service.findAllProducts();
		Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .serializeNulls()
                .create();
        String jsonProducts=gson.toJson(li);
        model.addObject("lists",jsonProducts);
        model.setViewName("adminPage");
        return model;
    }
	
	/*@RequestMapping("/users")
    public ModelAndView listUser(ModelAndView model) throws IOException
	{
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService service = (UserService) context.getBean("userService");
	    List<User> li= service.findAllUsers();
		Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .serializeNulls()
                .create();
        String jsonUsers=gson.toJson(li);
        model.addObject("lists",jsonUsers);
        model.setViewName("users");
        return model;
    }*/

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;

	}
	
	@RequestMapping("/aboutus")
    public String about()
	{
        return "aboutus";
    }
	
	@RequestMapping("/contactus")
    public String contact()
	{
        return "contactus";
    }
	
	/*@RequestMapping("/signup")
    public String signup()
	{
        return "signup";
    }*/
	
	@RequestMapping("/products")
    public ModelAndView viewAllProducts(ModelAndView model)throws IOException
	{
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ProductService service = (ProductService) context.getBean("productService");
	    List<Product> li= service.findAllProducts();
		Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .serializeNulls()
                .create();
        String jsonProducts=gson.toJson(li);
        model.addObject("lists",jsonProducts);
        model.setViewName("allProducts");
        return model;
    }
	
//	 @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
//	    public String accessDeniedPage() {
//	       return "accessDenied";
//	    }

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("loginPage");

		return model;

	}	
}
