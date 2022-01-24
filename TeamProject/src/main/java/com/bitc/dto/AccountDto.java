package com.bitc.dto;

import lombok.Data;

@Data
public class AccountDto {

	private int idx;
	private String userId;
	private String userPw;
	private String userNick;
	private String userPhone;
	private String userAddr;
	

	private String userAuthority;
	
}
