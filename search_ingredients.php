<?php
	require_once __DIR__ . '/db_connect.php';
	$response = array();
	
	if(isset($_POST['name'])){
		$ingredients = $_POST['name'];
		$db = connect();
		$response["success"] = 0;
		$sql = "SELECT * FROM users WHERE ingredients LIKE '$ingredients'";
		$result = $db->query($sql);
		if($result){
			$response["users"] = array();
			while($row = $result->fetch_array()){
				$users = array();
				$users["username"] = $row["username"];
				$users["recipe"] = $row["recipe"];
				//$users["ingredients"] = $row["ingredients"];
				$response["success"] = 1;
				array_push($response["users"], $users);
			}
		}
		else{
			$response["success"] = 0;
      $response["message"] = "No product found";
		}
	}
	else{
		$response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
	}

	echo json_encode($response);
	$db->close();
?>