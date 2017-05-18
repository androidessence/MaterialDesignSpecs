package com.androidessence.materialdesignspecs;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests the utility methods inside the MaterialPalettes class.
 *
 * Created by adammcneilly on 11/27/15.
 */
@SuppressWarnings("UnusedAssignment")
public class TestMaterialPalettes extends AndroidTestCase{
    /**
     * Verifies that the `getColorsByName()` method returns the proper color counts.
     *
     * This test does not validate the data returned, only the expected counts.
     */
    public void testGetColorsByName() {
        // For each color in the `COLORS_WITH_ACCENTS` we should return a list of colors for each
        // color level. Verify by making sure the size of the return list is the size of our color
        // levels.
        try {
            for(String colorName : MaterialPalettes.INSTANCE.getCOLORS_WITH_ACCENT_NAMES()) {
                List<Integer> colors = MaterialPalettes.INSTANCE.getColorsByName(colorName);
                assertEquals(MaterialPalettes.INSTANCE.getCOLOR_LEVELS().length, colors.size());
            }
        } catch(IllegalAccessException iae) {
            fail();
        }

        // For each color in the `COLORS_WITHOUT_ACCENTS` we should return a list of colors for
        // non-accent color levels. Verify by making sure the size of the return list is the size
        // of our color levels.
        try {
            for(String colorName : MaterialPalettes.INSTANCE.getCOLORS_WITHOUT_ACCENT_NAMES()) {
                List<Integer> colors = MaterialPalettes.INSTANCE.getColorsByName(colorName);
                assertEquals(MaterialPalettes.INSTANCE.getNON_ACCENT_COLOR_LEVELS().length, colors.size());
            }
        } catch(IllegalAccessException iae) {
            fail();
        }

        // Test that we throw an IllegalArgumentException if an invalid string is passed
        try {
            List<Integer> colors = MaterialPalettes.INSTANCE.getColorsByName("");
            // If we made it here, fail
            fail();
        } catch(IllegalArgumentException iae) {
            // Do nothing, we passed!
        } catch(IllegalAccessException iae1) {
            fail();
        }

        // Test that we throw an IllegalArgumentException if null is passed
        try {
            List<Integer> colors = MaterialPalettes.INSTANCE.getColorsByName(null);
            // If we made it here, fail
            fail();
        } catch(IllegalArgumentException iae) {
            // Do nothing, we passed!
        } catch(IllegalAccessException iae1) {
            fail();
        }
    }

    /**
     * Verifies that the `getColorsByNames(String[] colorNames)` method returns the proper color counts.
     *
     * This test does not validate the data returned, only the expected counts.
     */
    public void testGetColorsByNames() {
        // If we get a list of colors for all colors with accents, our results should be
        // The number of color levels * number of colors
        try {
            List<Integer> allColorsWithAccents = MaterialPalettes.INSTANCE.getColorsByNames(MaterialPalettes.INSTANCE.getCOLORS_WITH_ACCENT_NAMES());
            int expectedCount = MaterialPalettes.INSTANCE.getCOLOR_LEVELS().length * MaterialPalettes.INSTANCE.getCOLORS_WITH_ACCENT_NAMES().length;
            assertEquals(expectedCount, allColorsWithAccents.size());
        } catch(IllegalAccessException iae) {
            fail();
        }

        // If we get a list of colors for all colors without accents, our results should be
        // The number of non accent color levels * number of colors
        try {
            List<Integer> allColorsWithoutAccents = MaterialPalettes.INSTANCE.getColorsByNames(MaterialPalettes.INSTANCE.getCOLORS_WITHOUT_ACCENT_NAMES(), false);
            int expectedCount = MaterialPalettes.INSTANCE.getNON_ACCENT_COLOR_LEVELS().length * MaterialPalettes.INSTANCE.getCOLORS_WITHOUT_ACCENT_NAMES().length;
            assertEquals(expectedCount, allColorsWithoutAccents.size());
        } catch(IllegalAccessException iae) {
            fail();
        }

        // Test that we throw an IllegalArgumentException if an invalid string array is passed
        try {
            List<Integer> colors = MaterialPalettes.INSTANCE.getColorsByNames(new String[] { "", "" });
            // If we made it here, fail
            fail();
        } catch(IllegalArgumentException iae) {
            // Do nothing, we passed!
        } catch(IllegalAccessException iae1) {
            fail();
        }

        // Test that we throw an IllegalArgumentException if null is passed
        try {
            List<Integer> colors = MaterialPalettes.INSTANCE.getColorsByNames(null);
            // If we made it here, fail
            fail();
        } catch(IllegalArgumentException iae) {
            // Do nothing, we passed!
        } catch(IllegalAccessException iae1) {
            fail();
        }
    }

