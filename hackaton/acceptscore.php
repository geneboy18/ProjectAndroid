<?php
	include('dbconn.php');
	////// accept judge scores
	if(!empty($_GET['c1']) && !empty($_GET['c2']) && !empty($_GET['c3'])){
		$group_id=$_GET['group_id'];
		$judge_id=$_GET['judge_id'];
		
		$c1=$_GET['c1'];
		$c2=$_GET['c2'];
		$c3=$_GET['c3'];
		
		$ok=acceptScore($group_id,$judge_id,$c1,$c2,$c3);
		$message=($ok)?"Score Accepted":"Error Send";
		echo $message;
	} else echo 'Select Score for each Criteria';

?>