
package MainFunctionality;

import Resources.CRUD;
import Resources.PropertiesClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author ishu
 */
public class Vaultee {
    public static void main(String args[]){
        Scanner scInt = new Scanner(System.in);
        Scanner scText = new Scanner(System.in);
        Scanner scLine = new Scanner(System.in);
        PropertiesClass pc = new PropertiesClass();
        CRUD crud = new CRUD();
        boolean run =true;
        Statement stmt=null;
        Connection conn=null;
        System.out.println("Getting Properties...//.....//...///");
        try{
            
            Class.forName("org.h2.Driver");
             System.out.println("Registering Drivers /...//...");
         conn=DriverManager.getConnection(pc.url, pc.user, pc.pass);
        System.out.println("Connecting to Database ..../...//...");
        //Statement stmt = conn.createStatement();
        
       
      if(crud.userTableExist(conn, stmt)){
          //Do Nothing
      }else{
          System.out.println("\t \t \t Create new user");
          System.out.println("\t Please Enter new Username (DONOT INCLUDE SPACES AND NUMBERS)");
          String name = scText.next();
          System.out.println("\t \t \t Please Enter new password for user "+name+" (DONOT INCLUDE SPACES)");
          String password = scText.next();
          crud.createUserTable(conn,stmt);
         // conn.setAutoCommit(false); // We'll explicitly commit all of the rows after the insertions.
		crud.insertInUserTable(conn,stmt,name,password);
		//conn.commit();
                scText.reset();
      }
         System.out.println("Connected .... :) ");
         
        
         System.out.println("\t \t \t LOGIN  ");
          while(run){
              System.out.println(" ");
     System.out.println("\t Enter Username  ");
     String user = scText.next();
    
      System.out.println("\t Enter Password  ");
      String pass = scText.next();
      if(crud.checkUser(conn, stmt, user, pass)){
          System.out.println(" ");
          System.out.println("Connected Successfully");
          scText.reset();
          run=false;
      }else{
          System.out.println(" ");
          System.out.println("Not Connected :( Wrong Credentials, Please try again");
          scText.reset();
          
      
          
         }
          }
         boolean table_test=true;
         if(crud.checkDataTable(stmt, conn)){
             
           
         }else{
             crud.createVaulteeTable(stmt, conn);
                     
                     crud.insertVaulteeTableSample(stmt, conn);
                     
         }
        }
    catch(Exception e){
               System.out.println("Something went wrong!! Try again");
              
               
           }

       System.out.println("\t \t \t Welcome To Vaultee");
       System.out.println("\t \t  \t(Open Source Password vault with Embedded Database)");
       System.out.println("\t \t \t \t \t \t \t \t \t \t \t Made by Ishu");
       run=true;
       while(run){
       System.out.println("Select Appropriate option:");
       
       System.out.println("\t 1. Enter new record");
       System.out.println("\t 2. Read all records");
       System.out.println("\t 3. Edit record");
       System.out.println("\t 4. Delete record");
       System.out.println("\t 5. Author Details");
       System.out.println("\t 6. Exit");
       System.out.println("choose one:");
       Integer sc = scInt.nextInt();
       switch(sc){
           case 1:{
               System.out.println(" ");
              
               System.out.println(" \t \t Enter username/email");
               String name = scText.next();
               System.out.println(" ");
               System.out.println(" \t \t Enter password ");
               String pass = scText.next();
               System.out.println(" ");
               System.out.println(" \t \t Enter Description/remarks ");
               String desc = scLine.nextLine();
               
               crud.insertIntoVaultee(stmt, conn, name, pass, desc);
               System.out.println(" Press any key and then press enter to go back..");
               
               scText.next();
               scLine.reset();
                 System.out.println(" ");  System.out.println(" ");
                 
               break;
           }
           case 2:{
               crud.readVaultee(stmt, conn);
               System.out.println(" ");
               System.out.println("press any key and then press enter to back ");
               scText.next();
               break;
           }
           case 3:{
               System.out.println(" ");
               
               System.out.println("\t Before going to edit a record, you need a record ID no. ");
               System.out.println(" if you have type: y or want to go back type: n");
               System.out.println(" ");
               String ans = scText.next();
               if(ans.equalsIgnoreCase("y")){
                   System.out.println("please enter id number ");
                   Integer id=scInt.nextInt();
                   System.out.println("Please enter new username/email ");
                   String user= scText.next();
                   System.out.println("Please enter new password ");
                   String pass=scText.next();
                   System.out.println("Please Enter new Description ");
                   String desc = scLine.nextLine();
                   System.out.println(" ");
                   boolean flag =crud.editRecordVaultee(stmt, conn, id, user, pass, desc);
                   if(flag){
                       
                   }else{
                       System.out.println("Wrong id :( please try again");
                   }
               }else if(ans.equalsIgnoreCase("n")){
                   break;
               }else{
                   System.out.println(" Wrong choice try again :( ");
               }
               System.out.println(" ");
               System.out.println(" ");
               break;
           }
           case 4:{
               System.out.println(" ");
               
               System.out.println("\t Before going to delete a record, you need a record ID no. ");
               System.out.println(" if you have type: y or want to go back type: n");
               System.out.println(" ");
               String ans = scText.next();
               if(ans.equalsIgnoreCase("y")){
                   System.out.println("please enter id number ");
                   Integer id=scInt.nextInt();
                   boolean flag = crud.deleteRecordVaultee(stmt, conn, id);
                   if(flag){
                       
                   }else{
                       System.out.println("Wrong id :( please try again ");
                   }
                      
               }else if(ans.equalsIgnoreCase("n")){
                   break;
               }else{
                   System.out.println(" Wrong choice try again :( ");
               }
               
               System.out.println(" ");
             
               break;
           }
           case 5:{
               System.out.println(" ");
               System.out.println("\t \t \t \t Author");
               System.out.println("\t Name : Ishu Sharma");
               System.out.println("\t Email : ishubot@gmail.com ");
               System.out.println("\t Facebook : fb.com/ishu.mafia.007");
               System.out.println("\t If you find any bugs please report me on :- ishubot@gmail.com ");
               System.out.println("\t Thank you for giving a try to VAULTEE");
               System.out.println("Press any key and enter to go back to main menu.");
               scText.next();
               System.out.println(" ");
               System.out.println(" ");
               break;
           }
           case 6:{
               run=false;
               break;
           }
           default :{
               System.out.println("Please Check the input and try again..... (:");
           }
       }
       }
    

}
}
