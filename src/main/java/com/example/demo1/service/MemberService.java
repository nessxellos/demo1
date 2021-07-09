package com.example.demo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo1.domain.CustomUser;
import com.example.demo1.mapper.MemberMapper;
import com.example.demo1.vo.MemberDTO;

@Service
public class MemberService implements UserDetailsService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername test");
		MemberDTO member = memberMapper.read(username);
		
		// 리턴값은 UserDetails 형태여야 하므로 
		// CustomUser클래스를 통해 member를 UserDetails로 형변환함
		return member==null ? null : new CustomUser(member);
	}
	
	
	
	
}
