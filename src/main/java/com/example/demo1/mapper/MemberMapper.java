package com.example.demo1.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo1.vo.MemberDTO;

@Mapper
public interface MemberMapper {
	public MemberDTO read(String userid);
	
}
