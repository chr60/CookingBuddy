<?php
	require_once __DIR__ . '/db_connect.php';
	$response = array();

	if(isset($_POST['name']) && isset($_POST['recipe']) && isset($_POST['ingredients'])){
		$name = $_POST['name'];
		$recipe = $_POST['recipe'];
		$ingredients = $_POST['ingredients'];
		
		$db = connect();
		$sql = "INSERT INTO users(username, recipe, ingredients) VALUES ('$name', '$recipe', '$ingredients')";
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