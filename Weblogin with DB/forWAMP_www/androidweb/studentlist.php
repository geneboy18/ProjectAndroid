<?php

	$hostname='localhost';
	$username='root';
	$password='';
	$database='ictcongress';
	$sql="SELECT * FROM tbl_student ORDER BY familyname";
	
	$conn=mysql_connect($hostname,$username,$password) or die('server not available');
	$db=mysql_select_db($database) or die('Database not available');
	
	$query=mysql_query($sql) or die('SQL error');
	echo "<table width=100% style='border-collapse:collapse;border:1px solid #c0c0c0;'>";
	while($row=mysql_fetch_array($query)){
		echo '<tr>';
			echo "<td style='padding:5;border:1px solid #c0c0c0'>".$row['idno'].'</td>';
			echo "<td style='border:1px solid #c0c0c0'>&nbsp;".$row['familyname'].'</td>';
			echo "<td style='border:1px solid #c0c0c0'>&nbsp;".$row['givenname'].'</td>';
			echo "<td style='border:1px solid #c0c0c0'>".$row['course'].'</td>';
			echo "<td style='border:1px solid #c0c0c0'>&nbsp;".$row['yearlevel'].'</td>';
			echo "<td style='border:1px solid #c0c0c0'>&nbsp;".$row['campus'].'</td>';
			
			$mycheck=($row['attended']==0)?"<input type='checkbox' name='attended'>":"<input type='checkbox' name='attended' checked>";
			
			echo "<td  align='center' style='border:1px solid #c0c0c0' >&nbsp;".$mycheck.'</td>';
		echo '<tr>';
	}
	echo '</table>';
	mysql_close($conn);
?>