package himedia.myhome.dao;

import java.util.List;

import himedia.myhome.vo.UserVo;

public interface UsersDao {

	public List<UserVo> getList(); // 목록
	public boolean insert(UserVo uservo); //insert
	public boolean update(UserVo uservo); //update
	public boolean delete(Long no);	//delete -삭제는 pk만 있으면 되서
	public UserVo getUserByIdAndPassword(String email, String password); //로그인을 위한 메서드
	
}