<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class ProdukModel extends Model
{
    use HasFactory;
    protected $table = 'tb_produk';
 
    protected $fillable = [ 
        'id_kategori',
        'nama', 
        'harga',
        'gambar', 
    ];
}
