package com.androidessence.materialdesignspecs

import com.androidessence.materialdesignspeclibrary.R
import java.lang.reflect.Field
import java.util.*

/**
 * Utility functions for pulling various material design color palettes.
 *
 * Created by adammcneilly and mauker on 11/26/15.
 */
object MaterialPalettes {
    // Our variables
    private val PREFIX = "mds_"
    private val SEPARATOR = "_"
    private val ACCENT_SIGNIFIER = "_A"

    // Color names
    val RED = "red"
    val PINK = "pink"
    val PURPLE = "purple"
    val DEEP_PURPLE = "deeppurple"
    val INDIGO = "indigo"
    val BLUE = "blue"
    val LIGHT_BLUE = "lightblue"
    val CYAN = "cyan"
    val TEAL = "teal"
    val GREEN = "green"
    val LIGHT_GREEN = "lightgreen"
    val LIME = "lime"
    val YELLOW = "yellow"
    val AMBER = "amber"
    val ORANGE = "orange"
    val DEEP_ORANGE = "deeporange"
    val BROWN = "brown"
    val GREY = "grey"
    val BLUE_GREY = "bluegrey"

    val COLORS_WITH_ACCENT_NAMES = arrayOf(RED, PINK, PURPLE, DEEP_PURPLE, INDIGO, BLUE, LIGHT_BLUE, CYAN, TEAL, GREEN, LIGHT_GREEN, LIME, YELLOW, AMBER, ORANGE, DEEP_ORANGE)

    val COLORS_WITHOUT_ACCENT_NAMES = arrayOf(BROWN, GREY, BLUE_GREY)

    val ALL_COLOR_NAMES = arrayOf(RED, PINK, PURPLE, DEEP_PURPLE, INDIGO, BLUE, LIGHT_BLUE, CYAN, TEAL, GREEN, LIGHT_GREEN, LIME, YELLOW, AMBER, ORANGE, DEEP_ORANGE, BROWN, GREY, BLUE_GREY)

    // Color levels
    val LEVEL_50 = "50"
    val LEVEL_100 = "100"
    val LEVEL_200 = "200"
    val LEVEL_300 = "300"
    val LEVEL_400 = "400"
    val LEVEL_500 = "500"
    val LEVEL_600 = "600"
    val LEVEL_700 = "700"
    val LEVEL_800 = "800"
    val LEVEL_900 = "900"
    val LEVEL_A100 = "A100"
    val LEVEL_A200 = "A200"
    val LEVEL_A400 = "A400"
    val LEVEL_A700 = "A700"

    val NON_ACCENT_COLOR_LEVELS = arrayOf(LEVEL_50, LEVEL_100, LEVEL_200, LEVEL_300, LEVEL_400, LEVEL_500, LEVEL_600, LEVEL_700, LEVEL_800, LEVEL_900)

    val ACCENT_COLOR_LEVELS = arrayOf(LEVEL_A100, LEVEL_A200, LEVEL_A400, LEVEL_A700)

    val COLOR_LEVELS = arrayOf(LEVEL_50, LEVEL_100, LEVEL_200, LEVEL_300, LEVEL_400, LEVEL_500, LEVEL_600, LEVEL_700, LEVEL_800, LEVEL_900, LEVEL_A100, LEVEL_A200, LEVEL_A400, LEVEL_A700)

    /**
     * List of colors that have been randomly generated to avoid repeating colors.
     */
    private var randomList: MutableList<Int>? = null

    /**
     * List that contains all the colors.
     */
    private var allColors: MutableList<Int>? = null

    /**
     * Number of times we have created a random color. If we hit the limit we must start over.
     */
    private var randomCount: Int = 0

    private val MESSAGE_BAD_COLOR_NAME = "Invalid color name."
    private val MESSAGE_BAD_COLOR_LEVEL = "Invalid color level."

