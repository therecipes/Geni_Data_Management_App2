package com.company;

import com.company.GeniEngine.Engine;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by ayettey on 03/10/2016.
 */
public class GeniLayout extends JPanel {
    protected JPanel componentsPanel;
    protected JMenuBar menuBar;
    protected JMenu[] menu;
    public JMenuItem[] menuItem;
    public JMenuItem[] subMenuItem;
    protected JTable table;
    protected ArrayList column;
    public GridBagConstraints constraints;
    public GridBagLayout layout;
    protected ArrayList columnNames;
    protected ArrayList rowData;
    protected  DefaultTableModel model;
    protected  JMenu[] subMenu;
    public JButton create[] =new JButton[2];
    public JTextField createShowUsertext=new JTextField();
    private JLabel adminLables;
    private JPanel cabinetPanel;
   // public JPanel workBenchPanel;
    public JLabel craeteAndUpdateLable=new JLabel();
    int col;

    int rows;
    private JTextField entityValue;
    private JComboBox filters;
    private JTextField locatorAValue;
    private JComboBox logicValues;
    private JTextField equalsToSystem;
    private JComboBox filtersBY;
    private JTextField locatorAValueBY;
    public JButton searcher;
    public JPanel workBenchPanel=new JPanel();
    public JLabel infoLable;

    Engine engine=new Engine(this);
    GeniFrame frame;


    public GeniLayout(GeniFrame frame) {


        this.frame=frame;


        create[0]=new JButton("Create");

        buildFilters();
        buildTable();
        buildMenuBarLayout();
        workBenchPanel=new JPanel();
        buildEditWorkBench();
        //createTable();



    }

    public void buildMenuBarLayout() {


        //create menu bar and items
        menuBar = new JMenuBar();
        menuBar.getAccessibleContext().setAccessibleName("Items Bar");

        subMenu=new JMenu[2];
        subMenuItem=new JMenuItem[3];
        menu = new JMenu[2];

        menu[0] = new JMenu("Files");
        menu[0].setMnemonic(KeyEvent.VK_A);
        menu[0].getAccessibleContext().setAccessibleDescription("List of items");


        menu[1] = new JMenu("Edit");
        menu[1].setMnemonic(KeyEvent.VK_B);
        menu[1].getAccessibleContext().setAccessibleDescription("Edit");


        menuItem = new JMenuItem[7];
        menuItem[0] = new JMenuItem("Create Database");
        //menuItem[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem[0].getAccessibleContext().setAccessibleDescription("Create Database");


        menuItem[1] = new JMenuItem("Show Databases");
       // menuItem[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem[1].getAccessibleContext().setAccessibleDescription("Show Databases");

        menuItem[2] = new JMenuItem("Use Databases", KeyEvent.VK_D);
       // menuItem[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
        menuItem[2].getAccessibleContext().setAccessibleDescription("Use Databases");

        menuItem[3] = new JMenuItem("Create Table", KeyEvent.VK_E);
       // menuItem[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.SHIFT_MASK));
        menuItem[3].getAccessibleContext().setAccessibleDescription("Create Table");

        menuItem[4] = new JMenuItem("Alter Table", KeyEvent.CTRL_MASK);
        menuItem[4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menuItem[4].getAccessibleContext().setAccessibleDescription("Alter Table");

        menuItem[5] = new JMenuItem("Update Table", KeyEvent.CTRL_MASK);
        menuItem[5].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menuItem[5].getAccessibleContext().setAccessibleDescription("Update Table");

        menuItem[6] = new JMenuItem("Insert Into", KeyEvent.CTRL_MASK);
        menuItem[6].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menuItem[6].getAccessibleContext().setAccessibleDescription("Insert Into");




        subMenu[0]=new JMenu("Explain");
        subMenu[0].setMnemonic(KeyEvent.VK_S);

        subMenuItem[0] = new JMenuItem("Table", KeyEvent.CTRL_MASK);
        subMenuItem[0] .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        subMenuItem[0] .getAccessibleContext().setAccessibleDescription("Items");
        subMenu[0] .add(subMenuItem[0]);


        subMenu[1]=new JMenu("Drop");
        subMenu[1].setMnemonic(KeyEvent.VK_S);

        subMenuItem[1]=new JMenuItem("Database",KeyEvent.VK_A);
        subMenuItem[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.SHIFT_MASK));

        subMenuItem[2]=new JMenuItem("Table",KeyEvent.VK_A);
        subMenuItem[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.SHIFT_MASK));

