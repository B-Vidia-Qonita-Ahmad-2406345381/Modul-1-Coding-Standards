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
   


