# Tutorial APAP
## Authors
* **Fahira Anya Katili** - *1906399770* - *C*
---
## Tutorial 8
### Pertanyaan
1. Ceritakan langkah - langkah yang kalian lakukan untuk solve LATIHAN no.1, dan mengapa kalian melakukan langkah - langkah tersebut?
Saya tidak mengalami masalah tersebut sehingga tidak melakukan apapun untuk menanganinya.

2. Jelaskan fungsi dari async dan await!
Kedua fitur tersebut berfungsi untuk mempermudah proses asynchronous. Async berguna untuk mengubah suatu function menjadi asynchronous, sedangkan await berguna untuk menunda eksekusi hingga proses asynchronous tersebut selesai.
Sumber: https://medium.com/coderupa/panduan-komplit-asynchronous-programming-pada-javascript-part-4-async-await-fc504c344238

3. Masukkan jawaban dari Screenshot yang diperintahkan di halaman 9 pada Component Lifecycle
pada pertanyaan ini.
Step | Screenshot
------------- | -------------
1 | https://ibb.co/fFmfGfg & https://ibb.co/K5w9RN1
3 | https://ibb.co/Bgdsc5M
6 | https://ibb.co/8YXmTmK
7 | https://ibb.co/272BPN0
8 | https://ibb.co/68YGSDP
9 | https://ibb.co/94gJZgy (sebelum menambahkan `return true;` pada `shouldComponentUpdate`) & https://ibb.co/t8MHkRg (setelah menambahkan `return true;` pada `shouldComponentUpdate`)


4. Jelaskan fungsi dari componentDidMount, shouldComponentUpdate,
componentDidUpdate, componentWillReceiveProps, componentWillUnmount.
Notes : Penjelasan harus mencantumkan “kapan fungsi dipanggil” dan “use case apa saja
yang biasanya menggunakan lifecycle method tersebut”.
* `componentDidMount` dipanggil tepat setelah component dipasang (being mounted). Jika perlu untuk me-load data dari remote endpoint, fungsi ini baik digunakan untuk menginisiasi network requests.
* `shouldComponentUpdate` dipanggil sebelum rendering, yaitu ketika props atau state baru sedang diterima. Fungsi ini digunakan untuk memberi tahu React bahwa output component tidak dipengaruhi oleh state atau props saat ini.
* `componentDidUpdate` dipanggil tepat setelah terjadi update. Fungsi ini digunakakan untuk mengoperasikan DOM ketika component sudah di-update. Selain itu, fungsi ini juga baik untuk melakukan network requests.
* `componentWillReceiveProps` dipanggil ketika component menerima props baru, tetapi sebelum me-render. 
* `componentWillUnmount` dipanggil tepat sebelum component dihapus atau di-unmount. Fungsi ini digunakan untuk membersihkan seluruh connection requests ataupun subscription yang dibuat pada componentDidMount().
Sumber: https://reactjs.org/docs/react-component.html dan https://www.digitalocean.com/community/tutorials/react-lifecycle-functions

---
## Tutorial 7
### Pertanyaan
1. Jelaskan apa yang Anda lakukan di latihan dalam satu paragraf per-soal. Berikan screenshot sebagai ilustrasi dari apa yang Anda jelaskan.
*Soal 1*
Pada soal ini, saya diminta untuk memberikan fungsi pada button trash. Untuk itu, saya menambahkan function `handleDeleteItemFromCart` pada index.js Home maupun HomeFunc. Pada Home/index.js, saya membuat const cartItems yang merepresentasikan items yang ada pada cart saat ini dan membuat cartItemsBaru yang merepresentasikan items yang sudah difilter (items yang tidak di-click). Kemudian, ubah state dari cartItems menjadi cartItemsBaru. Setelah itu, baru panggil function update ShopItem() dengan `this.updateShopItem(cartItemBaru, false)`. Pada HomeFunc/index.js, saya hanya membuat const cartItemsBaru. Kemudian, untuk mengubah states, saya menggunakan `setCartItems(cartItemsBaru)`.
Screenshot code: https://ibb.co/CV11YgJ

