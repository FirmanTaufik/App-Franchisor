<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\ProdukModel;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;
use File;

class ProdukController extends Controller
{

    function index(Request $request)
    {

        $all = ProdukModel::all();
        if ($request->id_franchisor != null) {
            //  $all = ProdukModel::where('id_franchisor',$request->id_franchisor )->get();
            $all = DB::table('tb_produk')
                ->select(DB::raw('tb_produk.*'), 'tb_kategori.nama as nama_kategori')
                ->leftJoin('tb_kategori', 'tb_kategori.id', '=', 'tb_produk.id_kategori')
                ->where('tb_produk.id_franchisor', '=', $request->id_franchisor)
                ->get();
        }
        return response()->json([
            'data' => $all,
        ], 200);
    }


    function updateProduk(Request $request)
    {
        $id = $request->id_produk;
          
        $data = ProdukModel::find($id);
        $data->id_franchisor = $request->id_franchisor;
        $data->id_kategori = $request->id_kategori;
        $data->nama = $request->nama;
        $data->harga = $request->harga;

        $file = $request->file('gambar');
        if ($file != null) {

            $destinationPath = 'imageproduct/';
            if (File::exists($destinationPath ."".$data->gambar)) {
                unlink($destinationPath ."".$data->gambar);
            }

            $image = date('YmdHis') . "." . $file->getClientOriginalExtension();
            $file->move($destinationPath, $image);
            $data->gambar = $image;
        }

        $data->save();
        return response()->json([
            'message' => 'succes',
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
