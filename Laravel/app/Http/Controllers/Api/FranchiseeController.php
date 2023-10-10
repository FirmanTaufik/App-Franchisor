<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\FranchiseeModel;
use App\Models\KategoriModel;
use App\Models\ProdukModel;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use stdClass;

class FranchiseeController extends Controller
{


    function pesanProduk()   {
        $produk=ProdukModel::all();
        $kategori=KategoriModel::all();
        $std= new stdClass();
        $std->produk = $produk;
        $std->kategori = $kategori;
        return response()->json([
            'message' => 'succes',
            'data' =>  $std,
        ], 200);
    }

    public function update(Request $request, $id)
    { 
        $data = FranchiseeModel::find($id);
        $data->username = $request->username; 
        if ($data->password != $request->password) {
            $data->password =  Hash::make($request->password);
        }

        $data->pemilik = $request->pemilik;
        $data->email = $request->email;
        $data->alamat = $request->alamat;
        $data->nomor_telpon_outlet = $request->nomor_telpon_outlet;
        $data->pic = $request->pic;
        $data->nomor_pic = $request->nomor_pic;
        $data->save();

        return response()->json([
            'message' => 'succes',
        ], 200);
    }


    public function create(Request $request)
    { 
         
        $pw = Hash::make($request->password);
        $input = $request ->all();
        $input['password'] = $pw;
        FranchiseeModel::create($input);
        return response()->json([
            'message' => 'succes',
        ], 200);
    }


    public function index(Request $request)  {
        $id =$request->id;
        $data=FranchiseeModel::all();

        if ($id!=null) {
            $data=FranchiseeModel::where('id_franchisor' ,$id)->get();
        }

        return response()->json([
            'message' => 'succes',
            'data' =>  $data,
        ], 200);
    }
}
