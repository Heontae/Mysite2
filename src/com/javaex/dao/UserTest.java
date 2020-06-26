package com.javaex.dao;

import com.javaex.vo.UserVo;

public class UserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		UserVo vo = new UserVo("id-test","1234","홍길동","남");
		dao.Insert(vo);
		*/
		UserDao dao = new UserDao();
		UserVo vo1 = new UserVo("4242","길동","famel","홍길동");
		
		
		dao.Update(vo1);
	}

}
