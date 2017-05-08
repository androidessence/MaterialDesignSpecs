package com.androidessence.materialdesignspecs;

import com.androidessence.materialdesignspeclibrary.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Utility functions for pulling various material design color palettes.
 *
 * Created by adammcneilly and mauker on 11/26/15.
 */
@SuppressWarnings("WeakerAccess")
public class MaterialPalettes {
    // Our variables
    private static final String PREFIX = "mds_";
    private static final String SEPARATOR = "_";
    private static final String ACCENT_SIGNIFIER = "_A";

    // Color names
    public static final String RED = "red";
    public static final String PINK = "pink";
    public static final String PURPLE = "purple";
    public static final String DEEP_PURPLE = "deeppurple";
    public static final String INDIGO = "indigo";
    public static final String BLUE = "blue";
    public static final String LIGHT_BLUE = "lightblue";
    public static final String CYAN = "cyan";
    public static final String TEAL = "teal";
    public static final String GREEN = "green";
    public static final String LIGHT_GREEN = "lightgreen";
    public static final String LIME = "lime";
    public static final String YELLOW = "yellow";
    public static final String AMBER = "amber";
    public static final String ORANGE = "orange";
    public static final String DEEP_ORANGE = "deeporange";
    public static final String BROWN = "brown";
    public static final String GREY = "grey";
    public static final String BLUE_GREY = "bluegrey";

    public static final String[] COLORS_WITH_ACCENT_NAMES = new String[] {
            RED,
            PINK,
            PURPLE,
            DEEP_PURPLE,
            INDIGO,
            BLUE,
            LIGHT_BLUE,
            CYAN,
            TEAL,
            GREEN,
            LIGHT_GREEN,
            LIME,
            YELLOW,
            AMBER,
            ORANGE,
            DEEP_ORANGE
    };

    public static final String[] COLORS_WITHOUT_ACCENT_NAMES = new String[] {
            BROWN,
            GREY,
            BLUE_GREY
    };

    public static final String[] ALL_COLOR_NAMES = new String[] {
            RED,
            PINK,
            PURPLE,
            DEEP_PURPLE,
            INDIGO,
            BLUE,
            LIGHT_BLUE,
            CYAN,
            TEAL,
            GREEN,
            LIGHT_GREEN,
            LIME,
            YELLOW,
            AMBER,
            ORANGE,
            DEEP_ORANGE,
            BROWN,
            GREY,
            BLUE_GREY
    };

    // Color levels
    public static final String LEVEL_50 = "50";
    public static final String LEVEL_100 = "100";
    public static final String LEVEL_200 = "200";
    public static final String LEVEL_300 = "300";
    public static final String LEVEL_400 = "400";
    public static final String LEVEL_500 = "500";
    public static final String LEVEL_600 = "600";
    public static final String LEVEL_700 = "700";
    public static final String LEVEL_800 = "800";
    public static final String LEVEL_900 = "900";
    public static final String LEVEL_A100 = "A100";
    public static final String LEVEL_A200 = "A200";
    public static final String LEVEL_A400 = "A400";
    public static final String LEVEL_A700 = "A700";

    public static final String[] NON_ACCENT_COLOR_LEVELS = new String[] {
            LEVEL_50,
            LEVEL_100,
            LEVEL_200,
            LEVEL_300,
            LEVEL_400,
            LEVEL_500,
            LEVEL_600,
            LEVEL_700,
            LEVEL_800,
            LEVEL_900
    };

    public static final String[] ACCENT_COLOR_LEVELS = new String[] {
            LEVEL_A100,
            LEVEL_A200,
            LEVEL_A400,
            LEVEL_A700
    };

    public static final String[] COLOR_LEVELS = {
            LEVEL_50,
            LEVEL_100,
            LEVEL_200,
            LEVEL_300,
            LEVEL_400,
            LEVEL_500,
            LEVEL_600,
            LEVEL_700,
            LEVEL_800,
            LEVEL_900,
            LEVEL_A100,
            LEVEL_A200,
            LEVEL_A400,
            LEVEL_A700
    };

    /**
     * List of colors that have been randomly generated to avoid repeating colors.
     */
    private static List<Integer> randomList;

    /**
     * List that contains all the colors.
     */
    private static List<Integer> allColors;

    /**
     * Number of times we have created a random color. If we hit the limit we must start over.
     */
    private static int randomCount;

    private static final String MESSAGE_BAD_COLOR_NAME = "Invalid color name.";
    private static final String MESSAGE_BAD_COLOR_LEVEL = "Invalid color level.";

