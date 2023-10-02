<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\CartModel;
use App\Models\OrderModel;
use Illuminate\Http\Request;

class OrderController extends Controller
{
    function create(Request $request)
    {

 
        $inputOrder = $request->except('cart');
        $post =   OrderModel::create($inputOrder);
 
        $json = json_decode($request->cart);
        foreach ($json as   $value) {
            $data = new CartModel;
            $data->id_order =  $post->id;
            $data->id_produk =  $value->id_produk; 
            $data->qty = $value->qty; 
            $data->harga = $value->harga; 
            $data->save(); 
        } 

        return response()->json([
            'message' =>   $post->id,
        ], 200);
    }
}
