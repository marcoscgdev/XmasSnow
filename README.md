# XmasSnow
A fully customizable android library that adds a snowfall to an activity. It also includes a snowfall view.

---

## Releases:

#### Current release: 1.0.0.

You can see all the library releases [here](https://github.com/marcoscgdev/XmasSnow/releases).

---

## Screenshots
<img src="https://raw.githubusercontent.com/marcoscgdev/XmasSnow/master/device-2017-12-27-160558.png" width="350">
Download the sample apk [here](https://github.com/marcoscgdev/XmasSnow/releases/download/1.0.0/app-debug.apk).

---

## Usage:

### Adding the depencency

Add this to your root *build.gradle* file:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Now add the dependency to your app build.gradle file:

```
compile 'com.github.marcoscgdev:XmasSnow:1.0.0'
```

### Show into an Activity

Here is a complete snippet of it usage:

```java
XmasSnow.on(this)
        .belowActionBar(true)
        .belowStatusBar(true) // Always true if belowActionBar() is set to true
        .onlyOnChristmas(true) // Only the 25th of december
        .setInterval("12/25/2017", "1/7/2018") // 25th of december to 7th of january (not included). Date format: MM/dd/yyyy
        .start();
```

### Insert as a view

```xml
<com.marcoscg.xmassnow.SnowView
    android:id="@+id/snowview"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```
```java
SnowView snowView = findViewById(R.id.snowview);
snowView.onlyOnChristmas(true);
snowView.setInterval("12/25/2017", "1/7/2018");
```

---
>See the *sample project* to clarify any queries you may have.

---

## License

```
Copyright 2017 Marcos Calvo García

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
