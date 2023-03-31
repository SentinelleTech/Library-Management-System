<?php
require('config/db.php');

$stid = $_POST['st_id'];
$kitabu = $_POST['bk_id'];
$return_date = date('y-m-d', strtotime('+ 3 days'));
$date = date('y-m-d', strtotime('- 1 days'));

$query = "select * from student where student_id = '$stid'";
$fetch = mysqli_query($con,$query);
$rows = mysqli_num_rows($fetch);

if($rows > 0){
$query2 = mysqli_query($con,"select * from book where book_id ='$kitabu'");
$fetch2 = mysqli_fetch_assoc($query2);
$status = $fetch2['status'];

	//return an item
	if($status == 0){
		$query3 = mysqli_query($con,"update book set status = '1',student_id='',
		                       date_of_issue='',date_of_return=''");
		if($query3){

			echo json_encode($query3);

			echo "Items returned";
		}else{
			echo "Item return failed";
		}
	}
	//Assign an item
	else{
		$query3 = mysqli_query($con,"update items set status = '0', student_id='$stid',
		                       date_of_issue='$date', date_of_return='$return_date'");
		if($query3){

			echo json_encode($query3);

			echo "Items Assigned";
		}else{
			echo "Item Assign failed";
		}
	}
}else{
	echo "student does not exist";
}
?>