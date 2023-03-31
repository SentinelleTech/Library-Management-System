<?php 
	require('config/db.php');

	$kitabu = $_POST['bk_id'];

	//create the query
	$query = "select * from book where book_id = '$kitabu'";

	$result = mysqli_query($con, $query) or die(mysqli_error());

	$vitabu = mysqli_fetch_all($result, MYSQLI_ASSOC);
	//var_dump($vitabu);
	echo json_encode($vitabu);
	
	mysqli_free_result($result);

	//close the connection
	mysqli_close($con);

 ?>