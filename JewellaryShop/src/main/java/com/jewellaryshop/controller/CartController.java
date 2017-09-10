package com.jewellaryshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.backend.spring.configuration.AppConfig;
import com.backend.spring.model.Product;
import com.backend.spring.model.User;
import com.backend.spring.service.CartService;
import com.backend.spring.service.ProductService;

@Controller
public class CartController {


	AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	
	private CartService cartservice=(CartService) context.getBean("cartService");
	private ProductService pdtservice =  (ProductService) context.getBean("productService");
	
	@RequestMapping(value="/addToCart",method = RequestMethod.GET)
    public ModelAndView cart(HttpServletRequest request)
	{
		int productId = Integer.parseInt(request.getParameter("id"));
        Product product = pdtservice.findById(productId);
        ModelAndView model = new ModelAndView("cartPage");
        model.addObject("product", product);
        return model;
    }
	
	@RequestMapping(value="/shippingAddress",method = RequestMethod.GET)
	public ModelAndView pay(HttpServletRequest request){
		ModelAndView model=new ModelAndView("shippingAddress");
		model.addObject("user", new User());
		return model;
	}
}
