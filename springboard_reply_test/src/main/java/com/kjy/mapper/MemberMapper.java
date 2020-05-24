package com.kjy.mapper;

import java.util.List;

//import org.apache.ibatis.annotations.Select;

import com.kjy.domain.MemberVO;

public interface MemberMapper {

////		@Select("select * from tbl_board where bno > 0")
//		public List<BoardVO> getList();
//		
//		public void insert(BoardVO board);
	
		public void insertSelectKey(MemberVO member);
		
		public String loginCheck(MemberVO member);

		public int checkID(String userid);

		public int idChk(MemberVO vo) throws Exception;

		public String getModel(String id);

		public int authInsert(MemberVO member);

		public int delete(MemberVO member);

		public List<MemberVO> getMember(String id);

		public int update(MemberVO member);
		
//		public BoardVO read(Long bno);
//		
//		public int delete(Long bno);
//		//error가 날 시에는 int bno
//		
//		public int update(BoardVO board);
}
