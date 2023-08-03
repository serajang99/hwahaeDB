package pureView.dao;

import pureView.dto.CosmeticDto;

import java.sql.SQLException;
import java.util.List;

public interface CosmeticDao {
	// 목록
	public List<CosmeticDto> list(String cate, int ob) throws SQLException;
	// 상세
	public CosmeticDto findByName(String search) throws SQLException;
}