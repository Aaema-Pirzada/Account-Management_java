package bankmanagement;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
/**

*

* Bank Account Class

* // Main ClassName -- "Main"

*/



class Account{

    String name,acc_type;

    int Acc_num,Acc_Balance;
    
    boolean transferred= false;
    int AT[]= {0,0,0,0,0,0};
    int AZ[] = {0,0,0,0,0,0};
    int amtZ[] = {0,0,0,0,0,0};
    int countT=0;
    int countZ=0;

    Account(){

      

    }

        Account(String n,int acc_num,int b,String a_t){

            name=n;

            Acc_num=acc_num;

            Acc_Balance=b;

            acc_type=a_t;

        }

} // end class

class create_account extends Account{

    create_account(String n,int acc_num,int b,String a_t){ // pass name and account type

            name=n;

            Acc_num=acc_num;

            Acc_Balance=b;

            acc_type=a_t;

    }

    create_account(){

        super();

    }

        

    void insert(String n,int acc_num,String a_t){ // input user name, account number and type

        name=n;

        acc_type=a_t;

        Acc_num=acc_num; // generate random number

        Acc_Balance=0;

    }

    

    String display_details(){

    	String details;
	    details = "Depositor Name : " +name + " Account Number :  "+Acc_num +" Account Balance :  "+Acc_Balance +" Account Type :  "+acc_type ;  
        return details;

    }

        void deposite(int acc_num,int money){                 

                Acc_Balance=money;    

        }

      

        int withdraw(int withd, String acctype){
                if(acctype.equalsIgnoreCase("Checking")) {
                	 Acc_Balance=(Acc_Balance-withd)-10;
                	 AT[countT]=Acc_num;
                	 countT++;
                }
                else if(withd<=Acc_Balance)	
                Acc_Balance=Acc_Balance-withd;
                 
                return Acc_Balance;

        }

        String printStatement() {
        	String details;        
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            //System.out.println(dtf.format(now));
            details=display_details()+" "+ dtf.format(now);
            return details;
        }
        boolean transferAmount(int width,int acc_no) {
        if(acc_no==Acc_num) {	
        	if(width>Acc_Balance)
        		transferred=false;
        	else if (width<=0)  
        		transferred=false;

            else 
        	{  
        		transferred=true;
        	    withdraw(width,acc_type);
        	}
        }
        return transferred;
       }
  
   int  calculateZakat(int accnum){
	   int zakah=0;
	   if(accnum==Acc_num) {
	   if(Acc_Balance>=20000 && acc_type.equalsIgnoreCase("saving"))
	   {
		   zakah=(int)(Acc_Balance*0.025);
		   AZ[countZ]=Acc_num;
		   amtZ[countZ]=zakah;
		   countZ++;
		   withdraw(zakah,acc_type);
	   }
    	
     }
	   return zakah; 
   }   
   /*
 void displayalldeduction(int accnum) {
	 for (int i = 0; i < 5; i++) {
		  if(AT[i]==accnum)
			  System.out.println("The amount deducted from account " +accnum +" is 10");
		  if(AZ[i]==accnum)
		  {
			  System.out.println("The amount deducted from account " +accnum +" is " +amtZ[i]);
		  }
		}
	
	 
 }*/
} // end class

      