    /**
     * Builds a list of color resources for a Material Design color palette of a specific color.
     *
     * NOTE: For Brown, Grey, or Blue Grey this will not return any accent colors.
     * @param colorName The name of the color to build the palette for.
     * @return The list of relevant color resources for the specific color name.
     * @throws IllegalAccessException The resource could not be accessed.
     */
    public static List<Integer> getColorsByName(String colorName) throws IllegalAccessException{
        return getColorsByName(colorName, true); // Default true for accents.
    }

    /**
     * Builds a list of color resources for a Material Design color palette of a specific color.
     *
     * NOTE: For Brown, Grey, or Blue Grey this will not return any accent colors.
     * @param colorName The name of the color to build the palette for.
     * @param getAccents A flag variable that will either fetch the accent level colors or not.
     * @return The list of relevant color resources for the specific color name.
     * @throws IllegalAccessException The resource could not be accessed.
     */
    public static List<Integer> getColorsByName(String colorName, boolean getAccents) throws IllegalAccessException{
        // Verify color name
        if(!Arrays.asList(ALL_COLOR_NAMES).contains(colorName)) {
            throw new IllegalArgumentException(MESSAGE_BAD_COLOR_NAME);
        }

        List<Integer> colorList = new ArrayList<>();

        Field[] fields = R.color.class.getFields();

        // Loop through colors and find those that have this color name and meet our convention.
        for(Field field : fields) {
            String key = field.getName();
            // Find color that starts with this name.
            // If get accents is true, return color. If it's not, avoid adding ones that have
            // accent signifier.
            if(key.startsWith(PREFIX + colorName + SEPARATOR) && (getAccents || !key.startsWith(PREFIX + colorName + ACCENT_SIGNIFIER))) {
                colorList.add(field.getInt(null));
            }
        }

        return colorList;
    }

    /**
     * Builds a list of color resources for the Accents of a given color name.
     * @param colorName The color to retrieve accent shades for.
     * @return The color resources for all accent colors of the given name.
     */
    public static List<Integer> getAccentColorsByName(String colorName) throws IllegalAccessException {
        // Verify input
        if(!Arrays.asList(COLORS_WITH_ACCENT_NAMES).contains(colorName)) {
            throw new IllegalArgumentException(MESSAGE_BAD_COLOR_NAME);
        }

        List<Integer> colorList = new ArrayList<>();

        Field[] fields = R.color.class.getFields();

        // Loop through and get ones that meet our criteria
        for(Field field : fields) {
            String key = field.getName();
            if(key.startsWith(PREFIX + colorName + ACCENT_SIGNIFIER)) {
                colorList.add(field.getInt(null));
            }
        }

        return colorList;
    }

    /**
     * Builds a list of color resources for a Material Design color palette of a specific color level.
     *
     * NOTE: For accent levels, this will not return Brown, Grey, or Blue Grey.
     * @param colorLevel The level of the colors to build the palette with.
     * @return The list of relevant color resources for the specific color level.
     * @throws IllegalAccessException The resource could not be accessed.
     */
    public static List<Integer> getColorsByLevel(String colorLevel) throws IllegalAccessException{
        // Verify color level
        if(!Arrays.asList(COLOR_LEVELS).contains(colorLevel)) {
            throw new IllegalArgumentException(MESSAGE_BAD_COLOR_LEVEL);
        }

        List<Integer> colorList = new ArrayList<>();

        Field[] fields = R.color.class.getFields();

        // Loop through colors and find those that have this color level and meet our convention.
        for(Field field : fields) {
            String key = field.getName();
            if(key.startsWith(PREFIX) && key.endsWith(SEPARATOR + colorLevel)) {
                colorList.add(field.getInt(null));
            }
        }

        return colorList;
    }

