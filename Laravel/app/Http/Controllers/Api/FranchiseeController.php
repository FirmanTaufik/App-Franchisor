<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\FranchiseeModel;
use Illuminate\Http\Request;

class FranchiseeController extends Controller
{
    public function update(Request $request, $id)
    { 
        $data = FranchiseeModel::find($id);
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
        FranchiseeModel::create($input);
        return response()->json([
            'message' => 'succes',
        ], 200);
    }


    public function index()  {
        $data=FranchiseeModel::all();
        return response()->json([
            'message' => 'succes',
            'data' =>  $data,
        ], 200);
    }
}
