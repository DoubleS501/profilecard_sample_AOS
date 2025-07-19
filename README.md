# 📱 GyroCard Sample App

자이로 센서를 활용해 사용자의 핸드폰 기울기에 따라 카드가 움직이는 Android Jetpack Compose 기반 샘플 앱입니다.

<br>

---

## 프로젝트 개요

이 프로젝트는 Android의 `SensorManager`를 사용하여 **자이로 센서(Rotation Vector)** 데이터를 실시간으로 받아오고, 카드 컴포넌트가 좌우로 부드럽게 기울어지도록 구현한 샘플입니다. 이후 실제 사용자 데이터를 서버에서 연동하거나, 추천 카드/프로필 기능으로 확장할 수 있습니다.

<br>

---

## 📁 프로젝트 구조
```
gyrocard_sample/
├── MainActivity.kt // BottomNavigation 포함한 메인 화면
│
├── navigation/
│ └── NavGraph.kt // 화면 전환 라우팅
│
├── ui/
│ ├── screen/
│ │ ├── HomeScreen.kt // 자이로 카드 UI 포함
│ │ ├── PersonalScreen.kt
│ │ └── TeamScreen.kt
│ │
│ └── component/
│   ├── BottomNavigationBar.kt
│   └── GyroCard.kt // 카드 회전 UI 컴포저블
│
├── sensor/
│ └── GyroSensorManager.kt // 자이로 센서 로직 포함
│
├── model/
│ └── UserProfile.kt // 카드에 표시할 사용자 모델
│
├── assets/
│ ├── user_profiles.json // 샘플 유저 프로필 목록
│ └── profile.png
│
└── AndroidManifest.xml
```

---

## 주요 기능

### 1. GyroCard 카드 회전 UI

- `GyroCard.kt` 컴포저블에서 좌우 기울기에 따라 카드가 회전하는 UI를 구현
- `graphicsLayer`와 `animateFloatAsState`를 활용해 부드러운 움직임 제공
- 카드 내부에는 사용자 이름, 기술 스택, MBTI, 이미지 표시

<br>

### 2. 자이로 센서 값 수집

- `rememberGyroSensor()`를 통해 실시간 센서 데이터를 수집
- `Sensor.TYPE_ROTATION_VECTOR`를 활용해 rotation matrix로 변환
- pitch(상하)는 감도 감소 처리하여 움직임 최소화, roll(좌우)만 반영

```kotlin
val adjustedPitch = pitch.value * 0.1f
```

<br>

### 3. BottomNavigation + Navigation Graph

- 하단 바로 홈, 개인, 팀 3개 페이지 구성
- NavHost로 각 페이지를 Fragment가 아닌 Compose로 연결

<br>

### 4. 정적 프로필 데이터 로딩

- assets/user_profiles.json 파일에 유저 데이터를 작성하여 연동
- 앱 내에서 loadProfilesFromAssets() 함수로 JSON 파싱
- UserProfile 모델 클래스 사용

```
[
  {
    "name": "민수",
    "stack": "Android, Kotlin",
    "mbti": "ENFJ",
    "imagePath": "profile.png"
  }
]
```

<br>

### 5. 카드 내 이미지 표시

- assets/에 저장된 이미지 파일을 카드 상단에 렌더링
- Image(Bitmap) 방식으로 표시 (Jetpack Compose의 Image 사용)

<br>

### 6. 향후 확장 계획

- Spring 서버 API 연동하여 실시간 유저 정보 받아오기
- 카드 스와이프 기능 및 즐겨찾기 기능
- 유저 프로필 상세 화면 이동
- 다중 카드 슬라이더 UI (Carousel 형태)

<br>

---

## 사용 기술

| 기술              | 설명                                                       |
|------------------|------------------------------------------------------------|
| Kotlin           | 주요 프로그래밍 언어                                       |
| Jetpack Compose  | UI 프레임워크                                               |
| SensorManager    | Android 자이로 센서(Rotation Vector) API                  |
| Navigation       | Compose Navigation 사용                                    |
| JSON             | 로컬 유저 정보 구성 (`assets/user_profiles.json`)         |