    /**
     * Verifies that the `getColorsByName()` method returns the proper color counts.
     * This test uses the overload to check for non accents
     *
     * This test does not validate the data returned, only the expected counts.
     */
    public void testGetColorsByNameWithoutAccents() {
        // For all color names, if we get the color list without accents we should return the size
        // of non_accent color levels
        try {
            for(String colorName : MaterialPalettes.INSTANCE.getALL_COLOR_NAMES()) {
                List<Integer> colors = MaterialPalettes.INSTANCE.getColorsByName(colorName, false);
                assertEquals(MaterialPalettes.INSTANCE.getNON_ACCENT_COLOR_LEVELS().length, colors.size());
            }
        } catch(IllegalAccessException iae) {
            fail();
        }

        // Test that we throw an IllegalArgumentException if an invalid string is passed
        try {
            List<Integer> colors = MaterialPalettes.INSTANCE.getColorsByName("", false);
            // If we made it here, fail
            fail();
        } catch(IllegalArgumentException iae) {
            // Do nothing, we passed!
        } catch(IllegalAccessException iae1) {
            fail();
        }

        // Test that we throw an IllegalArgumentException if null is passed
        try {
            List<Integer> colors = MaterialPalettes.INSTANCE.getColorsByName(null, false);
            // If we made it here, fail
            fail();
        } catch(IllegalArgumentException iae) {
            // Do nothing, we passed!
        } catch(IllegalAccessException iae1) {
            fail();
        }
    }

    /**
     * Verifies that the `getColorsByLevel()` method returns the proper color counts.
     *
     * This test does not validate the data returned, only the expected counts.
     */
    public void testGetColorsByLevel() {
        // For each level in the `NON_ACCENT_COLOR_LEVELS` we should return a list of colors
        // for each material design color palette. Verify count against size of color names.
        try {
            for(String colorLevel : MaterialPalettes.INSTANCE.getNON_ACCENT_COLOR_LEVELS()) {
                List<Integer> colors = MaterialPalettes.INSTANCE.getColorsByLevel(colorLevel);
                assertEquals(MaterialPalettes.INSTANCE.getALL_COLOR_NAMES().length, colors.size());
            }
        } catch(IllegalAccessException iae) {
            fail();
        }

        // For each level in the `ACCENT_COLOR_LEVELS` we should return a list of colors
        // for each material design color with accents. Verify count against size of accent color list.
        try {
            for(String colorLevel : MaterialPalettes.INSTANCE.getACCENT_COLOR_LEVELS()) {
                List<Integer> colors = MaterialPalettes.INSTANCE.getColorsByLevel(colorLevel);
                assertEquals(MaterialPalettes.INSTANCE.getCOLORS_WITH_ACCENT_NAMES().length, colors.size());
            }
        } catch(IllegalAccessException iae) {
            fail();
        }

        // Test that we throw an IllegalArgumentException if an invalid string is passed
        try {
            List<Integer> colors = MaterialPalettes.INSTANCE.getColorsByName("");
            // If we made it here, fail
            fail();
        } catch(IllegalArgumentException iae) {
            // Do nothing, we passed!
        } catch(IllegalAccessException iae1) {
            fail();
        }

        // Test that we throw an IllegalArgumentException if null is passed
        try {
            List<Integer> colors = MaterialPalettes.INSTANCE.getColorsByLevel(null);
            // If we made it here, fail
            fail();
        } catch(IllegalArgumentException iae) {
            // Do nothing, we passed!
        } catch(IllegalAccessException iae1) {
            fail();
        }
    }

    /**
     * Tests to ensure getAllColors returns the proper count.
     */
    public void testGetAllColors() {
        // The expected count is one for each accent_colors and all color levels, and each non_accent_color and non_accent_color_levels
        int expectedCount = (MaterialPalettes.INSTANCE.getALL_COLOR_NAMES().length * MaterialPalettes.INSTANCE.getNON_ACCENT_COLOR_LEVELS().length) + (MaterialPalettes.INSTANCE.getCOLORS_WITH_ACCENT_NAMES().length * MaterialPalettes.INSTANCE.getACCENT_COLOR_LEVELS().length);

        try {
            List<Integer> colorList = MaterialPalettes.INSTANCE.getAllColors();
            assertEquals(expectedCount, colorList.size());
        } catch(IllegalAccessException iae) {
            fail();
        }
    }

