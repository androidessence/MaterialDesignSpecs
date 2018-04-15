@file:Suppress("DEPRECATION")

package com.androidessence.materialdesignspecs

import android.test.AndroidTestCase
import android.util.Log
import junit.framework.Assert
import java.util.*

/**
 * Tests the utility methods inside the MaterialPalettes class.
 * Created by adammcneilly on 11/27/15.
 */
class TestMaterialPalettes : AndroidTestCase() {
    /**
     * Verifies that the `getColorsByName()` method returns the proper color counts.
     *
     * This test does not validate the data returned, only the expected counts.
     */
    fun testGetColorsByName() {
        // For each color in the `COLORS_WITH_ACCENTS` we should return a list of colors for each
        // color level. Verify by making sure the size of the return list is the size of our color
        // levels.
        try {
            MaterialPalettes.COLORS_WITH_ACCENT_NAMES
                    .map { MaterialPalettes.getColorsByName(it) }
                    .forEach { Assert.assertEquals(MaterialPalettes.COLOR_LEVELS.size, it.size) }
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

        // For each color in the `COLORS_WITHOUT_ACCENTS` we should return a list of colors for
        // non-accent color levels. Verify by making sure the size of the return list is the size
        // of our color levels.
        try {
            MaterialPalettes.COLORS_WITHOUT_ACCENT_NAMES
                    .map { MaterialPalettes.getColorsByName(it) }
                    .forEach { Assert.assertEquals(MaterialPalettes.NON_ACCENT_COLOR_LEVELS.size, it.size) }
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

        // Test that we throw an IllegalArgumentException if an invalid string is passed
        try {
            MaterialPalettes.getColorsByName("")
            // If we made it here, fail
            Assert.fail()
        } catch (iae: IllegalArgumentException) {
            // Do nothing, we passed!
        } catch (iae1: IllegalAccessException) {
            Assert.fail()
        }
    }

    /**
     * Verifies that the `getColorsByNames(String[] colorNames)` method returns the proper color counts.
     *
     * This test does not validate the data returned, only the expected counts.
     */
    fun testGetColorsByNames() {
        // If we get a list of colors for all colors with accents, our results should be
        // The number of color levels * number of colors
        try {
            val allColorsWithAccents = MaterialPalettes.getColorsByNames(MaterialPalettes.COLORS_WITH_ACCENT_NAMES)
            val expectedCount = MaterialPalettes.COLOR_LEVELS.size * MaterialPalettes.COLORS_WITH_ACCENT_NAMES.size
            Assert.assertEquals(expectedCount, allColorsWithAccents.size)
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

        // If we get a list of colors for all colors without accents, our results should be
        // The number of non accent color levels * number of colors
        try {
            val allColorsWithoutAccents = MaterialPalettes.getColorsByNames(MaterialPalettes.COLORS_WITHOUT_ACCENT_NAMES, false)
            val expectedCount = MaterialPalettes.NON_ACCENT_COLOR_LEVELS.size * MaterialPalettes.COLORS_WITHOUT_ACCENT_NAMES.size
            Assert.assertEquals(expectedCount, allColorsWithoutAccents.size)
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

        // Test that we throw an IllegalArgumentException if an invalid string array is passed
        try {
            MaterialPalettes.getColorsByNames(arrayOf("", ""))
            // If we made it here, fail
            Assert.fail()
        } catch (iae: IllegalArgumentException) {
            // Do nothing, we passed!
        } catch (iae1: IllegalAccessException) {
            Assert.fail()
        }

        // Test that we throw an IllegalArgumentException if null is passed
        try {
            MaterialPalettes.getColorsByNames(null)
            // If we made it here, fail
            Assert.fail()
        } catch (iae: IllegalArgumentException) {
            // Do nothing, we passed!
        } catch (iae1: IllegalAccessException) {
            Assert.fail()
        }

    }

    /**
     * Verifies that the `getColorsByName()` method returns the proper color counts.
     * This test uses the overload to check for non accents
     *
     * This test does not validate the data returned, only the expected counts.
     */
    fun testGetColorsByNameWithoutAccents() {
        // For all color names, if we get the color list without accents we should return the size
        // of non_accent color levels
        try {
            MaterialPalettes.ALL_COLOR_NAMES
                    .map { MaterialPalettes.getColorsByName(it, false) }
                    .forEach { Assert.assertEquals(MaterialPalettes.NON_ACCENT_COLOR_LEVELS.size, it.size) }
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

        // Test that we throw an IllegalArgumentException if an invalid string is passed
        try {
            MaterialPalettes.getColorsByName("", false)
            // If we made it here, fail
            Assert.fail()
        } catch (iae: IllegalArgumentException) {
            // Do nothing, we passed!
        } catch (iae1: IllegalAccessException) {
            Assert.fail()
        }
    }

    /**
     * Verifies that the `getColorsByLevel()` method returns the proper color counts.
     *
     * This test does not validate the data returned, only the expected counts.
     */
    fun testGetColorsByLevel() {
        // For each level in the `NON_ACCENT_COLOR_LEVELS` we should return a list of colors
        // for each material design color palette. Verify count against size of color names.
        try {
            MaterialPalettes.NON_ACCENT_COLOR_LEVELS
                    .map { MaterialPalettes.getColorsByLevel(it) }
                    .forEach { Assert.assertEquals(MaterialPalettes.ALL_COLOR_NAMES.size, it.size) }
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

        // For each level in the `ACCENT_COLOR_LEVELS` we should return a list of colors
        // for each material design color with accents. Verify count against size of accent color list.
        try {
            MaterialPalettes.ACCENT_COLOR_LEVELS
                    .map { MaterialPalettes.getColorsByLevel(it) }
                    .forEach { Assert.assertEquals(MaterialPalettes.COLORS_WITH_ACCENT_NAMES.size, it.size) }
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

        // Test that we throw an IllegalArgumentException if an invalid string is passed
        try {
            MaterialPalettes.getColorsByName("")
            // If we made it here, fail
            Assert.fail()
        } catch (iae: IllegalArgumentException) {
            // Do nothing, we passed!
        } catch (iae1: IllegalAccessException) {
            Assert.fail()
        }
    }

    /**
     * Tests to ensure getAllColors returns the proper count.
     */
    fun testGetAllColors() {
        // The expected count is one for each accent_colors and all color levels, and each non_accent_color and non_accent_color_levels
        // We also need to consider white/black/transparent
        //TODO: There has to be a less hacky way of this.
        val nonAccentCount = MaterialPalettes.ALL_COLOR_NAMES.size * MaterialPalettes.NON_ACCENT_COLOR_LEVELS.size
        val accentCount = MaterialPalettes.COLORS_WITH_ACCENT_NAMES.size * MaterialPalettes.ACCENT_COLOR_LEVELS.size
        val miscCount = 3
        val expectedCount = nonAccentCount + accentCount + miscCount

        try {
            val colorList = MaterialPalettes.getAllColors()
            Assert.assertEquals(expectedCount, colorList.size)
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

    }

    /**
     * Tests that a random color is returned by ensuring the one returned is in getAllColors().
     */
    fun testGetRandomColor() {
        try {
            val valueCheck = ArrayList<Int?>()
            val colorSize = MaterialPalettes.getAllColors().size

            Log.d("RandomTest", "Color size: $colorSize")

            for (i in 0 until colorSize) {
                val color = MaterialPalettes.getRandomColorNonRepeating()
                if (valueCheck.contains(color)) {
                    Assert.fail()
                } else {
                    valueCheck.add(color)
                }
            }
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

    }

    /**
     * Tests that the color returned by getRandomNonAccent color exists in the non accent colors.
     */
    fun testGetRandomNonAccentColor() {
        // Start with empty list
        val nonAccentColorList = ArrayList<Int>()

        try {
            for (name in MaterialPalettes.ALL_COLOR_NAMES) {
                nonAccentColorList.addAll(MaterialPalettes.getColorsByName(name, false))
            }

            // Ensure it exists
            val randomColor = MaterialPalettes.getRandomNonAccentColor()
            Assert.assertTrue(nonAccentColorList.contains(randomColor))
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

    }

    /**
     * Tests that retrieving the accent colors gets the right count.
     */
    fun testGetAccentColorsByName() {
        try {
            MaterialPalettes.COLORS_WITH_ACCENT_NAMES
                    .map { MaterialPalettes.getAccentColorsByName(it) }
                    .forEach { Assert.assertEquals(MaterialPalettes.ACCENT_COLOR_LEVELS.size, it.size) }
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

    }

    /**
     * Tests that the color returned by getRandomAccentColor exists in the accent color groups.
     */
    fun testGetRandomAccentColor() {
        // Start with empty list
        val accentColorList = ArrayList<Int>()

        try {
            for (name in MaterialPalettes.COLORS_WITH_ACCENT_NAMES) {
                accentColorList.addAll(MaterialPalettes.getAccentColorsByName(name))
            }
            // Get random color, make sure it's in our accent list
            val randomColor = MaterialPalettes.getRandomAccentColor()
            Assert.assertTrue(accentColorList.contains(randomColor))
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

    }

    /**
     * Ensures the color returned has the appropriate level.
     */
    fun testGetRandomColorByLevel() {
        try {
            // Test this for each level
            for (level in MaterialPalettes.NON_ACCENT_COLOR_LEVELS) {
                val colorList = MaterialPalettes.getColorsByLevel(level)
                val randomColor = MaterialPalettes.getRandomColorByLevel(level)
                Assert.assertTrue(colorList.contains(randomColor))
            }
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

    }

    /**
     * Ensures the color returned is for the right name.
     */
    fun testGetRandomColorByName() {
        try {
            // Do this for every color
            for (name in MaterialPalettes.ALL_COLOR_NAMES) {
                val colorList = MaterialPalettes.getColorsByName(name)
                val randomColor = MaterialPalettes.getRandomColorByName(name)
                Assert.assertTrue(colorList.contains(randomColor))
            }
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

    }

    /**
     * Ensures that the color returned is within the bounds.
     */
    fun testGetRandomColorWithLimits() {
        try {
            // Get a random red color, make sure it's in the list
            val redColors = MaterialPalettes.getColorsByName(MaterialPalettes.RED)
            val randomColor = MaterialPalettes.getRandomColorWithLimits(arrayOf(MaterialPalettes.RED))
            Assert.assertTrue(redColors.contains(randomColor))
        } catch (iae: IllegalAccessException) {
            Assert.fail()
        }

    }
}
