[lib](../../../index.md) / [com.androidessence.materialdesignspecs.colorpicker](../../index.md) / [BaseColorAdapter](../index.md) / [BaseColorViewHolder](./index.md)

# BaseColorViewHolder

`abstract inner class BaseColorViewHolder : ViewHolder, `[`OnClickListener`](https://developer.android.com/reference/android/view/View/OnClickListener.html)

Base ViewHolder for a color adapter.

TODO: Don't make this an inner class.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BaseColorViewHolder(colorView: `[`View`](https://developer.android.com/reference/android/view/View.html)`)`<br>Base ViewHolder for a color adapter. |

### Functions

| Name | Summary |
|---|---|
| [bindColor](bind-color.md) | `open fun bindColor(color: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Binds a color resource to this ViewHolder. |
| [initView](init-view.md) | `abstract fun initView(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Abstract method to provide any custom initializations the user may want. |
| [onClick](on-click.md) | `open fun onClick(v: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [CircleColorViewHolder](../../-circle-color-adapter/-circle-color-view-holder/index.md) | `inner class CircleColorViewHolder : `[`BaseColorViewHolder`](./index.md)<br>RecyclerView.ViewHolder for displaying a color resource in a circle. |
| [SquareColorViewHolder](../../-square-color-adapter/-square-color-view-holder/index.md) | `inner class SquareColorViewHolder : `[`BaseColorViewHolder`](./index.md)<br>ViewHolder for displaying a color resource as a square. |
