[lib](../../index.md) / [com.androidessence.materialdesignspecs](../index.md) / [BaseColorAdapter](./index.md)

# BaseColorAdapter

`abstract class BaseColorAdapter : Adapter<`[`BaseColorViewHolder`](-base-color-view-holder/index.md)`>`

Base adapter class for displaying a list of colors in a RecyclerView.

### Parameters

`onColorSelectedListener` - A callback to notify when a color is selected in the dialog.

`colors` - The list of color resources to display in the adapter.

`initialPosition` - The position in the list of [colors](colors.md) to set as the initially selected one.

### Types

| Name | Summary |
|---|---|
| [BaseColorViewHolder](-base-color-view-holder/index.md) | `abstract inner class BaseColorViewHolder : ViewHolder, `[`OnClickListener`](https://developer.android.com/reference/android/view/View/OnClickListener.html)<br>Base ViewHolder for a color adapter. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BaseColorAdapter(onColorSelectedListener: `[`OnColorSelectedListener`](../-color-dialog/-on-color-selected-listener/index.md)`?, colors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`> = ArrayList(), initialPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = RecyclerView.NO_POSITION)`<br>Base adapter class for displaying a list of colors in a RecyclerView. |

### Properties

| Name | Summary |
|---|---|
| [colors](colors.md) | `var colors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`<br>The list of color resources to display in the adapter. |
| [onColorSelectedListener](on-color-selected-listener.md) | `var onColorSelectedListener: `[`OnColorSelectedListener`](../-color-dialog/-on-color-selected-listener/index.md)`?`<br>A callback to notify when a color is selected in the dialog. |
| [selectedPosition](selected-position.md) | `var selectedPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The position in the list of [colors](colors.md) that the user has selected, and is used to notify the [onColorSelectedListener](on-color-selected-listener.md). |

### Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | `open fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Determines the number of items to display in the adapter. |
| [getRVItemLayout](get-r-v-item-layout.md) | `abstract fun getRVItemLayout(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Abstract method that retrieves the item layout for a row in the RecyclerView. |
| [getViewHolder](get-view-holder.md) | `abstract fun getViewHolder(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`BaseColorViewHolder`](-base-color-view-holder/index.md)<br>Abstract method for the ViewHolder used by this adapter. |
| [onBindViewHolder](on-bind-view-holder.md) | `open fun onBindViewHolder(holder: `[`BaseColorViewHolder`](-base-color-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Binds an individual color resource to the ViewHolder. |
| [onCreateViewHolder](on-create-view-holder.md) | `open fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`BaseColorViewHolder`](-base-color-view-holder/index.md)<br>Creates a ViewHolder for displaying a color resource. |

### Inheritors

| Name | Summary |
|---|---|
| [CircleColorAdapter](../-circle-color-adapter/index.md) | `class CircleColorAdapter : `[`BaseColorAdapter`](./index.md)<br>Extension of [BaseColorAdapter](./index.md) that's used to display a list of color resources as circular items. |
| [SquareColorAdapter](../-square-color-adapter/index.md) | `class SquareColorAdapter : `[`BaseColorAdapter`](./index.md)<br>Adapter that displays a bunch of square color choices. |