*Soal 2*
Pada soal nomor 2, saya diminta untuk menghandle berkurang atau bertambahnya balance ketika menambah ataupun menghapus item pada cart. Agar balance berkurang ketika memasukkan item ke dalam cart, saya menambahkan `balance : (prevState.balance-item.price)` pada. Sebaliknya, agar balance bertambah lagi ketika menghapus item dari cart, pada Home/index.js, saya menambahkan `balance: prevState.balance+intem.price` ketika mengubah state. Untuk HomeFunc/index.js, saya hanya menggunakan `setBalance((prev)=>prev+item.price);` ketika menghapus item dari cart dan `setBalance((prev)=>prev-item.price);` ketika menambah item ke cart.
Screenshot code: https://ibb.co/vQdD1zh

*Soal 3*
Pada soal terakhir, saya diminta untuk menghandle apabila memasukkan item yang harganya melebihi balance yang dimiliki. untuk intu saya menambahkan condition `if ((this.state.balance-item.price) >= 0)` pada class-based dan `if (balance-item.price >= 0)` pada functional-based.
Screenshot code: https://ibb.co/1zYZtMr

2. Menurut pemahaman kamu selama pengerjaan tutorial ini, apa perbedaan antara state dan props?
State merupakan data yang tersimpan dalam sebuah component secara private. State tidak dapat diakses dari atau oleh component lain karena hanya relevan terhadap component yang mendefinisikan state itu sendiri.
Sedangkan props merupakan alat komunikasi antar component yang sifatnya read-only. Berbeda dengan state, data props dapat dilempar dari component lain.
Sumber: https://medium.com/coderupa/react-prop-state-apa-bedanya-7ee61df8257f dan https://www.mahirkoding.com/tutorial-react-perbedaan-state-dan-props/

3. Menurut kamu, apakah sebaiknya kita menggunakan component (e.g. List, Item) dalam React? sebutkan alasannya.
Salah satu alasan mengapa Reach sangat banyak digunakan oleh para developer adalah karena fitur component-based yang memungkinkan mereka untuk membuat encapsilated component. Component bersifat modular sehingga dapat digunakan berulang kali. Hal tersebut dapat mempersingkat waktu dan juga tenaga dalam pengembangan web.
Sumber: https://www.softwareseni.co.id/blog/react-js

4. Apa perbedaan class component dan functional component?
Terdapat dua tipe component dalam React, yaitu Class Component dan Functional Component. Berikut contoh kode dari kedua tipe component.
* Class Component
class App extends Component {
  render() {
    return (
      <div>
        <h1>Hello World</h1>
      </div>
    );
  }
}
// kode dari hobikoding.github.io/component-react/

* Functional Component
function App(){
  return(
    <div>
      <h1>Hello World</h1>
    </div>
  )
}
// kode dari hobikoding.github.io/component-react/
Jika dilihat secara sekilas, functional component memiliki kode yang lebih ringkas dan mudah dimengerti. Namun, sebenarnya terdapat perbedaan yang signifikan di antara kedua tipe component. Pada functional component, component hanya dapat menggunakan prop sehingga disebut *stateless component*. Sedangkan pada class component, dapat menggunakan prop dan juga state.
Sumber: https://hobikoding.github.io/component-react/

5. Dalam react, apakah perbedaan component dan element?
Element merupakan unit terkecil dalam React dan biasanya didefinisikan sebagai representasi virtual dari DOM. Element bersifat immutable yang berarti attribute dan childrennya tidak dapat diubah setelah dibuat. Selain itu, kita juga tidak bisa menerapkan method apapun dalam element. Untuk menampilkan React element pada root DOM, element tersebut harus di-pass ke ```ReactDOM.render()```. Sedangkan component merupakan function atau class yang menerima sebuah input dan me-return sebuah element.
Sumber: https://stackoverflow.com/questions/30971395/difference-between-react-component-and-react-element
---
## Tutorial 6
### Pertanyaan
1. Jelaskan secara singkat perbedaan Otentikasi dan Otorisasi! Di bagian mana (dalam kode
yang telah anda buat) konsep tersebut diimplementasi?
Otentikasi adalah adalah proses verifikasi pengguna oleh sistem. Proses ini bertujuan untuk membuktikan identitas pengguna. Ketika pengguna memasukkan username dan password, itu adalah proses otentikasi. Implementasi otentikasi dalam kode terdapat pada class WebSecurityConfig.java, yaitu: 
```
@Autowired
public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
}
```
Otorisasi terjadi setelah pengguna melewati proses otentikasi. Proses ini bertujuan untuk memberikan hak akses kepada pengguna untuk dapat mengakses resources atau data tertentu. Implementasinya dalam kode terdapat pada class WebSecurityConfig.java, misalnya:
```
.antMatchers("/user/viewall").hasAuthority("ADMIN")
```
* Potongan baris di atas berarti halaman dengan path user/viewall hanya dapat diakses oleh  pengguna dengan role ADMIN.

