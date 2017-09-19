package service;

import java.util.List;
import entity.CalEntity;
import exception.*;
import vo.*;

public class CalService {
	private CalEntity ce;
	private CalVO vo;
	private CalExcMsgVO emvo;
	
	public CalService() {
		ce = new CalEntity();
	}
	
	public void doService(List list) throws Exception {
		vo = (CalVO)list.get(0);
		emvo = (CalExcMsgVO) list.get(1);
		String op1 = vo.getOp1();
		String op = vo.getOp();
		String op2 = vo.getOp2();
		
		if (op.equals("+")) {
			if (Integer.parseInt(op1) == 0 || Integer.parseInt(op2) == 0) {
				emvo.setMsgAddZeroExc(getMsgAddZeroExc());
				throw new AddZeroException(emvo.getMsgAddZeroExc());
			} else {
				vo.setResult(Integer.parseInt(op1) + Integer.parseInt(op2));
			}
		}

		else if (op.equals("-")) {
			if (Integer.parseInt(op2) == 0) {
				emvo.setMsgSubZeroExc(getMsgSubZeroExc());
				throw new SubZeroException(emvo.getMsgSubZeroExc());
			} else {
				vo.setResult(Integer.parseInt(op1) - Integer.parseInt(op2));
			}
		}

		else if (op.equals("*")) {
			if (Integer.parseInt(op1) == 1 || Integer.parseInt(op2) == 1) {
				emvo.setMsgMulOneExc(getMsgMulOneExc());
				throw new MulOneException(emvo.getMsgMulOneExc());
			} else {
				vo.setResult(Integer.parseInt(op1) * Integer.parseInt(op2));
			}
		}

		else if (op.equals("/")) {
			if (Integer.parseInt(op2) == 1) {
				emvo.setMsgDivOneExc(getMsgDivOneExc());
				throw new DivOneException(emvo.getMsgDivOneExc());
			} else {
				vo.setResult(Integer.parseInt(op1) / Integer.parseInt(op2));
			}
		}
		ce.doService(list);
	}
	
	public String getMsgAddZeroExc() {
		return ce.getMsgAddZeroExc();
	}

	public String getMsgSubZeroExc() {
		return ce.getMsgSubZeroExc();
	}

	public String getMsgMulOneExc() {
		return ce.getMsgMulOneExc();
	}

	public String getMsgDivOneExc() {
		return ce.getMsgDivOneExc();
	}
}
