# 🚕 MyTaxi — Real-Time Taxi Booking App (Android)

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" />
  <img src="https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black" />
  <img src="https://img.shields.io/badge/Hilt-007396?style=for-the-badge&logo=dagger&logoColor=white" />
  <img src="https://img.shields.io/badge/OpenStreetMap-7EBC6F?style=for-the-badge&logo=openstreetmap&logoColor=white" />
  <img src="https://img.shields.io/badge/Razorpay-02042B?style=for-the-badge&logo=razorpay&logoColor=3395FF" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/MyTaxi-Real--Time%20Taxi%20Booking-blue?style=for-the-badge&logo=uber&logoColor=white" />
</p>

---

## 📌 About the Project

**MyTaxi** is a modern, real-time taxi booking Android application built using **100% Kotlin** and **Jetpack Compose**.  
The project follows **Clean Architecture** and **MVVM**, demonstrating **production-ready Android development** practices.

🔗 GitHub Repo: [Link](https://github.com/mr-piyushkr/MyTaxi-Android-App.git)

📦 APK: [Link](https://github.com/mr-piyushkr/MyTaxi-Android-App/releases/tag/v1.0.0)


---

## ✨ Key Highlights

- 📱 Single codebase for **Rider & Driver**
- ⚡ Real-time driver discovery & tracking
- 🧱 Clean Architecture + MVVM
- 🔐 Firebase Phone OTP Authentication
- 🗺️ OpenStreetMap based map UI
- 💳 Payment-ready architecture (Razorpay planned)

---

## 👥 App Roles & Features

### 🚖 Rider Features
- ✅ Phone number authentication (Firebase OTP)
- ✅ Discover nearby drivers in real time
- 🚧 Ride booking (pickup & drop)
- 🚧 Live driver tracking
- 🚧 Payments (Razorpay / Cash)

### 🧑‍✈️ Driver Features
- 🚧 Driver registration & onboarding
- 🚧 Go Online / Offline
- 🚧 Accept / reject ride requests
- 🚧 Real-time location sharing

---

## 🛠️ Tech Stack

### 🚀 Core
- **Kotlin**
- **Jetpack Compose**
- **Kotlin Coroutines**
- **Hilt (Dependency Injection)**

### ☁️ Backend
- **Firebase Authentication (Phone OTP)**
- **Cloud Firestore**
- **Firebase Realtime Database**

### 🗺️ Maps & Payments
- **OpenStreetMap**
- **Fused Location Provider**
- **Razorpay (Planned)**

---

## 🧠 Architecture
```
UI (Compose)
↓
ViewModel
↓
Use Cases (Domain)
↓
Repository
↓
Firebase (Firestore / Realtime DB)
```

---

## 📂 Project Structure
```
com.piyush.mytaxi
│
├── data/
│ ├── model/
│ └── repository/
│
├── domain/
│ ├── repository/
│ └── usecase/
│
├── ui/
│ ├── screens/
│ ├── components/
│ └── theme/
│
├── viewmodel/
├── navigation/
├── di/
│
└── MyTaxiApplication.kt
```

---
## 🚀 Getting Started

### 🔧 Prerequisites
- Android Studio Iguana (2023.2.1+)
- Kotlin 1.9+
- Firebase Account

---

### ⚙️ Setup

#### 1️⃣ Clone Repository
```bash
git clone https://github.com/USERNAME/MyTaxi.git
cd MyTaxi
```

### 2️⃣ Firebase Configuration

- Create Firebase project
- Add Android app with package name:
  ```
  com.piyush.mytaxi
  ```
- Download google-services.json
- Place it inside app/

### 3️⃣ Enable Phone Authentication

Firebase Console → Authentication → Sign-in Method → Phone

### 4️⃣ Firestore Rules
```agsl
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {

    match /drivers/{driverId} {
      allow read: if request.auth != null;
    }

    match /users/{userId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
  }
}

```
### 5️⃣ Run the App
- Open project in Android Studio
- Sync Gradle
- Run on emulator or device

---
## 🛣️ Roadmap
- Ride booking flow
- Live ride tracking
- Driver onboarding
- Razorpay integration
- Ride history & ratings
- Push notifications

---

## 📄 License
This project is licensed under the MIT License.

---
**Piyush Kumar**  
🚀 Android Developer 

<h3 align="center">📬 Let's Connect</h3>

<p align="center">
  <a href="https://my-portfolio-umber-zeta-11.vercel.app/" target="_blank">🌐 Portfolio</a>
  &nbsp;•&nbsp;
  <a href="https://github.com/mr-piyushkr" target="_blank">💻 GitHub</a>
  &nbsp;•&nbsp;
  <a href="https://linkedin.com/in/piyushkumar06" target="_blank">💼 LinkedIn</a>
  &nbsp;•&nbsp;
  <a href="mailto:0602.piyushkumar@gmail.com">📧 Email</a>
</p>

