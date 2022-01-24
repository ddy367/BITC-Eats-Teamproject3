package com.bitc.service;

import java.util.List;

import com.bitc.dto.KategorieDto;
import com.bitc.dto.OrderDto;
import com.bitc.dto.ShopDto;
import com.github.pagehelper.Page;

public interface ShopService {
//	메인 화면 카테고리
	List<KategorieDto> selectMain() throws Exception;
	
//	가게 목록
	List<ShopDto> selectShopList(String shopKate) throws Exception;
	
//	검색창 - 음식명
	List<OrderDto> search2(String shopMenu) throws Exception;


	

	

}
