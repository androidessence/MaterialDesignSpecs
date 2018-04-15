[lib](../../index.md) / [com.androidessence.materialdesignspecs](../index.md) / [MaterialPalettes](index.md) / [getRandomColorByName](./get-random-color-by-name.md)

# getRandomColorByName

`@JvmOverloads @JvmStatic fun getRandomColorByName(colorName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, useAccents: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?`

Retrieves a random material design color for the given name. The caller has the ability to
decide if accent colors can be excluded using the [useAccents](get-random-color-by-name.md#com.androidessence.materialdesignspecs.MaterialPalettes$getRandomColorByName(kotlin.String, kotlin.Boolean)/useAccents) parameter.

TODO: We want to add a companion getAccents to [getRandomColorWithLimits](get-random-color-with-limits.md) as well,
and also rename that to be more consistent with this one.