        subMenu[1].add(subMenuItem[1]);
        subMenu[1].add(subMenuItem[2]);

        for(int i=0;i<subMenuItem.length;i++){
            subMenuItem[i].addActionListener(engine);
        }


        for (int i = 0; i < menuItem.length; i++) {



        }

              for (int i = 0; i < menuItem.length; i++) {

                  menu[1].add(menuItem[i]);
                  menuItem[i].addActionListener(engine);

        }







        for (int i = 0; i < menu.length; i++) {

            menuBar.add(menu[i]);
        }

        for(int x=0;x<subMenu.length;x++){
           menu[1].add(subMenu[x]);

       }
        frame.frame.setJMenuBar(menuBar);
     //
    }


    public void buildTable() {

        //create layout
        constraints = new GridBagConstraints();
        layout = new GridBagLayout();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 10, 5, 10);

        componentsPanel = new JPanel();
        componentsPanel.setLayout(layout);
        componentsPanel.setBackground(new Color(94, 200, 255));
        //componentsPanel.setPreferredSize(new Dimension(400,400));


        constraints.gridx = 0;


        model = new DefaultTableModel();




        columnNames= new ArrayList();
        rowData = new ArrayList();




            try {


                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Connecting......");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/information_schema", "root", "0000");

                Statement stmt = connection.createStatement();
                String sql = "SELECT *FROM information_schema.COLLATIONS ";

                ResultSet set=stmt.executeQuery(sql);

                ResultSetMetaData metaData=set.getMetaData();


                int columnCount=metaData.getColumnCount();

                for(int i=1;i<=columnCount;i++){
                    model.addColumn(metaData.getColumnName(i));
                }



                while (set.next()) {
                    ArrayList row=new ArrayList(columnCount);
                    for (int i = 1; i <= columnCount; i++) {
                        row.add(set.getObject(i));



                    }
                    rowData.add(row);


                }

                for (int i = 0; i < rowData.size(); i++) {
                    ArrayList temp=(ArrayList)rowData.get(i);
                    Vector subtemp=new Vector();

                    for(int x=0;x<temp.size();x++){
                        subtemp.add(temp.get(x));



                    }
                    model.addRow(subtemp);



                }



            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        //  Create table with database data
            table = new JTable(model);
           // table.setLayout( new GridLayout(2,2));
            //table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);





















        //table=new JTable(model);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(1364, 430));
        layout.setConstraints(pane, constraints);
        add(pane);

        }



    public void buildFilters(){

        adminLables=new JLabel();
        cabinetPanel=new JPanel();


        constraints = new GridBagConstraints();
        layout = new GridBagLayout();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 10, 5, 10);

        constraints.gridx=0;
        adminLables=new JLabel("Predicate",JLabel.LEFT);
        adminLables.setFont(new Font("", Font.CENTER_BASELINE, 12));
        layout.setConstraints(adminLables, constraints);


        cabinetPanel.add(adminLables);

        constraints.gridx=1;
        entityValue= new JTextField();
        entityValue.setPreferredSize(new Dimension(160,20));
        layout.setConstraints( entityValue, constraints);
        cabinetPanel.add( entityValue);

        constraints.gridx=2;
        adminLables=new JLabel("Filter",JLabel.LEFT);
        adminLables.setFont(new Font("", Font.CENTER_BASELINE, 12));
        layout.setConstraints(adminLables, constraints);
        cabinetPanel.add(adminLables);


        final String sort[]={"WHERE","ORDER BY ","GROUP BY","FORCE","FOR UPDATE","LEFT JOIN",
                "LEFT","AS","HAS","IS","HAVE","UNION"};
        constraints.gridx=5;
        filters= new JComboBox();
        for(int i=0;i<sort.length;i++){
            filters.addItem(sort[i]);
        }

        layout.setConstraints(filters, constraints);
        cabinetPanel.add(filters);


        constraints.gridx=6;
        locatorAValue= new JTextField();
        locatorAValue.setPreferredSize(new Dimension(100,20));
        layout.setConstraints(locatorAValue, constraints);
        cabinetPanel.add(locatorAValue);

        constraints.gridx=7;
        String [] logicSymbol={"<>",">","<","=","<=",">="};
        logicValues=new JComboBox();
        for(int i=0;i<logicSymbol.length;i++){
            logicValues.addItem(logicSymbol[i]);
        }

        layout.setConstraints(logicValues, constraints);
        cabinetPanel.add(logicValues);

        constraints.gridx=8;
        equalsToSystem= new JTextField();
        equalsToSystem.setPreferredSize(new Dimension(20, 20));
        layout.setConstraints(equalsToSystem, constraints);
        cabinetPanel.add(equalsToSystem);


        constraints.gridx=9;
        adminLables=new JLabel("Sort",JLabel.LEFT);
        adminLables.setFont(new Font("", Font.CENTER_BASELINE, 12));
        layout.setConstraints(adminLables, constraints);
        cabinetPanel.add(adminLables);

        constraints.gridx=10;
        filtersBY= new JComboBox();

        for(int i=0;i<sort.length;i++){
            filtersBY.addItem(sort[i]);
        }

        layout.setConstraints(filtersBY, constraints);
        cabinetPanel.add(filtersBY);

        constraints.gridx=11;
        locatorAValueBY= new JTextField();
        locatorAValueBY.setPreferredSize(new Dimension(100, 20));
        layout.setConstraints(locatorAValueBY, constraints);
        cabinetPanel.add(locatorAValueBY);

        constraints.gridx=12;
        searcher=new JButton("Find");
        layout.setConstraints(searcher, constraints);
        cabinetPanel.add(searcher);
        searcher.addActionListener(engine);

        add(cabinetPanel);


    }

    public void buildEditWorkBench(){







        TitledBorder title=new TitledBorder("Work Bench");
        workBenchPanel.setBorder(title);
        workBenchPanel.setPreferredSize(new Dimension(1364, 200));
        workBenchPanel.setBackground(new Color(0xC0C0C0));
        // frame.frame.pack();
       //workBenchPanel.validate();

        add(workBenchPanel);
        //frame.frame.setSize(7100, 7600);




    }


  //public void showDatabases(){
    //
    //
  //    constraints = new GridBagConstraints();
  //    layout = new GridBagLayout();
    //
  //    workBenchPanel.setLayout(layout);
  //    constraints.anchor = GridBagConstraints.WEST;
  //    constraints.insets = new Insets(5, 10, 5, 10);
    //
    //
    //
  //    constraints.gridy=0;
  //    adminLables.setText("Show Database");
  //    layout.setConstraints(adminLables,constraints);
  //    workBenchPanel.add(adminLables);
    //
    //
  //    constraints.gridy=1;
  //    createShowUsertext.setPreferredSize(new Dimension(200,30));
  //    layout.setConstraints(createShowUsertext,constraints);
  //    workBenchPanel.add(createShowUsertext);
    //
    //
  //    constraints.gridy=3;
  //    create[0].setText("Show");
  //    layout.setConstraints(create[0],constraints);
  //    workBenchPanel.add(create[0]);
    //
    //
  //    frame.frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    //
    //
    //
    //
  //    //workBenchPanel.add(subWorkBenchPanel);
    //
    //
  //}
    //
  //public void showTable(){
    //
    //
  //    constraints = new GridBagConstraints();
  //    layout = new GridBagLayout();
    //
  //    workBenchPanel.setLayout(layout);
  //    constraints.anchor = GridBagConstraints.WEST;
  //    constraints.insets = new Insets(5, 10, 5, 10);
    //
    //
    //
  //    constraints.gridy=0;
  //    adminLables.setText("Show Table");
  //    layout.setConstraints(adminLables,constraints);
  //    workBenchPanel.add(adminLables);
    //
    //
  //    constraints.gridy=1;
  //    createShowUsertext.setPreferredSize(new Dimension(200,30));
  //    layout.setConstraints(createShowUsertext,constraints);
  //    workBenchPanel.add(createShowUsertext);
    //
    //
  //    constraints.gridy=3;
  //    create [0].setText("Show");
  //    layout.setConstraints(create[0],constraints);
  //    workBenchPanel.add(create[0]);
    //
    //
  //    frame.frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    //
    //
    //
    //
  //    //workBenchPanel.add(subWorkBenchPanel);
    //
    //
  //}
    //
  //public void createDatabase(){
    //
    //
  //    constraints = new GridBagConstraints();
  //    layout = new GridBagLayout();
    //
  //    workBenchPanel.setLayout(layout);
  //    constraints.anchor = GridBagConstraints.WEST;
  //    constraints.insets = new Insets(5, 10, 5, 10);
    //
    //
    //
  //    constraints.gridy=0;
  //    adminLables.setText("Show Table");
  //    layout.setConstraints(adminLables,constraints);
  //    workBenchPanel.add(adminLables);
    //
    //
  //    constraints.gridy=1;
  //    createShowUsertext.setPreferredSize(new Dimension(200,30));
  //    layout.setConstraints(createShowUsertext,constraints);
  //    workBenchPanel.add(createShowUsertext);
    //
    //
  //    constraints.gridy=3;
  //    create[0].setText("Show");
  //    layout.setConstraints(create[0],constraints);
  //    workBenchPanel.add(create[0]);
    //
    //
  //    frame.frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    //
    //
    //
    //
  //    //workBenchPanel.add(subWorkBenchPanel);
    //
    //
  //}
    //
  //public void useDatabase(){
    //
    //
  //    constraints = new GridBagConstraints();
  //    layout = new GridBagLayout();
    //
  //    workBenchPanel.setLayout(layout);
  //    constraints.anchor = GridBagConstraints.WEST;
  //    constraints.insets = new Insets(5, 10, 5, 10);
    //
    //
    //
  //    constraints.gridy=0;
  //    adminLables.setText("Show Table");
  //    layout.setConstraints(adminLables,constraints);
  //    workBenchPanel.add(adminLables);
    //
    //
  //    constraints.gridy=1;
  //    createShowUsertext.setPreferredSize(new Dimension(200,30));
  //    layout.setConstraints(createShowUsertext,constraints);
  //    workBenchPanel.add(createShowUsertext);
    //
    //
  //    constraints.gridy=3;
  //    create[0].setText("Show");
  //    layout.setConstraints(create[0],constraints);
  //    workBenchPanel.add(create[0]);
    //
    //
  //    frame.frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    //
    //
    //
    //
  //    //workBenchPanel.add(subWorkBenchPanel);
    //
    //
  //}
    //
    public void createTable(boolean on){

          if(on==true) {

              constraints = new GridBagConstraints();
              layout = new GridBagLayout();

             // WorkBenchPanel=new JPanel();
              workBenchPanel.setLayout(layout);

              constraints.anchor=GridBagConstraints.WEST;
              //constraints.insets=new Insets(5,10,5,10);
              constraints.gridy=0;

              adminLables.setText("Create Table");
              layout.setConstraints(adminLables,constraints);
              workBenchPanel.add(adminLables);

              constraints.gridy=1;

             // createShowUsertext.setFont(new Font("",Font.ITALIC,15));
             // createShowUsertext.setPreferredSize(new Dimension(700,60));
              layout.setConstraints(createShowUsertext,constraints);
              workBenchPanel.add(createShowUsertext);

              constraints.gridy=2;
              create[0].setText("Create");
              layout.setConstraints(create[0],constraints);
              workBenchPanel.add(create[0]);

             //workBenchPanel.add(subWorkBenchPanel);
              workBenchPanel.validate();

              // frame.frame.setExtendedState(Frame.MAXIMIZED_BOTH);


              //workBenchPanel.add(subWorkBenchPanel);
          }

    }

   // public void createInsertion(){
   //
   //
   //     constraints = new GridBagConstraints();
   //     layout = new GridBagLayout();
   //
   //     workBenchPanel.setLayout(layout);
   //     constraints.anchor = GridBagConstraints.WEST;
   //     constraints.insets = new Insets(5, 10, 5, 10);
   //
   //
   //
   //     constraints.gridy=2;
   //     //adminLables.setText("Insert Values");
   //    // layout.setConstraints(adminLables,constraints);
   //     //workBenchPanel.add(adminLables);
   //
   //
   //     constraints.gridy=3;
   //     createShowUsertext.setPreferredSize(new Dimension(800,90));
   //     createShowUsertext.setFont(new Font("",Font.CENTER_BASELINE,18));
   //
   //     layout.setConstraints(createShowUsertext,constraints);
   //     workBenchPanel.add(createShowUsertext);
   //
   //
   //     constraints.gridy=4;
   //     create[1].setText("Insert");
   //     layout.setConstraints(create[1],constraints);
   //     workBenchPanel.add(create[1]);
   //     create[1].setVisible(true);
   //     create[1].addActionListener(engine);
   //
   //
   //
   //
   //
   //
   //
   //     //workBenchPanel.add(subWorkBenchPanel);
   //
   //
   // }
   //
   //


    }





