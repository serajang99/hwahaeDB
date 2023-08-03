package pureView.service;

import java.sql.SQLException;
import java.util.List;

import pureView.exception.DuplicatedIdException;
import pureView.exception.LogException;
import pureView.exception.RecordNotFoundException;
import pureView.dao.MemberDao;
import pureView.dao.MemberDaoImpl;
import pureView.dto.LoginDto;
import pureView.dao.LoginDao;
import pureView.dao.LoginDaoImpl;


public class LogServiceImpl implements LogService{

	private LoginDao loginDao = new LoginDaoImpl();

	@Override
	public void add(LoginDto m) throws LogException {
		// TODO Auto-generated method stub
		try {
			loginDao.add(m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public void update(LoginDto m) throws LogException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) throws LogException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count() throws LogException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LoginDto> read() throws LogException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginDto findById(String id) throws LogException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
