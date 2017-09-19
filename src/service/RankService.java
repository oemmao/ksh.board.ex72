package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.*;
import exception.*;
import vo.*;

public class RankService {
	private RankEntity re;
	private RankVO vo;
	private RankMsgVO mvo;
	private List list;
	
	public RankService() {
		re = new RankEntity();
	}
	public void doService(List list) throws Exception {
		this.list = list;
		sum();
		avg();
		ranking();
		subTotal();
		re.store(list);
	}

	public void sum() throws Exception { //�� ���
		vo = (RankVO)list.get(0);
		mvo = (RankMsgVO)list.get(1);
	
		String[][] score = vo.getScore();
		for (int i=0; i<score.length-1; i++) {
			int tmp = 0;
			for (int j=1; j<score[i].length-4; j++) {
				if (Integer.parseInt(score[i][j]) <= 40) {
					tmp += Integer.parseInt(score[i][j]);
					mvo.setScoreFailException(getScoreFailException());
					score[i][score[i].length-1] = mvo.getScoreFailException(); //�����Դϴ�.
				} else {
					tmp += Integer.parseInt(score[i][j]);
					score[i][score[i].length-1] = " ";
				}
				score[i][score[i].length-4] = Integer.toString(tmp);
			}
			
		}
		vo.setScore(score);
	}
	
	public void avg() throws Exception { //��� ���
		vo = (RankVO)list.get(0);
		mvo = (RankMsgVO)list.get(1);
		
		String[][] score = vo.getScore();

		for (int i=0; i<score.length-1; i++) {
			float tmp = 0;
			for (int j=1; j<score[i].length-4; j++) {
				tmp += Float.parseFloat(score[i][j]);
			}
			score[i][score[i].length-3] = Float.toString(tmp/3f);
			if (Float.parseFloat(score[i][score[i].length-3]) < 60) {
				mvo.setAverageFailException(getAverageFailException());
				if (score[i][score[i].length-1] == " ") {
				score[i][score[i].length-1] = mvo.getAverageFailException();
				} else {
					score[i][score[i].length-1] +="," + mvo.getAverageFailException();
				}
			}		
		}
		vo.setScore(score);
	}
	public void ranking() throws Exception { //���� ���
		vo = (RankVO)list.get(0);
		mvo = (RankMsgVO)list.get(1);
		
		String[][] score = vo.getScore();
		int[] ranking = {1, 1, 1, 1, 1}; //��ŷ�� ������� �迭
		
		for (int i=0; i<score.length-1; i++) { //������ ���ϱ� ���� �� �л��� ���� ����.
			int tmp = 0;
			for (int j=1; j<score[i].length-4; j++) {
				tmp += Integer.parseInt(score[i][j]);
			}
			score[i][score[i].length-2] = Integer.toString(tmp);
		}
		for (int i=0; i<score.length-1; i++) { //�л��� ���� ���Ͽ� ������� ����(�����Ʈ)
			int num = 1;
			for (int j=0; j<score.length-1; j++) {
				if(Integer.parseInt(score[i][score[i].length-4]) < Integer.parseInt(score[j][score[j].length-4])) {	
					num++;
				}
			}
			ranking[i] = num;
		}
		
		for (int i=0; i<score.length-1; i++) { //���ĵ� ������ score�� ����.
			score[i][score[i].length-2] = Integer.toString(ranking[i]);
		}
		vo.setScore(score);
	}
	
	public void subTotal() throws Exception {
		vo = (RankVO)list.get(0);
		String[][] score = vo.getScore();
		int[] total = {0, 0, 0};
		int tmp = 0;
		for (int i=0; i<score.length-1; i++) {
			for (int j=1; j<score[i].length-4; j++) {
				tmp = Integer.parseInt(score[i][j]);
				total[(j-1)] += tmp;
			}
		}
		for (int i=1; i<score.length-2; i++) {
			score[5][i] = Integer.toString(total[(i-1)]);
		}
		vo.setScore(score);
	}
	
	public String getScoreFailException() {
		return re.getMsgScoreFail();
	}
	public String getAverageFailException() {
		return re.getMsgavgFail();
	}
}
