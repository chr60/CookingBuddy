<?php
    require_once __DIR__ . '/db_connect.php';
    $response = array();

    if(isset($_POST['recipe'] && isset($_POST['ingredients']) && isset($_POST['name'])){
        $ingredients = $_POST['ingredients'];
        $name = $_POST['name'];
        $recipe = $_POST['recipe'];
        $db = connect();
        $sql = "INSERT INTO users (username, recipe, ingredients) VALUES ('$name', '$recipe', $ingredients')";
        $result = $db->query($sql);
        if($result){
            $response["success"] = '1';
            $response["message"] = "ingredients successfully added.";
        else{
            $response["success"] = '0';
            $response["message"] = "Oops! An error occurred while adding";
        
    else{
        $response["success"] = '0';
        $response["message"] = "Required field(s) is missing";
    }
    echo json_encode($response);
    $db->close();
?>

