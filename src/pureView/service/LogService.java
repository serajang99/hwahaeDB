package pureView.service;

import java.sql.SQLException;
import java.util.List;

import pureView.dao.DuplicatedIdException;
import pureView.dao.RecordNotFoundException;
import pureView.dto.LoginDto;

public interface LogService {
	//등록
	  public void add(LoginDto m) throws SQLException, DuplicatedIdException;
	  //수정
	  public void update(LoginDto m) throws SQLException, RecordNotFoundException;
	  //삭제
	  public void delete(String id) throws SQLException, RecordNotFoundException;
	  //갯수
	  public int count() throws SQLException;
	  //목록
	  public List<LoginDto> read() throws SQLException;
	  //검색
	  public LoginDto findById(String id) throws SQLException;
}