package jdbc_training;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;



public class Employee {
	
	private String url = "jdbc:mysql://localhost:3306/jdbc_trainings";
	private String username = "root";
	private String password = "Mayur@8864";
	private Connection connection ;
	
	Employee(){
		try{		
			connection = DriverManager.getConnection(url, username, password);
		   }catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
  void  insert ()
	{
		 String sql = "INSERT INTO Employee ( name, age, email) VALUES ( ?, ?, ?)";
		 try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			 Scanner insert_obj = new Scanner(System.in);	
			 System.out.println("enter employee name");
			 String name = insert_obj.nextLine();
			 System.out.println("enter employee age");
			 int age = insert_obj.nextInt();
			 insert_obj.nextLine();
			 System.out.println("enter employee email");
			 String email = insert_obj.nextLine();
				
			 preparedStatement.setString(1,name); 
			 preparedStatement.setInt(2,age); 
			 preparedStatement.setString(3,email); 
	            
	         int rowsAffected = preparedStatement.executeUpdate();

	            System.out.println(rowsAffected + " row(s) inserted.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}
  
  void show(){
	  
	  System.out.println("\n");
	  
	  String sql ="SELECT * FROM EMPLOYEE;";
	  try {
		 Statement statement= connection.createStatement();
		  
		 ResultSet result= statement.executeQuery(sql);
		 System.out.printf("emp_id \t employee name \t age \t email \n");
		 while(result.next()){
			 
			 int id =result.getInt("emp_id");
			 String name= result.getString("name");
			 int age= result.getInt("age");
			 String email= result.getString("email");
			 
			 System.out.printf("%d \t %s \t %d \t %s \n",id,name,age,email);	 
		 }	  
	  }catch (Exception e) {
          e.printStackTrace();
      } 
	  
	  finally 
	  {
		  //statement.close();
    	  System.out.println("\n");
    	  /*if(statement != null) {
    		  statement.close();
    	  }*/
      }
	  
	  }
  
  void update () {
	  Scanner update_obj =new Scanner(System.in);
	  System.out.println("\nUpdate the data....");
	  System.out.print("\n 1.Name \n 2.Age \n 3.Email \n Choose what you want to update:");
	  int choice= update_obj.nextInt();
	  update_obj.nextLine();
	  System.out.println("\nplz enter employee email:");
	  String email= update_obj.nextLine();
	  try {
	  
	  switch(choice){
	  case 1:
		  String sqlName="update employee SET name=? WHERE email=?;";
		  PreparedStatement statementName = connection.prepareStatement(sqlName);
		  System.out.println("update the employee name:");
		  String name=update_obj.nextLine();
		  statementName.setString(1,name);
		  statementName.setString(2, email);
		  int nameUpdateAffected_row= statementName.executeUpdate();
		  if(nameUpdateAffected_row==1) {  
			  System.out.println("\nupdate successfully");
		  }
		  else {
			  System.out.println("\nupdate fail");
		  }
		  statementName.close();
		  break;
	  
	  case 2:
		  String sqlAge="UPDATE EMPLOYEE SET age= ? WHERE email=?";
		  PreparedStatement statementAge=connection.prepareStatement(sqlAge);
		  System.out.println("\nupdate the employee age:");
		  int age=update_obj.nextInt();
		  statementAge.setInt(1, age);
		  statementAge.setString(2,email);
		  int ageUpdateAffect_row=statementAge.executeUpdate();
		  if(ageUpdateAffect_row==1) {  
			  System.out.println("\nupdate successfully");
		  }
		  else {
			  System.out.println("\nupdate fail");
		  }
		  statementAge.close();
		  break;
	  
	  case 3:
		  String sqlEmail="UPDATE EMPLOYEE SET email=? WHERE email= ?;";
		  PreparedStatement statementEmail = connection.prepareStatement(sqlEmail);
		  System.out.println("update the employee Email:");
		  String update_email=update_obj.nextLine();
		  statementEmail.setString(1,update_email);
		  statementEmail.setString(2,email);
		  int emailUpdateAffect_row= statementEmail.executeUpdate();
		  if(emailUpdateAffect_row == 1) {  
			  System.out.println("\nupdate successfully");
		  }
		  else {
			  System.out.println("\nupdate fail");
		  }
		  statementEmail.close();
		  break;
	  default:
			System.out.println("plz check the options and try again");
			break;
	  }	
	  }catch(Exception e)
	  {
		e.printStackTrace();  
	  }
	  finally {
		  update_obj.close();
	  }
	  
  }
  
  void delete() {
	  
	  Scanner delete_obj=new Scanner(System.in);
	  System.out.println("plz enter employee email:");
	  String email=delete_obj.nextLine();
	  String sql="DELETE FROM EMPLOYEE WHERE EMAIL = ? ";
	  try {
		  PreparedStatement statement=connection.prepareStatement(sql);
		  
		  statement.setString(1,email);
		  
		  int affected_row = statement.executeUpdate();  
  }
	  catch( Exception e)
	  {
	  e.printStackTrace();
	  }
	  finally {
		  delete_obj.close();
	  }
	  }
  

  
  public static void main(String[] args) {
		
		Scanner sc_obj=new Scanner(System.in);
		
		Employee m=new Employee();
		
		System.out.print("Welcome to the employee management\n");
		
		System.out.print("\n 1.Create Employee Entry \n 2.Update Employee Entry \n 3.Show Employee Entrys \n 4.Delete Employee Entry \n Choose what you want to do:");
		int mainChoice = sc_obj.nextInt();
		
		switch(mainChoice) {
		case 1:
			m.insert();
			break;
		case 2:
			m.update();
			break;
		case 3:
			m.show();
			break;
		case 4:
			m.delete();
			break;
		default:
			System.out.println("plz check the options and try again");
			break;
			
		}	
		//m.show();
		
		
	}

}
