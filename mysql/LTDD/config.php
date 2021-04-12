<?php
$hostname = 'localhost';
$username = 'root';
$password = '';
$name ='baitaplondd';
$conn = mysqli_connect($hostname, $username, $password,$name);
if (!$conn) {
  die ("khong ket noi");
  exit();
}


?>