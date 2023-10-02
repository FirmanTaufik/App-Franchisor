<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\ProdukModel;
use Illuminate\Http\Request;

class ProdukController extends Controller
{
    public function create(Request $request)

    {  $destinationPath = 'imageproduct/';
        $profileImage = date('YmdHis') . "." . $image->getClientOriginalExtension();
        $image->move($destinationPath, $profileImage); 
         
        $input = $request->all();
        ProdukModel::create($input);
        return response()->json([
            'message' => 'succes',
        ], 200);
    }
}
