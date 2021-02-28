package com.company;

 import javax.swing.*;
 import javax.swing.table.DefaultTableModel;
 import java.sql.*;


         public class TitanicListing extends JFrame {


             private PreparedStatement getSurvivalByStatus;


             public TitanicListing() {


                 String[] columns = new String[]{
                                 "name", "gender", "age"
                         };


                 DefaultTableModel model = new DefaultTableModel();


                 model.setColumnIdentifiers(columns);


                 JTable table = new JTable(model);


                 //  model.addRow(new Object[]{"testName1", "testGender1", 24});
                 //  model.addRow(new Object[]{"testName2", "testGender2", 25});
                 // model.addRow(new Object[]{"testName3", "testGender3", 26});


                 this.add(new JScrollPane(table));
                 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 this.pack();
                 this.setVisible(true);


                 final String DB_DATABASE = "titanicmanifest";
                 final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_DATABASE;
                 final String DB_USER = "root";
                 final String DB_PASSWORD = "123Sanamre321";


                 try {
                     Class.forName("com.mysql.cj.jdbc.Driver");
                     Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


                     Statement stmt = con.createStatement();
                   //  String sqlQuery = "select name, gender, age from titanic WHERE survived = ?";
                     String sqlQuery = "select name, gender, age from titanic WHERE name like ? and survived = ?";
                     getSurvivalByStatus = con.prepareStatement(sqlQuery);




                        // ResultSet rs = stmt.executeQuery(sqlQuery);


                         getSurvivalByStatus.setInt(2,0); //survived
                         getSurvivalByStatus.setString(1,"%john%");


                         ResultSet rs = getSurvivalByStatus.executeQuery();


                         while (rs.next()) {


                             String name = rs.getString(1);
                             String gender = rs.getString(2);
                             Integer age = rs.getInt(3);


                            model.addRow(new Object[]{name, gender, age});


                         }


                     } catch (SQLException e) {
                         System.out.println(e.toString());

              } catch (Exception e) {




                    } finally {
                    }
             }
 }
