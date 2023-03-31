<?php 
	require('config/db.php');

	$stid = $_POST['st_id'];
	$kitabu = $_POST['bk_id'];
	$return_date = date('y-m-d', strtotime('+ 3 days'));
	$date = date('y-m-d', strtotime('- 1 days'));

	//create the query
	$query = "select * from student where student_id = '$stid'";

	$result = mysqli_query($con, $query)or die(mysqli_error());

	$vitabu = mysqli_fetch_all($result, MYSQLI_ASSOC);
	
	$rows = mysqli_num_rows($result);

	if($rows > 0){
		$query2 = mysqli_query($con, "select * from book where book_id = '$kitabu'");
		$fetch2 = mysqli_fetch_assoc($query2);
		$status = $fetch2['status'];

		//return an item
		if($status == 1){
			
			$query3 = mysqli_query($con,"update book set status = '0',student_id='$stid', date_of_issue='$date',date_of_return='$return_date' where book_id = '$kitabu' ");

			if($query3){

				echo "book Issued";

			}else{

				echo "Book issue failed";

			}
		}
	}else{
		echo "student does not exist";
	}

	

	//close the connection
	mysqli_close($con);

 ?>