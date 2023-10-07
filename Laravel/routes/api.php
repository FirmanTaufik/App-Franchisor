<?php

use App\Http\Controllers\Api\FranchiseeController;
use App\Http\Controllers\Api\FranchisorController;
use App\Http\Controllers\Api\KategoriController;
use App\Http\Controllers\Api\OrderController;
use App\Http\Controllers\Api\ProdukController;
use App\Models\FranchiseeModel;
use App\Models\OrderModel;
use App\Models\ProdukModel;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::post('/login', [\App\Http\Controllers\Api\AuthController::class, 'login']);

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});
Route::post('/register', [\App\Http\Controllers\Api\AuthController::class, 'register']);

Route::middleware('auth:sanctum')->group(function () {
    Route::post('/logout', [\App\Http\Controllers\Api\AuthController::class, 'logout'])->name('login'); 
    
    //franchisor
    Route::get('/franchisor', [FranchisorController::class, 'index'])->name('franchisor');
    Route::post('/createFranchisor', [FranchisorController::class, 'create'])->name('createFranchisor');
    Route::post('/updateFranchisor/{id}', [FranchisorController::class, 'update'])->name('updateFranchisor');
    
    //franchisee
    Route::get('/franchisee', [FranchiseeController::class, 'index'])->name('Franchisee');
    Route::post('/createFranchisee', [FranchiseeController::class, 'create'])->name('createFranchisee');
    Route::post('/updateFranchisee/{id}', [FranchiseeController::class, 'update'])->name('updateFranchisee');
    

    //kategori 
    Route::get('/kategori', [KategoriController::class, 'index'])->name('kategori');
    Route::post('/createKategori', [KategoriController::class, 'create'])->name('createKategori');


    //pproduk
    Route::get('/produk', [ProdukController::class, 'index'])->name('produk');
    Route::post('/createProduk', [ProdukController::class, 'create'])->name('createProduk');

    //order
    Route::get('/order/{id}', [OrderController::class, 'index'])->name('order');
    Route::post('/createOrder', [OrderController::class, 'create'])->name('createOrder');
    Route::post('/updateStatusOrder/{id}', [OrderController::class, 'updateStatusOrder'])->name('updateStatusOrder');

    //franchisee
    Route::get('/pesanProduk', [FranchiseeController::class, 'pesanProduk'])->name('pesanProduk');

    Route::get('/test', function () {
        return response()->json([
            'message' => 'logout success'
        ]);
    });
    
});