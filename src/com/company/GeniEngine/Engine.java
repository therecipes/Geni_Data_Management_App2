package com.company.GeniEngine;

import com.company.GeniLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by ayettey on 05/10/2016.
 */
public class Engine extends JFrame implements ActionListener {
   protected JPanel subWorkBenchPanel;
    protected Connection connection;
    protected java.sql.Statement statement;
    protected ResultSet resultSet;
    protected ResultSetMetaData setMetaData;
    private String sql;


    GeniLayout object;
    private GridBagConstraints constraints;
    private GridBagLayout layout;
    private JLabel infoLabel;
    private JLabel noLable;
    private JTextField createShowUserText;
    private JButton[] create;

    public Engine(GeniLayout object){





             this.object=object;




        infoLabel=new JLabel();
        createShowUserText=new JTextField();
        create=new JButton[2];
        create[0]=new JButton();


    }

   private int i;
   private boolean sqlControl;


public void actionPerformed(ActionEvent e) {



    if(e.getSource().equals(object.menuItem[0])){
        infoLabel.setText("Create Database");
            for(int x=0;x<2;x++){
                if(i==0){
                createEntities(true);
                i++;
                System.out.print("Tell me" + i);
            }
        }


    }


    if(e.getSource().equals(object.menuItem[1])){
        System.out.print("what you");
    }

    if(e.getSource().equals(object.menuItem[2])){
        System.out.print("Want");
    }

    }












        public void createEntities(boolean onAndOff){
            subWorkBenchPanel=new JPanel();
            object.workBenchPanel.validate();

                layout = new GridBagLayout();
                constraints = new GridBagConstraints();
                subWorkBenchPanel.setLayout(layout);

                constraints.anchor=GridBagConstraints.WEST;
                constraints.insets=new Insets(5,327,10,327);


                constraints.ipadx=5;
                constraints.ipadx=5;

                constraints.gridy=0;


                infoLabel.setFont(new Font("",Font.BOLD,12));
                layout.setConstraints(infoLabel,constraints);
                subWorkBenchPanel.add(infoLabel);

                constraints.gridy=1;

                createShowUserText.setPreferredSize(new Dimension(700,60));
                createShowUserText.setFont(new Font("",Font.ITALIC,14));
                createShowUserText.setBackground(new Color(0xFFFFFF));
                layout.setConstraints(createShowUserText,constraints);
                subWorkBenchPanel.add(createShowUserText);

                constraints.gridy=2;

                create[0].setText("Create");
                layout.setConstraints(create[0],constraints);
                subWorkBenchPanel.add(create[0]);
                create[0].addActionListener(this);

                subWorkBenchPanel.setBackground(new Color(0xC0C0C0));

               // subWorkBenchPanel.validate();
                //(1358,200);
                object.workBenchPanel.add(subWorkBenchPanel);
               object.workBenchPanel.validate();


        }






    public void createDatabaseTablesAndValues(Object event,String syntax,String infoLabels,String buttonLabel,int menuItems) {

        String getFields = createShowUserText.getText();
        createShowUserText.setFocusable(true);
        if (event.equals(create[0])) {
            getCreateTableValuesAndCreateTable(syntax.toUpperCase(), getFields, infoLabels);


            if (createShowUserText.getText().equals("")) {
                infoLabel.setText(infoLabels);
                JOptionPane.showMessageDialog(null, "The text fields must not be left empty");

                return;
            }
            if (sqlControl == true ) {

                infoLabel.setText(infoLabels);
                create[0].setText(buttonLabel);
                createShowUserText.setText("");
              // int option = JOptionPane.showConfirmDialog(null, "Do you want to create tables", "", JOptionPane.YES_NO_OPTION);
              //
              // if (option == JOptionPane.YES_OPTION) {
              //     infoLabel.setText("Insert Into");
              //     create[0].setText(buttonLabel);
              //     createShowUserText.setText("");
              //   //  return;
              // } else if (option == JOptionPane.NO_OPTION) {
              //     //return;
              // }


            }




        }
    }

    public void createTable(Object eventHandler) {

    }















    public void getCreateTableValuesAndCreateTable(String entityCreator,String getText,String info){
        String getDatabaseName;
        String getrootName;
        String getPassword;

        final String create=entityCreator+" " +  getText;
        System.out.print(getText + create);

        try{
            Class.forName("com.mysql.jdbc.Driver");

            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","0000");

            statement=connection.createStatement();

            statement.executeUpdate(create);
            sqlControl=true;




        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"You have an error in your JDC syntax;" +
                    "Class not found");

            e.printStackTrace();
            return;

        } catch (SQLException e) {
            infoLabel.setText(info);
            JOptionPane.showMessageDialog(null,"You have an error in your SQL syntax;" +
                    " check the manual that corresponding manual ");
            sqlControl=false;
            return;


        }

}



public void getInsertionValues(){
    final String texValues=object.createShowUsertext.getText();

    final String createTable="INSERT INTO " +  texValues;
    System.out.print(texValues + createTable);

    try{
        Class.forName("com.mysql.jdbc.Driver");

        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop", "root", "0000");

        statement=connection.createStatement();

        statement.executeUpdate(createTable);




    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }

}







}

