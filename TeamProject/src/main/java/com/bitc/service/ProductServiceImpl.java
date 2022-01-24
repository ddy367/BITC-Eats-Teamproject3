package com.bitc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.dto.ProductDto;
import com.bitc.mapper.ProductMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper projectMapper;

	@Override
	public List<ProductDto> selectMenuList() throws Exception {
		return projectMapper.selectMenuList();
	}

	@Override
	public List<ProductDto> selectBasketList() throws Exception {
		return projectMapper.selectBasketList();
	}

	@Override
	public Page<ProductDto> selectMenuPageList(int pageNum, String shopName) throws Exception {
		PageHelper.startPage(pageNum, 3);
		return projectMapper.selectMenuPageList(shopName);
	}

	@Override
	public Page<ProductDto> selectBasketPageList(int pageNum) throws Exception {
		PageHelper.startPage(pageNum, 3);
		return projectMapper.selectBasketPageList();
	}

	@Override
	public int insertMenu(ProductDto shop) throws Exception {
		int result = projectMapper.insertMenu(shop);
		
		return result;
	}

	@Override
	public void deleteMenu(int no) throws Exception {
		projectMapper.deleteMenu(no);
	}

	@Override
	public List<ProductDto> selectPriceList() throws Exception {
		return projectMapper.selectPriceList();
	}

	@Override
	public int clearBasket(int[] no) throws Exception {
		int count = 0;
		
		for (int i = 0; i < no.length; i++) {
			var result = projectMapper.clearBasket(no[i]);
			count += result;
		}
		
		return count;
		
	}
}