    /**
     * Retrieves a random material design color.
     * @return A color resource for one of the material design colors.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    public static Integer getRandomColor() throws IllegalAccessException{
        List<Integer> colorList = getAllColors();

        // Get random
        Random random = new Random();
        return colorList.get(random.nextInt(colorList.size()));
    }

    /**
     * Retrieves a random color from the palettes that is not an accent color.
     * @return A color resource for one of the material design colors.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    public static Integer getRandomNonAccentColor() throws IllegalAccessException {
        List<Integer> colorList = new ArrayList<>();

        // For each color, add its non-accent colors to list
        for(String color : ALL_COLOR_NAMES) {
            colorList.addAll(getColorsByName(color, false));
        }

        // Return a random one
        Random random = new Random();
        return colorList.get(random.nextInt(colorList.size()));
    }

    /**
     * Retrieves a random accent color from the palettes.
     * @return A color resource for one of the material design accent colors.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    public static Integer getRandomAccentColor() throws IllegalAccessException {
        List<Integer> colorList = new ArrayList<>();

        // For each accent level, add its colors to the list.
        for(String level : ACCENT_COLOR_LEVELS) {
            colorList.addAll(getColorsByLevel(level));
        }

        // Return a random one
        Random random = new Random();
        return colorList.get(random.nextInt(colorList.size()));
    }

    /**
     * Retrieves a random material design color for a given level.
     * @param colorLevel The color level to get a color for.
     * @return A color resource for a random color of the given level.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    public static Integer getRandomColorByLevel(String colorLevel) throws IllegalAccessException {
        // Get all colors for this level
        // Input check is done in this function.
        List<Integer> colorList = getColorsByLevel(colorLevel);

        Random random = new Random();
        return colorList.get(random.nextInt(colorList.size()));
    }

    /**
     * Retrieves a random material design color for the given shade, including any available accent shades.
     * @param colorName The name of the color to get a shade for.
     * @return A color resource for a randomly generated shade of the specified color.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    public static Integer getRandomColorByName(String colorName) throws IllegalAccessException{
        return getRandomColorByName(colorName, true);
    }

    /**
     * Determines a random color that does not repeat until a color from all different palettes has
     * been returned.
     * @return A color resource for a random Material Design color.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    public static Integer getRandomColorNonRepeating() throws IllegalAccessException {
        if(randomList == null || randomList.isEmpty() || randomCount >= randomList.size()) {
            initColorRandomizer();
        }

        // Get color name from our random list
        int colorIndex = randomList.get(randomCount++);
        return getAllColors().get(colorIndex);
    }

    /**
     * Retrieves a random material design color for the given shade.
     * @param colorName The name of the color to get a shade for.
     * @param useAccents Whether or not to include accent shades in the random selection.
     * @return A color resource for a randomly generated shade of the specified color.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    public static Integer getRandomColorByName(String colorName, boolean useAccents) throws IllegalAccessException{
        // Get all colors for this name.
        // Input check is in this function.
        List<Integer> colorList = getColorsByName(colorName, useAccents);

        Random random = new Random();
        return colorList.get(random.nextInt(colorList.size()));
    }

    /**
     * Retrieves a random color from a list of accepted values.
     * @param acceptedColorNames The color names that are acceptable for the random color returned.
     * @return A color resource for a random Material Design color.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    public static Integer getRandomColorWithLimits(String[] acceptedColorNames) throws IllegalAccessException {
        // First do error checking
        if (!Arrays.asList(ALL_COLOR_NAMES).containsAll(Arrays.asList(acceptedColorNames))) {
            throw new IllegalArgumentException(MESSAGE_BAD_COLOR_NAME);
        }

        // Get all possible colors
        List<Integer> colorList = new ArrayList<>();

        for (String name : acceptedColorNames) {
            colorList.addAll(getColorsByName(name));
        }

        // Return a random color from the list.
        Random random = new Random();
        return colorList.get(random.nextInt(colorList.size()));
    }

    /**
     * Retrieves all material design palette colors.
     * @return A list of color resources for all available material design colors.
     */
    public static List<Integer> getAllColors() throws IllegalAccessException{
        if (allColors == null) {
            allColors = new ArrayList<>();
        }
        if (allColors.size() > 0) {
            return allColors;
        }

        Field[] fields = R.color.class.getFields();

        // Add all colors that start with our prefix
        for(Field field : fields) {
            String key = field.getName();
            if(key.startsWith(PREFIX)) {
                allColors.add(field.getInt(null));
            }
        }

        return allColors;
    }

    /**
     * Initializes the list used to keep track of whether or not a color has been returned yet.
     */
    private static void initColorRandomizer() throws IllegalAccessException {
        if(randomList == null) {
            randomList = new ArrayList<>();
        }

        if(randomList.size() > 0) {
            randomList.clear();
        }

        int size = getAllColors().size();

        for(int i = 0; i < size; i++) {
            randomList.add(i);
        }

        Collections.shuffle(randomList);
        randomCount = 0;
    }

    private static void sortFields(Field[] fields) {
        final AlphaNumComparator anComp = new AlphaNumComparator();

        Arrays.sort(fields, new Comparator<Field>() {
            @Override
            public int compare(Field lhs, Field rhs) {
                String lhss = lhs.getName();
                String rhss = rhs.getName();

                return anComp.compare(lhss,rhss);
            }
        });
    }

    public static Field[] getFields() {
        return R.color.class.getFields();
    }

//    private static List<String> getAllColorNames(boolean sorted) {
//        List<String> colors = Arrays.asList(ALL_COLOR_NAMES);
//        if (sorted) {
//            Arrays.sort(ALL_COLOR_NAMES);
//        }
//        return new ArrayList<>(Arrays.asList(ALL_COLOR_NAMES));
//    }
}