2. Apa itu BCryptPasswordEncoder? Jelaskan secara singkat cara kerja dan tujuannya.
BCrypthPasswordEncoder adalah fungsi yang menggunakan algoritma BCrypt dan berfungsi sebagai password encoder. Dengan fungsi ini, password akan dienkripsi, kemudian hasilnya akan disimpan ke dalam database. Jika pengguna menyatakan bahwa mereka lupa akan password mereka, makan password yang sudah terenkripsi tersebuh harus dibuat dan disimpan ulang di database.

3. Apakah penyimpanan password sebaiknya menggunakan encryption atau hashing? Mengapa
demikian?
Hashing dan encryption menyediakan cara untuk menjaga keamanan data yang bersifat sensitif. Namun, hampi di setiap keadaan, password harus di-hash, bukan dienkripsi. Hashing merupakan fungsi satu arah yang cocok untuk password validation. Dengan begitu, walaupun penyerang berhasil mendapatkan hashed password pengguna, mereka tidak dapat memasukkannya ke field password dan login sebagai korban. 
Berbeda dengan hashing, encryption merupakan fungsi dua arah, yang artinya plaintext orisinil datanya dapat dengan mudah diambil. Encryption lebih cocok digunakan untuk menyimpan data yang sifatnya tidak sensitif, misalnya alamat atau nomor telepon.

4. Jelaskan secara singkat apa itu UUID beserta penggunaannya!
Universally Unique Identifier (UUID) merupakan 128 bit value yang digunakan untuk mengidentifikasi sebuah object atau entity secara unik. UUID dapat dijamin berbeda atau setidaknya sangat kecil probabilitasnya untuk terulang, tergantung pada mekanisme yang digunakan. Karena keunikannya, UUID digunakan untuk meningkatkan keamanan data pengguna. Id pengguna akan dibuat dengan hashing 32 karakter secara unik dan acak sehingga id tersebut akan sulit diretas.

5. Apa kegunaan class UserDetailsServiceImpl.java? Mengapa harus ada class tersebut
padahal kita sudah memiliki class UserRoleServiceImpl.java?
UserDetailsServiceImpl.java merupakan class yang mengimplementasikan interface UserDetailsService yang diimport dari org.springframework.security.core. Interface UserDetailsService tersebut digunakan sebagai user DAO untuk mengambil informasi autentikasi serta autorisasi pengguna. 

---
## Tutorial 5
### Pertanyaan
1. Apa itu Postman? Apa kegunaannya?
* Postman adalah aplikasi yang berfungsi REST CLIENT yang biasa digunakan developer untuk menguji API yang telah dibuat. Pada awalnya, Postman merupakan add on pada Chrome, tetapi sekarang sudah menjadi aplikasi native. Fungsi utama dari aplikasi ini adalah adalah sebagai GUI API Caller. 

2. Jelaskan fungsi dari anotasi @JsonIgnoreProperties dan @JsonProperty.
* @JsonIgnoreProperties merupakan anotasi yang digunakan untuk menandai property atau sekumpulan property yang akan diabaikan. Anotasi ini digunakan pada level Class
* @JsonProperty merupakan anotasi yang digunakan untuk mengindikasi nama property pada JSON. Biasanya, anotasi ini digunakan untuk serialize atau deserialize nama property ketika sedang berurusan dengan non-standard setter dan getter.

3. Apa kegunaan atribut WebClient?
* Atribut WebClient yang digunakan pada restServiceImpl berfungsi untukmengirim dan menerima data dari resource URI.

4. Apa itu ResponseEntity dan BindingResult? Apa kegunaannya?
* ResponseEntity merupakan perwakilan seluruh respons HTTP. Dengan ResponseEntity, kita dapat mengontrol status code yang ditandai dengan @ResponseStatus, header, dan body yang ditandai dengan @ResponseBody.
* BindingResult merupakan sebuah interface yang menentukan bagaimana suatu object menyimpan dan mengambil hasil dari suatu validasi. Object BindingResult dapat dijadikan argumen untuk method Validator di dalam Controller.

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