    /**
     * Tests that a random color is returned by ensuring the one returned is in getAllColors().
     */
    public void testGetRandomColor() {
        try {
            ArrayList<Integer> valueCheck = new ArrayList<>();
            int colorSize = MaterialPalettes.INSTANCE.getAllColors().size();

            Log.d("RandomTest","Color size:" + colorSize);

            for (int i = 0; i < colorSize; i++) {
                Integer color = MaterialPalettes.INSTANCE.getRandomColorNonRepeating();
                if (valueCheck.contains(color)) {
                    fail();
                }
                else {
                    valueCheck.add(color);
                }
            }
        } catch(IllegalAccessException iae) {
            fail();
        }
    }

    /**
     * Tests that the color returned by getRandomNonAccent color exists in the non accent colors.
     */
    public void testGetRandomNonAccentColor() {
        // Start with empty list
        List<Integer> nonAccentColorList = new ArrayList<>();

        try {
            for(String name : MaterialPalettes.INSTANCE.getALL_COLOR_NAMES()) {
                nonAccentColorList.addAll(MaterialPalettes.INSTANCE.getColorsByName(name, false));
            }

            // Ensure it exists
            int randomColor = MaterialPalettes.INSTANCE.getRandomNonAccentColor();
            assertTrue(nonAccentColorList.contains(randomColor));
        } catch(IllegalAccessException iae) {
            fail();
        }
    }

    /**
     * Tests that retrieving the accent colors gets the right count.
     */
    public void testGetAccentColorsByName() {
        try {
            for(String name : MaterialPalettes.INSTANCE.getCOLORS_WITH_ACCENT_NAMES()) {
                List<Integer> colorList = MaterialPalettes.INSTANCE.getAccentColorsByName(name);
                assertEquals(MaterialPalettes.INSTANCE.getACCENT_COLOR_LEVELS().length, colorList.size());
            }
        } catch(IllegalAccessException iae) {
            fail();
        }
    }

    /**
     * Tests that the color returned by getRandomAccentColor exists in the accent color groups.
     */
    public void testGetRandomAccentColor() {
        // Start with empty list
        List<Integer> accentColorList = new ArrayList<>();

        try {
            for(String name : MaterialPalettes.INSTANCE.getCOLORS_WITH_ACCENT_NAMES()) {
                accentColorList.addAll(MaterialPalettes.INSTANCE.getAccentColorsByName(name));
            }
            // Get random color, make sure it's in our accent list
            Integer randomColor = MaterialPalettes.INSTANCE.getRandomAccentColor();
            assertTrue(accentColorList.contains(randomColor));
        } catch(IllegalAccessException iae) {
            fail();
        }
    }

    /**
     * Ensures the color returned has the appropriate level.
     */
    public void testGetRandomColorByLevel() {
        try {
            // Test this for each level
            for(String level : MaterialPalettes.INSTANCE.getNON_ACCENT_COLOR_LEVELS()) {
                List<Integer> colorList = MaterialPalettes.INSTANCE.getColorsByLevel(level);
                Integer randomColor = MaterialPalettes.INSTANCE.getRandomColorByLevel(level);
                assertTrue(colorList.contains(randomColor));
            }
        } catch(IllegalAccessException iae) {
            fail();
        }
    }

    /**
     * Ensures the color returned is for the right name.
     */
    public void testGetRandomColorByName() {
        try {
            // Do this for every color
            for(String name : MaterialPalettes.INSTANCE.getALL_COLOR_NAMES()) {
                List<Integer> colorList = MaterialPalettes.INSTANCE.getColorsByName(name);
                Integer randomColor = MaterialPalettes.INSTANCE.getRandomColorByName(name);
                assertTrue(colorList.contains(randomColor));
            }
        } catch(IllegalAccessException iae) {
            fail();
        }
    }

    /**
     * Ensures that the color returned is within the bounds.
     */
    public void testGetRandomColorWithLimits() {
        try {
            // Get a random red color, make sure it's in the list
            List<Integer> redColors = MaterialPalettes.INSTANCE.getColorsByName(MaterialPalettes.INSTANCE.getRED());
            Integer randomColor = MaterialPalettes.INSTANCE.getRandomColorWithLimits(new String[] {MaterialPalettes.INSTANCE.getRED()} );
            assertTrue(redColors.contains(randomColor));
        } catch(IllegalAccessException iae) {
            fail();
        }
    }
}
