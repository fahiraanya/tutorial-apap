# Tutorial APAP
## Authors
* **Fahira Anya Katili** - *1906399770* - *C*
---
## Tutorial 3
### Pertanyaan
1. Tolong jelaskan secara singkat apa kegunaan dari anotasi-anotasi yang ada pada model (@AllArgsConstructor, @NoArgsConstructor, @Setter, @Getter, @Entity, @Table)
Anotasi yang ada pada model merupakan anotasi yang dimiliki oleh Lombok yang dapat membantu kita untuk melakukan constructor injection secara otomatis. Menggunakan anotasi-anotasi dari Lombok tersebut dapat membantu kita untuk menghindari adanya kode yang repetitif. Berikut penjelasan dari masing-masing anotasi.
* @AllArgsConstructor digunakan untuk membuat constructor untuk semua field yang ada pada suatu class
* @NoArgsContructor digunakan untuk membuat empty constructor
* @Setter & @Getter digunakan untuk membuat setter ataupun getter default
* @Entity digunakan pada entity class artinya field-field pada class tersebut merepresentasikan sebuah object pada database
* @Table digunakan untuk memberi nama pada tabel. Apabila tidak ada anotasi tersebut, tabel akan diberi nama sama dengan nama class entity tersebut.

Referensi: https://www.baeldung.com/spring-injection-lombok, https://www.baeldung.com/intro-to-project-lombok, https://www.objectdb.com/java/jpa/start/entity, https://huongdanjava.com/learn-about-entity-and-table-annotation-in-jpa.html

2. Pada class BioskopDB, terdapat method findByNoBioskop, apakah kegunaan dari method tersebut?
Pada Spring Data JPA terdapat derived query method yang dapat melakukan query find, read, count, dan get. Method tersebut dapat digunakan dengan *queryByName*. Seperti yang ada pada class BioskopDB, terdapat method findByNoBioskop yang digunakan untuk me-filter data berdasarkan field noBioskop. Method tersebut sama saja seperti menjalankan perintah SELECT * FROM noBioskop pada SQL.

Referensi: https://www.youtube.com/watch?v=hq06DG4FX3s

3. Jelaskan perbedaan kegunaan dari anotasi @JoinTable dan @JoinColumn
Anotasi @JoinTable menyimpan id dari kedua entity yang di-join ke dalam sebuah tabel terpisah, sedangkan @JoinColumn menyimpan id dari entity yang berbeda ke dalam kolom baru di tabel yang sama. Anotasi @JoinColumn dapat digunakan ketika terdapat entity-entity yang memiliki hubungan langusng, misalnya foreign key antara dua entity. Anotasi @JoinTable dapat digunakan ketika terdapat hubungan antar entity pada tabel yang berbeda.

Rerefensi: https://javakeypoint.wordpress.com/2020/04/21/difference-between-joincolumn-and-jointable-in-hibernate/

4. Pada class PenjagaModel, digunakan anotasi @JoinColumn pada atribut bioskop, apa kegunaan dari name, referencedColumnName, dan nullable dalam anotasi tersebut? dan apa perbedaan nullable dan penggunaan anotasi @NotNull
Pada atribut bioskop, terdapat anotasi @JoinColumn yang didalamnya terdapat beberapa properti, seperti name, referencedColumnName, dan nullable. Properti *name* tersebut menunjukkan nama kolom yang menjadi foreign key. Sedangkan, properti *referenceColumnName* menjunjukkan nama kolom yang dijadikan referensi dari foreign key tersebut. Properti *nullable* digunakan untuk menentukan apakah kolom tersebut dapat bernilai null atau tidak.

Referensi: https://www.baeldung.com/jpa-join-column

5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER
Terdapat dua tipe pengambilan data pada Hibertane, yaitu LAZY dan EAGER. Perbedaan di antara keduanya terletak pada kapan data tersebut dimuat. Pada FetchType.EAGER, data di-load secara langsung, sedangkan pada FetchType.LAZY loading data dapat ditunda hingga ada pemanggilan method secara eksplisit. Kedua tipe tentunya memiliki kelebihan dan kekurangannya masing-masing sehingga penggunaannya dapat disesuaikan dengan kebutuhan.

