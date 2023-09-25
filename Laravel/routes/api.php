<?php

use App\Http\Controllers\Api\FranchisorController;
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
    Route::get('/franchisor', [FranchisorController::class, 'index'])->name('franchisor');
    Route::post('/createFranchisor', [FranchisorController::class, 'create'])->name('createFranchisor');
    Route::post('/updateFranchisor/{id}', [FranchisorController::class, 'update'])->name('updateFranchisor');

    Route::get('/test', function () {
        return response()->json([
            'message' => 'logout success'
        ]);
    });
    
});