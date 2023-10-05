<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\CartModel;
use App\Models\OrderModel;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class OrderController extends Controller
{


    function index($id)  {
        $data = OrderModel::where('id_franchisee', $id)
        ->orderBy('created_at','desc')->get();

        foreach ($data as  $value) {
          //  $cart =  CartModel::where('id_order', $value->id )->get();
          $cart =  DB::table('tb_cart')
          ->select(DB::raw('tb_cart.*'), 'tb_produk.nama', 'tb_produk.gambsar')
          ->leftJoin('tb_produk', 'tb_produk.id', '=', 'tb_cart.id_produk')
          ->where('tb_cart.id_order', '=', $value->id)
          ->get();

            $value->cart= $cart;
        }

        return response()->json([
            'message' =>    'success',
            'data' =>     $data,
        ], 200);
    }

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
