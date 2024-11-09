package com.nt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.nt.dao.ILoginDao;

public class AppTest 
{
	private static ILoginDao loginDao;
	private static ILoginMgmtService loginService;
	@BeforeAll
	public static void setupOnce()
	{
		loginDao=Mockito.mock(ILoginDao.class);
		System.out.println("Proxy Obj Class Name : "+loginDao.getClass()+"..."+Arrays.toString(loginDao.getClass().getInterfaces()));
		loginService=new LoginMgmtServiceImpl(loginDao);		
	}
	@Test
	public void testLoginWithValidCredentials()
	{
		Mockito.when(loginDao.authenticate("raja","rani")).thenReturn(1);
		String actual=loginService.login("raja","rani");
		assertEquals("Valid Credentials",actual);
	}
	@Test
	public void testLoginWithInValidCredentials()
	{
		Mockito.when(loginDao.authenticate("raja","rani1")).thenReturn(0);
		String actual=loginService.login("raja","rani1");
		assertEquals("InValid Credentials",actual);
	}
	@Test
	public void testLoginWithNoCredentials()
	{
		assertThrows(IllegalArgumentException.class,()->{loginService.login("", "");});
	}
	
	@AfterAll
	public static void tearDownOnce()
	{
		loginDao=null;
		loginService=null;
	}
}
