<?php

	

		function connect(){
			require_once __DIR__ . '/db_config.php';

			$conn = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

			if($conn->connect_error){
				trigger_error('UNABLE TO CONNECT '.$conn->connect_error);
			}
			return $conn;
		}

?>
	