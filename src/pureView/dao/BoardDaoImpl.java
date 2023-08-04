package pureView.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pureView.dto.BoardDto;
import pureView.exception.DuplicatedIdException;
import pureView.exception.RecordNotFoundException;
import pureView.util.JdbcUtil;

public class BoardDaoImpl implements BoardDao {

	@Override
	public void add(BoardDto dto) throws SQLException, DuplicatedIdException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			String sql = " INSERT INTO BOARD(boardNum, boardTitle, boardContent, "
					+ "writeTime, starRating, memberId, cosNum) ";
			sql += " VALUES(BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, ?, ?, ?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBoardTitle());
			pstmt.setString(2, dto.getBoardContent());
			pstmt.setInt(3, dto.getStarRating());
			pstmt.setString(4, dto.getMemberId());
			pstmt.setInt(5, dto.getCosNum());
			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
	}

	@Override
	public void update(BoardDto dto) throws SQLException, RecordNotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			if (findById(dto.getBoardNum()) == null)
				throw new RecordNotFoundException(dto.getBoardNum() + "는 없습니다");

			con = JdbcUtil.connect();

			String sql = " UPDATE BOARD set boardTitle = ?, " + "boardContent = ? ";
			sql += "WHERE boardNum = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBoardTitle());
			pstmt.setString(2, dto.getBoardContent());
			pstmt.setInt(3, dto.getBoardNum());

			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
	}

	@Override
	public void delete(int no) throws SQLException, RecordNotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();

			String sql = "DELETE BOARD ";
			sql += "WHERE boardNum = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);

			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
	}

	@Override
	public List<BoardDto> list() throws SQLException {

		List<BoardDto> result = new ArrayList<BoardDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();

			String sql = "SELECT * FROM BOARD order by boardNum DESC ";
			pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int boardNum = rs.getInt("boardNum");
				String boardTitle = rs.getString("boardTitle");
				String boardContent = rs.getString("boardContent");
				Date writeTime = rs.getDate("writeTime");
				int starRating = rs.getInt("starRating");
				String memberId = rs.getString("memberId");
				int cosNum = rs.getInt("cosNum");
				BoardDto dto = new BoardDto(boardNum, boardTitle, boardContent, writeTime, starRating, memberId,
						cosNum);
				result.add(dto);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return result;
	}

	@Override
	public BoardDto findById(int no) throws SQLException {
		BoardDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();

			String sql = "SELECT * FROM BOARD WHERE boardNum = ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 조회 결과가 있다
				int boardNum = rs.getInt("boardNum");
				String boardTitle = rs.getString("boardTitle");
				String boardContent = rs.getString("boardContent");
				Date writeTime = rs.getDate("writeTime");
				int starRating = rs.getInt("starRating");
				String memberId = rs.getString("memberId");
				int cosNum = rs.getInt("cosNum");
				dto = new BoardDto(boardNum, boardTitle, boardContent, writeTime, starRating, memberId, cosNum);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return dto;
	}

}
