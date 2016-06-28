/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ishu
 */
public class CRUD {
    public boolean userTableExist(Connection con,Statement stmt){
        boolean bool = false;
       
	try {
	    stmt = con.createStatement();
	    String sql = "SELECT COUNT(*) AS TOTAL FROM USER";
	    ResultSet rs = stmt.executeQuery(sql);
	    while(rs.next()){
	    if (rs.getInt("TOTAL") > 0)
		bool = true;
            }
	}
	catch(SQLException e) {   System.out.println(""); }
	return bool;
       
    }
    public void createUserTable(Connection con,Statement stmt){
         try{
             stmt=con.createStatement();
        String sql="CREATE TABLE USER( ID INT PRIMARY KEY,USERNAME VARCHAR(255),PASSWORD VARCHAR(500) "
                + ")";
        
       
           stmt.executeUpdate(sql);
            
        }catch(SQLException e){
            System.out.println("Oppss Something went wrong, Restart again");
        }
    }
        public void insertInUserTable(Connection conn,Statement stmt,String name, String pass){
            String insertUser="INSERT INTO USER (ID,USERNAME,PASSWORD) VALUES (1,'"+name+"','"+pass+"')";
            try{
                stmt=conn.createStatement();
               int rows2=stmt.executeUpdate(insertUser); 
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
        public boolean checkUser(Connection con,Statement stmt,String user,String pass){
            boolean flag = false;
            try{
                
                stmt=con.createStatement();  
                ResultSet rs = stmt.executeQuery("SELECT * FROM USER");
                System.out.println(rs);
                while(rs.next()){
                    String name=rs.getString("USERNAME");
                    String passw=rs.getString("PASSWORD");
                     
                
                    if(user.equalsIgnoreCase(name)&&pass.equalsIgnoreCase(passw)){
                        flag=true;
                    }else{
                        flag=false;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
          return flag;
            
        }
        public boolean checkDataTable(Statement stmt,Connection con){
            boolean flag =false;
            try{
                
                stmt=con.createStatement();
                String sql="SELECT COUNT(*) AS TOTAL FROM VAULTEE";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                     if (rs.getInt("TOTAL") > 0){
                         
		flag = true;
                     }
            }
             
            }catch(SQLException e){
                System.out.println(" ");
            }
            return flag;
        }
        public void createVaulteeTable(Statement stmt,Connection con){
            try{
            stmt=con.createStatement();
            String sql = "CREATE TABLE VAULTEE(ID INT PRIMARY KEY auto_increment,USERNAME VARCHAR(255),PASSWORD VARCHAR(500),DESCRIPTION VARCHAR(500))";
            stmt.executeUpdate(sql);
            }catch(Exception e){
                System.out.println(" ");
            }
            
        }

public void insertVaulteeTableSample(Statement stmt, Connection con){
                try{
                    stmt=con.createStatement();
                    String sql="INSERT INTO VAULTEE(USERNAME,PASSWORD,DESCRIPTION) VALUES('sample','sample','sample')";
                    stmt.executeUpdate(sql);
                    
                }catch(Exception e){
                   e.printStackTrace();
                }
                
            }    
public void insertIntoVaultee(Statement stmt,Connection con,String user_name,String password,String description){
try{
    stmt=con.createStatement();
    String sql="INSERT INTO VAULTEE(USERNAME,PASSWORD,DESCRIPTION) VALUES ('"+user_name+"','"+password+"','"+description+"')";
stmt.executeUpdate(sql);
System.out.println("Record has been successfully added");
}catch(Exception e){
    e.printStackTrace();
}
}
public void readVaultee(Statement stmt,Connection conn){
            try{
           
        stmt=conn.createStatement();
        String sql="SELECT * FROM VAULTEE";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.printf("\n \n");
        System.out.println("\t \t ID \t \t| USERNAME \t \t| PASSWORD \t \t| DESCRIPTION | ");
        System.out.printf("\n");
        while(rs.next()){
            if(rs.getString("USERNAME").equals("sample")){
                //nothing
            }else{
            System.out.print("\t \t "+rs.getInt("ID"));
            System.out.print("\t \t   "+rs.getString("USERNAME"));
             System.out.print("\t \t   "+rs.getString("PASSWORD"));
              System.out.print("\t \t   "+rs.getString("DESCRIPTION"));
              System.out.println("\n");
            }
        }
        
        }catch(Exception e){
            e.printStackTrace();
        }
}
public boolean deleteRecordVaultee(Statement stmt,Connection con,Integer id){
    boolean flag=false;
    try{
        stmt=con.createStatement();
        String sql1="SELECT * FROM VAULTEE";
        String sql="DELETE FROM VAULTEE WHERE ID="+id+" ";
        ResultSet rs=stmt.executeQuery(sql1);
        while(rs.next()){
            Integer i = rs.getInt("ID");
            if(i.equals(id)){
                stmt.executeUpdate(sql);
                flag=true;
        System.out.println(" ");
        System.out.println("Deleted Successfully ");
        System.out.println(" ");
        System.out.println(" ");
        }
        }
        
    }catch(Exception e){
        
        /* e.printStackTrace();*/
      //  System.out.println("Wrong id please try again :(  ");
    }
    return flag;
}
public boolean editRecordVaultee(Statement stmt,Connection con,Integer id, String username,String password,String description)
{
    boolean flag= false;
    try{
        stmt=con.createStatement();
        String sql1="SELECT * FROM VAULTEE";
        String sql="UPDATE VAULTEE SET USERNAME='"+username+"',PASSWORD='"+password+"',DESCRIPTION='"+description+"' WHERE ID="+id+"";
        ResultSet rs=stmt.executeQuery(sql1);
        while(rs.next()){
            Integer i = rs.getInt("ID");
            if(i.equals(id)){
        stmt.executeUpdate(sql);
        System.out.println(" ");
        flag=true;
        System.out.println("ALL DONE :)  ");
         System.out.println(" ");
          
            }
        }
    }catch(Exception e){
       
        //System.out.println("Wrong id :( ");
    }
    return flag;
}
        }

