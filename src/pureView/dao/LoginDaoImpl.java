package pureView.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

import oracle.sql.DATE;


import pureView.dto.LoginDto;
import pureView.util.JdbcUtil;
public class LoginDaoImpl implements LoginDao {

	// 메서드 오버라이딩
	// 예외는 부모보다 개수가 같거나 작앙함
	// 예외 타입은 부모보다 같거나 자식 타입
	@Override
	public void add(LoginDto m) throws SQLException, DuplicatedIdException {
		// DBMS 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 등록 여부 검사
			if(findById(m.getMember_id())!=null)
				throw new DuplicatedIdException(m.getMember_id()+"는 이미 사용중입니다.");
			con = JdbcUtil.connect();
			// 3. SQL 작성
//			String query = "SELECT sysdate as current_date, TO_CHAR(sysdate, 'HH24:MI:SS') as current_time FROM dual";

			String sql = "INSERT INTO LOG(member_id, login_date, logout_date)";
			sql += "VALUES(?,TO_CHAR(SYSDATE,'yyyy-mm-dd'),?) ";
            pstmt = con.prepareStatement(sql);
			// 4. Statement 생성
			// 메서드 명은 prepare, 반환형은 prepared

			
			// 5. 필요한 데이터 설정 
			pstmt.setString(1,m.getMember_id());
			pstmt.setString(2,"null");
			// 6. SQL 전송 및 결과 수신 
			// DML 전송 : executeUpdate() : int 타입 반환
			// SELECT 전송 : executeQuery() : ResultSet 타입 반환
			
			int cnt = pstmt.executeUpdate(); // 몇 행에 대해 수행했는지 반환
		} catch (ClassNotFoundException e) {
			// Exception을 감싸는 새로운 Exception을 만든다.
			throw new SQLException(e);
		} finally {
			// 7. 자원 반환
			JdbcUtil.close(pstmt,con);
		}
		
	}

	@Override
	public void update(LoginDto m) throws SQLException, RecordNotFoundException {
		
		// DBMS 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 등록 여부 검사
			if(findById(m.getMember_id())==null)
				throw new RecordNotFoundException(m.getMember_id()+"는 없는 ID입니다.");
			
			con = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "UPDATE LOG set logout_date = TO_CHAR(SYSDATE,'yyyy-mm-dd') ";
			sql += "WHERE member_id = ?";
            pstmt = con.prepareStatement(sql);
			// 4. Statement 생성
			// 메서드 명은 prepare, 반환형은 prepared
           
			
			// 5. 필요한 데이터 설정 
			pstmt.setString(1,m.getMember_id());
			// 6. SQL 전송 및 결과 수신 
			// DML 전송 : executeUpdate() : int 타입 반환
			// SELECT 전송 : executeQuery() : ResultSet 타입 반환
			
			int cnt = pstmt.executeUpdate(); // 몇 행에 대해 수행했는지 반환
		} catch (ClassNotFoundException e) {
			// Exception을 감싸는 새로운 Exception을 만든다.
			throw new SQLException(e);
		} finally {
			// 7. 자원 반환
			JdbcUtil.close(pstmt,con);
		}

	}

	@Override
	public void delete(String m_id) throws SQLException, RecordNotFoundException {
		//DBMS연결
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
			// 등록 여부 검사
			if(findById(m_id)==null)
				throw new RecordNotFoundException(m_id+"는 없는 ID입니다.");
            con = JdbcUtil.connect();
            // 3. SQL 작성
            String sql = "DELETE LOG ";
            sql += "WHERE member_id = ?";
            // 4. Statement 생성
            pstmt = con.prepareStatement(sql);
            // 5. 데이터 설정
            pstmt.setString(1, m_id);
            // 6. SQL 전송, 결과수신
            int count = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }

	}

	@Override
	public int count() throws SQLException {
		int cnt=0;
		//DBMS연결, statement 생성
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.connect();
    		// 3. SQL 작성
    		String sql = "SELECT COUNT(*) FROM LOG ";
    		pstmt = con.prepareStatement(sql);
    		// 5. 데이터 설정
    		
    		// 6. SQL 전송, 결과수신
    		//   DML전송: executeUpdate() : int 
    		//   SELECT전송: executeQuery() : ResultSet
    		ResultSet rs = pstmt.executeQuery();
    		rs.next(); //조회결과가 있다
    		cnt=rs.getInt(1);
			
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }
        return cnt;
	}
	

	@Override
	public List<LoginDto> read() throws SQLException {
		
		List<LoginDto> result = new ArrayList<LoginDto>();
		
		//DBMS연결, statement 생성
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.connect();
    		// 3. SQL 작성
    		String sql = "SELECT * FROM LOG ORDER BY member_id";
    		pstmt = con.prepareStatement(sql);
    		// 5. 데이터 설정
    		
    		// 6. SQL 전송, 결과수신
    		//   DML전송: executeUpdate() : int 
    		//   SELECT전송: executeQuery() : ResultSet
    		ResultSet rs = pstmt.executeQuery();

    		while(rs.next()) {//조회결과가 있다
    			int login_num = rs.getInt("login_num");
    			Date login_date = rs.getDate("login_date");
    			Date logout_date = rs.getDate("logout_date");
    			String member_id  = rs.getString("member_id");
    			LoginDto dto = new LoginDto(login_num, login_date, logout_date, member_id);
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
	public LoginDto findById(String m_id) throws SQLException {
		LoginDto dto = null;
		//DBMS연결, statement 생성
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.connect();
    		// 3. SQL 작성
    		String sql = "SELECT * FROM LOGIN where member_id = ?";
    		pstmt = con.prepareStatement(sql);
    		// 5. 데이터 설정
    		pstmt.setString(1, m_id);
    		// 6. SQL 전송, 결과수신
    		//   DML전송: executeUpdate() : int 
    		//   SELECT전송: executeQuery() : ResultSet
    		ResultSet rs = pstmt.executeQuery();

    		if(rs.next()) {//조회결과가 있다
    			int login_num = rs.getInt("login_num");
    			Date login_date = rs.getDate("login_date");
    			Date logout_date = rs.getDate("logout_date");
    			dto = new LoginDto(login_num, login_date, logout_date, m_id);
    		}
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            JdbcUtil.close(pstmt, con);
        }
        return dto;
	}


}
