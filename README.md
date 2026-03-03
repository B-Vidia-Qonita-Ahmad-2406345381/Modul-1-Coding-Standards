# Modul-1-Coding-Standards
**Reflection 1**
 
 Saat saya menulis refleksi 1 ini saya belum implementasi dua fungsi dari exercise 1: edit dan delete. Saya juga belum mengerjakan tutorial testing.
 
 Dari modul 1 ini, saya belajar bahwa kode yang baik adalah kode yang ditulis dengan clean dan secure. Kode yang clean/bersih adalah yang tidak memerlukan banyak comment (bahkan satu pun comment!) karena semua kode sudah jelas dan mudah dimengerti. Selain itu, untuk menjaga kode tetap clean, penting untuk melakukan refactoring (memperbaiki struktur kode).  Termasuk juga menggunakan indentasi yang benar agar gampang dibaca. 
 
 Di modul ini juga membahas tentang secure coding, atau prinsip menjaga agar kode tetap aman, contohnya adalah mengatur siapa saja yang bisa mengakses kode kita. Di tutorial ini, saya belajar menulis kode dengan Spring Boot, yang menggunakan MVC pattern (Model - View - Controller). Menurut saya, MVC ini merupakan suatu cara kita menjaga kode tetap aman, dengan memisahkan tugas-tugas sesuai kebutuhan. 
 
 Selama mengerjakan tutorial, ada saat-saat saya merasa kesulitan, baik saat menginstall aplikasi IDE mau pun saat menjalankan kode. Saya masih harus banyak belajar dan mereview materi, terutama di Java dan HTML. Banyak juga hal baru yang saya belum familiar di kode tutorial ini, terutama di bagian service. 
Setelah ini, saya akan berusaha menyelesaikan exercise dengan baik, mempelajari dan mereview materi-materi yang saya kurang paham, dan tentunya mengerjakan tutorial 2. 

The workflow mirrors Continuous Integration (CI) because every push to the development branch (module-2-exercise) or main branch triggers an automated build, checking that the Docker image can be built and is ready to be deployed.

The workflow also mirrors Continuous Deployment (CD) because every successful build is immediately pushed to the Heroku Container Registry and released to the production environment, without manual intervention.

The workflow facilitates rapid feedback: build or push errors are immediately visible in the GitHub Actions log, allowing the team to quickly fix issues, aligning with modern CI/CD principles.

# Modul-2-CI-CD
**Reflection**

1. Code quality issue: *Workflow does not contain permissions*

   Solusi: Tambahkan permissions di file `ci.yml` 
   ```
   permissions:
    contents: read
    security-events: write
    actions: read
   ```
2. **Evaluasi CI/CD Workflow**

   Workflow sudah mencerminkan *Continuous Integration*, karena setiap push ke branch `module-2-exercise` mau pun `main` langsung memicu build otomatis, serta memeriksa apakah Docker image siap di-build untuk deployment.

   Workflow juga mencerminkan *Continuous Development*, karena setiap build yang sukses akan otomatis di-push ke Heroku Container Registry dan di-release ke environment produksi.

   Workflow ini juga menerapkan rapid feedback, yaitu estiap error saat build atau push akan langsung terlihat di log GitHub Actions, sehingga bisa langsung diperbaiki oleh developer, sesuai dengan prinsip CI/CD.


# Modul-3-OO-Principles
**Reflection**
1. SOLID principles yang saya apply ke project ini:

SRP:
Pada kode ini, logika pembuatan objek Product dan Car masih sekaligus dengan meng-generate id. Sebaiknya dipisah agar tugas tidak tercampur antara menyimpan data dan generate id. Maka, saya memindahkan logika generate id ke fungsi lain dan dipanggil di fungsi create.

OCP: 
Pada kode ini, awalnya Car Controller extends Product Controller. Hal ini melanggar OCP karena seandainya Product Controller mengubah kodenya, Car Controller ikut berubah yang mana seharusnya tidak bisa dimodifikasi. Maka, saya menghapus extends Product Controller sehingga kedua Controller menjadi saling independen dan tetap bisa menjaga kode masing-masing.

