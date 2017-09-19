package vo;

public class RankVO {
	private String[][] score;
	private String sno;
	private String name;
	private String sub1;
	private String sub2;
	private String sub3;
	private String sum;
	private String avg;
	private String rank;
	private String bigo;
	
	public RankVO() {
		
	}
	
	public RankVO(String[][] score) {
		this.score = score;
	}

	public void setScore(String[][] score) {
		this.score = score;
	}
	public String[][] getScore() {
		return score;
	}
	public void setSno(String sno) {
		this.sno= sno;
	}
	public String getSno() {
		return sno;
	}
	public void setName(String name) {
		this.name= name;
	}
	public String getName() {
		return name;
	}
	public String getSub1() {
		return sub1;
	}

	public void setSub1(String sub1) {
		this.sub1 = sub1;
	}

	public String getSub2() {
		return sub2;
	}

	public void setSub2(String sub2) {
		this.sub2 = sub2;
	}

	public String getSub3() {
		return sub3;
	}

	public void setSub3(String sub3) {
		this.sub3 = sub3;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getAvg() {
		return avg;
	}

	public void setAvg(String avg) {
		this.avg = avg;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getBigo() {
		return bigo;
	}
	public void setBigo(String bigo) {
		this.bigo = bigo;
	}
}
