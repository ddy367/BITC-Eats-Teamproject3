package com.bitc.dto;

import lombok.Data;

@Data
public class ShopDto {

	private int shopPk;
	private String shopName;
	private String shopKate;
	private String shopDetail;
	private String shopMenu;
	private int shopMprice;
	private String shopMdetail;
	private String deletedYn;
	private String shopImage;
}
