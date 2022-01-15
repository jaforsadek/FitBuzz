<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {


    //Importing our db connection script
    require_once('connection.php');

    //Getting values
    $name = $_POST['name'];
    $pass = $_POST['pass'];


    //Creating an sql query
    $sql = "SELECT * FROM user WHERE name ='$name' AND password = '$pass' ";


    //Executing query to database
    $row = mysqli_query($con, $sql);

    $count = mysqli_num_rows($row);

    if ($count > 0)
        echo "Login Successful";
        
    else
        echo "Invalied Information";

    //Closing the database 
    mysqli_close($con);
}

?>