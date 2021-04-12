<?php
 require_once("config.php");
 $query= "SELECT * FROM loaisanpham";
 $data= mysqli_query($conn,$query);
 $mangloaisp=array();
 While ($row =mysqli_fetch_assoc($data)){
     array_push($mangloaisp,new loaisp(
        $row["id"],
        $row["Loaisp"],
        $row["hinhanh"]));
 }
 echo json_encode($mangloaisp);
class loaisp
    {function loaisp($id,$tenloaisp,$hinhanhloaisp){
    $this->id=$id;
    $this->tenloaisp=$tenloaisp;
    $this->hinhanhloaisp=$hinhanhloaisp;
}
    }