package pureView.dto;

public class CosStatistic {
	String category;
	int count, avgPrice;

	public CosStatistic() {
	}

	public CosStatistic(String category, int count, int avgPrice) {
		super();
		this.category = category;
		this.count = count;
		this.avgPrice = avgPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(int avgPrice) {
		this.avgPrice = avgPrice;
	}

	@Override
	public String toString() {
		return "CosStatistic [category=" + category + ", count=" + count + ", avgPrice=" + avgPrice + "]";
	}

}
