<?php	//scriptlet tag

	//this script, accepts querystring data(add-on data on the URL)
	//check if there are actually arriving from the client
	
	if(!empty($_GET['idno']) && !empty($_GET['lname'])){
	
		$idno=$_GET['idno'];
		$fname=$_GET['fname'];
		$lname=$_GET['lname'];	
		$course=$_GET['course'];
		$year=$_GET['year'];
		$campus=$_GET['campus'];	
		
		$hostname='localhost';
		$username='root';
		$password='';
		//database name
		$database='ictcongress';
		//query variable
		
		$sql="INSERT into tbl_student(idno,familyname,givenname,course,yearlevel,campus) values('$idno','$lname','$fname','$course','$year','$campus')";
		//connect your php program to the database server
		$conn=mysql_connect($hostname,$username,$password) or die('server not available');
		//select a database at the database server
		
		$db=mysql_select_db($database) or die('Database not available');
		//access a database table in the selected database
		$query=mysql_query($sql) or die('SSQL Error');
		
		
		mysql_close($conn);
			echo 'New Student added'; //send reply to client
		}
		else echo 'Please fill all fields'; //send reply to client
	
?>

