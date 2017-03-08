<?php

	if(!empty($_GET['username']) && !empty($_GET['password'])){
		$username=$_GET['username'];
		$password=$_GET['password'];
		
		$message=(($username=='admin') && ($password=='ccs'))?"Login Accepted":"Login Failed";
		
		echo $message;
	}
	
	else echo 'Login Properly';
?>