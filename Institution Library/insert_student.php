<?php
 require('config/db.php');

//if(isset($_POST['save'])){	 
	 
	 $student_id = $_POST['id'];
	 $fn = $_POST['first_name'];
	 $sn = $_POST['surname'];
	 $course = $_POST['course'];
	 $b = $_POST['branch'];
	 $y = $_POST['year'];
	 $s = $_POST['sem'];

	 $sql = "INSERT INTO student (student_id, first_name, surname, course, branch, year, semester)
	 VALUES ('$student_id', '$fn', '$sn', '$course','$b', '$y', '$s')";
	
	 if (mysqli_query($con, $sql)) {

		echo "New record created successfully !";

	 } else {

		echo "Error: " . $sql . mysqli_error($con);
	 }

	 mysqli_close($con);
//}
?>