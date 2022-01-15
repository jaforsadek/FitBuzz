<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

  //Importing our db connection script
  require_once('connection.php');


    //Getting values
    $name = $_POST['name'];
    $email = $_POST['email'];
    $pass = $_POST['pass'];


  

    //Creating an sql query
    $sql = "INSERT INTO user (name,email,password) VALUES ('$name','$email','$pass')";


    //Executing query to database
    if (mysqli_query($con, $sql)) {
        echo 'User Added Successfully';
    } else {
        echo 'Could Not Add User';
    }

    //Closing the database 
    mysqli_close($con);
}


?>