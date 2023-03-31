<?php
  require('config/db.php');

  $bookID = $_POST['bkid'];
  $bookName =$_POST['bkname'];
  $bookEdition = $_POST['ed'];
  $pub = $_POST['pub'];
  $status = $_POST['s'];
  
  
  $item_exists = mysqli_query($con,"select book_id from book where book_id = '$bookID'") or die(mysqli_error());
  $rows = mysqli_num_rows($item_exists);
  
  if($rows > 0){
	 
   echo "Item exists";  

  } else{
    $query = "insert into book(book_id, name, edition, publisher, status)  values('$bookID','$bookName', '$bookEdition', '$pub', '$status')";
    $insert = mysqli_query($con,$query) or die(mysqli_error());
  
    if($insert){

	   echo "Data Inserted";

    }else{

	   echo "Data Insert Failed";
     
    }
  }
?>