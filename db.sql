-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for db_franchisor
CREATE DATABASE IF NOT EXISTS `db_franchisor` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_franchisor`;

-- Dumping structure for table db_franchisor.failed_jobs
DROP TABLE IF EXISTS `failed_jobs`;
CREATE TABLE IF NOT EXISTS `failed_jobs` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `connection` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `payload` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `exception` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `failed_jobs_uuid_unique` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table db_franchisor.failed_jobs: ~0 rows (approximately)

-- Dumping structure for table db_franchisor.migrations
DROP TABLE IF EXISTS `migrations`;
CREATE TABLE IF NOT EXISTS `migrations` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table db_franchisor.migrations: ~12 rows (approximately)
INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
	(2, '2014_10_12_000000_create_users_table', 1),
	(3, '2014_10_12_100000_create_password_reset_tokens_table', 1),
	(4, '2019_08_19_000000_create_failed_jobs_table', 1),
	(5, '2019_12_14_000001_create_personal_access_tokens_table', 1),
	(6, '2023_09_25_150825_create_franchisor_models_table', 1),
	(7, '2023_09_27_143922_create_franchisee_models_table', 1),
	(8, '2023_09_27_144843_create_produk_models_table', 1),
	(9, '2023_09_27_145717_create_kategori_models_table', 1),
	(11, '2023_10_02_085413_add_id_franchisor_to_tb_produk', 1),
	(14, '2023_10_02_091755_create_cart_models_table', 2),
	(16, '2023_09_27_150903_create_order_models_table', 3),
	(21, '2023_10_05_222636_add_column_to_tb_order', 4);

-- Dumping structure for table db_franchisor.password_reset_tokens
DROP TABLE IF EXISTS `password_reset_tokens`;
CREATE TABLE IF NOT EXISTS `password_reset_tokens` (
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table db_franchisor.password_reset_tokens: ~0 rows (approximately)

-- Dumping structure for table db_franchisor.personal_access_tokens
DROP TABLE IF EXISTS `personal_access_tokens`;
CREATE TABLE IF NOT EXISTS `personal_access_tokens` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `tokenable_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tokenable_id` bigint unsigned NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `abilities` text COLLATE utf8mb4_unicode_ci,
  `last_used_at` timestamp NULL DEFAULT NULL,
  `expires_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `personal_access_tokens_token_unique` (`token`),
  KEY `personal_access_tokens_tokenable_type_tokenable_id_index` (`tokenable_type`,`tokenable_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table db_franchisor.personal_access_tokens: ~2 rows (approximately)
INSERT INTO `personal_access_tokens` (`id`, `tokenable_type`, `tokenable_id`, `name`, `token`, `abilities`, `last_used_at`, `expires_at`, `created_at`, `updated_at`) VALUES
	(1, 'App\\Models\\User', 1, 'auth_token', '998aa63d8179d9a4909a657029423571595bf82f5cb04a8d638af4915f4a89a1', '["*"]', '2023-10-10 15:45:42', NULL, '2023-10-02 02:15:52', '2023-10-10 15:45:42'),
	(12, 'App\\Models\\FranchiseeModel', 1, 'auth_token', '451d1647132361d553a130c95d2b6694575ac57c66b166da774a7092a0a2ea14', '["*"]', '2023-10-10 15:47:34', NULL, '2023-10-10 15:40:29', '2023-10-10 15:47:34');

-- Dumping structure for table db_franchisor.tb_aplikator
DROP TABLE IF EXISTS `tb_aplikator`;
CREATE TABLE IF NOT EXISTS `tb_aplikator` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nama` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` int NOT NULL DEFAULT '2',
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_aplikator_email_unique` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table db_franchisor.tb_aplikator: ~0 rows (approximately)
INSERT INTO `tb_aplikator` (`id`, `username`, `nama`, `email`, `password`, `role`, `remember_token`, `created_at`, `updated_at`) VALUES
	(1, 'aplikator', 'aplikator', 'aplikator@mail.com', '$2y$10$L8DeDi6ZVUn3nEp1tjAcGeIRAO0kFjxafgk61/nhZinvLRq53uGoi', 1, NULL, '2023-10-02 02:15:17', '2023-10-02 02:15:17');

-- Dumping structure for table db_franchisor.tb_cart
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE IF NOT EXISTS `tb_cart` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `id_order` int NOT NULL,
  `id_produk` int NOT NULL,
  `qty` int NOT NULL,
  `harga` int NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table db_franchisor.tb_cart: ~14 rows (approximately)
INSERT INTO `tb_cart` (`id`, `id_order`, `id_produk`, `qty`, `harga`, `created_at`, `updated_at`) VALUES
	(28, 19, 9, 2, 30000, '2023-10-05 06:09:44', '2023-10-05 06:09:44'),
	(29, 19, 10, 2, 23000, '2023-10-05 06:09:44', '2023-10-05 06:09:44'),
	(30, 20, 10, 2, 23000, '2023-10-05 06:10:13', '2023-10-05 06:10:13'),
	(31, 21, 10, 2, 23000, '2023-10-05 06:18:49', '2023-10-05 06:18:49'),
	(32, 21, 9, 2, 30000, '2023-10-05 06:18:49', '2023-10-05 06:18:49'),
	(33, 22, 10, 6, 23000, '2023-10-05 06:19:17', '2023-10-05 06:19:17'),
	(34, 22, 9, 2, 30000, '2023-10-05 06:19:17', '2023-10-05 06:19:17'),
	(35, 23, 4, 2, 21000000, '2023-10-05 13:24:37', '2023-10-05 13:24:37'),
	(36, 24, 1, 10, 5000, '2023-10-05 15:41:39', '2023-10-05 15:41:39'),
	(37, 25, 1, 10, 5000, '2023-10-05 15:42:28', '2023-10-05 15:42:28'),
	(38, 26, 1, 10, 5000, '2023-10-05 15:45:14', '2023-10-05 15:45:14'),
	(39, 27, 10, 3, 23000, '2023-10-06 15:28:13', '2023-10-06 15:28:13'),
	(40, 27, 9, 2, 30000, '2023-10-06 15:28:13', '2023-10-06 15:28:13'),
	(41, 27, 4, 2, 21000000, '2023-10-06 15:28:13', '2023-10-06 15:28:13'),
	(42, 28, 8, 2, 40000, '2023-10-07 13:40:44', '2023-10-07 13:40:44'),
	(43, 29, 9, 2, 30000, '2023-10-10 15:44:22', '2023-10-10 15:44:22'),
	(44, 29, 11, 3, 23000, '2023-10-10 15:44:22', '2023-10-10 15:44:22');

-- Dumping structure for table db_franchisor.tb_franchisee
DROP TABLE IF EXISTS `tb_franchisee`;
CREATE TABLE IF NOT EXISTS `tb_franchisee` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `id_franchisor` int NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` int NOT NULL DEFAULT '3',
  `pemilik` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `alamat` text COLLATE utf8mb4_unicode_ci,
  `nomor_telpon_outlet` int DEFAULT NULL,
  `pic` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nomor_pic` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table db_franchisor.tb_franchisee: ~0 rows (approximately)
INSERT INTO `tb_franchisee` (`id`, `id_franchisor`, `username`, `password`, `role`, `pemilik`, `email`, `alamat`, `nomor_telpon_outlet`, `pic`, `nomor_pic`, `created_at`, `updated_at`) VALUES
	(1, 2, 'franchisee', '$2y$10$Ar/Mva7cyoZfWB2gC6tTHeh7BfHKtTGYqWFWwEQM3HKOIvMgU2PGS', 3, 'pemilik er', 'j@gmail.com', 'alamat e', 888, 'pic', '245', '2023-10-02 08:48:14', '2023-10-10 06:05:11'),
	(2, 2, 'franchisee2', '$2y$10$Ar/Mva7cyoZfWB2gC6tTHeh7BfHKtTGYqWFWwEQM3HKOIvMgU2PGS', 3, 'pemilik er2', 'j2@gmail.com', 'alamat e2', 8880015, 'pic2', '245123', '2023-10-02 08:48:14', '2023-10-09 01:52:28');

-- Dumping structure for table db_franchisor.tb_franchisor
DROP TABLE IF EXISTS `tb_franchisor`;
CREATE TABLE IF NOT EXISTS `tb_franchisor` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `id_aplikator` int NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` int NOT NULL DEFAULT '2',
  `pemilik` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `alamat` text COLLATE utf8mb4_unicode_ci,
  `nomor_telpon_outlet` int DEFAULT NULL,
  `pic` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nomor_pic` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table db_franchisor.tb_franchisor: ~2 rows (approximately)
INSERT INTO `tb_franchisor` (`id`, `id_aplikator`, `username`, `password`, `role`, `pemilik`, `email`, `alamat`, `nomor_telpon_outlet`, `pic`, `nomor_pic`, `created_at`, `updated_at`) VALUES
	(2, 1, 'franchisor', '$2y$10$IFVrVt.wKMARVlPtYxIFbuKWfEMX1z12xatqFcmTnWxBmgToFumji', 2, 'pemilik', 'g@gmail.com', 'alamat', 885555, 'pic', '1234', '2023-10-02 08:32:46', '2023-10-02 08:46:59'),
	(3, 1, 'franchiso2r', '$2y$10$IFVrVt.wKMARVlPtYxIFbuKWfEMX1z12xatqFcmTnWxBmgToFumji', 2, 'pemilik2', 'g2@gmail.com', 'alamat2', 88555513, 'pic2', '1234455', '2023-10-02 08:32:46', '2023-10-02 08:46:59');

-- Dumping structure for table db_franchisor.tb_kategori
DROP TABLE IF EXISTS `tb_kategori`;
CREATE TABLE IF NOT EXISTS `tb_kategori` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gambar` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table db_franchisor.tb_kategori: ~3 rows (approximately)
INSERT INTO `tb_kategori` (`id`, `nama`, `gambar`, `created_at`, `updated_at`) VALUES
	(1, 'Bahan Baku', '20231003153048.png', '2023-10-03 08:30:48', '2023-10-03 08:30:48'),
	(2, 'Chicken', '20231003153147.png', '2023-10-03 08:31:47', '2023-10-03 08:31:47'),
	(3, 'Alat Masak', '20231003153159.png', '2023-10-03 08:31:59', '2023-10-03 08:31:59'),
	(7, 'Bahan Mentah', '20231003153415.png', '2023-10-03 08:34:15', '2023-10-03 08:34:15');

-- Dumping structure for table db_franchisor.tb_order
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE IF NOT EXISTS `tb_order` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `tanggal` date NOT NULL,
  `id_franchisee` int NOT NULL,
  `nama_pembeli` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `no_hp` bigint NOT NULL DEFAULT '0',
  `alamat` text COLLATE utf8mb4_unicode_ci,
  `status` int NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table db_franchisor.tb_order: ~10 rows (approximately)
INSERT INTO `tb_order` (`id`, `tanggal`, `id_franchisee`, `nama_pembeli`, `no_hp`, `alamat`, `status`, `created_at`, `updated_at`) VALUES
	(19, '2022-10-05', 1, 'tesnama', 0, NULL, 1, '2023-10-05 06:09:44', '2023-10-05 06:09:44'),
	(20, '2023-10-05', 1, 'tesnama', 0, NULL, 1, '2023-10-05 06:10:13', '2023-10-05 06:10:13'),
	(21, '2023-10-05', 1, 'tesnama', 0, NULL, 1, '2023-10-05 06:18:49', '2023-10-05 06:18:49'),
	(22, '2023-10-05', 1, 'tesnama', 0, NULL, 1, '2023-10-05 06:19:17', '2023-10-05 06:19:17'),
	(23, '2023-10-05', 1, 'tesnama', 0, NULL, 1, '2023-10-05 13:24:37', '2023-10-05 13:24:37'),
	(24, '2023-10-02', 1, 'nama_pembeli', 0, NULL, 1, '2023-10-05 15:41:39', '2023-10-05 15:41:39'),
	(25, '2023-10-02', 1, 'nama_pembeli', 0, NULL, 1, '2023-10-05 15:42:27', '2023-10-05 15:42:27'),
	(26, '2023-10-02', 1, 'nama_pembeli', 835454545, 'dadadagfg hhfh', 1, '2023-10-05 15:45:14', '2023-10-05 15:45:14'),
	(27, '2023-10-06', 1, 'a', 82585, 'yfucuf', 1, '2023-10-06 15:28:13', '2023-10-07 14:09:31'),
	(28, '2023-10-10', 1, 'g', 6, 'h', 2, '2023-10-07 13:40:44', '2023-10-07 14:10:26'),
	(29, '2023-10-10', 1, 'vg', 88, 'fgg', 2, '2023-10-10 15:44:22', '2023-10-10 15:44:36');

-- Dumping structure for table db_franchisor.tb_produk
DROP TABLE IF EXISTS `tb_produk`;
CREATE TABLE IF NOT EXISTS `tb_produk` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `id_franchisor` int NOT NULL DEFAULT '0',
  `id_kategori` int NOT NULL,
  `nama` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `harga` int NOT NULL,
  `gambar` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table db_franchisor.tb_produk: ~12 rows (approximately)
INSERT INTO `tb_produk` (`id`, `id_franchisor`, `id_kategori`, `nama`, `harga`, `gambar`, `created_at`, `updated_at`) VALUES
	(1, 2, 2, 'Ayam Kakas', 5000000, '20231002091611.png', '2023-10-02 02:16:11', '2023-10-02 02:16:11'),
	(2, 2, 2, 'prodvu', 5000000, '20231010223148.jpg', '2023-10-02 09:41:18', '2023-10-10 15:31:48'),
	(3, 2, 3, 'Kompor Mawar', 7000000, '20231004143421.png', '2023-10-04 07:34:21', '2023-10-04 07:34:21'),
	(4, 2, 3, 'Deep Fryer', 21000000, '20231004143507.png', '2023-10-04 07:35:07', '2023-10-04 07:35:07'),
	(5, 2, 3, 'Rice Cooker', 1100000, '20231004143558.png', '2023-10-04 07:35:58', '2023-10-04 07:35:58'),
	(6, 2, 3, 'Pemanggang', 1950000, '20231004143617.png', '2023-10-04 07:36:17', '2023-10-04 07:36:17'),
	(7, 2, 2, 'Ayam Fillet Paha', 1950, '20231004144557.png', '2023-10-04 07:45:57', '2023-10-04 07:45:57'),
	(8, 2, 2, 'Ayam Fillet Dada', 40000, '20231004144626.png', '2023-10-04 07:46:26', '2023-10-04 07:46:26'),
	(9, 2, 1, 'Kecap Manis Bango', 30000, '20231004144709.png', '2023-10-04 07:47:09', '2023-10-04 07:47:09'),
	(10, 2, 1, 'Minyak Goreng', 23000, '20231004144731.png', '2023-10-04 07:47:31', '2023-10-04 07:47:31'),
	(11, 2, 1, 'Minyak Goreng', 23000, '20231009074519.png', '2023-10-09 00:45:19', '2023-10-09 00:45:19'),
	(12, 2, 2, 'prod edd', 5000000, '20231010221707.jpg', '2023-10-10 15:17:07', '2023-10-10 15:17:07');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
