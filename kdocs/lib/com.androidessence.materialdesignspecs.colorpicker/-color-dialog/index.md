[lib](../../index.md) / [com.androidessence.materialdesignspecs.colorpicker](../index.md) / [ColorDialog](./index.md)

# ColorDialog

`class ColorDialog : DialogFragment`

Dialog fragment that displays a list of colors resources.

### Types

| Name | Summary |
|---|---|
| [OnColorSelectedListener](-on-color-selected-listener/index.md) | `interface OnColorSelectedListener`<br>Interface that handles a color being selected. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ColorDialog()`<br>Dialog fragment that displays a list of colors resources. |

### Properties

| Name | Summary |
|---|---|
| [adapter](adapter.md) | `var adapter: `[`BaseColorAdapter`](../-base-color-adapter/index.md)`?`<br>An adapter to display our list of color resources. Defaults to a [CircleColorAdapter](../-circle-color-adapter/index.md) if not supplied. |
| [columnCount](column-count.md) | `var columnCount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The number of columns in the grid of colors. Used for the default [layoutManager](layout-manager.md). |
| [layoutManager](layout-manager.md) | `var layoutManager: LayoutManager?`<br>The layout manager applied to the RecyclerView in the dialog. If the user never supplies this, we'll use a GridLayoutManager. |
| [onColorSelectedListener](on-color-selected-listener.md) | `var onColorSelectedListener: `[`OnColorSelectedListener`](-on-color-selected-listener/index.md)`?`<br>A callback to notify anytime a color is selected. |

### Functions

| Name | Summary |
|---|---|
| [onCreateView](on-create-view.md) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [newInstance](new-instance.md) | `fun newInstance(colorList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>, selectedPos: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`ColorDialog`](./index.md)<br>Creates an instance of the ColorDialog. |
