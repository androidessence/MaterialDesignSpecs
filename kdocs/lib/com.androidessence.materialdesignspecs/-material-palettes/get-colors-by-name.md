[lib](../../index.md) / [com.androidessence.materialdesignspecs](../index.md) / [MaterialPalettes](index.md) / [getColorsByName](./get-colors-by-name.md)

# getColorsByName

`@JvmOverloads @JvmStatic fun getColorsByName(colorName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, getAccents: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`

Builds a list of color resources for a Material Design color palette of a specific color. The
caller has the ability to exclude accent colors using the [getAccents](get-colors-by-name.md#com.androidessence.materialdesignspecs.MaterialPalettes$getColorsByName(kotlin.String, kotlin.Boolean)/getAccents) parameter.

NOTE: For Brown, Grey, or Blue Grey this will not return any accent colors.

