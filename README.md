# PlaceholderView
[![Release](https://jitpack.io/v/yzzzd/PlaceholderView.svg)](https://jitpack.io/#yzzzd/PlaceholderView)

Placeholder for your view, include generating random text length variation.

## Screenshot
| as single placeholder | as loading list placeholder |
|:-:|:-:|
| <img src="https://github.com/yzzzd/Screenshot/blob/main/PlaceholderText.png" width="480"> | <img src="https://github.com/yzzzd/Screenshot/blob/main/PlaceholderList.gif" width="480"> |

## Download
Add it to your build.gradle with:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and:

```gradle
dependencies {
    implementation 'com.github.yzzzd:PlaceholderView:{latest version}'
}
```

## Usage

### 1. PlaceholderTextView (for single generating placeholder)
To generate placeholder text with random length
```xml
<com.yzzzd.placeholder.PlaceholderTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    app:placeholder_color="#AEAEAE"
    app:placeholder_length="text_long"
    app:placeholder_round="4dp" />
```

**XML Attributes**

|attribute            |value                            |default                            |means|
|:--                  |:--                              |:--                                |:--  |
|placeholder_color    |color_resource                   |R.color.tint_placeholder (#87939A) |set the placeholder color|
|placeholder_length   |text_short/text_medium/text_long |text_medium                        |set how long placeholder text will generated|
|placeholder_round    |dimension                        |0dp                                |set if you want get the rounded placeholder|
|placeholder_text     |string_array                     |none                               |set if you want use your own spesific random-string|

### 2. PlaceholderListView (for list generating placeholder)
To generate placeholder as a list
```xml
 <com.yzzzd.placeholder.PlaceholderListView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="8dp"
    app:list_item="@layout/item_placeholder" />
```

**XML Attributes**

|attribute            |value                |default    |means|
|:--                  |:--                  |:--        |:--  |
|list_item            |layout_resource      |null       |set the item layout, create item layout like make for a RecyclerView|
|with_animation       |boolean              |true       |set if you want get the animation effect when show the list|
|android:orientation  |vertical/horizontal  |vertical   |orientation of item will be shown like in LinearLayout|

## Customization

You can also override the array resources to make your own variant generated text. (**short_phrase**, **medium_phrase**, and **long_phrase**)
```xml
<string-array name="short_phrase">
    <item>lorem</item>
    <item>deserunt</item>
    <item>reprehenderit</item>
</string-array>

<string-array name="medium_phrase">
    <item>sed do eiusmod tempor</item>
    <item>ut enim ad minim veniam aute</item>
    <item>consectetur adipiscing elit, sed do</item>
</string-array>

<string-array name="long_phrase">
    <item>ut enim ad minim veniam, quis nostrud exercitation ullamco</item>
    <item>aboris nisi ut aliquip ex ea commodo consequat. duis aute irure dolor in reprehenderit in voluptate</item>
    <item>velit esse cillum dolore eu fugiat nulla pariatur. excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum</item>
</string-array>
```

## License
Licensed under the Apache License, Version 2.0,
