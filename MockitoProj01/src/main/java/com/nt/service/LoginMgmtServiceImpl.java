package com.nt.service;
import com.nt.dao.ILoginDao;
public class LoginMgmtServiceImpl implements ILoginMgmtService 
{	
	private ILoginDao loginDao;
	public LoginMgmtServiceImpl(ILoginDao loginDao)
	{
		this.loginDao=loginDao;
	}
	@Override
	public String login(String user, String pwd) 
	{
		System.out.println("DAO class Obj : "+loginDao.getClass());
		if(user.equalsIgnoreCase("")||user.length()==0||pwd.length()==0)
			throw new IllegalArgumentException("Invalid Inputs");
		
		int count=loginDao.authenticate(user, pwd);
		if(count==1)
			return "Valid Credentials";
		else
		return "InValid Credentials";
	}

}
