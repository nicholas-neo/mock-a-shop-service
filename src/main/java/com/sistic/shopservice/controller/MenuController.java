package com.sistic.shopservice.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistic.shopservice.model.Product;
import com.sistic.shopservice.service.ProductService;

@RestController
@RequestMapping("api/menu")
public class MenuController {

	@Autowired
	private ProductService productService;
	
	@GetMapping()
	public List<String> getMenu() {
		List<Product> products = productService.listAllProduct();
		
		List<String> menu = products.stream()
							.filter(distinctByKey(p -> p.getItemType()))
							.map(Product::getItemType)
							.collect(Collectors.toList());
							
		return menu;
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) 
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
