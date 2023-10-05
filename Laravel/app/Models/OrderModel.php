<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class OrderModel extends Model
{
    use HasFactory;
    protected $table = 'tb_order';
 
    protected $fillable = [ 
        'tanggal',
        'id_franchisee',
        'nama_pembeli',
        'no_hp',
        'alamat',
        'status' 
    ];
}
