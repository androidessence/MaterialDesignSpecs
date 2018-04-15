package com.androidessence.materialdesignspecs

import com.androidessence.materialdesignspeclibrary.R
import java.lang.reflect.Field
import java.util.*

/**
 * Utility functions for pulling various material design color palettes.
 *
 * Created by adammcneilly and mauker on 11/26/15.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
object MaterialPalettes {
    //region Private Properties & Helpers
    private const val PREFIX = "mds_"
    private const val SEPARATOR = "_"
    private const val ACCENT_SIGNIFIER = "_A"

    private const val MESSAGE_BAD_COLOR_NAME = "Invalid color name."
    private const val MESSAGE_BAD_COLOR_LEVEL = "Invalid color level."

    private fun sortFields(fields: Array<Field>) {
        val anComp = AlphaNumComparator()

        Arrays.sort(fields) { lhs, rhs ->
            val lhss = lhs.name
            val rhss = rhs.name

            anComp.compare(lhss, rhss)
        }
    }

    private val fields: Array<Field>
        get() = R.color::class.java.fields
    //endregion

    //region Color Names & Levels
    const val RED = "red"
    const val PINK = "pink"
    const val PURPLE = "purple"
    const val DEEP_PURPLE = "deeppurple"
    const val INDIGO = "indigo"
    const val BLUE = "blue"
    const val LIGHT_BLUE = "lightblue"
    const val CYAN = "cyan"
    const val TEAL = "teal"
    const val GREEN = "green"
    const val LIGHT_GREEN = "lightgreen"
    const val LIME = "lime"
    const val YELLOW = "yellow"
    const val AMBER = "amber"
    const val ORANGE = "orange"
    const val DEEP_ORANGE = "deeporange"
    const val BROWN = "brown"
    const val GREY = "grey"
    const val BLUE_GREY = "bluegrey"

    const val LEVEL_50 = "50"
    const val LEVEL_100 = "100"
    const val LEVEL_200 = "200"
    const val LEVEL_300 = "300"
    const val LEVEL_400 = "400"
    const val LEVEL_500 = "500"
    const val LEVEL_600 = "600"
    const val LEVEL_700 = "700"
    const val LEVEL_800 = "800"
    const val LEVEL_900 = "900"
    const val LEVEL_A100 = "A100"
    const val LEVEL_A200 = "A200"
    const val LEVEL_A400 = "A400"
    const val LEVEL_A700 = "A700"

    @JvmStatic
    val COLORS_WITH_ACCENT_NAMES = arrayOf(RED, PINK, PURPLE, DEEP_PURPLE, INDIGO, BLUE, LIGHT_BLUE, CYAN, TEAL, GREEN, LIGHT_GREEN, LIME, YELLOW, AMBER, ORANGE, DEEP_ORANGE)

    @JvmStatic
    val COLORS_WITHOUT_ACCENT_NAMES = arrayOf(BROWN, GREY, BLUE_GREY)

    @JvmStatic
    val ALL_COLOR_NAMES = arrayOf(RED, PINK, PURPLE, DEEP_PURPLE, INDIGO, BLUE, LIGHT_BLUE, CYAN, TEAL, GREEN, LIGHT_GREEN, LIME, YELLOW, AMBER, ORANGE, DEEP_ORANGE, BROWN, GREY, BLUE_GREY)

    @JvmStatic
    val NON_ACCENT_COLOR_LEVELS = arrayOf(LEVEL_50, LEVEL_100, LEVEL_200, LEVEL_300, LEVEL_400, LEVEL_500, LEVEL_600, LEVEL_700, LEVEL_800, LEVEL_900)

    @JvmStatic
    val ACCENT_COLOR_LEVELS = arrayOf(LEVEL_A100, LEVEL_A200, LEVEL_A400, LEVEL_A700)

    @JvmStatic
    val COLOR_LEVELS = arrayOf(LEVEL_50, LEVEL_100, LEVEL_200, LEVEL_300, LEVEL_400, LEVEL_500, LEVEL_600, LEVEL_700, LEVEL_800, LEVEL_900, LEVEL_A100, LEVEL_A200, LEVEL_A400, LEVEL_A700)
    //endregion

    //region Get Colors By Name & Level
    /**
     * Builds a list of color resources for a Material Design color palette of a specific color. The
     * caller has the ability to exclude accent colors using the [getAccents] parameter.
     *
     * NOTE: For Brown, Grey, or Blue Grey this will not return any accent colors.
     */
    @Throws(IllegalAccessException::class)
    @JvmOverloads
    @JvmStatic
    fun getColorsByName(colorName: String, getAccents: Boolean = true): List<Int> {
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
     * TODO: Consider if we can consolidate this and `getColorsByName` somehow.
     * @see getColorsByName
     */
    @Throws(IllegalAccessException::class)
    @JvmOverloads
    @JvmStatic
    fun getColorsByNames(colorNames: Array<String>?, getAccents: Boolean = true): List<Int> {
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
     * Builds a list of color resources for the accents of a given color name.
     */
    @Throws(IllegalAccessException::class)
    @JvmStatic
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
     */
    @Throws(IllegalAccessException::class)
    @JvmStatic
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
    //endregion

    //region Random
    /**
     * List of colors that have been randomly generated to avoid repeating colors.
     */
    private var randomList: MutableList<Int>? = null

    /**
     * Number of times we have created a random color. If we hit the limit we must start over.
     */
    private var randomCount: Int = 0

    /**
     * Retrieves a random material design color from any palette.
     */
    @Throws(IllegalAccessException::class)
    @JvmStatic
    fun getRandomColor(): Int {
        val colorList = getAllColors()
        val random = Random()
        return colorList[random.nextInt(colorList.size)]
    }

    /**
     * Retrieves a random color from the palettes that is not an accent color.
     */
    @Throws(IllegalAccessException::class)
    @JvmStatic
    fun getRandomNonAccentColor(): Int {
        val colorList = ArrayList<Int>()
        for (color in ALL_COLOR_NAMES) {
            colorList.addAll(getColorsByName(color, false))
        }
        val random = Random()
        return colorList[random.nextInt(colorList.size)]
    }

    /**
     * Retrieves a random accent color from the palettes that support accents.
     */
    @Throws(IllegalAccessException::class)
    @JvmStatic
    fun getRandomAccentColor(): Int {
        val colorList = ArrayList<Int>()
        for (level in ACCENT_COLOR_LEVELS) {
            colorList.addAll(getColorsByLevel(level))
        }
        val random = Random()
        return colorList[random.nextInt(colorList.size)]
    }

    /**
     * Retrieves a random material design color for a given level. If this is an accent level,
     * certain colors will be excluded from the result.
     */
    @Throws(IllegalAccessException::class)
    @JvmStatic
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
     * @see randomList
     */
    @Throws(IllegalAccessException::class)
    @JvmStatic
    fun getRandomColorNonRepeating(): Int {
        if (randomList == null || randomList!!.isEmpty() || randomCount >= (randomList?.size
                        ?: 0)) {
            initColorRandomizer()
        }

        val colorIndex = randomList!![randomCount++]
        return getAllColors()[colorIndex]
    }

    /**
     * Retrieves a random material design color for the given name. The caller has the ability to
     * decide if accent colors can be excluded using the [useAccents] parameter.
     *
     * TODO: We want to add a companion getAccents to [getRandomColorWithLimits] as well,
     * and also rename that to be more consistent with this one.
     */
    @Throws(IllegalAccessException::class)
    @JvmOverloads
    @JvmStatic
    fun getRandomColorByName(colorName: String, useAccents: Boolean = true): Int? {
        // Get all colors for this name.
        // Input check is in this function.
        val colorList = getColorsByName(colorName, useAccents)

        val random = Random()
        return colorList[random.nextInt(colorList.size)]
    }

    /**
     * Retrieves a random color from a list of accepted color names.
     */
    @Throws(IllegalAccessException::class)
    @JvmStatic
    fun getRandomColorWithLimits(acceptedColorNames: Array<String>): Int? {
        // First do error checking
        //TODO: Convert color names and such to lists, to avoid this stuff.
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
     * Initializes the list used to keep track of whether or not a color has been returned yet.
     */
    @Throws(IllegalAccessException::class)
    private fun initColorRandomizer() {
        if (randomList == null) {
            randomList = ArrayList()
        }

        if (randomList?.isNotEmpty() == true) {
            randomList?.clear()
        }

        val size = getAllColors().size

        for (i in 0 until size) {
            randomList?.add(i)
        }

        randomList?.shuffle()
        randomCount = 0
    }
    //endregion

    //region All Colors
    /**
     * List that contains all the colors.
     */
    private var allColors: MutableList<Int>? = null

    /**
     * Retrieves all material design palette colors.
     *
     * @return A list of color resources for all available material design colors.
     */
    @Throws(IllegalAccessException::class)
    @JvmStatic
    fun getAllColors(): List<Int> {
        if (allColors == null) {
            allColors = ArrayList()
        }

        if (allColors?.isNotEmpty() == true) {
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
    //endregion
}
