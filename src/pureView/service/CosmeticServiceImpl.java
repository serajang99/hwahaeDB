package pureView.service;

import java.sql.SQLException;
import java.util.List;

import pureView.dao.CosmeticDao;
import pureView.dao.CosmeticDaoImpl;
import pureView.dto.CosmeticDto;

public class CosmeticServiceImpl implements CosmeticService {

	private CosmeticDao cosmeticDao = new CosmeticDaoImpl();
	
	@Override
	public List<CosmeticDto> list(String cate, String ob) {
		List<CosmeticDto> list = null;
		try {
			list = cosmeticDao.list(cate, ob);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}