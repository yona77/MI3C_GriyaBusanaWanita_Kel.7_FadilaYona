<?php

use Restserver\Libraries\REST_Controller;
defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . 'libraries/REST_Controller.php';
require APPPATH . 'libraries/Format.php';

class Pembeli extends REST_Controller {
    // Konfigurasi letak folder untuk upload image
    private $folder_upload = 'uploads/';
    function all_get(){
        $get_pembeli = $this->db->query("
            SELECT
                id_pembeli,
                username,
                password,
                nama,
                alamat,
                telp
            FROM pembeli")->result();
       $this->response(
           array(
               "status" => "success",
               "result" => $get_pembeli
           )
       );
    }

    function all_post() {
        $action  = $this->post('action');
        $data_pembeli = array(
     	               'id_pembeli' => $this->post('id_pembeli'),
                       'username' => $this->post('username'),
                       'password' => $this->post('password'),
     	               'nama'       => $this->post('nama'),
     	               'alamat'     => $this->post('alamat'),
     	               'telp'      => $this->post('telp')
 	               );
        switch ($action) {
            case 'insert':
                $this->insertPembeli($data_pembeli);
                break;           
            case 'update':
                $this->updatePembeli($data_pembeli);
                break;           
            case 'delete':
                $this->deletePembeli($data_pembeli);
                break;          
            default:
                $this->response(
                    array(
                        "status"  =>"failed",
                        "message" => "action harus diisi"
                    )
                );
                break;
        }
    }

    function insertPembeli($data_pembeli){
 	   // Cek validasi
 	    empty($data_pembeli['username']) || empty($data_pembeli['password']) || empty($data_pembeli['nama']) || empty($data_pembeli['alamat']) || empty($data_pembeli['telp']){
 	       $this->response(
 	           array(
 	               "status" => "failed",
 	               "message" => "Username / password / nama / alamat / telp harus diisi"
 	           )
 	       );
 	    } 
 	    
    }

    function updatePembeli($data_pembeli){
 	   // Cek validasi
 	    if ($data_pembeli['username']) || empty($data_pembeli['password']) || empty($data_pembeli['nama']) || empty($data_pembeli['alamat']) || empty($data_pembeli['telp'])){
 	       $this->response(
 	           array(
 	               "status" => "failed",
 	               "message" => "Username / password / nama / alamat / telp harus diisi"
 	           )
 	       );
 	    } else {
 	       // Cek apakah ada di database
 	       $get_pembeli_baseID = $this->db->query("
 	           SELECT 1
 	           FROM pembeli
 	           WHERE id_pembeli =  {$data_pembeli['id_pembeli']}")->num_rows();
 	        if($get_pembeli_baseID === 0){
     	       // Jika tidak ada
     	       $this->response(
     	           array(
     	               "status"  => "failed",
      	               "message" => "ID Pembeli tidak ditemukan"
     	           )
     	       );
         	    } else {
         	       // Jika foto kosong atau upload foto tidak berhasil, eksekusi update
                    $update = $this->db->query("
                        UPDATE pembeli
                        SET
                            username    = '{$data_pembeli['username']}',
                            password    = '{$data_pembeli['password']}',
                            nama    = '{$data_pembeli['nama']}',
                            alamat  = '{$data_pembeli['alamat']}',
                            telp    = '{$data_pembeli['telp']}'
                        WHERE id_pembeli = {$data_pembeli['id_pembeli']}"
                    );
         	    }        	  
         	    if ($update){
             	   $this->response(
             	       array(
             	           "status"    => "success",
             	           "result"    => array($data_pembeli),
             	           "message"   => $update
             	       )
             	   );
                }
 	       }   
 	   }
    }

    function deletePembeli($data_pembeli){
        if (empty($data_pembeli['id_pembeli'])){
 	       $this->response(
 	           array(
 	               "status" => "failed",
 	               "message" => "ID Pembeli harus diisi"
 	           )
 	       );
 	    } else {
 	       // Cek apakah ada di database
 	       $get_pembeli_baseID =$this->db->query("
 	           SELECT 1
 	           FROM pembeli
 	           WHERE id_pembeli = {$data_pembeli['id_pembeli']}")->num_rows();
           
 	        
         	       $this->db->query("
         	           DELETE FROM pembeli
         	           WHERE id_pembeli = {$data_pembeli['id_pembeli']}");
         	       $this->response(
         	           array(
         	               "status" => "success",
         	               "message" => "Data ID = " .$data_pembeli['id_pembeli']. " berhasil dihapus"
         	            )
         	       );
         	   }	       
            } else {
                $this->response(
                    array(
                        "status" => "failed",
                        "message" => "ID Pembeli tidak ditemukan"
                    )
                );
            }
 	    }
    }
