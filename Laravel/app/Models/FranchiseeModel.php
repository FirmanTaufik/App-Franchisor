<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class FranchiseeModel extends Model
{
    use HasFactory;
    protected $table = 'tb_franchisee';
 
    protected $fillable = [ 
        'pemilik',
        'email', 
        'alamat',
        'nomor_telpon_outlet',
        'pic',
        'nomor_pic',
    ];
}
