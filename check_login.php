<?php
  require_once __DIR__ . '/db_connect.php';
  $response = array();
  if(isset($_POST['name']) && isset($_POST['password'])){
      // Innitialize Variable
        
    $name = $_POST['name'];
    $password = $_POST['password'];
    $db = connect();
    // Query database for row exist or not
    $sql = "SELECT * FROM users WHERE username LIKE '$name' AND password LIKE '$password'";
    $result = $db->query($sql);
    $num_rows = $result->num_rows;
    if($num_rows > 0){
      $response["success"] = "1";
      $response["message"] = "user found";
    }
    else{
      $response["success"] = "0";
      $response["message"] = "Oops! An error occurred while adding";
      
    }
  }
  else{
    $response["success"] = "0";
    $response["message"] = "Required field(s) is missing";
  }
  echo json_encode($response);
  $db->close();

?>