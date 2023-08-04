package pureView.service;

import java.sql.SQLException;

import pureView.exception.*;
import pureView.dao.MemberDao;
import pureView.dao.MemberDaoImpl;
import pureView.dto.MemberDto;

public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao = new MemberDaoImpl();

	@Override
	public void add(MemberDto m) throws MemberException {
		try {
			memberDao.add(m);
		} catch (SQLException e) {
			throw new MemberException(e.getMessage());
		} catch (DuplicatedIdException e) {
		}
	}

	@Override
	public void update(MemberDto m) throws MemberException {
		try {
			memberDao.update(m);
		} catch (SQLException e) {
			throw new MemberException(e.getMessage());
		} catch (RecordNotFoundException e) {
			throw new MemberException(e.getMessage());
		}
	}

	@Override
	public void delete(String id) throws MemberException {
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


	@Override
	public MemberDto findByPw(String pw) throws MemberException {
		// TODO Auto-generated method stub
		MemberDto dto = null;
		try {
			dto = memberDao.findByPw(pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
	
}
