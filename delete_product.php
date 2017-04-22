<?php
	require_once __DIR__ . '/db_connect.php';
	
	if(isset($_POST['name']) && isset($_POST['recipe']) && isset($_POST['ingredients'])){
		$name = $_POST['name'];
		$recipe = $_POST['recipe'];
		$ingredients = $_POST['ingredients'];
		
		$db = connect();
		$sql = "DELETE FROM users WHERE username LIKE '$name' AND recipe LIKE '$recipe' AND ingredients LIKE '$ingredients'";
		$result = $db->query($sql);
		if($result){
			$response["success"] = 1;
      $response["message"] = "Product successfully deleted";
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