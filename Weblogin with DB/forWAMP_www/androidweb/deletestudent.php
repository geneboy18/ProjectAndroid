<?php	//scriptlet tag

	//this script, accepts querystring data(add-on data on the URL)
	//check if there are actually arriving from the client
	
	if(!empty($_GET['idno'])){
	
		$idno=$_GET['idno'];
		
		$hostname='localhost';
		$username='root';
		$password='';
		//database name
		$database='ictcongress';
		//query variable
		
		$sql="DELETE FROM tbl_student where idno = '$idno'";
		//connect your php program to the database server
		$conn=mysql_connect($hostname,$username,$password) or die('server not available');
		//select a database at the database server
		
		$db=mysql_select_db($database) or die('Database not available');
		//access a database table in the selected database
		$query=mysql_query($sql) or die('SSQL Error');
		
		
		mysql_close($conn);
			echo 'Student Successfully Deleted'; //send reply to client
		}
		else echo 'Please fill all fields'; //send reply to client
	
?>