Referensi: https://www.baeldung.com/hibernate-lazy-eager-loading

---

## Tutorial 2
### Pertanyaan
1. Cobalah untuk menambahkan sebuah Bioskop dengan mengakses link berikut: http://localhost:8080/bioskop/add?idBioskop=1&namaBioskop=Bioskop%20PAPA%20 APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx&jumlahStudio=10 Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.
   * Ketika mengakses link tersebut, ditampilkan halaman Whitelabel Error Page. Hal ini terjadi karena pada tahap ini, saya baru membuat method untuk menambah bioskop tanpa membuat templatenya.
   * Screenshot: https://ibb.co/brS9XNz
   
2. Menurut kamu anotasi @Autowired pada class Controller tersebut merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja @Autowired tersebut dalam konteks service dan controller yang telah kamu buat
   * Anotasi @Autowired pada class Controller merupakan implementasi dari konsep Dependency Injection (DI) yang memungkinkan Spring untuk me-resolve dan meng-inject collaborating beans ke dalam bean kita. Cara kerja dari @Autowired adalah interface dari suatu dependency akan ter-inject secara otomatis ke Service yang mengimplementasikan autowiring tersebut. Pada BioskopController, terdapat anotasi @Service yang menandakan bahwa itu adalah Service. 
3. Cobalah untuk menambahkan sebuah Bioskop dengan mengakses link berikut: http://localhost:8080/bioskop/add?idBioskop=1&namaBioskop=Bioskop%20PAPA%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.
   * Ditampilkan halaman Whitelabel Error Page dan terdapat error message “Required request parameter ‘jumlahStudio’ for method parameter type int is not present”, yang artinya error tersebut terjadi karena kurangnya parameter jumlahStudio pada link tersebut.
   * Screenshot: https://ibb.co/KFpSFWT
   
4. Jika Papa APAP ingin melihat Bioskop dengan nama Bioskop Maung, link apa yang harus diakses?
   * Pada method yang dapat dilihat di https://ibb.co/m68X50k, saya menggunakan Path Variable dengan value idBioskop. Untuk melihat bioskop sesuai dengan nama bioskopnya, ubah value pada @PathVariable menjadi namaBioskop sehingga link yang harus diakses adalah http://localhost:8080/bioskop/view/nama-bioskop/{namaBioskop}.

5. Tambahkan 1 contoh Bioskop lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/bioskop/viewall, apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.
   * Menambah bioskop: http://localhost:8080/bioskop/add?idBioskop=2&namaBioskop=Bioskop%20XXI&alamat=PIM&noTelepon=081xxx&jumlahStudio=5
   * Screenshot: https://ibb.co/w79KXVP (berhasil ditambah) dan https://ibb.co/PYm6tkk (hasil)

### Latihan
1. Pada BioskopController tambahkan sebuah method view Bioskop dengan menggunakan Path Variable. Misalnya, kamu ingin memasukkan data sebuah Bioskop yang memiliki idBioskop 1, untuk melihat data yang baru dimasukkan tersebut, user dapat mengakses halaman http://localhost:8080/bioskop/view/id-bioskop/1.
   * Screenshot: https://ibb.co/k9ZDsZG
2. Tambahkan fitur untuk melakukan update jumlahStudio Bioskop berdasarkan idBioskop. Misalnya, setelah melakukan add Bioskop pada tahap 1 bab View Template, cobalah untuk mengubah jumlahStudio objek Bioskop tersebut menjadi 999 dengan mengakses halaman http://localhost:8080/bioskop/update/id-bioskop/1/jumlah-studio/999. Tampilkan juga sebuah halaman yang memberikan informasi bahwa data tersebut telah berhasil diubah. 
   * Screenshot: https://ibb.co/7b2L71Z dan https://ibb.co/7Wd0dN5
3. Tambahkan fitur untuk melakukan delete Bioskop berdasarkan idBioskop. Misalnya, setelah melakukan add Bioskop pada tahap 1 bab View Template dan melakukan update seperti pada latihan nomor 2, cobalah untuk melakukan delete data tersebut dengan mengakses halaman http://localhost:8080/bioskop/delete/id-bioskop/1. Tampilkan sebuah halaman yang memberikan informasi bahwa data tersebut telah berhasil dihapus. 
   * Screenshot: https://ibb.co/MRw9r5H dan https://ibb.co/fnHvCnJ
