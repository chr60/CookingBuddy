<?php
	require_once __DIR__ . '/db_connect.php';
	$response = array();

	if(isset($_POST['name']) && isset($_POST['password'])){
		$name = $_POST['name'];
		$password = $_POST['password'];
		
		
		$db = connect();
		$sql = "INSERT INTO users(username, password) VALUES ('$name', '$password')";
		$result = $db->query($sql);

		if($result){
			$response["success"] = 1;
	    $response["message"] = "recipe successfully added.";
		}
		else{
			$response["success"] = 0;
	    $response["message"] = "Oops! An error occurred while adding";
	    trigger_error('query error'.$db->error);
		}
	}
	else{
		$response["success"] = 0;
	  $response["message"] = "Required field(s) is missing";
	}

	echo json_encode($response);
	$db->close();
?>