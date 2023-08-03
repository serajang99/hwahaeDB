package pureView.dto;

import java.util.Date;

public class CommentDto {
	int num, boardNum;
	String memberId, content;
	Date commentTime;

	public CommentDto() {
	}

	public CommentDto(int num, int boardNum, String memberId, String content, Date commentTime) {
		super();
		this.num = num;
		this.boardNum = boardNum;
		this.memberId = memberId;
		this.content = content;
		this.commentTime = commentTime;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	@Override
	public String toString() {
		return "commentDto [num=" + num + ", boardNum=" + boardNum + ", memberId=" + memberId + ", content=" + content
				+ ", commentTime=" + commentTime + "]";
	}

}
