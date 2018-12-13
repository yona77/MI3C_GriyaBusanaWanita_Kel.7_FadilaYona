<?php

use Restserver\Libraries\REST_Controller;
defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . 'libraries/REST_Controller.php';
require APPPATH . 'libraries/Format.php';

class Transaksi extends REST_Controller {

  // menampilkan data transaksi
  function user_get() {
    $get_transaksi = $this->db->query("SELECT .id_transaksi, trans.id_transaksi, trans. total_harga, trans.id_baju FROM pembeli, transaksi trans, baju Where trans.id_transaksi=pembeli.id_pembeli AND trans.id_baju=baju.id_baju")->result();

    $this->response(array("status"=>"success","result" => $get_transaksi));
  }

  // menambah data transaksi
  function user_post() {
    $data_transaksi = array(
       'id_transaksi'   => $this->post('id_transaksi'),
       'id_pembeli'     => $this->post('id_pembeli'),
       'total_harga'    => $this->post('total_harga'),
       'id_baju'       => $this->post('id_baju')
    );
    
    if  (empty($data_transaksi['id_transaksi'])){
      $this->response(array('status'=>'fail',"message"=>"id_transaksi kosong"));
    } else {
      $getId = $this->db->query("SELECT id_transaksi from transaksi where id_transaksi='".$data_transaksi['id_transaksi']."'")->result();
        
      //jika id_transaksi tidak ada dalam database maka eksekusi insert
      if (empty($getId)){
        //memeriksa record
        if (empty($data_transaksi['id_pembeli'])){
           $this->response(array('status'=>'fail',"message"=>"id_pembeli kosong"));
        }
        else if(empty($data_transaksi['total_harga'])){
           $this->response(array('status'=>'fail',"message"=>"total_harga kosong"));
        }else if(empty($data_transaksi['id_baju'])){
           $this->response(array('status'=>'fail',"message"=>"id_baju kosong"));
    
        } else {
          //select id_pembeli dan id_baju
          $getIdPembeli = $this->db->query("SELECT id_pembeli from pembeli Where id_pembeli='".$data_transaksi['id_pembeli']."'")->result();
          $getIdBaju = $this->db->query("SELECT id_baju from baju Where id_baju='".$data_transaksi['id_baju']."'")->result();
          $message="";
          if (empty($getIdPembeli)) $message.="id_pembeli tidak ada/salah ";
          if (empty($getIdBaju)) {
             if (empty($message)) {
                 $message.="id_baju tidak ada/salah";
             }
             else {
                 $message.="dan id_baju tidak ada/salah";
             }
          }
          if (empty($message)){
             $insert= $this->db->insert('transaksi',$data_transaksi);
             if ($insert){
                 $this->response(array('status'=>'success','result' => $data_transaksi,"message"=>$insert));   
             }
            
          }else{
             $this->response(array('status'=>'fail',"message"=>$message));   
          }
        }
      }else{
         $this->response(array('status'=>'fail',"message"=>"id_transaksi sudah ada"));
      }  
    }
  }

  // mengupdate data transaksi
  function user_put() {
    $data_transaksi = array(
       'id_transaksi'    => $this->put('id_transaksi'),
       'id_pembeli'      => $this->put('id_pembeli'),
       'total_harga'     => $this->put('total_harga'),
       'id_baju'        => $this->put('id_baju')
    );
    if(empty($data_transaksi['id_transaksi'])){
      $this->response(array('status'=>'fail',"message"=>"id_transaksi kosong"));
    }else{
      $getId = $this->db->query("SELECT id_transaksi from transaksi where id_transaksi='".$data_transaksi['id_transaksi']."'")->result();
      
      if (empty($getId)){
        $this->response(array('status'=>'fail',"message"=>"id_transaksi tidak ada/salah")); 
      } else {
        //memeriksa record
        if (empty($data_transaksi['id_pembeli'])){
           $this->response(array('status'=>'fail',"message"=>"id_pembeli kosong"));
        } else if(empty($data_transaksi['total_harga'])){
           $this->response(array('status'=>'fail',"message"=>"total_harga kosong"));
        } else if(empty($data_transaksi['id_baju'])){
           $this->response(array('status'=>'fail',"message"=>"id_baju kosong"));
        } else{
          //select id_pembeli dan id_baju
          $getIdPembeli = $this->db->query("SELECT id_pembeli from pembeli Where id_pembeli='".$data_transaksi['id_pembeli']."'")->result();
             $getIdBaju = $this->db->query("SELECT id_baju from baju Where id_baju='".$data_transaksi['id_baju']."'")->result();
          $message="";
          if (empty($getIdPembeli)) $message.="id_pembeli tidak ada/salah ";
          if (empty($getIdBaju)) {
            if(empty($message)) {
                 $message.="id_baju tidak ada/salah";
            } else {
              $message.="dan id_baju tidak ada/salah";
            }
          }
          if(empty($message)){
            $this->db->where('id_transaksi',$data_transaksi['id_transaksi']);
            $update= $this->db->update('transaksi',$data_transaksi);
            if ($update){
              $this->response(array('status'=>'success','result' => $data_transaksi,"message"=>$update));
            }
          } else{
             $this->response(array('status'=>'fail',"message"=>$message));   
          }
        }
      }
    }
  }

  // delete transaksi
  function user_delete() {
    $id_transaksi = $this->delete('id_transaksi');
    if (empty($id_transaksi)){
      $this->response(array('status' => 'fail', "message"=>"id_transaksi harus diisi"));
    } else {
      $this->db->where('id_transaksi', $id_transaksi);
      $delete = $this->db->delete('transaksi');  
      if ($this->db->affected_rows()) {
        $this->response(array('status' => 'success','message' =>"Berhasil delete dengan id_transaksi = ".$id_transaksi));
      } else {
        $this->response(array('status' => 'fail', 'message' =>"id_transaksi tidak dalam database"));
      }
    }
  }
}  