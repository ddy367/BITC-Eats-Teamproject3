package com.bitc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bitc.dto.ProductDto;
import com.github.pagehelper.Page;

@Mapper
public interface ProductMapper {

	public List<ProductDto> selectMenuList() throws Exception;

	public List<ProductDto> selectBasketList() throws Exception;
	
	Page<ProductDto> selectMenuPageList(@Param("shopName") String shopName) throws Exception;

	Page<ProductDto> selectBasketPageList() throws Exception;

	public int insertMenu(ProductDto shop) throws Exception;

	public void deleteMenu(int no) throws Exception;

	public List<ProductDto> selectPriceList() throws Exception;

	public int clearBasket(int no) throws Exception;
}
