<?php
require_once("config.php");
$page= $_GET['page'];
$idsp=$_POST['idloaisp'];
$spage=5;
$limit=($page-1)*$spage;
$mangsp= array();
$query= "SELECT * FROM sanpham Where idloaisp=$idsp LIMIT $limit,$spage";
$data= mysqli_query($conn,$query);
While ($row =mysqli_fetch_assoc($data)){
    array_push($mangsp,new Sanpham(
       $row["id"],
       $row["tensp"],
       $row["giasp"],
       $row["hinhanhsp"],
       $row["mota"],
       $row["idloaisp"]));
}
echo json_encode($mangsp);
class Sanpham{
    function Sanpham($id,$tensp,$giasp,$hinhanhsp,$mota,$idloaisp){
        $this->id=$id;
        $this->tensp=$tensp;
        $this->giasp=$giasp;
        $this->hinhanhsp=$hinhanhsp;
        $this->mota=$mota;
        $this->idloaisp=$idloaisp;
    }
}

?>