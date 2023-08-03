
package pureView.service;

import java.sql.SQLException;
import java.util.List;

import pureView.exception.MemberException;
import pureView.exception.RecordNotFoundException;
import pureView.dto.MemberDto;

public interface MemberService {
	
	  //등록
	  public void add(MemberDto m) throws MemberException;
	  //수정
	  public void update(MemberDto m) throws MemberException;
	  //삭제
	  public void delete(String id) throws MemberException;
	  //검색
	  public MemberDto findById(String id) throws MemberException;
	  
	  
}
