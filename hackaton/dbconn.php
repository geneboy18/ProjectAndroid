<?php
	//////////////
	$hostname='localhost';
	$username='root';
	$password='';
	$database='hackaton';
	$conn;
	
	function connect(){
		global $hostname,$username,$password,$database,$conn;
		try{
			$conn=new PDO("mysql:host=$hostname;dbname=$database",$username,$password);
			$conn->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
		}catch(PDOException $e){
			echo $e->getMessage();
		}		
	}
	
	
	function acceptScore($group_id,$judge_id,$c1,$c2,$c3){
		global $conn;
		$ok=false;
		$data=array($group_id,$judge_id,$c1,$c2,$c3);
		$sql="INSERT INTO tbl_scoresheet(group_id,judge_id,c1,c2,c3) VALUES(?,?,?,?,?)";
		try{
			connect();
			$stmt=$conn->prepare($sql);
			$stmt->execute($data);
			$ok=true;
		}
		catch(PDOException $e){
			echo $e->getMessage();
		}		
		return $ok;
	}
	
	function showScores(){
		global $conn;
		$sql="SELECT * FROM tbl_scoresheet";
		$data=array();
		try{
			connect();
			$stmt=$conn->prepare($sql);
			$stmt->execute();
			while(($row=$stmt->fetchAll(PDO::FETCH_ASSOC))!=null){				
				$data[]=$row;
			}
			
		}catch(PDOException $e){
			echo $e->getMessage();
		}		
	}
	
	function getLastJudgeUpdate($group_id,$judge_id){
		global $conn;
		$row;
		$sql="SELECT group_id,judge_id,c1,c2,c3 FROM tbl_scoresheet WHERE group_id=? AND judge_id=? ORDER BY score_id DESC LIMIT 1";
		try{
			connect();
			$stmt=$conn->prepare($sql);
			$stmt->execute(array($group_id,$judge_id));
			$row=$stmt->fetch(PDO::FETCH_ASSOC);
		}catch(PDOException $e){
			echo $e->getMessage();
		}
		return $row;
	}
	
?>