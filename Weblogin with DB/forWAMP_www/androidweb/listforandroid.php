<?php
//for android client
	//database preliminaries
	
	$hostname='localhost';
	$username='root';
	$password='';
	//database name
	$database='ictcongress';
	//query variable
	$sql="SELECT * FROM tbl_student ORDER BY familyname";
	//connect your php program to the database server
	$conn=mysql_connect($hostname,$username,$password) or die('server not available');
	//select a database at the database server
	
	$db=mysql_select_db($database) or die('Database not available');
	//access a database table in the selected database
	$query=mysql_query($sql) or die('SSQL Error');
	
	$container['student']=array();
	while($row=mysql_fetch_array($query)){
		$item=array();
		$item['idno']=$row['idno'];
		$item['familyname']=$row['familyname'];
		$item['givenname']=$row['givenname'];
		$item['course']=$row['course'];
		$item['yearlevel']=$row['yearlevel'];
		$item['campus']=$row['campus'];		
		array_push($container["student"],$item);
	}
	echo json_encode($container);	
	mysql_close($conn);
?>