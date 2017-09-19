package service;

import java.util.List;
import entity.MemberEntity;
import vo.MemberVO;

public class MemberService {
	private MemberEntity me;
	
	public MemberService() {
		me = new MemberEntity();
	}
	public void memberInsert(MemberVO vo){
		me.memberInsert(vo);
	}
	public void checkID(MemberVO vo){
		me.checkID(vo);
	}
	public void login(MemberVO vo){
		me.login(vo);
	}
	public void selectAllMember(List<MemberVO> list){
		me.selectAllMember(list);
	}
	public void selectMember(MemberVO vo){
		me.selectMember(vo);
	}
	public void updateMember(MemberVO vo){
		me.updateMember(vo);
	}
	public void deleteMember(MemberVO vo){
		me.deleteMember(vo);
	}
}
