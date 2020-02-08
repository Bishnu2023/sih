package sih;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aasth
 */
public class Display {
    
    public void D()
    {
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kvs","root","1234");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select* from studentdetails");
            System.out.println("LOCAL DATABASE");
            while(rs.next())
            {
               
                    System.out.println(rs.getInt(1)+" "+rs.getString(2));
                
            }
            con.close();
            
            System.out.println("\nGOOGLE DATABASE");
            Connection con1=DriverManager.getConnection("jdbc:mysql://35.223.139.32:3306/kvs","root","1234");
            Statement stmt1=con1.createStatement();
            ResultSet rs1=stmt1.executeQuery("select* from studentdetails");
           while(rs1.next())
           {
                System.out.println(rs1.getInt(1)+" "+rs1.getString(2));
           }
           con1.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
}
