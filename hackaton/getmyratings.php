<html>
	<head>
		
		<meta http-equiv="refresh" content="2">
		<style>
			body{margin:0;padding:0;}
			table{margin:0;}
		</style>
	</head>
<body>
<?php

	include('dbconn.php');
	
	if(!empty($_GET['judge_id'])){
		$judge_id=$_GET['judge_id'];
		
		$row1=getLastJudgeUpdate(1,$judge_id);
		$row2=getLastJudgeUpdate(2,$judge_id);
		$row3=getLastJudgeUpdate(3,$judge_id);
		
		
		$g1_total=$row1['c1']+$row1['c2']+$row1['c3'];
		$g2_total=$row2['c1']+$row2['c2']+$row2['c3'];
		$g3_total=$row3['c1']+$row3['c2']+$row3['c3'];
		
		
		
		echo "<table style=' width:100%;border-collapse:collapse;border:1px solid #c0c0c0'>";
			echo "<tr style=border:1px solid #c0c0c0'>";
				echo "<td>&nbsp;</td>";
				echo "<td colspan=3 style='text-align:center;border:1px solid #c0c0c0;padding:10px'>Group Names</td>";				
				
			echo '</tr>';
			echo "<tr style='border:1px solid #c0c0c0;padding:10px;background-color:#addce6'>";
				echo "<td style='border:1px solid #c0c0c0;padding:10px'>CRITERIA</td>";
				echo "<td style='background-color:#7CFC00;text-align:center;border:1px solid #c0c0c0;padding:10px'>0-wan-0</td>";
				echo "<td style='background-color:#7CFC00;text-align:center;border:1px solid #c0c0c0;padding:10px'>NotToday</td>";
				echo "<td style='background-color:#7CFC00;text-align:center;border:1px solid #c0c0c0;padding:10px'>Team Ex and Whys</td>";
				
			echo '</tr>';
			echo "<tr >";;
				echo "<td style='border:1px solid #c0c0c0;padding:10px'>Uniqueness</td>";
				echo "<td style='text-align:center;border:1px solid #c0c0c0;padding:10px'>".$row1['c1']."%</td>";
				echo "<td style='text-align:center;border:1px solid #c0c0c0;padding:10px'>".$row2['c1']."%</td>";
				echo "<td style='text-align:center;border:1px solid #c0c0c0;padding:10px'>".$row3['c1']."%</td>";
			echo '</tr>';
			echo "<tr >";;
				echo "<td style='border:1px solid #c0c0c0;padding:10px'>CLOSE TO `PRODUCT`</td>";
				echo "<td style='text-align:center;border:1px solid #c0c0c0;padding:10px'>".$row1['c2']."%</td>";
				echo "<td style='text-align:center;border:1px solid #c0c0c0;padding:10px'>".$row2['c2']."%</td>";
				echo "<td style='text-align:center;border:1px solid #c0c0c0;padding:10px'>".$row3['c2']."%</td>";
			echo '</tr>';
			echo "<tr >";;
				echo "<td style='border:1px solid #c0c0c0;padding:10px'>CREATIVITY</td>";
				echo "<td style='text-align:center;border:1px solid #c0c0c0;padding:10px'>".$row1['c3']."%</td>";
				echo "<td style='text-align:center;border:1px solid #c0c0c0;padding:10px'>".$row2['c3']."%</td>";
				echo "<td style='text-align:center;border:1px solid #c0c0c0;padding:10px'>".$row3['c3']."%</td>";
			echo '</tr>';
			echo "<tr >";;
				echo "<td style='border:1px solid #c0c0c0;padding:10px'>TOTAL</td>";
				echo "<td style='text-align:center;border:1px solid #c0c0c0;padding:10px'>".$g1_total."%</td>";
				echo "<td style='text-align:center;border:1px solid #c0c0c0;padding:10px'>".$g2_total."%</td>";
				echo "<td style='text-align:center;border:1px solid #c0c0c0;padding:10px'>".$g3_total."%</td>";
			echo '</tr>';
		echo '</table>';
	}
?>
</body>
</html>