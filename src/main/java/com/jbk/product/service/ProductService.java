package com.jbk.product.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jbk.product.entity.Product;

public interface ProductService {

	public boolean saveProduct(Product product);

	public List<Product> getAllProduct();

	public Product getProductById(String productId);

	public boolean deleteProduct(String productId);

	public boolean updateProduct(Product product);

	public List<Product> sortProducts(String sortBy);

	public Product getMaxPriceProducts();

	public double sumOfProductPrice();

	public int getTotalCountOfProducts();

	public Map<String, String> uploadSheet(CommonsMultipartFile file, HttpSession httpSession); // read data from excel
																								// and write into DB

	public String exportToExcel();

}
