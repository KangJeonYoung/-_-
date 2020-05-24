package com.kjy.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.ibatis.session.SqlSession;

import com.kjy.domain.BoardVO;
import com.kjy.domain.MemberVO;
import com.kjy.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;


@Log4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Override
	public void register(MemberVO member) {
		log.info("register....."+member);
		mapper.insertSelectKey(member);
	}

	@Override
	public String LoginCheck(MemberVO member) {
		log.info("MemberServiceImpl 부분" + member);
		String result = mapper.loginCheck(member);
		System.out.println(result);
		
		return (result == null) ? "0" : "1";
	}

	@Override
	public int idChk(MemberVO vo) throws Exception {
		int result = mapper.idChk(vo);
		return result;
	}


	@Override
	public String getModel(String id) {
		String result = mapper.getModel(id);
		
		return result;
	}
	
	@Override
	public boolean authInsert(MemberVO member) {
		log.info("authInsert....." + member);
		return mapper.authInsert(member) == 1;
	}

	@Override
	public boolean remove(MemberVO member) {
		log.info("remove....."+ member);
		return mapper.delete(member) == 1;
	}

	@Override
	public List<MemberVO> getMember(String id) {
		
		return mapper.getMember(id);
	}
	
	@Override
	public boolean modify(MemberVO member) {
		log.info("modify....." + member);
		return mapper.update(member) == 1;
	}
	
//	@Override
//	public BoardVO get(Long bno) {
//		log.info("get....." + bno);
//		return mapper.read(bno);
//	}
//
//	@Override
//	public boolean modify(BoardVO board) {
//		log.info("modify....." + board);
//		return mapper.update(board) == 1;
//	}
//
//	@Override
//	public boolean remove(Long bno) {
//		log.info("remove....."+ bno);
//		return mapper.delete(bno) == 1;
//	}
//
//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList.....");
//		return mapper.getList();
//	}


}
