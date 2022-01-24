package com.bitc.dto;

import lombok.Data;

@Data
public class OrderDto {
    
	private int no;
	private String shopName;
	private String shopMenu;
	private String shopMprice;
	private String deletedYn;
	private int shopPk;
	private String completedYn;
	private String shopMdetail;
	private String menuImage;
}
