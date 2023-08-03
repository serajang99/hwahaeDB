package pureView.service;

import java.util.List;

import pureView.dto.CosmeticDto;

public interface CosmeticService {
	// 화장품 목록
	public List<CosmeticDto> list(String cate, String ob);
}