<?php
	require_once __DIR__ . '/db_connect.php';
	$response = array();
	$db = connect();
	$sql = "SELECT * FROM users";
	$result = $db->query($sql);

	if($result){
		$response["users"] = array();

		while($row = $result->fetch_array()){
			$users = array();
			$users["username"] = $row["username"];
			$users["recipe"] = $row["recipe"];
			$users["ingredients"] = $row["ingredients"];
			array_push($response["users"], $users);
		}
		$response["success"] = 1;
		
	}
	else{
		$response["success"] = 0;
		$response["message"] = "No products found";
	}

	echo json_encode($response);
	$db->close();
?>