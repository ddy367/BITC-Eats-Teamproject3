package com.bitc.service;

import com.bitc.dto.AccountDto;

public interface AccountService {

	void userRegister(AccountDto account) throws Exception;

	// id의 uk
	int idOverlapChk(AccountDto account) throws Exception;

	String selectUserInfoYn(String userId) throws Exception;

	AccountDto userDetail(String userId) throws Exception;

	int loginChk(AccountDto account) throws Exception;

	void editUser(AccountDto account) throws Exception;

	void editPw(AccountDto account) throws Exception;

	


}
