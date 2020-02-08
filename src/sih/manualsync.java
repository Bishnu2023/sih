/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sih;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 *
 * @author BISHNU
 */
public class manualsync {
    
    int found=0;
    int syrn;
    String syname;
    public void sync()
    {
        try
       {
           Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kvs","root","1234");
            Connection con1=DriverManager.getConnection("jdbc:mysql://35.223.139.32:3306/kvs","root","1234");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("show tables");
            while(rs.next())
            {
                   String tname=rs.getString(1);
                    System.out.println(rs.getString(1));
                    Statement stmt1=con.createStatement();
                    String query="select * from"+" "+tname;
                    ResultSet rs1=stmt1.executeQuery(query);
                    
                    
                    Statement stmt2=con1.createStatement();
                    
                    
                    while(rs1.next())
                    {
                        found=0;
                        ResultSet rs2=stmt2.executeQuery("select* from"+" "+tname);
                        int id1=rs1.getInt(1);
                        //System.out.println("LOCAL DATABASE");
                        //System.out.print(rs1.getInt(1)+" ");
                        //System.out.println(rs1.getString(2));
                        while(rs2.next())
                        {
                            int id2=rs2.getInt(1);
                            //System.out.println("CLOUD DATABASE");
                            //System.out.print(rs2.getInt(1)+" ");
                        //System.out.println(rs2.getString(2));
                            if(id1==id2)
                            {
                                found=1;
                                break;
                            }
                        }
                        if(found==0)
                        {
                            System.out.println("MISSING DATA");
                            System.out.println(rs1.getInt(1)+" "+rs1.getString(2));
                            ResultSetMetaData rsmd = rs1.getMetaData();
                            int tcolumns=rsmd.getColumnCount();
                           // System.out.println(tcolumns);
                            String cname="";
                            int pkey=rs1.getInt(1);
                            String pcname=rsmd.getColumnName(1);
                             stmt2.executeUpdate("INSERT INTO"+" "+tname+"("+pcname+")"+" "+"VALUES("+rs1.getInt(1)+")");
                            for(int i=2; i<=tcolumns; i++)
                            {
                                if(rsmd.getColumnTypeName(i).equals("INT"))
                                {
                                    cname=rsmd.getColumnName(i);
                                    System.out.println(cname);
                                    System.out.println(rs1.getInt(i));
                                    //stmt2.executeUpdate("INSERT INTO"+" "+tname+"("+cname+")"+" "+"VALUES("+rs1.getInt(i)+")"+" "+"WHERE"+" "+pcname+"="+pkey);
                                    stmt2.executeUpdate("UPDATE"+" "+tname+" "+"SET"+" "+cname+"="+rs1.getInt(i)+" "+"WHERE"+" "+pcname+"="+pkey);
                                }
                                else if(rsmd.getColumnTypeName(i).equals("VARCHAR"))
                                {
                                    cname=rsmd.getColumnName(i);
                                    System.out.println(cname);
                                    System.out.println(rs1.getString(i));
                                    String n = '\''+ rs1.getString(i) + '\'';
                                    System.out.println(n);
                                    System.out.println(cname);
                                    System.out.println(pcname);
                                    System.out.println(pkey);
                                    //stmt2.executeUpdate("INSERT INTO"+" "+tname+"("+cname+")"+" "+"VALUES("+n+")"+" "+"WHERE"+" "+pcname+"="+pkey);
                                    stmt2.executeUpdate("UPDATE"+" "+tname+" "+"SET"+" "+cname+"="+n+" "+"WHERE"+" "+pcname+"="+pkey);
                                    
    
                                }
                            }
                        }
                    }
            }
       }
       catch(Exception ex)
       {
           System.out.println(ex);
       }
    }
    
}
