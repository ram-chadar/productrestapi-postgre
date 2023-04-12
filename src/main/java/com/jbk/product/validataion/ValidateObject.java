package com.jbk.product.validataion;

import com.jbk.product.entity.Product;

public class ValidateObject {


	public static boolean validateProduct(Product product) {
		boolean isValid = true;
		if (product.getProductName() == null || product.getProductName().equals("")) {
			isValid = false;
		}
		if (product.getProductQty() <= 0) {
			isValid = false;
		}
		if (product.getProductPrice() <= 0) {
			isValid = false;
		}
		if (product.getProductType() == null || product.getProductType().equals("")) {
			isValid = false;
		}

		return isValid;

	}


}
