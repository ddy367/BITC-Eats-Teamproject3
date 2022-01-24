package com.bitc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.dto.AccountDto;
import com.bitc.mapper.AccountMapper;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;
	
	// 회원가입 시 아이디 중복 체크(아이디가 UK를 만족 and 
	@Override
	public int idOverlapChk(AccountDto account) throws Exception {
		int uniq = accountMapper.idUniqChk(account);
		return uniq;
	}
	
	// 회원가입
	@Override
	public void userRegister(AccountDto account) throws Exception {
		accountMapper.userRegister(account);
	}
	
//	로그인시 아이디와 권한이 일치
	@Override
	public int loginChk(AccountDto account) throws Exception {
		int idOverlapChk = accountMapper.idAuthorityChk(account);
		return idOverlapChk;
	}

//	아이디와 암호화된 비밀번호가 매칭
	@Override
	public String selectUserInfoYn(String userId) throws Exception {
		return accountMapper.selectUserInfoYn(userId);
	}

//	유저의 상세정보
	@Override
	public AccountDto userDetail(String userId) throws Exception {
		return accountMapper.userDetail(userId);
	}

//	유저의 정보수정
	@Override
	public void editUser(AccountDto account) throws Exception {
		accountMapper.editUser(account);
	}

//	유저의 비밀번호 수정
	@Override
	public void editPw(AccountDto account) throws Exception {
		accountMapper.editPw(account);
		
	}
}
