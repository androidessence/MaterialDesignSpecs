Material Design Specs Library
=============

This library provides an easy and quick way to access the entire material design color pallete and elevation values, along with some neat helper methods like random access to material design colors.


Usage
-----

To have access to the library, add the dependency to your build.gradle:

```java

	    compile 'com.androidessence:materialdesignspecs:1.0.0'

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


Elevation values
-----

To know which elevation value to use, please refer to the [material design specs](https://www.google.com/design/spec/what-is-material/elevation-shadows.html#elevation-shadows-elevation-android-).

And with that you're all set. Go make some awesome apps with our lib :)

Credits & Contact
-----------------

Material Design Specs Lib was created with **<3** by:

- [Adam McNeilly](https://github.com/AdamMc331)
- [Maurício Pessoa](https://github.com/Mauker1)

And it's released under [Android Essence blog](http://androidessence.com/).

License
-------

Material Design Specs Lib is available under the [MIT License](https://opensource.org/licenses/MIT).

When you use it, don't forget to mention us ;) 

Happy coding!