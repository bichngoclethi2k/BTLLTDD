<?php
require_once("config.php");
$query= "SELECT * FROM sanpham ORDER BY ID DESC LIMIT 10";
$data= mysqli_query($conn,$query);
$mangspmoi= array();
While ($row =mysqli_fetch_assoc($data)){
    array_push($mangspmoi,new spmoi(
       $row["id"],
       $row["tensp"],
       $row["giasp"],
       $row["hinhanhsp"],
       $row["mota"],
       $row["idloaisp"]));
}
echo json_encode($mangspmoi);

class spmoi
    {function spmoi($id,$tensp,$giasp,$hinhanhsp,$mota,$idloaisp){
    $this->id=$id;
    $this->tensp=$tensp;
    $this->giasp=$giasp;
    $this->hinhanhsp=$hinhanhsp;
    $this->mota=$mota;
    $this->idloaisp=$idloaisp;
}
    }
?>