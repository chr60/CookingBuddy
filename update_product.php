<?php
	require_once __DIR__ . '/db_connect.php';
	$response = array();

	if(isset($_POST['name']) && isset($_POST['recipe']) && isset($_POST['ingredients'])){
		$name = $_POST['name'];
		$recipe = $_POST['recipe'];
		$ingredients = $_POST['ingredients'];
		
		$db = connect();
		$sql = "UPDATE users SET ingredients='$ingredients' WHERE username LIKE '$name' AND recipe LIKE '$recipe'";
		$result = $db->query($sql);

		if($result){
			$response["success"] = 1;
      $response["message"] = "Recipe successfully updated.";
		} 
	}
	else{
			$response["success"] = 0;
      $response["message"] = "Required field(s) is missing";
	}

	echo json_encode($response);
	$db->close();
?>
