package pureView.service;

import java.sql.SQLException;
import java.util.List;

import pureView.exception.*;
import pureView.dao.MemberDao;
import pureView.dao.MemberDaoImpl;
import pureView.dto.MemberDto;

public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao = new MemberDaoImpl();
	
	@Override
	public void add(MemberDto m) throws MemberException {
		// TODO Auto-generated method stub
		try {
			memberDao.add(m);
		} catch (SQLException e) {
			throw new MemberException(e.getMessage());
		} catch (DuplicatedIdException e) {
		}
	}

	@Override
	public void update(MemberDto m) throws MemberException {
		// TODO Auto-generated method stub
		try {
			memberDao.update(m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new MemberException(e.getMessage());
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			throw new MemberException(e.getMessage());
		}
	}

	@Override
	public void delete(String id) throws MemberException {
		// TODO Auto-generated method stub
		try {
			memberDao.delete(id);
		} catch (SQLException e) {
			throw new MemberException(e.getMessage());
		} catch (RecordNotFoundException e) {
			throw new MemberException(e.getMessage());
		}
	}



	@Override
	public MemberDto findById(String id) throws MemberException {
		// TODO Auto-generated method stub
		MemberDto dto = null;
		try {
			dto = memberDao.findById(id);
		} catch (SQLException e) {
			throw new MemberException(e.getMessage());
		} catch (RecordNotFoundException e) {
			throw new MemberException(e.getMessage());
		}
		return dto;
	}
	
}
