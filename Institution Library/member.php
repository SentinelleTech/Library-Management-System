<?php 
	require('config/db.php');

	$stid = $_POST['st_id'];

	//create the query
	$query = "select student_id from student where student_id = '$stid'   ";

	$result = mysqli_query($con, $query);

	$person = mysqli_fetch_all($result, MYSQLI_ASSOC);
	//var_dump($vitabu);
	echo json_encode($person);

	mysqli_free_result($result);

	//close the connection
	mysqli_close($con);

 ?>