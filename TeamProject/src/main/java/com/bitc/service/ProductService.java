package com.bitc.service;

import java.util.List;

import com.bitc.dto.ProductDto;
import com.github.pagehelper.Page;

public interface ProductService {

	List<ProductDto> selectMenuList() throws Exception;

	List<ProductDto> selectBasketList() throws Exception;

	Page<ProductDto> selectMenuPageList(int pageNum, String shopName) throws Exception;

	Page<ProductDto> selectBasketPageList(int pageNum) throws Exception;

	int insertMenu(ProductDto shop) throws Exception;

	void deleteMenu(int no) throws Exception;

	List<ProductDto> selectPriceList()  throws Exception;

	int clearBasket(int[] no) throws Exception;
}
