package com.javaex.dao;

import java.util.List;

import com.javaex.vo.BoardVo;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoardDao guest = new BoardDao();
		
		List<BoardVo> list = guest.BoardList();
		
		System.out.println(list.toString());
		
		
		
	}

}
