package pureView.dao;

import java.sql.SQLException;
import java.util.List;

import pureView.dto.LoginDto;
import pureView.exception.DuplicatedIdException;
import pureView.exception.LogException;
import pureView.exception.RecordNotFoundException;



public interface LoginDao{
	  //등록
	  public void add(LoginDto m) throws  SQLException;
	  //수정
	  public void update_in(LoginDto m) throws  SQLException,RecordNotFoundException;
	  public void update_out(LoginDto m) throws  SQLException,RecordNotFoundException;
	  //삭제
	  public void delete(String id) throws  SQLException,RecordNotFoundException;
	  //갯수
	  public int count() throws SQLException;
	  //목록
	  public List<LoginDto> read() throws SQLException;
	  //검색
	  public LoginDto findById(String id) throws SQLException;
	}