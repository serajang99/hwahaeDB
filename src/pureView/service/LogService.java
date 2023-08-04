package pureView.service;

import java.sql.SQLException;
import java.util.List;

import pureView.exception.DuplicatedIdException;
import pureView.exception.LogException;
import pureView.exception.RecordNotFoundException;
import pureView.dto.LoginDto;

public interface LogService {
	  //등록
	  public void add(LoginDto m) throws LogException;
	  //수정
	  public void update_in(LoginDto m) throws LogException,RecordNotFoundException;
	  public void update_out(LoginDto m) throws LogException,RecordNotFoundException;
	  //삭제
	  public void delete(String id) throws LogException;
	  //갯수
	  public int count() throws LogException;
	  //목록
	  public List<LoginDto> read() throws LogException;
	  //검색
	  public LoginDto findById(String id) throws LogException,RecordNotFoundException;;

}
