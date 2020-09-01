package com.sistic.shopservice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistic.shopservice.model.Product;
import com.sistic.shopservice.service.ProductService;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@MockBean
	private ProductService productService;
	
	private List<Product> productList;
	
	@BeforeEach
	void setUp() {
		this.productList = new ArrayList<>();
		this.productList.add(new Product("SONY-001", "Sony", "Xperia X1", new BigDecimal(899.99), "", "Best sony phone", 0));
		this.productList.add(new Product("SONY-002", "Sony", "Xperia XZ", new BigDecimal(699.99), "", "Next sony phone", 0));
	}
	
	@Test
	void shouldFetchAllProducts() throws Exception {
		given(productService.listAllProduct()).willReturn(productList);
		
		this.mockMvc.perform(get("/api/products"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.size()", is(productList.size())));
	}
	
	@Test
    void shouldCreateNewProduct() throws Exception {
        given(productService.addProduct(any(Product.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Product product = new Product("SONY-003", "Sony", "Xperia XZP", new BigDecimal(1099.99), "", "Last Gen sony phone", 0);

        this.mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.itemNo", is(product.getItemNo())))
                .andExpect(jsonPath("$.itemName", is(product.getItemName())))
                .andExpect(jsonPath("$.itemType", is(product.getItemType())))
                .andExpect(jsonPath("$.itemUnitPrice", is(product.getItemUnitPrice())))
                .andExpect(jsonPath("$.description", is(product.getDescription())));
    }
}
