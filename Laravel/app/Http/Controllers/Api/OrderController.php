<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\CartModel;
use App\Models\OrderModel;
use DateInterval;
use DateTime;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use stdClass;

class OrderController extends Controller
{

    function pendapatan(Request $request, $id)
    {

        $jenis = $request->jenis;
        $data = "";
        if ($jenis == "day") {
            $day1 = $request->day1;
            $day2 = $request->day2;

            $t1 = DB::table('tb_cart')
                ->select(
                    'tb_cart.id_order',
                    'tb_cart.id_produk',
                    DB::raw('ifnull(SUM(tb_cart.qty*tb_cart.harga),0) AS total'),
                    DB::raw('tb_order.tanggal AS tanggal_order')
                )
                ->leftJoin('tb_order', 'tb_order.id', '=', 'tb_cart.id_order')
                ->where('tb_order.tanggal', '=', $day1)
                ->groupBy('tb_cart.id');



            $t2 = DB::table('tb_cart')
                ->select(
                    'tb_cart.id_order',
                    'tb_cart.id_produk',
                    DB::raw('ifnull(SUM(tb_cart.qty*tb_cart.harga),0) AS total'),
                    DB::raw('tb_order.tanggal AS tanggal_order')
                )
                ->leftJoin('tb_order', 'tb_order.id', '=', 'tb_cart.id_order')
                ->where('tb_order.tanggal', '=', $day2)
                ->groupBy('tb_cart.id');

            $data = new  stdClass();
            $data->pertama = $this->listProduk($t1);
            $data->kedua = $this->listProduk($t2);
        }

        return response()->json([
            'message' => 'succes',
            'data' => $data,
        ], 200);
    }


    function listProduk($t1)
    {
        $data = DB::table('tb_produk')
            ->leftJoinSub($t1, 't1', function ($join) {
                $join->on('tb_produk.id', '=', 't1.id_produk');
            })
            ->select('tb_produk.id', 'tb_produk.nama',   DB::raw('ifnull(SUM(total),0) AS total'))
            ->groupBy('tb_produk.id')
            ->orderBy('total', 'desc')
            ->limit(5)
            ->get();

        return $data;
    }

    function terlaris(Request $request, $id)
    {
        $dari = $request->dari;
        $hingga = $request->hingga;

        $t1 = DB::table('tb_cart')
            ->select(DB::raw('tb_cart.*'), DB::raw('tb_order.tanggal AS tanggal_order'))
            ->leftJoin('tb_order', 'tb_order.id', '=', 'tb_cart.id_order')
            ->where('id_franchisee', '=', $id);

        if ($dari != null && $hingga  == "null") {
            $t1->where('tb_order.tanggal', '=', $dari);
        } else if ($dari != null && $hingga != null) {
            $t1->whereBetween('tb_order.tanggal', [$dari, $hingga]);
        }

        $t1->groupBy('tb_cart.id');


        $data = DB::table('tb_produk')
            ->leftJoinSub($t1, 't1', function ($join) {
                $join->on('tb_produk.id', '=', 't1.id_produk');
            })
            ->select('tb_produk.id', 'tb_produk.nama',   DB::raw('ifnull(SUM(t1.qty),0) AS terjual'))
            ->groupBy('tb_produk.id')
            ->orderBy('terjual', 'desc')
            ->limit(5)
            ->get();

        return response()->json([
            'message' => 'succes',
            'data' => $data,
        ], 200);
    }

    function filter(Request $request)
    {
        $dari = $request->dari;
        $hingga = $request->hingga;

        $t1 = DB::table('tb_cart')
            ->select(DB::raw('tb_cart.*'), DB::raw('tb_order.tanggal AS tanggal_order'))
            ->leftJoin('tb_order', 'tb_order.id', '=', 'tb_cart.id_order');

        if ($dari != null && $hingga != null) {
            $t1->whereBetween('tb_order.tanggal', [$dari, $hingga]);
        }

        $t1->groupBy('tb_cart.id');


        $data = DB::table('tb_produk')
            ->leftJoinSub($t1, 't1', function ($join) {
                $join->on('tb_produk.id', '=', 't1.id_produk');
            })
            ->select('tb_produk.id', 'tb_produk.nama',   DB::raw('ifnull(SUM(t1.qty),0) AS terjual'))
            ->groupBy('tb_produk.id')
            ->orderBy('terjual', 'desc')
            ->limit(5)
            ->get();

        return response()->json([
            'message' => 'succes',
            'data' => $data,
        ], 200);
    }

    function updateStatusOrder(Request $request, $id)
    {
        $data = OrderModel::find($id);
        $data->status = $request->status;
        $data->save();

        return response()->json([
            'message' => 'succes',
        ], 200);
    }

    function index($id)
    {
        $data = OrderModel::where('id_franchisee', $id)
            ->orderBy('created_at', 'desc')->get();

        foreach ($data as  $value) {
            //  $cart =  CartModel::where('id_order', $value->id )->get();
            $cart =  DB::table('tb_cart')
                ->select(DB::raw('tb_cart.*'), 'tb_produk.nama', 'tb_produk.gambar')
                ->leftJoin('tb_produk', 'tb_produk.id', '=', 'tb_cart.id_produk')
                ->where('tb_cart.id_order', '=', $value->id)
                ->get();

            $value->cart = $cart;
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
