<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\FranchisorModel;
use Illuminate\Http\Request;

class FranchisorController extends Controller
{



    public function update(Request $request, $id)
    { 
        $data = FranChisorModel::find($id);
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
         
        $input = $request->all();
        FranChisorModel::create($input);
        return response()->json([
            'message' => 'succes',
        ], 200);
    }


    public function index()  {
        $data=FranChisorModel::all();
        return response()->json([
            'message' => 'succes',
            'data' =>  $data,
        ], 200);
    }
}
