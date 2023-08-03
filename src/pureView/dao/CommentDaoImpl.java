package pureView.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pureView.util.JdbcUtil;
import pureView.dto.CommentDto;
import pureView.exception.RecordNotFoundException;

public class CommentDaoImpl implements CommentDao {

	@Override
	public void add(CommentDto dto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtil.connect();

			String sql = "INSERT INTO CMNT (num, boardNum, memberId, content, commentTime)";
			sql += "VALUES(CMNT_SEQ.nextval, ?, ?, ?, SYSDATE)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getBoardNum());
			pstmt.setString(2, dto.getMemberId());
			pstmt.setString(3, dto.getContent());

			int count = pstmt.executeUpdate();
			System.out.println(count + "행 입력 완료");

		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, conn);
		}
	}

	@Override
	public void update(CommentDto dto) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			CommentDto findDto = findById(dto.getNum());
			if (findDto == null)
				throw new RecordNotFoundException(dto.getNum() + "는 없습니다");

			conn = JdbcUtil.connect();
			
			String sql = "UPDATE CMNT set content = ?, commentTime = SYSDATE ";
			sql += "WHERE num = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getContent());
			pstmt.setInt(2, dto.getNum());

			int count = pstmt.executeUpdate();
			System.out.println(count + "행 업데이트 완료");

		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, conn);
		}
	}

	@Override
	public void delete(int no) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtil.connect();

			String sql = "DELETE FROM CMNT WHERE num = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			int count = pstmt.executeUpdate();
			System.out.println(count + "행 삭제 완료");

		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, conn);
		}
	}

	@Override
	public int count() throws SQLException {
		return 0;
	}

	@Override
	public List<CommentDto> list(int no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<CommentDto> result = new ArrayList<CommentDto>(); // ArrayList의 상위 타입인 List로 반환
		try {
			conn = JdbcUtil.connect();
			String sql = "SELECT * FROM CMNT order by num DESC "
					+ "WHERE boardNum = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("num");
				String memberId = rs.getString("memberId");
				String content = rs.getString("content");
				Date commentTime = rs.getDate("commentTime");
				CommentDto dto = new CommentDto(num, no, memberId, content, commentTime);
				result.add(dto);
			}

		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, conn);
		}
		return result;
	}

	@Override
	public CommentDto findById(int no) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		CommentDto dto = null;
		try {
			conn = JdbcUtil.connect();
			
			String sql = "SELECT * FROM CMNT WHERE num = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 조회 결과가 있다
				int boardNum = rs.getInt("boardNum");
				String memberId = rs.getString("memberId");
				String content = rs.getString("content");
				Date commentTime = rs.getDate("commentTime");
				dto = new CommentDto(no, boardNum, memberId, content, commentTime);
			}

		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, conn);
		}
		return dto;
	}

}