    /**
     * Builds a list of color resources for a Material Design color palette of a specific color.
     * NOTE: For Brown, Grey, or Blue Grey this will not return any accent colors.
     *
     * @param colorName The name of the color to build the palette for.
     * @param getAccents A flag variable that will either fetch the accent level colors or not.
     * @return The list of relevant color resources for the specific color name.
     * @throws IllegalAccessException The resource could not be accessed.
     */
    @Throws(IllegalAccessException::class)
    @JvmOverloads fun getColorsByName(colorName: String, getAccents: Boolean = true): List<Int> {
        // Verify color name
        if (!ALL_COLOR_NAMES.contains(colorName)) {
            throw IllegalArgumentException(MESSAGE_BAD_COLOR_NAME)
        }

        val colorList = ArrayList<Int>()

        // Loop through colors and find those that have this color name and meet our convention.
        for (field in fields) {
            val key = field.name
            // Find color that starts with this name.
            // If get accents is true, return color. If it's not, avoid adding ones that have
            // accent signifier.
            if (key.startsWith(PREFIX + colorName + SEPARATOR) && (getAccents || !key.startsWith(PREFIX + colorName + ACCENT_SIGNIFIER))) {
                colorList.add(field.getInt(null))
            }
        }

        return colorList
    }

    /**
     * Retrieves a list of colors for multiple color names.
     *
     * @param colorNames The names of the colors to return.
     * @param getAccents A flag variable that will either fetch the accent level colors or not.
     * @return An array list of color resources for each of the colors given.
     * @throws IllegalAccessException Thrown if any resources cannot be accessed.
     */
    @Throws(IllegalAccessException::class)
    @JvmOverloads fun getColorsByNames(colorNames: Array<String>?, getAccents: Boolean = true): List<Int> {
        if (colorNames == null) {
            throw IllegalArgumentException(MESSAGE_BAD_COLOR_NAME)
        }

        val colorList = ArrayList<Int>()

        for (name in colorNames) {
            colorList.addAll(getColorsByName(name, getAccents))
        }

        return colorList
    }

    /**
     * Builds a list of color resources for the Accents of a given color name.
     *
     * @param colorName The color to retrieve accent shades for.
     * @return The color resources for all accent colors of the given name.
     */
    @Throws(IllegalAccessException::class)
    fun getAccentColorsByName(colorName: String): List<Int> {
        // Verify input
        if (!COLORS_WITH_ACCENT_NAMES.contains(colorName)) {
            throw IllegalArgumentException(MESSAGE_BAD_COLOR_NAME)
        }

        val colorList = ArrayList<Int>()

        // Loop through and get ones that meet our criteria
        for (field in fields) {
            val key = field.name
            if (key.startsWith(PREFIX + colorName + ACCENT_SIGNIFIER)) {
                colorList.add(field.getInt(null))
            }
        }

        return colorList
    }

    /**
     * Builds a list of color resources for a Material Design color palette of a specific color level.
     * NOTE: For accent levels, this will not return Brown, Grey, or Blue Grey.
     *
     * @param colorLevel The level of the colors to build the palette with.
     * @return The list of relevant color resources for the specific color level.
     * @throws IllegalAccessException The resource could not be accessed.
     */
    @Throws(IllegalAccessException::class)
    fun getColorsByLevel(colorLevel: String): List<Int> {
        // Verify color level
        if (!COLOR_LEVELS.contains(colorLevel)) {
            throw IllegalArgumentException(MESSAGE_BAD_COLOR_LEVEL)
        }

        val colorList = ArrayList<Int>()

        // Loop through colors and find those that have this color level and meet our convention.
        for (field in fields) {
            val key = field.name
            if (key.startsWith(PREFIX) && key.endsWith(SEPARATOR + colorLevel)) {
                colorList.add(field.getInt(null))
            }
        }

        return colorList
    }

