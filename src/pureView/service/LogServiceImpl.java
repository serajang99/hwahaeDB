package pureView.service;

import java.sql.SQLException;
import java.util.List;

import pureView.exception.LogException;
import pureView.exception.RecordNotFoundException;
import pureView.dto.LoginDto;
import pureView.dao.LoginDao;
import pureView.dao.LoginDaoImpl;

public class LogServiceImpl implements LogService {

	private LoginDao loginDao = new LoginDaoImpl();

	@Override
	public void add(LoginDto m) throws LogException {
		try {
			loginDao.add(m);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override

	public void update_in(LoginDto m) throws LogException {
		// TODO Auto-generated method stub
		try {
			loginDao.update_in(m);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update_out(LoginDto m) throws LogException, RecordNotFoundException {
		// TODO Auto-generated method stub
		try {
			loginDao.update_out(m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(String id) throws LogException {

	}

	@Override
	public int count() throws LogException {
		return 0;
	}

	@Override
	public List<LoginDto> read() throws LogException {
		return null;
	}

	@Override
	public LoginDto findById(String id) throws LogException {

		// TODO Auto-generated method stub
		LoginDto dto = null;
		try {
			dto = loginDao.findById(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;

	}

}
