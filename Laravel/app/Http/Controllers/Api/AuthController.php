<?php

namespace App\Http\Controllers\Api;


use App\Http\Controllers\Controller;
use App\Models\FranchiseeModel;
use App\Models\FranchisorModel;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;

class AuthController extends Controller

{
    public function register(Request $request)
    {


        if ($this->sudahUsernameDiPakai($request->username) == true) {
            return response()->json([
                'message' =>  "Username Sudah di gunakan"
            ], 401);
        } 

        $user = "";
        switch ($request->role) {
            case '1':
                $user = User::create([
                    'username' => $request->username,
                    'nama' => $request->nama,
                    'email' => $request->email,
                    'role' => $request->role,
                    'password' => Hash::make($request->password)
                ]);
                break;
            case '2':
                $pw = Hash::make($request->password);
                $input = $request ->all();
                $input['password'] = $pw;
                $user = FranchisorModel::create($input);
                break;
            case '3':
                $pw = Hash::make($request->password);
                $input = $request ->all();
                $input['password'] = $pw;
                $user = FranchiseeModel::create($input);
                break;
        }


        // $token = $user->createToken('auth_token')->plainTextToken;

        return response()->json([
            'data' => $user,
            'token_type' => 'Success'
        ]);
    }

    public function login(Request $request)
    {

        $user = User::where('username', $request->username)  ->first(); 

        if ( $user && Hash::check($request->password, $user->password)) {      

            $token = $user->createToken('auth_token')->plainTextToken; 
            return response()->json([
                'message' => 'Login success',
                'user_id' => $user->id,
                'role' => $user->role,
                'access_token' => $token,
                'token_type' => 'Bearer'
            ]);
        }


        $user = FranchisorModel::where('username', $request->username)  ->first();  
        if ( $user && Hash::check($request->password, $user->password)) {      

            $token = $user->createToken('auth_token')->plainTextToken; 
            return response()->json([
                'message' => 'Login success',
                'user_id' => $user->id,
                'role' => $user->role,
                'access_token' => $token,
                'token_type' => 'Bearer'
            ]);
        }

        $user = FranchiseeModel::where('username', $request->username)  ->first();  
        if ( $user && Hash::check($request->password, $user->password)) {      

            $token = $user->createToken('auth_token')->plainTextToken; 
            return response()->json([
                'message' => 'Login success',
                'user_id' => $user->id,
                'role' => $user->role,
                'access_token' => $token,
                'token_type' => 'Bearer'
            ]);
        }
        

        return response()->json([
            'message' => 'Unauthorized1' 
        ],401); 

       
    }
 

    public function sudahUsernameDiPakai($username)
    {
        $satu = User::where('username', $username)->count();
        if ($satu > 0) {
            return true;
        }
        $dua = FranchisorModel::where('username', $username)->count();
        if ($dua > 0) {
            return true;
        }

        $tiga = FranchiseeModel::where('username', $username)->count();
        if ($tiga > 0) {
            return true;
        }
        return false;
    }

    public function logout(Request $request)
    {
        $request->user()->currentAccessToken()->delete();
        // Auth::user()->tokens()->delete();
        return response()->json([
            'message' => 'logout success'
        ]);
    }
}