    /**
     * Retrieves a random material design color.
     *
     * @return A color resource for one of the material design colors.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    // Get random
    val randomColor: Int?
        @Throws(IllegalAccessException::class)
        get() {
            val colorList = getAllColors()
            val random = Random()
            return colorList[random.nextInt(colorList.size)]
        }

    /**
     * Retrieves a random color from the palettes that is not an accent color.
     *
     * @return A color resource for one of the material design colors.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    // For each color, add its non-accent colors to list
    // Return a random one
    val randomNonAccentColor: Int?
        @Throws(IllegalAccessException::class)
        get() {
            val colorList = ArrayList<Int>()
            for (color in ALL_COLOR_NAMES) {
                colorList.addAll(getColorsByName(color, false))
            }
            val random = Random()
            return colorList[random.nextInt(colorList.size)]
        }

    /**
     * Retrieves a random accent color from the palettes.
     *
     * @return A color resource for one of the material design accent colors.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    // For each accent level, add its colors to the list.
    // Return a random one
    val randomAccentColor: Int?
        @Throws(IllegalAccessException::class)
        get() {
            val colorList = ArrayList<Int>()
            for (level in ACCENT_COLOR_LEVELS) {
                colorList.addAll(getColorsByLevel(level))
            }
            val random = Random()
            return colorList[random.nextInt(colorList.size)]
        }

    /**
     * Retrieves a random material design color for a given level.
     *
     * @param colorLevel The color level to get a color for.
     * @return A color resource for a random color of the given level.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    @Throws(IllegalAccessException::class)
    fun getRandomColorByLevel(colorLevel: String): Int? {
        // Get all colors for this level
        // Input check is done in this function.
        val colorList = getColorsByLevel(colorLevel)

        val random = Random()
        return colorList[random.nextInt(colorList.size)]
    }

    /**
     * Determines a random color that does not repeat until a color from all different palettes has
     * been returned.
     *
     * @return A color resource for a random Material Design color.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    val randomColorNonRepeating: Int?
        @Throws(IllegalAccessException::class)
        get() {
            if (randomList == null || randomList!!.isEmpty() || randomCount >= (randomList?.size ?: 0)) {
                initColorRandomizer()
            }
            val colorIndex = randomList!![randomCount++]
            return getAllColors()[colorIndex]
        }

    /**
     * Retrieves a random material design color for the given shade.
     *
     * @param colorName The name of the color to get a shade for.
     * @param useAccents Whether or not to include accent shades in the random selection.
     * @return A color resource for a randomly generated shade of the specified color.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    @Throws(IllegalAccessException::class)
    @JvmOverloads fun getRandomColorByName(colorName: String, useAccents: Boolean = true): Int? {
        // Get all colors for this name.
        // Input check is in this function.
        val colorList = getColorsByName(colorName, useAccents)

        val random = Random()
        return colorList[random.nextInt(colorList.size)]
    }

    /**
     * Retrieves a random color from a list of accepted values.
     *
     * @param acceptedColorNames The color names that are acceptable for the random color returned.
     * @return A color resource for a random Material Design color.
     * @throws IllegalAccessException If a resource cannot be accessed.
     */
    @Throws(IllegalAccessException::class)
    fun getRandomColorWithLimits(acceptedColorNames: Array<String>): Int? {
        // First do error checking
        if (!Arrays.asList(*ALL_COLOR_NAMES).containsAll(Arrays.asList(*acceptedColorNames))) {
            throw IllegalArgumentException(MESSAGE_BAD_COLOR_NAME)
        }

        // Get all possible colors
        val colorList = ArrayList<Int>()

        for (name in acceptedColorNames) {
            colorList.addAll(getColorsByName(name))
        }

        // Return a random color from the list.
        val random = Random()
        return colorList[random.nextInt(colorList.size)]
    }

    /**
     * Retrieves all material design palette colors.
     *
     * @return A list of color resources for all available material design colors.
     */
    @Throws(IllegalAccessException::class)
    fun getAllColors(): List<Int> {
        if (allColors == null) {
            allColors = ArrayList<Int>()
        }

        if (allColors?.isNotEmpty() ?: false) {
            return allColors as MutableList<Int>
        }

        // Add all colors that start with our prefix
        for (field in fields) {
            val key = field.name
            if (key.startsWith(PREFIX)) {
                allColors?.add(field.getInt(null))
            }
        }

        return allColors as MutableList<Int>
    }

    /**
     * Initializes the list used to keep track of whether or not a color has been returned yet.
     */
    @Throws(IllegalAccessException::class)
    private fun initColorRandomizer() {
        if (randomList == null) {
            randomList = ArrayList<Int>()
        }

        if (randomList?.isNotEmpty() ?: false) {
            randomList?.clear()
        }

        val size = getAllColors().size

        for (i in 0..size - 1) {
            randomList?.add(i)
        }

        Collections.shuffle(randomList)
        randomCount = 0
    }

    private fun sortFields(fields: Array<Field>) {
        val anComp = AlphaNumComparator()

        Arrays.sort(fields) { lhs, rhs ->
            val lhss = lhs.name
            val rhss = rhs.name

            anComp.compare(lhss, rhss)
        }
    }

    val fields: Array<Field>
        get() = R.color::class.java.fields
}
