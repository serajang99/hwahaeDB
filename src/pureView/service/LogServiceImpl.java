package pureView.service;

import java.sql.SQLException;
import java.util.List;

import pureView.dao.DuplicatedIdException;
import pureView.dao.RecordNotFoundException;
import pureView.dto.LoginDto;

public class LogServiceImpl implements LogService{

	@Override
	public void add(LoginDto m) throws SQLException, DuplicatedIdException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(LoginDto m) throws SQLException, RecordNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) throws SQLException, RecordNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LoginDto> read() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginDto findById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
