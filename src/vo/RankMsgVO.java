package vo;

public class RankMsgVO {
	private String scoreFailException; //���� ����
	private String averageFailException; //���հ� ����
	
	public RankMsgVO() {
	}
	
	public void setScoreFailException(String msgExc) {
		this.scoreFailException = msgExc;
	}
	public String getScoreFailException() {
		return scoreFailException;
	}
	public void setAverageFailException(String msgExc) {
		this.averageFailException = msgExc;
	}
	public String getAverageFailException() {
		return averageFailException;
	}
}
