[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MaterialDesignSpecs-green.svg?style=true)](https://android-arsenal.com/details/1/3539)

Material Design Specs Library
=============

This library provides an easy and quick way to access the entire material design color palette and elevation values, along with some neat helper methods like random access to material design colors.


Usage
-----

To have access to the library, add the dependency to your build.gradle:

```java
	implementation 'com.androidessence:materialdesignspecs:2.0.1'
```

At the time of publication, the library has not yet been linked to JCenter, so you will also have to add the link to our Maven repository as well:

```java
	repositories {
    	    maven {
        	url  "http://dl.bintray.com/androidessence/maven"
    	    }
	}
```

Now, you'll be able to access the full color pallete from material design, either by XML, or programatically.

- **XML way**

```xml
	<!-- You can use it in any view or other XML resources -->
	<!-- Access the resources by using @color/ or @dimen/ -->
	<View
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:background="@color/mds_red_500"
	    android:elevation="@dimen/mds_elevation_card_resting"/>
```

- **Java way**

```java
	// Access it through the android R file. Like the examples below.
	R.color.mds_red_500
	R.color.mds_blue_700
	R.color.mds_pink_A100
	R.color.mds_indigo_200
	// Elevations
	R.dimen.mds_elevation_card_resting
	R.dimen.mds_elevation_dialog
	R.dimen.mds_elevation_navigation_view
	R.dimen.mds_elevation_menu
```

You'll also have access to some static helper methods like:

```java
	getColorsByName(String colorName) // Returns a List<Integer> of colors with the given name.
	// The methods below returns an Integer to use along your code.
	getRandomColor()
	getRandomNonAccentColor()
	getRandomColorByLevel(String colorLevel)
	getRandomColorByName(String colorName)
	getRandomColorNonRepeating()
	// And a few more!
```

To specify a color name or level, use the available static strings such as:

```java
    MaterialPalettes.RED
    MaterialPalettes.LEVEL_500
```

Please be aware that the Integer returned by the methods above is the Integer of the resource identifier, not the color itself. So, in order to give a TextView a random text color, it would be done like this:

```java
    Integer randomColor = MaterialPalettes.getRandomColor();
    myTextView.setTextColor(getResources().getColor(randomColor));
```

ColorPicker
-----

MDS also supplies a built in ColorPicker dialog you can use to allow users to pick a color. There's both a circle and a square color adapter you can use, and also a base one you can implement to make your own. Supply an adapter and a list of colors for the picker, as well as implement an `OnColorSelectedListener` interface to use it. Here is an example usage of the dialog:

```java
    try {
        ColorDialog colorDialog = ColorDialog.newInstance(MaterialPalettes.getColorsByLevel(MaterialPalettes.LEVEL_500), selectedPos);
        colorDialog.setOnColorSelectedListener(this);
        colorDialog.setAdapter(new CircleColorAdapter(this));
        colorDialog.setLayoutManager(new GridLayoutManager(MainActivity.this, GRID_COUNT));
        colorDialog.show(getSupportFragmentManager(), CIRCLE_COLOR_PICKER_TAG);
    } catch (IllegalAccessException iae) {
        Log.e(MainActivity.class.getSimpleName(), iae.getMessage(), iae);
    }
```

Note that depending on orientation or screen size, you may want to consider how many columns you add to the GridLayoutManager. For example, 5 columns on a Nexus 6P begin to overlap and don't look god together.

Sample
-----

To see the library in action, this is the output of getting all 500 level colors and displaying them in individual TextViews:

<img src='/images/sample.png' width='400' height='640' />

Here is an example of the ColorPicker in action:

<img src='/images/picker.png' width='400' height='640' />

Colors
-----

Here's the list of the colors names. Change "X" to the color level, like "50" or "A100".

- mds_red_X
- mds_pink_X
- mds_purple_X
- mds_deeppurple_X
- mds_indigo_X
- mds_blue_X
- mds_lightblue_X
- mds_cyan_X
- mds_teal_X
- mds_green_X
- mds_lightgreen_X
- mds_lime_X
- mds_yellow_X
- mds_amber_X
- mds_orange_X
- mds_deeporange_X
- mds_brown_X
- mds_grey_X
- mds_bluegrey_X

The color levels can be:

- 50
- 100
- 200
- 300
- 400
- 500
- 600
- 700
- 800
- 900

And the accent ones are:

- A100
- A200
- A400
- A700

Example of a color: `mds_orange_A400`.

**Reminder:** brown, grey, and bluegrey don't have accent colors.

We highly recommend you use this library for the mds_white and mds_black colors as well, as some OEM's change the `@android:color/white` resource - https://twitter.com/mandybess/status/910555678476951552

The full color palettes as well as some more information on how to use them can be found in Google's [Material Design Specifications](https://www.google.com/design/spec/style/color.html#color-color-palette).


Elevation values
-----

To know which elevation value to use, please refer to the [material design specs](https://www.google.com/design/spec/what-is-material/elevation-shadows.html#elevation-shadows-elevation-android-).

And with that you're all set. Go make some awesome apps with our lib :)

Credits & Contact
-----------------

Material Design Specs Lib was created with **<3** by:

- [Adam McNeilly](https://github.com/AdamMc331)
- [Maurício Pessoa](https://github.com/Mauker1)
- [Ankit Agrawal](https://github.com/ankitagrawal2411)

With special thanks to [Eric Cugota](https://github.com/tryadelion) for helping us get this into Bintray and make it available for you.

And it's released under [Android Essence blog](http://androidessence.com/).

If you contribute to this library, please add yourself to this section when you submit your pull request.

License
-------

Material Design Specs Lib is available under the [MIT License](https://opensource.org/licenses/MIT).

When you use it, don't forget to mention us ;) 



Happy coding!
