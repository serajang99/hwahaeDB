package pureView.service;

import java.sql.SQLException;
import java.util.List;

import pureView.dao.BoardException;
import pureView.dao.DuplicatedIdException;
import pureView.dao.RecordNotFoundException;
import pureView.dao.BoardDao;
import pureView.dao.BoardDaoImpl;
import pureView.dto.BoardDto;

public class BoardServiceImpl implements BoardService {
	
	private BoardDao boardDao = new BoardDaoImpl();
	
	@Override
	public boolean add(BoardDto dto) throws BoardException {
		try {
			boardDao.add(dto);
		} catch (SQLException e) {
			throw new BoardException(e.getMessage());
		} catch (DuplicatedIdException e) {
		}
		return true;
	}

	@Override
	public List<BoardDto> list() throws BoardException {
		List<BoardDto> list = null;
		try {
			list = boardDao.list();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		}
		return list;
	}

	@Override
	public BoardDto read(int no) throws BoardException, RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(BoardDto dto) throws BoardException, RecordNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int no) throws BoardException, RecordNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

}
