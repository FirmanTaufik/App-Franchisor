<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Laravel\Sanctum\HasApiTokens;

class FranchisorModel extends Authenticatable
{
    use HasApiTokens, HasFactory, Notifiable;
    protected $table = 'tb_franchisor';
 
    protected $fillable = [ 
        'id_aplikator',
        'username', 
        'password', 
        'role', 
        'pemilik', 
        'email', 
        'alamat',
        'nomor_telpon_outlet',
        'pic',
        'nomor_pic',
    ];

}
