package Model;
import java.sql.*; 
public class Buyer 
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.cj.jdbc.Driver"); 
 con = 
 DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", ""); 
 } 
 catch (Exception e) 
 { 
 e.printStackTrace(); 
 } 
 return con; 
 } 
public String readbuyer()
{ 
 String output = ""; 
try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for reading."; 
 } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Item Code</th>" 
 + "<th>Item Name</th><th>Item Price</th>"
 + "<th>Update</th><th>Remove</th></tr>"; 
 String query = "select * from buyer"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String purchaseID = Integer.toString(rs.getInt("purchaseID")); 
 String userID = rs.getString("userID"); 
 String productID = rs.getString("productID"); 
 String quantity = Double.toString(rs.getDouble("quantity")); 

// Add into the html table
 output += "<tr><td>" + userID + "</td>"; 
 output += "<td>" + productID + "</td>"; 
 output += "<td>" + quantity + "</td>"; 
 
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-purchaseID='" + purchaseID + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-purchaseID='" + purchaseID + "'></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
catch (Exception e) 
 { 
 output = "Error while reading the buyer."; 
 System.err.println(e.getMessage()); 
 } 
return output; 
}
public String insertItem(String userID, String productID, 
 String quantity) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for inserting."; 
 } 
 // create a prepared statement
 String query = " insert into buyer (`purchaseID`,`userID`,`productID`,`quantity`)"+ " values (?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, userID); 
 preparedStmt.setString(3, productID); 
 preparedStmt.setString(4, quantity); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 String newbuyer = readbuyer(); 
 output = "{\"status\":\"success\", \"data\": \"" + newbuyer + "\"}"; 
 } 
 catch (Exception e) 
 { 
 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String updateItem(String ID, String user, String product, 
 String quan) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for updating."; 
 } 
 // create a prepared statement
 String query = "UPDATE buyer SET userID=?,productID=?,quantity=? WHERE purchaseID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, user); 
 preparedStmt.setString(2, product); 
 preparedStmt.setString(4, quan); 
 preparedStmt.setInt(5, Integer.parseInt(ID)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 String newbuyer = readbuyer(); 
 output = "{\"status\":\"success\", \"data\": \"" + 
 newbuyer + "\"}"; 
 } 
 catch (Exception e) 
 { 
 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String deleteItem(String purchaseID) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for deleting."; 
 } 
 // create a prepared statement
 String query = "delete from buyer where purchaseID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(purchaseID)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 String newbuyer = readbuyer(); 
 output = "{\"status\":\"success\", \"data\": \"" + newbuyer + "\"}"; 
 } 
 catch (Exception e) 
 { 
 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
}