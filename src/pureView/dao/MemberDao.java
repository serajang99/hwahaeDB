
package pureView.dao;

import java.sql.SQLException;
import java.util.List;


import pureView.dto.MemberDto;
import pureView.exception.DuplicatedIdException;
import pureView.exception.RecordNotFoundException;

public interface MemberDao{
	  //등록
	  public void add(MemberDto m) throws SQLException, DuplicatedIdException;
	  //수정
	  public void update(MemberDto m) throws SQLException, RecordNotFoundException;
	  //삭제
	  public void delete(String id) throws SQLException, RecordNotFoundException;
	  //아이디 조회
	  public MemberDto findById(String id) throws SQLException,RecordNotFoundException;
	  
}

