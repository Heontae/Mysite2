package com.javaex.dao;

import java.util.List;

import com.javaex.vo.BoardVo;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoardDao guest = new BoardDao();
		
		String keyword = "3" ;
		List<BoardVo> list = guest.BoardSelect(keyword);
		
		for(BoardVo vo : list) {
			System.out.println(vo.getTitle());
		}
		
		
	}

}