---

## Tutorial 1
### What I have learned today
(Masukkan pertanyaan yang diikuti jawaban di setiap nomor, contoh seperti dibawah. Anda juga boleh menambahkan catatan apapun di bagian ini)
### Github
1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?
Issue tracker adalah sebuah fitur di GitHub yang dapat membantu kita untuk mencatat issue atau masalah pada repository. Masalah yang dapat diselesaikan dengan fitur ini antara lain masalah yang berkaitan dengan bug, documentation, enchancement, dan masih banyak lagi (dapat dilihat pada bagian Label ketika membuat issue baru).

Referensi: https://guides.github.com/features/issues/

2. Apa perbedaan dari git merge dan git merge --squash?
Kedua perintah memiliki tujuan yang sama, yaitu menggabungkan beberapa branch dengan main. Bedanya, pada git merge, beberapa commit dari branch yang berbeda akan di-merge ke main. Sedangkan pada git merge --squash, beberapa commit yang akan di-merge dijadikan ke dalam satu commit terlebih dahulu, kemudian baru di-merge.

Referensi: https://rietta.com/blog/github-merge-types/

3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan suatu aplikasi?
Version control dapat me-manage apabila terjadi perubahan pada file, dokumen, ataupun dataset. Setiap ada perubahan, akan disimpan menjadi satu versi. Hal tersebut membantu kita untuk melihat perkembangan yang terjadi pada setiap perubahan. Selain itu, version control juga menyediakan bukti setiap revisi atau perubahan serta kontribusi apa yang dilakukan oleh developer.

Referensi: https://reqtest.com/requirements-blog/what-are-benefits-of-version-control/

### Spring
4. Apa itu library & dependency?
Library pada Spring berisi koleksi berbagai fungsi yang dapat digunakan pada pengembangan program atau aplikasi. Sedangkan depencency pada Spring berarti keterkaitan antar fungsi atau class pada aplikasi. Misalnya, terdapat sebuah class A yang mengimplement class B, berarti A dependency dari B.

Referensi: https://www.idtech.com/blog/what-are-libraries-in-coding & https://www.springboottutorial.com/spring-framework-what-is-a-dependency

5. Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven? 
Maven merupakan build automation tool yang biasanya digunakan proyek Java. Sebenarnya, kita bisa menggunakan Spring tanpa menggunakan Maven, tetapi karena kelebihan-kelebihannya, banyak yang merekomendasikan untuk menggunakan Maven. Dengan power yang disediakan Maven, project yang sedang dilakukan akan lebih mudah. Selain itu, Maven juga dapat membantu kita mengintegrasikan project yang sedang dikerjakan dengan git. Alternatif dari Maven yang dapat digunakan, antara lain Sonatype Nexus, CMake, Gradle, dll.

Referensi: https://spring.io/blog/2011/01/17/green-beans-getting-started-with-maven-and-spring/#:~:text=Apache%20Maven%20is%20a%20popular,approach%20to%20project%20build%20management.&text=Even%20though%20you%20can%20use,its%20use%20to%20Spring%20developers.

6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring framework?
Spring framework juga dapat digunakan untuk microservices, reactive, cloud, serverless, event driven, dan batch.

Referensi: https://spring.io/

7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya menggunakan @RequestParam atau @PathVariable?
Kedua hal tersebut memiliki tujuan yang sama, yaitu untuk mem-fecth request parameter. Perbedaannya terletak pada penggunaannya. Pada @RquestParam digunakan untuk mengakses nilai dari parameter query, sedangkan @PathVariable digunakan untuk mengakses nilai dari template URI.

Referensi: https://www.dineshonjava.com/requestparam-vs-pathvariable-annotations-in-spring-mvc/

### What I did not understand
(tuliskan apa saja yang kurang Anda mengerti, Anda dapat men-_check_ apabila Anda sudah mengerti dikemudian hari, dan tambahkan tulisan yang membuat Anda mengerti) - [ ] Kenapa saya harus belajar APAP?
- [ ] Sebenarnya saya masih bingung dengan cara kerja GitHub.
