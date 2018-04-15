[lib](../../index.md) / [com.androidessence.materialdesignspecs](../index.md) / [ColorDialog](./index.md)

# ColorDialog

`class ColorDialog : DialogFragment`

Dialog fragment that displays a list of colors.

Created by adam.mcneilly on 5/8/17.

### Types

| Name | Summary |
|---|---|
| [OnColorSelectedListener](-on-color-selected-listener/index.md) | `interface OnColorSelectedListener`<br>Interface that handles a color being selected. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ColorDialog()`<br>Dialog fragment that displays a list of colors. |

### Properties

| Name | Summary |
|---|---|
| [adapter](adapter.md) | `var adapter: `[`BaseColorAdapter`](../-base-color-adapter/index.md)`?`<br>The adapter to display the colors with. |
| [columnCount](column-count.md) | `var columnCount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The number of columns in the grid. |
| [layoutManager](layout-manager.md) | `var layoutManager: LayoutManager?`<br>The LayoutManager for the RecyclerView. |
| [onColorSelectedListener](on-color-selected-listener.md) | `var onColorSelectedListener: `[`OnColorSelectedListener`](-on-color-selected-listener/index.md)`?`<br>The callback for when a color is selected. |

### Functions

| Name | Summary |
|---|---|
| [onCreateView](on-create-view.md) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [newInstance](new-instance.md) | `fun newInstance(colorList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>, selectedPos: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`ColorDialog`](./index.md)<br>Creates an instance of the ColorDialog. |
