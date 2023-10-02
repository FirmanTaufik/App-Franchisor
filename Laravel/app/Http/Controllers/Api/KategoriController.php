<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\KategoriModel;
use Illuminate\Http\Request;

class KategoriController extends Controller
{


    function index()  {
        $all = KategoriModel::all();
        return response()->json([
            'data' => $all,
        ], 200);
    }

    public function create(Request $request)

    {  
        
        $image = $request->file('gambar');
        $destinationPath = 'imagekategori/';
        $imageName = date('YmdHis') . "." . $image->getClientOriginalExtension();
        $image->move($destinationPath, $imageName); 
         
        $input = $request->all();
        $input['gambar'] =  $imageName; 
        KategoriModel::create($input);
        return response()->json([
            'message' => 'succes',
        ], 200);
    }
}
