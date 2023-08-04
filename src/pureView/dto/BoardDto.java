package pureView.dto;

import java.util.Date;

public class BoardDto {
	private int boardNum;
	private String boardTitle, boardContent;
	private Date writeTime;
	private int starRating;
	private String memberId;
	private int cosNum;

	public BoardDto() {
	}

	public BoardDto(int boardNum, String boardTitle, String boardContent, Date writeTime, int starRating,
			String memberId, int cosNum) {
		super();
		this.boardNum = boardNum;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.writeTime = writeTime;
		this.starRating = starRating;
		this.memberId = memberId;
		this.cosNum = cosNum;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Date getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}

	public int getStarRating() {
		return starRating;
	}

	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getCosNum() {
		return cosNum;
	}

	public void setCosNum(int cosNum) {
		this.cosNum = cosNum;
	}

	@Override
	public String toString() {
		return "boardNum=" + boardNum + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent + ", writeTime="
				+ writeTime + ", starRating=" + starRating + ", memberId=" + memberId + ", cosNum=" + cosNum;
	}

}
