package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ayettey on 03/10/2016.
 */
public class GeniFrame  {
    JFrame frame;


   public GeniFrame(){
       CreateFrame();

    }

    public void CreateFrame(){
        frame=new JFrame("Geni Data Management");
        try{

           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        try{

            frame.setContentPane(new GeniLayout(this));

        }
        catch (Exception e){
            e.getStackTrace();

        }
            frame.pack();
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);


        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);




    }catch (Exception e){

        }
}
}
