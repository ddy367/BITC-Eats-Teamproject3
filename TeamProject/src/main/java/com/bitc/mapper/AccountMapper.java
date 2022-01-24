package com.bitc.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.bitc.dto.AccountDto;

@Mapper
public interface AccountMapper {

	int idAuthorityChk(AccountDto account) throws Exception;
	
	int idUniqChk(AccountDto account) throws Exception;

	void userRegister(AccountDto account) throws Exception;

	String selectUserInfoYn(String userId) throws Exception;

	AccountDto userDetail(String userId) throws Exception;

	void editUser(AccountDto account) throws Exception;

	void editPw(AccountDto account) throws Exception;

	

}
