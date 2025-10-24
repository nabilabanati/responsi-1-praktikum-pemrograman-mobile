# Identitas

Nama        : Nabila Rizki Banati

NIM         : H1D023086

Shift Awal  : C

Shift Baru  : D

# Video/ gif Demo Aplikasi

![Demo Responsi 1](demo_responsi.gif)

# Penjelasan Alur Data Mulai dari Pemanggilan ke API Hingga Penyajian di Layar.

Alur data dalam aplikasi ini dimulai dari konfigurasi Retrofit pada kelas `RetrofitInstance` yang mengimplementasikan OkHttp Client dengan interceptor untuk menambahkan header autentikasi berisi API key "feaab1988df44f03827076ebc391bbf2" pada setiap request ke base URL `https://api.football-data.org/v4/teams/`. Interface `FootballDataApi` mendefinisikan dua endpoint dengan metode `getClubSquad()` dan `getHeadCoach()` yang menerima parameter `clubId` bernilai 322 untuk mengambil data skuad tim dan pelatih kepala. Ketika `MainActivity` menginisialisasi listener pada layout menu, pengguna dapat memilih untuk membuka `TeamSquadActivity` atau `HeadCoachActivity`, yang masing-masing menggunakan `MainViewModel` untuk memanggil fungsi `fetchSquad()` atau `fetchCoach()` melalui coroutine. Response dari API kemudian diparsing menggunakan Gson Converter menjadi objek data class `TeamSquadResponse` atau `HeadCoachResponse` yang berisi entitas `TeamSquad` dan `HeadCoach` dengan properti seperti id, name, dateOfBirth, nationality, dan position. Data yang telah dimapping disimpan dalam `LiveData` pada ViewModel dan diobservasi oleh Activity untuk kemudian ditampilkan, dimana `TeamSquadActivity` menggunakan `TeamSquadAdapter` dengan `RecyclerView` untuk menampilkan daftar pemain dengan warna kartu yang berbeda berdasarkan posisi, sementara `HeadCoachActivity` menampilkan informasi pelatih pada layout statis, dan ketika item pemain diklik, `PlayerDetailFragment` sebagai Bottom Sheet Dialog menampilkan detail lengkap pemain yang dipilih.

# Visualisasi Alur Data

```
┌───────────────────────────────────────────────────────────────┐
│                         User Interface                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐         │
│  │ MainActivity │  │HeadCoachAct. │  │TeamSquadAct. │         │
│  └──────┬───────┘  └──────┬───────┘  └──────┬───────┘         │
│         │                  │                │                 │
└─────────┼──────────────────┼────────────────┼──────────────── ┘
          │                  │                │
          ▼                  ▼                ▼
   ┌─────────────────────────────────────────────────┐
   │            MainViewModel (LiveData)             │
   │  ┌──────────────┐        ┌──────────────┐       │
   │  │ fetchCoach() │        │ fetchSquad() │       │
   │  └──────┬───────┘        └──────┬───────┘       │
   └─────────┼───────────────────────┼───────────────┘
             │                       │
             ▼                       ▼
   ┌─────────────────────────────────────────────────┐
   │          RetrofitInstance + Interceptor         │
   │  (Menambahkan API Key ke Header Request)        │
   │  Header: X-Auth-Token: feaab1988df44f03...      │
   └─────────────────┬───────────────────────────────┘
                     │
                     ▼
   ┌─────────────────────────────────────────────────┐
   │    FootballDataApi Interface (Retrofit)         │
   │  ┌─────────────────────────────────────┐        │
   │  │ GET /teams/{clubId} -> HeadCoach    │        │
   │  │ GET /teams/{clubId} -> TeamSquad[]  │        │
   │  └─────────────────────────────────────┘        │
   └─────────────────┬───────────────────────────────┘
                     │
                     ▼
   ┌─────────────────────────────────────────────────┐
   │   API: https://api.football-data.org/v4/teams/  │
   │        (Mengembalikan JSON Response)            │
   └─────────────────┬───────────────────────────────┘
                     │
                     ▼
   ┌─────────────────────────────────────────────────┐
   │         Gson Converter (JSON Parsing)           │
   │  JSON → Data Class (HeadCoach, TeamSquad)       │
   └─────────────────┬───────────────────────────────┘
                     │
                     ▼
   ┌─────────────────────────────────────────────────┐
   │              LiveData Update                    │
   │  ViewModel menyimpan data ke LiveData           │
   └─────────────────┬───────────────────────────────┘
                     │
                     ▼
   ┌─────────────────────────────────────────────────┐
   │         Activity Observe LiveData               │
   │  Data ditampilkan ke TextView/RecyclerView      │
   └─────────────────────────────────────────────────┘
```