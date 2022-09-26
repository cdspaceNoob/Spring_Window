package gntp.lesson.guestbook.vo;

import java.sql.Timestamp;

public class ReplyVO {
	private int replySeq;
	private String replyContent;
	private Timestamp replyDate;
	private int gbSeq;
	
	public ReplyVO() {}
	public ReplyVO(int replySeq, String content, Timestamp regDate, int seq) {
		this.replySeq = replySeq;
		this.replyContent = content;
		this.replyDate = regDate;
		this.gbSeq = seq;
	}
	public int getReplySeq() {
		return replySeq;
	}
	public void setReplySeq(int replySeq) {
		this.replySeq = replySeq;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Timestamp getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
	}
	public int getGbSeq() {
		return gbSeq;
	}
	public void setGbSeq(int gbSeq) {
		this.gbSeq = gbSeq;
	}
	
	@Override
	public String toString() {
		return "ReplyVO [replySeq=" + replySeq + ", replyContent=" + replyContent + ", replyDate=" + replyDate
				+ ", gbSeq=" + gbSeq + "]";
	}
	

}
