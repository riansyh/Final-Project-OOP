# Fifteen Puzzle VDJCISN

This repository is a final project (Java GUI) from Object-Oriented Programming Class, Teknik Informatika Universitas Padjadjaran.

[Challenge Guidelines](challenge-guideline.md)

**Fifteen Puzzle** adalah game teka-teki yang teknis permainannnya di geser. Game ini terdiri atas 1 bingkai berukuran n x n dimana didalamnya terdiri dari kotak yang jumlahnya n x n dan didalamnya terdapat nomor acak, dimana pemain harus memecahkan teka-teki tersebut dengan cara menggeser si kotak kosong terhadap kotak yang berisi nomor acak supaya menjadi berurutan.

## Credits

| NPM          | Name                      |
| ------------ | ------------------------- |
| 140810190002 | Rizal Herliansyah Hidayat |
| 140810190014 | Indra Kurniawan           |
| 140810190026 | Rian Febriansyah          |

## Change log

- **[Sprint Planning](changelog/sprint-planning.md) - (17/11/2020)**

  - Berdiskusi, mencari referensi, menentukan product backlog dan sprint backlog

- **[Sprint 1](changelog/sprint-1.md) - (16/11/2020 - 22/11/2020)**

  - Menyiapkan OOP dan Java GUI dengan JFrame
  - Membuat kerangka class FifteenPuzzle dan main class
  - Membuat default tiles dan grid puzzle
  - Membuat kondisi untuk custom tiles

- **[Sprint 2](changelog/sprint-2.md) - (23/11/2020 - 29/11/2020)**

  - Mengubah 1D array ke 2D array, mengisi angka, dan blank tiles
  - Membuat listener untuk mouse
  - Membuat method shuffle dan solvable
  - Membuat method new game

- **[Sprint 3](changelog/sprint-3.md) - (30/11/2020 - 06/12/2020)**
  - Membuat button
  - Membuat animasi ketika ubin di klik
  - Finishing dan menamba fitur

## Running The App

- Buka command prompt pada .../Final-Project-OOP/src
- ketik javac Main.java
- Lalu ketik java Main (untuk size 4 x 4) atau ketik java Main n (untuk size n x n)

## Classes Used

1. **Main** : Main.java

   - Main program di mana FifteenPuzzle dijalankan dalam UI.

2. **FramePuzzle** : FramePuzzle.java

   - Objek yang mengimplementasi class `Runanble` dan sebagai frame dari keseluruhan UI FifteenPuzzle.

3. **FifteenPuzzle** : FifteenPuzzle.java

   - Objek yang mengimplementasi `JPanel` sebagai logic dan class utama di dalam game ini.

   ![UML](/images/UML_15Puzzle.png)

## Notable Assumption and Design App Details

- Sebelum Aplikasi berjalan user dapat menginput ukuran tiles sehingga grid ubin akan berukuran n x n.
- Jika user tidak menginputkan ukuran tiles maka grid ubin secara default akan berukuran 4 x 4.
- Urutan angka setiap grid akan diacak kecuali pada blank tiles (ubin kosong).
- Untuk Mengecek apakah urutan tersebut dapat dipecahkan,berikut adalah aturan yang harus terpenuhi :
  - Setiap angka yang didahului oleh angka yang lebih besar dianggap sebagai inversi
  - Jumlah inversi dari puzzle harus genap.
- Saat user mencoba untuk menyelesaikan puzzle, mouse listener akan mengecek hal berikut :
  - Koordinat x dan y dimana klik-an tersebut berada
  - Jika ubin telah terurut maka ubin diacak lagi
  - Jika ubin belum terurut (game belum berakhir) maka pindahkan ubin ke tempat yang kosong.
  - Jika permainan terasa sulit user dapat menekan tombol reset untuk mengulang permainan.
- Permainan akan berakhir jika user berhasil mengurutkan angka pada ubin sampai n x n - 1.
- Dalam permainan terdapat tombol reset untuk mengulang kembali susunan puzzle.
