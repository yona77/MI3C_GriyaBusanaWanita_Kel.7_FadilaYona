<?php

use Restserver\Libraries\REST_Controller;
defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . 'libraries/REST_Controller.php';
require APPPATH . 'libraries/Format.php';

class Baju extends REST_Controller {
    // Konfigurasi letak folder untuk upload image
    private $folder_upload = 'uploads/';
    function all_get(){
        $get_baju = $this->db->query("
            SELECT
                id_baju,
                nama_baju,
                harga,
                kategori,
                photo_url
            FROM baju")->result();
       $this->response(
           array(
               "status" => "success",
               "result" => $get_baju
           )
       );
    }

    function all_post() {
        $action  = $this->post('action');
        $data_baju = array(
     	               'id_baju' => $this->post('id_baju'),
     	               'nama_baju'       => $this->post('nama_baju'),
     	               'harga'     => $this->post('harga'),
     	               'kategori'      => $this->post('kategori'),
     	               'photo_url'   => $this->post('photo_url')
 	               );
        switch ($action) {
            case 'insert':
                $this->insertBaju($data_baju);
                break;           
            case 'update':
                $this->updateBaju($data_baju);
                break;           
            case 'delete':
                $this->deleteBaju($data_baju);
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

    function insertBaju($data_baju){
 	   // Cek validasi
 	    if (empty($data_baju['nama_baju']) || empty($data_baju['harga']) ||  empty($data_baju['kategori'])){
 	       $this->response(
 	           array(
 	               "status" => "failed",
 	               "message" => "Nama / harga / kategori harus diisi"
 	           )
 	       );
 	    } else {
 	       $data_baju['photo_url'] = $this->uploadPhoto();
 	       $do_insert = $this->db->insert('baju', $data_baju);
     	   if ($do_insert){
         	   $this->response(
         	       array(
         	           "status" => "success",
         	           "result" => array($data_baju),
         	           "message" => $do_insert
         	       )
         	   );
            }
 	    }
    }

    function updateBaju($data_baju){
 	   // Cek validasi
 	    if (empty($data_baju['nama_baju']) || empty($data_baju['harga']) || empty($data_baju['kategori'])){
 	       $this->response(
 	           array(
 	               "status" => "failed",
 	               "message" => "Nama / harga / kategori"
 	           )
 	       );
 	    } else {
 	       // Cek apakah ada di database
 	       $get_baju_baseID = $this->db->query("
 	           SELECT 1
 	           FROM baju
 	           WHERE id_baju =  {$data_baju['id_baju']}")->num_rows();
 	        if($get_baju_baseID === 0){
     	       // Jika tidak ada
     	       $this->response(
     	           array(
     	               "status"  => "failed",
      	               "message" => "ID Baju tidak ditemukan"
     	           )
     	       );
 	        } else {
 	           $data_baju['photo_url'] = $this->uploadPhoto();
         	    if ($data_baju['photo_url']){
         	       // Jika upload foto berhasil, eksekusi update
         	       $update = $this->db->query("
         	           UPDATE baju SET
         	               nama_baju = '{$data_baju['nama_baju']}',
         	               harga = '{$data_baju['harga']}',
         	               kategori = '{$data_baju['kategori']}',
         	               photo_url = '{$data_baju['photo_url']}'
         	           WHERE id_baju = '{$data_baju['id_baju']}'");
         	    } else {
         	       // Jika foto kosong atau upload foto tidak berhasil, eksekusi update
                    $update = $this->db->query("
                        UPDATE baju
                        SET
                            nama_baju    = '{$data_baju['nama']}',
                            harga  = '{$data_baju['harga']}',
                            kategori    = '{$data_baju['kategori']}'
                        WHERE id_baju = {$data_baju['id_baju']}"
                    );
         	    }        	  
         	    if ($update){
             	   $this->response(
             	       array(
             	           "status"    => "success",
             	           "result"    => array($data_baju),
             	           "message"   => $update
             	       )
             	   );
                }
 	       }   
 	   }
    }

    function deleteBaju($data_baju){
        if (empty($data_baju['id_baju'])){
 	       $this->response(
 	           array(
 	               "status" => "failed",
 	               "message" => "ID Baju harus diisi"
 	           )
 	       );
 	    } else {
 	       // Cek apakah ada di database
 	       $get_baju_baseID =$this->db->query("
 	           SELECT 1
 	           FROM baju
 	           WHERE id_baju = {$data_baju['id_baju']}")->num_rows();
 	        if($get_baju_baseID > 0){        
 	           $get_photo_url =$this->db->query("
 	           SELECT photo_url
 	           FROM baju
 	           WHERE id_baju = {$data_baju['id_baju']}")->result(); 	       
                if(!empty($get_photo_url)){
                    // Dapatkan nama file
                    $photo_nama_file = basename($get_photo_url[0]->photo_url);
                    // Dapatkan letak file di folder upload
                    $photo_lokasi_file = realpath(FCPATH . $this->folder_upload . $photo_nama_file);                   
                    // Jika file ada, hapus
                    if(file_exists($photo_lokasi_file)) {
                        // Hapus file
         	           unlink($photo_lokasi_file);
         	        }
         	       $this->db->query("
         	           DELETE FROM baju
         	           WHERE id_baju = {$data_baju['id_baju']}");
         	       $this->response(
         	           array(
         	               "status" => "success",
         	               "message" => "Data ID = " .$data_baju['id_baju']. " berhasil dihapus"
         	            )
         	       );
         	   }	       
            } else {
                $this->response(
                    array(
                        "status" => "failed",
                        "message" => "ID Baju tidak ditemukan"
                    )
                );
            }
 	    }
    }

    function uploadPhoto() {
        // Apakah user upload gambar?
        if ( isset($_FILES['photo_url']) && $_FILES['photo_url']['size'] > 0 ){
            // Foto disimpan di android_api/uploads
            $config['upload_path'] = realpath(FCPATH . $this->folder_upload);
            $config['allowed_types'] = 'jpg|png';
 	       // Load library upload & helper
 	       $this->load->library('upload', $config);
 	       $this->load->helper('url');
 	       // Apakah file berhasil diupload?
 	       if ( $this->upload->do_upload('photo_url')) {
               // jika berhasil, simpan nama file-nya
               // URL image yang disimpan adalah http://localhost/android_api/uploads/namafile
        	   $img_data = $this->upload->data();
        	   $post_image = base_url(). $this->folder_upload .$img_data['file_name'];
 	       } else {
 	           // Upload gagal, beri nama image dengan errornya
 	           $post_image = $this->upload->display_errors();        
 	       }
 	   } else {
 	       // Tidak ada file yang di-upload, kosongkan nama image-nya
 	       $post_image = '';
 	   }
 	   return $post_image;
    }
}
