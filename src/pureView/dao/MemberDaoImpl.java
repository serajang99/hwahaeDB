package pureView.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pureView.dto.MemberDto;
import pureView.exception.DuplicatedIdException;
import pureView.exception.RecordNotFoundException;
import pureView.util.JdbcUtil;


public class MemberDaoImpl implements MemberDao{
	// 메서드 오버라이딩
		// 예외는 부모보다 개수가 같거나 작앙함
		// 예외 타입은 부모보다 같거나 자식 타입
		@Override
		public void add(MemberDto m) throws SQLException, DuplicatedIdException {
			// DBMS 연결
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				// 등록 여부 검사
				if(findById(m.getId())!=null)
					throw new DuplicatedIdException(m.getId()+"는 이미 사용중입니다.");
				con = JdbcUtil.connect();
				// 3. SQL 작성
				String sql = "INSERT INTO MEMBER(id, name, passwd, skintype, age)";
				sql += "VALUES(?,?,?,?,?)";
	            pstmt = con.prepareStatement(sql);
				// 4. Statement 생성
				// 메서드 명은 prepare, 반환형은 prepared

				
				// 5. 필요한 데이터 설정 
				pstmt.setString(1,m.getId());
				pstmt.setString(2,m.getName());
				pstmt.setString(3,m.getPasswd());
				pstmt.setString(4,m.getSkintype());
				pstmt.setInt(5,m.getAge());
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
		public void update(MemberDto m) throws SQLException, RecordNotFoundException {
			
			// DBMS 연결
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				// 등록 여부 검사
				if(findById(m.getId())==null)
					throw new RecordNotFoundException(m.getId()+"는 없는 ID입니다.");
				
				con = JdbcUtil.connect();
				// 3. SQL 작성
				String sql = "UPDATE MEMBER set id = ?, name = ?, passwd = ?, skintype = ? age = ? ";
				sql += "WHERE id = ?";
	            pstmt = con.prepareStatement(sql);
				// 4. Statement 생성
				// 메서드 명은 prepare, 반환형은 prepared

				
				// 5. 필요한 데이터 설정 
				pstmt.setString(1,m.getId());
				pstmt.setString(2,m.getName());
				pstmt.setString(3,m.getPasswd());
				pstmt.setString(4,m.getSkintype());
				pstmt.setInt(5,m.getAge());
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
		public void delete(String id) throws SQLException, RecordNotFoundException {
			//DBMS연결
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        try {
				// 등록 여부 검사
				if(findById(id)==null)
					throw new RecordNotFoundException(id+"는 없는 ID입니다.");
	            con = JdbcUtil.connect();
	            // 3. SQL 작성
	            String sql = "DELETE MEMBER ";
	            sql += "WHERE id = ?";
	            // 4. Statement 생성
	            pstmt = con.prepareStatement(sql);
	            // 5. 데이터 설정
	            pstmt.setString(1, id);
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
	    		String sql = "SELECT COUNT(*) FROM MEMBER ";
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
		public List<MemberDto> read() throws SQLException {
			
			List<MemberDto> result = new ArrayList<MemberDto>();
			
			//DBMS연결, statement 생성
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        try {
	            con = JdbcUtil.connect();
	    		// 3. SQL 작성
	    		String sql = "SELECT * FROM MEMBER ORDER BY id";
	    		pstmt = con.prepareStatement(sql);
	    		// 5. 데이터 설정
	    		
	    		// 6. SQL 전송, 결과수신
	    		//   DML전송: executeUpdate() : int 
	    		//   SELECT전송: executeQuery() : ResultSet
	    		ResultSet rs = pstmt.executeQuery();

	    		while(rs.next()) {//조회결과가 있다
	    			String id  = rs.getString("id");
	    			String name = rs.getString("name");
	    			String passwd = rs.getString("passwd");
	    			String skin_type = rs.getString("skintype");
	    			int age = rs.getInt("age");
	    			MemberDto dto = new MemberDto(id, name, passwd, skin_type, age);
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
		public MemberDto findById(String id) throws SQLException {
			MemberDto dto = null;
			//DBMS연결, statement 생성
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        try {
	            con = JdbcUtil.connect();
	    		// 3. SQL 작성
	    		String sql = "SELECT * FROM MEMBERS where id = ?";
	    		pstmt = con.prepareStatement(sql);
	    		// 5. 데이터 설정
	    		pstmt.setString(1, id);
	    		// 6. SQL 전송, 결과수신
	    		//   DML전송: executeUpdate() : int 
	    		//   SELECT전송: executeQuery() : ResultSet
	    		ResultSet rs = pstmt.executeQuery();

	    		if(rs.next()) {//조회결과가 있다
	    			String name = rs.getString("name");
	    			String passwd = rs.getString("passwd");
	    			String skin_type = rs.getString("passwd");	    			
	    			int age = rs.getInt("age");
	    			dto = new MemberDto(id, name, passwd, skin_type, age);
	    		}
	        } catch (ClassNotFoundException e) {
	            throw new SQLException(e);
	        } finally {
	            JdbcUtil.close(pstmt, con);
	        }
	        return dto;
		}

}
