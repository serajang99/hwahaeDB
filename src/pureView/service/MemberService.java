
package pureView.service;

import java.util.List;

import pureView.dao.MemberException;
import pureView.dto.MemberDto;

public interface MemberService {
	
	  //등록
	  public void add(MemberDto m) throws MemberException;
	  //수정
	  public void update(MemberDto m) throws MemberException;
	  //삭제
	  public void delete(String id) throws MemberException;
	  //갯수
	  public int count() throws MemberException;
	  //목록
	  public List<MemberDto> read() throws MemberException;
	  //검색
	  public MemberDto findById(String id) throws MemberException;
}
