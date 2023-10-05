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
        Schema::table('tb_order', function (Blueprint $table) {
            $table->bigInteger('no_hp')->default(0)->after('nama_pembeli');
            $table->text('alamat')->nullable()->after('no_hp');
            //
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::table('tb_order', function (Blueprint $table) {
            $table->dropColumn('no_hp');
            $table->dropColumn('alamat');
        });
    }
};
