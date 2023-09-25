<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class FranchisorModel extends Model
{
    use HasFactory; 
    protected $table = 'tb_franchisor';
 
    protected $fillable = [ 
        'pemilik',
        'email', 
        'alamat',
        'nomor_telpon_outlet',
        'pic',
        'nomor_pic',
    ];

}
