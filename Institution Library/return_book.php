<?php 
	require('config/db.php');

	$stid = $_POST['st_id'];
	$kitabu = $_POST['bk_id'];

	//create the query
	$query = "select * from student where student_id = '$stid'";

	$result = mysqli_query($con, $query)or die(mysqli_error());

	$persons = mysqli_fetch_all($result, MYSQLI_ASSOC);
	
	$rows = mysqli_num_rows($result);

	if($rows > 0){
		$query2 = mysqli_query($con, "select * from book where book_id = '$kitabu'");
		$fetch2 = mysqli_fetch_assoc($query2);
		$status = $fetch2['status'];

		//return an item
		if($status == 0){
			
			$query3 = mysqli_query($con,"update book set status = '1', student_id='0', date_of_issue='0000-00-00', date_of_return='0000-00-00' where book_id = '$kitabu' ") or die(mysqli_error());
			
			if($query3){

				echo "book returned";

			}else{

				echo "Book return failed";

			}
		}

	}else{
		echo "student does not exist";
	}

	

	//close the connection
	mysqli_close($con);

 ?>