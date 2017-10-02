/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.jdbc.EmbeddedDriver;

public class DatabaseParties {

   public static void main(String[] args) {
  /*    DatabaseParties e =
         new DatabaseParties();
      String name="Party 2";
      String details="Content ";
      e.testDerby(name, details);
   */}
   public void addDerby(String name,String details) {
      Connection conn = null;
      PreparedStatement pstmt;
      Statement stmt;
      ResultSet rs = null;
/*      String createSQL = "create table person ("
      + "id integer not null generated always as"
      + " identity (start with 1, increment by 1),   "
      + "name varchar(30) not null, email varchar(30),phone varchar(10),"
      + "constraint primary_key primary key (id))";
*/
   //   String createSQL ="create table parties (name varchar(30), text varchar(500))"; 
      try {
         Driver derbyEmbeddedDriver = new EmbeddedDriver();
         DriverManager.registerDriver(derbyEmbeddedDriver);
         conn = DriverManager.getConnection("jdbc:derby:testdb1;create=true", "root","root");
         conn.setAutoCommit(false);
         stmt = conn.createStatement();
     //    stmt.execute(createSQL);

         pstmt = conn.prepareStatement("insert into parties values(?,?)");
         pstmt.setString(1, name);
         pstmt.setString(2,details);
         pstmt.executeUpdate();

         rs = stmt.executeQuery("select * from parties");
         while (rs.next()) {
            System.out.printf("%s %s \n",
            rs.getString(1), rs.getString(2));
         }

 //        stmt.execute("drop table person");

         conn.commit();

      } catch (SQLException ex) {
         System.out.println("in connection" + ex);
      }

      try {
         DriverManager.getConnection
            ("jdbc:derby:;shutdown=true");
      } catch (SQLException ex) {
         if (((ex.getErrorCode() == 50000) &&
            ("XJ015".equals(ex.getSQLState())))) {
               System.out.println("Derby shut down normally");
         } else {
            System.err.println("Derby did not shut down normally");
            System.err.println(ex.getMessage());
         }
      }
   }
  public String[] fetchNames(){
     Connection conn = null;
      PreparedStatement pstmt;
      Statement stmt;
      ResultSet rs = null;
      String[] fetch=new String[100];
      try {
         Driver derbyEmbeddedDriver = new EmbeddedDriver();
         DriverManager.registerDriver(derbyEmbeddedDriver);
         conn = DriverManager.getConnection("jdbc:derby:testdb1;create=true", "root","root");
         conn.setAutoCommit(false);
         stmt = conn.createStatement();
     //    stmt.execute(createSQL);
          int i=0;
         rs = stmt.executeQuery("select name from parties");
         while (rs.next()) {
            fetch[i]=rs.getString(1);
            i++;
         }

 //        stmt.execute("drop table person");

         conn.commit();

      } catch (SQLException ex) {
         System.out.println("in connection" + ex);
      }

      try {
         DriverManager.getConnection
            ("jdbc:derby:;shutdown=true");
      } catch (SQLException ex) {
         if (((ex.getErrorCode() == 50000) &&
            ("XJ015".equals(ex.getSQLState())))) {
               System.out.println("Derby shut down normally");
         } else {
            System.err.println("Derby did not shut down normally");
            System.err.println(ex.getMessage());
         }
      }
      return fetch;
  }
  public String[] fetchDetails(int n){
      Connection conn = null;
      Statement stmt;
      ResultSet rs = null;
      String s[] =new String[100] ;
      try {
         Driver derbyEmbeddedDriver = new EmbeddedDriver();
         DriverManager.registerDriver(derbyEmbeddedDriver);
         conn = DriverManager.getConnection("jdbc:derby:testdb1;create=true", "root","root");
         conn.setAutoCommit(false);
         stmt = conn.createStatement();
     //    stmt.execute(createSQL);

         rs = stmt.executeQuery("select details from parties");
         while(n>=0)
         {
         rs.next();
         n--;
         }
         s=rs.getString(1).split("\\n");
         

 //        stmt.execute("drop table person");

         conn.commit();

      } catch (SQLException ex) {
         System.out.println("in connection" + ex);
      }

      try {
         DriverManager.getConnection
            ("jdbc:derby:;shutdown=true");
      } catch (SQLException ex) {
         if (((ex.getErrorCode() == 50000) &&
            ("XJ015".equals(ex.getSQLState())))) {
               System.out.println("Derby shut down normally");
         } else {
            System.err.println("Derby did not shut down normally");
            System.err.println(ex.getMessage());
         }
      }
      return s;
  }
  public void deleteParty(int in){
      Connection conn = null;
      Statement stmt;
      try {
         Driver derbyEmbeddedDriver = new EmbeddedDriver();
         DriverManager.registerDriver(derbyEmbeddedDriver);
         conn = DriverManager.getConnection("jdbc:derby:testdb1;create=true", "root","root");
         conn.setAutoCommit(false);
         stmt = conn.createStatement();
     //    stmt.execute(createSQL);
ResultSet rs;
rs = stmt.executeQuery("select name from parties");
         while(in>=0)
         {
         rs.next();
         in--;
         }
         conn.commit();
         String name=rs.getString(1);
         
         stmt.executeUpdate("delete from parties where name= '"+name+"'");
          conn.commit();
 //        stmt.execute("drop table person");

         

      } catch (SQLException ex) {
         System.out.println("in connection" + ex);
      }

      try {
         DriverManager.getConnection
            ("jdbc:derby:;shutdown=true");
      } catch (SQLException ex) {
         if (((ex.getErrorCode() == 50000) &&
            ("XJ015".equals(ex.getSQLState())))) {
               System.out.println("Derby shut down normally");
         } else {
            System.err.println("Derby did not shut down normally");
            System.err.println(ex.getMessage());
         }
      }
  }
}