LSP:
Pada kode ini, awalnya Car Controller extends Product Controller. Hal ini melanggar LSP karena Car Controller seharusnya bisa menggantikan Product Controller, tapi fungsi mappingnya sudah berbeda dengan Product Controller asli. Maka, saya menghapus extends Product Controller agar Car Controller tidak punya kewajiban menggantikan Product Controller dan bisa punya fungsi mapping sendiiri.

ISP:
Pada kode ini, semua kode tidak melanggar ISP karena semua fungsi sudah terpisah dan tidak ada yang memuat fungsi yang tidak ia butuhkan. Car dan Product masing-masing punya interface service sendiri sehingga sesuai dengan apa yang dibutuhkan masing-masing ketika implement service di CarServiceImpl dan ProductServiceImpl. 

DIP:
Pada kode ini, awalnya Car Controller menggunakan CarServiceImpl untuk memanggil service. Hal ini melanggar DIP, karena CarServiceImpl merupakan concrete class, dan seharusnya jika kita ingin menggunakan CarService yang merupakan abstraksi, langsung saja menggunakan objek CarService. Tujuannya agar Controller tidak dependent ke class concrete dan langsung menunjuk ke interface Service. Di Springboot, walaupun Controller tidak implement method Service, tapi nantinya isi fungsinya akan sesuai dengan yang ada di CarServiceImpl karena ada tag @Service di sana, yang menandakan itu bagian dari service. Jadi, tidak masalah jika kita langsung menunjuk ke CarService guna memenuhi DIP sebagai Controller.

2: Kegunaan SOLID principles di projek ini + contoh:

SRP:
Kode akan lebih mudah dibaca dan diuji pada tahap testing. Contohnya, seandainya ada error saat test penyimpanan data, kita bisa langsung cek saja ke Repository karena di sana logika utama saat data disimpan dan tidak perlu membongkar seluruh class.

OCP: 
Kita bisa menambah fitur baru tanpa merusak kode lama yang sudah bekerja dengan baik. Contohnya, seandainya kita ingin menambah Car jenis baru seperti ElectricCar, kita bisa extends Car saja tanpa mengubah kode Car asli, dan menambah implementasi baru ke class ElectricCar. Caranya bisa dengan membuat interface baru seperti ElectricVehicle dan diimplementasi ke ElectricCarServiceImpl agar tidak mengganggu CarService asli.

LSP:
Kode bisa saling menggantikan tanpa ada error. Contohnya, jika kita buat ElectricCar yang extends Car, ElectricCar tetap bisa menggantikan Car dengan implement CarService, ElectricVehicle di ElectricCarServiceImpl sehingga ElectricCar tetap bisa menggantikan Car sekaligus menambah fungsi baru.

ISP: Klien tidak terbebani dengan fungsi-fungsi yang tidak diperlukan. Contohnya, Car hanya mengimplementasi CarService yang fungsinya dipakai semua. Seandainya ada interface yang lebih umum seperti VehicleService (belum ISP) mungkin saja ada method yang tidak diperlukan Car jika mengacu ke interface itu, maka lebih baik fungsi-fungsi yang banyak di satu VehicleService dipecah ke interface-interface yang lebih khusus seperti CarService, BycicleService, dsb. 

DIP: Sistem akan lebih fleksibel dan tidak kaku (loose coupling). Contohnya, Controller memanggil ke abstraksi Service langsung, bukan ke ServiceImpl, sehingga Controller tidak tergantung ke suatu concrete class. Akan lebih baik kedua concrete class (seperti Controller dan ServiceImpl) mengimplementasikan atau memanggil ke abstraksi Service langsung sehingga tidak saling bergantung dan bisa punya implementasi masing-masing.  


3: Disadvantages jika tidak menerapan SOLID principles di projek ini:

SRP:
Kode jadi kaku dan fragile. Seandainya ada satu logika yang salah, kita harus mengubah fungsi ya

OCP: 

LSP:

ISP: 

DIP:
   


