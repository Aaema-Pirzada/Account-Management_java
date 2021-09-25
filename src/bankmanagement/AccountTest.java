package bankmanagement;

import org.junit.*;
import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;


public class AccountTest {
	private static create_account acc;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 acc = new create_account();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		 acc=null;
	}

	@Test
	public void testAccount() {
		Account newacc = new Account("AAEMA", 0001, 20000,"saving");
		assertEquals("AAEMA",newacc.name);
		assertEquals(0001,newacc.Acc_num);
		assertEquals("saving",newacc.acc_type);
		
	}
	@Test
	public void testTransfer_pos()
	{
	create_account acc2 = new create_account("Aaema",1904,100,"Savings");
	acc2.transferAmount(100,1904);
	assertEquals(true,acc2.transferred);
	}

	@Test
	public void testTransfer_neg1()
	{
	create_account acc2 = new create_account("Aaema",1904,100,"Savings");
	acc2.transferAmount(125,1904);
	assertEquals(false,acc2.transferred);
	}
	
	@Test
	public void testTransfer_neg() {
		create_account acc3 = new create_account("Aaema",1110,0,"Savings");
		acc3.transferAmount(-2,1110);
		boolean transfer=acc3.transferred;
		assertEquals(false,transfer);
	}
	
	@Test
	public void testInsert_pos() {
		acc.insert("Anum", 1344, "Checking");
		assertEquals("Anum",acc.name);
		assertEquals(1344,acc.Acc_num);
		assertEquals("Checking",acc.acc_type);		
	} 
	
	@Test
	public void testInsert_neg() {
		acc.insert("Anum", 1344, "Checking");
		assertNotEquals("Aaema",acc.name);
		assertNotEquals(1443,acc.Acc_num);
		assertNotEquals("Savings",acc.acc_type);		
	}
	
	@Test
	public void testDeposite_pos() {
		acc.deposite(1344, 50000);
		assertEquals(50000,acc.Acc_Balance);
	}
	
	@Test
	public void testDeposite_neg() {
		acc.deposite(1344, 500);
		assertNotEquals(50000,acc.Acc_Balance);
	}
	
	@Test
	public void testZakat_Saving_neg() {
		create_account c1 = new create_account("cust1", 1312,10000,"saving");
		assertEquals(0, c1.calculateZakat(1312));
				
	}
	
	@Test
	public void testZakat_Checking_pos() {
		create_account c2 = new create_account("cust2", 1324,20000,"checking");
		assertEquals(0, c2.calculateZakat(1324));
	}
	
	@Test
	public void testZakat_Saving_pos() {
		create_account c3 = new create_account("cust3", 4561,25000,"saving");
		assertEquals(625, c3.calculateZakat(4561));	
	}
	
	@Test
	public void testWithdraw_Saving_pos() {
		acc.deposite(1344, 50000);
		acc.withdraw(4500, "saving");
		int bal =50000-4500;
		assertEquals(bal, acc.Acc_Balance);
	}
	
	@Test
	public void testWithdraw_Checking_neg() {
		create_account acc4=new create_account("test1", 1321, 0,"checking");
		acc4.withdraw(5000, "Checking");
		int bal =0-5010;
		assertEquals(bal, acc4.Acc_Balance);	
	}
	@Test
	public void testWithdraw_Saving_neg() {
		create_account acc5=new create_account("test2", 4321, 0,"saving");
		acc5.withdraw(5000, "saving");
		int bal =0;
		assertEquals(bal, acc5.Acc_Balance);	
	}
	@Test
	public void testWithdraw_Checking_pos() {
		create_account acc6=new create_account("test3", 1322,6000,"checking");
		acc6.withdraw(5000, "Checking");
		int bal =6000-5010;
		assertEquals(bal, acc6.Acc_Balance);	
	}
	
	@Test
	public void testdisplaydetails() {
		String details = "Depositor Name : " +acc.name + " Account Number :  "+acc.Acc_num +" Account Balance :  "+acc.Acc_Balance +" Account Type :  "+acc.acc_type;  
		String check=acc.display_details();
		assertEquals(details, check);
	}
	
	@Test
	public void testPrintStatement() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
		String details = "Depositor Name : " +acc.name + " Account Number :  "+acc.Acc_num +" Account Balance :  "+acc.Acc_Balance +" Account Type :  "+acc.acc_type + " "+ dtf.format(now);  
        String check = acc.printStatement();
        assertEquals(details, check);
	}
	
}
	
