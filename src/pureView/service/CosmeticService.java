package pureView.service;

import java.util.List;

import pureView.dto.CosStatistic;
import pureView.dto.CosmeticDto;

public interface CosmeticService {
	// 화장품 목록
	public List<CosmeticDto> list(String cate, String ob);

	// 화장품 상세
	public CosmeticDto detail(String search);

	// 화장품 통계
	public List<CosStatistic> statistics();
}