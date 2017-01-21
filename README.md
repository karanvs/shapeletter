# shapeletter
shapeletter is a simple view that displays a letter within circle or rectangle much like the gmail app.

## Getting Started

*  Add this to build.gradle
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

*  Add dependency for library
```
dependencies {
	        compile 'com.github.karanvs:shapeletter:v1.0'
	}
```

## Usage

```
 <com.veer.shapeletter.ShapeLetter
        xmlns:shpltr="http://schemas.android.com/apk/res-auto"
        android:layout_width="56dp"
        android:layout_height="56dp"
        shpltr:letter="B"
	shpltr:letter_size="20dp"
        shpltr:letter_color="@color/colorPrimaryDark"
        shpltr:shape="rect" //you can use oval as alternative
        shpltr:shape_color="@color/colorAccent"
        />
```

### License

This project is licensed under the Apache 2.0 License - see the [LICENSE.txt](LICENSE.txt) file for details
