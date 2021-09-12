package bankmanagement;
import java.util.Random;
import java.util.*;
import java.text.SimpleDateFormat;  
import java.util.Date;  


public class Main {

    public static void main(String args[]){

        String user_name=null,type;

        type = null;

        int balance=0,tmp=0,tmp1=0, zak=0;

        boolean transfer=false;
        
        int withd=0,cb=0;

// to generate Random Account Number
 Random rand = new Random(); //instance of random class
        int upperbound = 9999;
int aNumber = 0;



        create_account user = new create_account("user",0,0,"savings"); // initilaize -- name,acc_number,Balance,Type

    

            Scanner in = new Scanner(System.in);

            Scanner strng=new Scanner(System.in);

            int userChoice;

            boolean quit = false;

            while(!quit) {

                  System.out.println("1. Create Account");

                  System.out.println("2. Deposit money");

                  System.out.println("3. Withdraw money from Saving Account");
                  
                  System.out.println("4. Withdraw money from Checking Account");

                  System.out.println("5. Check balance");
                  
                  System.out.println("6. For Transferring Funds");

                  System.out.println("7. Display Account Details");
                  
                  System.out.println("8. Print Statement");
                  
                  System.out.println("9. Calculate Zakat");
                  
                  System.out.println("0. Check All Deductions");

                  System.out.println("10. to quit: \n");

                  System.out.print("Enter Your Choice : ");

                  userChoice = in.nextInt();
                  if(userChoice==10)
                	  quit=true;

                  switch (userChoice) {

                      

                  case 1:

                        System.out.print("Enter your Name : ");

                        user_name=strng.nextLine();

                        System.out.print("Enter Accout Type : ");

                        type=in.next();
                        
                        aNumber = rand.nextInt(upperbound);

                        user.insert(user_name, aNumber, type);  // inserted

                        System.out.println("\n\tYour Account Details\n\tDont Forget Account Number\n");

                        System.out.println("**************************");                       

                        user.display_details();

                        break;

                      

                case 2: // deposit

                    System.out.print("Enter your account Number : ");

                    tmp=in.nextInt();

                 if(tmp==user.Acc_num){

                 System.out.print("Enter Amount Of Money : ");

                 balance=in.nextInt();

                 user.Acc_Balance=balance;

                 System.out.println("\t Successfully Deposited.");

              }                

                     else

                    System.out.println("Wrong Accoount Number.");          

                   break;

                    

                  case 3: // withdraw money  from saving                   

                	  System.out.print("Enter your account Number : ");

                      tmp=in.nextInt();

                      

                          if(tmp==user.Acc_num && user.acc_type.equalsIgnoreCase("saving")){                         

                             if(user.Acc_Balance==0)

                             System.out.print("Your Account is Empty.");

                             

                             else{

                             System.out.print("Enter Amout Of Money : ");   

                             withd=in.nextInt();  

                             

                             if(withd>user.Acc_Balance){

                             System.out.print("Enter Valid Amout of Money : ");

                             withd=in.nextInt();

                             }

                             else

                             cb= user.withdraw(withd,user.acc_type);

                             System.out.println("Your Current Balance : "+cb);   

                             }

                          }

                             else

                             System.out.println("Wrong Accoount Number or Wrong Account Type");  

                        break;

                  
                  case 4: // withdraw money from Checking account
                	  System.out.print("Enter your account Number : ");

                      tmp=in.nextInt();

                      System.out.print("Want to Withdraw from Account Balance? Press 1 for YES and 0 For No ");
      
                      tmp1=in.nextInt();
                      
                          if(tmp==user.Acc_num && user.acc_type.equalsIgnoreCase("checking")){      
                        	  System.out.print("Enter Amout Of Money : ");   

                              withd=in.nextInt();  
                              if(tmp1==0) {
                                 if(withd>=5000 && user.Acc_Balance==0 )
                                 {
                                	 System.out.print("Enter Valid Amount");
                                	 withd=in.nextInt();
                                 }
                                 else if(user.Acc_Balance==0 && withd<=5000) {
                                	 cb= user.withdraw(withd,user.acc_type);

                                 System.out.println("Your Current Balance : "+cb);  
                                 }
                              }
                              else if(tmp1==1) {
                             	 System.out.print("Enter Valid Amount");
                             	 withd=in.nextInt();
                              }
                              else {
                            	  cb= user.withdraw(withd,user.acc_type);
                                 System.out.println("Your Current Balance : "+cb);   
                                 }
                          }
                          else

                              System.out.println("Wrong Account Number or Wrong Account Type");  

                         break;
                             
 
                        
                  case 5: // check balance

                      System.out.print("Enter your Account Number : ");

                      tmp=in.nextInt();

                      

                             if(tmp==user.Acc_num){

                             System.out.println("Your Current Balance : "+user.Acc_Balance);

                             }

                             else

                             System.out.println("Wrong Accoount Number.");                         

                      break;

                      
                  case 6: //transferred amount;
                	  
                	  System.out.print("Enter your Account Number :");

                      tmp=in.nextInt();                     

                             if(tmp==user.Acc_num){   
                            	 System.out.print("Enter Account Number for transfer :");
                            	 tmp1=in.nextInt();
                            	 System.out.print("Enter Amount :");
                            	 withd=in.nextInt();
                            	 transfer=user.transferAmount(withd);
                            	 if(transfer) {
                            		 cb= user.withdraw(withd,user.acc_type);
                            		 System.out.println("Your Amount of : " +withd  +"has been transferred");
                            	     user.printStatement();
                            	 
                            	 }
                            	 

                             }
                	  
                	  break;

                  case 7: // display all info

                          

                      System.out.print("Enter your Account Number :");

                      tmp=in.nextInt();                     

                             if(tmp==user.Acc_num){                               

                             user.display_details();                             

                        }else

                             System.out.println("Wrong Accoount Number.");

                             

                      break;
                      
                  case 8:
                	  user.printStatement();
                	  
                  case 9:
                	  System.out.print("Enter your Account Number :");

                      tmp=in.nextInt();                     

                             if(tmp==user.Acc_num && user.acc_type.equalsIgnoreCase("saving")){ 
                            	 zak=user.calculateZakat();
                            	 System.out.println("Total Amount Deducted for Zakat is : " +zak);
                             }
                             else 
                            	 System.out.println("Wrong Acount Number or Wrong Account Type");
                             break;
                	 
                             
                  

                  case 0:
                	  System.out.print("Enter your Account Number :");

                      tmp=in.nextInt();  
                      if(tmp==user.Acc_num)
                      user.displayalldeduction(tmp);
                      else
                         	 System.out.println("Wrong Acount Number");
                      break;
                        
                  default:

                        System.out.println("Quitting or Wrong Choice");

                        break;

                  }

                  System.out.println("\n");

            } 


             

     } //  end main function

    

} //  end main class
