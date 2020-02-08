/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sih;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author aasth
 */
public class SIH {

   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int found=0;
        NewJFrame NJF=new NewJFrame();
        NJF.setVisible(true);
        NJF.setTitle("GOVERMENT OF SIKKIM");
        
     Display obj=new Display();
       obj.D();
       
       CheckInternetConnection C=new CheckInternetConnection();
Timer timer=new Timer();
TimerTask myTask=new TimerTask()
{
    public void run()
    {
        C.CheckConnection();
    }    
};
timer.schedule(myTask, 20000,20000);
        
       
           
        // TODO code application logic here
    }
    
}
