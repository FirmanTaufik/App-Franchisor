<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\ProdukModel;
use Illuminate\Http\Request;

class ProdukController extends Controller
{

    function index()  {
        
        $all = ProdukModel::all();
        return response()->json([
            'data' => $all,
        ], 200);
    }

    public function create(Request $request)

    {   
        $image = $request->file('gambar');
        $destinationPath = 'imageproduct/';
        $imageName = date('YmdHis') . "." . $image->getClientOriginalExtension();
        $image->move($destinationPath, $imageName); 
         
        $input = $request->all();
        $input['gambar'] =  $imageName; 
        ProdukModel::create($input);
        return response()->json([
            'message' => 'succes',
        ], 200);
    }

    
}
