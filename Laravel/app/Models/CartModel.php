<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class CartModel extends Model
{
    use HasFactory;
    protected $table = 'tb_cart';
 
    protected $fillable = [ 
        'id_order',
        'id_produk',
        'qty',
        'harga' 
    ];
}
