<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('tb_franchisee', function (Blueprint $table) {
            $table->id(); 
            $table->integer('id_franchisor');
            $table->string('username');
            $table->string('password');
            $table->integer('role')->default(3);
            $table->string('pemilik');
            $table->string('email')->nullable();  
            $table->text('alamat')->nullable();
            $table->integer('nomor_telpon_outlet')->nullable();
            $table->string('pic')->nullable();
            $table->string('nomor_pic')->nullable(); 
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('tb_franchisee');
    }
};
