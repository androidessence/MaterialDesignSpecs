[lib](../../index.md) / [com.androidessence.materialdesignspecs](../index.md) / [SquareColorAdapter](./index.md)

# SquareColorAdapter

`class SquareColorAdapter : `[`BaseColorAdapter`](../-base-color-adapter/index.md)

Adapter that displays a bunch of square color choices.

Created by ankitagrawal on 5/15/17.

### Types

| Name | Summary |
|---|---|
| [SquareColorViewHolder](-square-color-view-holder/index.md) | `inner class SquareColorViewHolder : `[`BaseColorViewHolder`](../-base-color-adapter/-base-color-view-holder/index.md) |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SquareColorAdapter(listener: `[`OnColorSelectedListener`](../-color-dialog/-on-color-selected-listener/index.md)`)`<br>`SquareColorAdapter(colors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>, listener: `[`OnColorSelectedListener`](../-color-dialog/-on-color-selected-listener/index.md)`)`<br>`SquareColorAdapter(colors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>, listener: `[`OnColorSelectedListener`](../-color-dialog/-on-color-selected-listener/index.md)`, selectedPos: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Inherited Properties

| Name | Summary |
|---|---|
| [colors](../-base-color-adapter/colors.md) | `var colors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`<br>The list of color resources to display in the adapter. |
| [onColorSelectedListener](../-base-color-adapter/on-color-selected-listener.md) | `var onColorSelectedListener: `[`OnColorSelectedListener`](../-color-dialog/-on-color-selected-listener/index.md)`?`<br>A callback to notify when a color is selected in the dialog. |
| [selectedPosition](../-base-color-adapter/selected-position.md) | `var selectedPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The position in the list of [colors](../-base-color-adapter/colors.md) that the user has selected, and is used to notify the [onColorSelectedListener](../-base-color-adapter/on-color-selected-listener.md). |

### Functions

| Name | Summary |
|---|---|
| [getRVItemLayout](get-r-v-item-layout.md) | `fun getRVItemLayout(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Abstract method that retrieves the item layout for a row in the RecyclerView. |
| [getViewHolder](get-view-holder.md) | `fun getViewHolder(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`BaseColorViewHolder`](../-base-color-adapter/-base-color-view-holder/index.md)<br>Abstract method for the ViewHolder used by this adapter. |

### Inherited Functions

| Name | Summary |
|---|---|
| [getItemCount](../-base-color-adapter/get-item-count.md) | `open fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Determines the number of items to display in the adapter. |
| [onBindViewHolder](../-base-color-adapter/on-bind-view-holder.md) | `open fun onBindViewHolder(holder: `[`BaseColorViewHolder`](../-base-color-adapter/-base-color-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Binds an individual color resource to the ViewHolder. |
| [onCreateViewHolder](../-base-color-adapter/on-create-view-holder.md) | `open fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`BaseColorViewHolder`](../-base-color-adapter/-base-color-view-holder/index.md)<br>Creates a ViewHolder for displaying a color resource. |
