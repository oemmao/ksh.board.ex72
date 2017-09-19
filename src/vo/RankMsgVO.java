package vo;

public class RankMsgVO {
	private String scoreFailException; //과락 예외
	private String averageFailException; //불합격 예외
	
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
