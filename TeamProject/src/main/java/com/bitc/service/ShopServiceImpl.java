package com.bitc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.dto.KategorieDto;
import com.bitc.dto.OrderDto;
import com.bitc.dto.ShopDto;
import com.bitc.mapper.ShopMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private ShopMapper shopmapper;
	
//	메인 화면 카테고리
	@Override
	public List<KategorieDto> selectMain() throws Exception {
		return shopmapper.selectMain();
	}

//	가게 목록
	@Override
	public List<ShopDto> selectShopList(String shopKate) throws Exception {
		return shopmapper.selectShopList(shopKate);
	}
	
//	검색창 - 음식명
	@Override
	public List<OrderDto> search2(String shopMenu) throws Exception {
		return shopmapper.search2(shopMenu);
	}

	

	

	
	
}
