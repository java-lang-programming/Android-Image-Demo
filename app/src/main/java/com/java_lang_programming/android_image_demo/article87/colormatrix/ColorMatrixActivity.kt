package com.java_lang_programming.android_image_demo.colormatrix

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import com.java_lang_programming.android_image_demo.R

/**
 * TODO
 * 次はspinnerの表示から
 * https://akira-watson.com/android/spinner.html
 * https://github.com/googlesamples/android-architecture/tree/dev-todo-mvp-kotlin/
 * filter
 * https://github.com/phoboslab/WebGLImageFilter/blob/master/webgl-image-filter.js
 *
 * // 理論
 * http://www.c-sharpcorner.com/article/color-transformations-and-the-color-matrix/
 */
class ColorMatrixActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_matrix)

        img = findViewById(R.id.img)
        spinner = findViewById(R.id.filters)
        // https://github.com/googlesamples/android-architecture/blob/dev-todo-mvp-kotlin/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/addedittask/AddEditTaskActivity.kt
        // 1. applicationContext
        // xmlを作成する　あり得ない。。。styleで継承すると、面倒なので作るべき。
        val adapter = ArrayAdapter.createFromResource(applicationContext, R.array.filters, R.layout.filters_spinner_item)
        adapter.setDropDownViewResource(R.layout.filters_spinner_dropdown_item)
        spinner.adapter = adapter

        // 2. 説明を書く 複数のインターフェースをもつインターフェース
        // http://qiita.com/pankuz_list/items/6d57bb868af6a13496c0
        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // 3. let
                parent?.let {
                    when (it.getItemIdAtPosition(position)) {
                        0.toLong() -> normal()
                        1.toLong() -> grayScale()
                        2.toLong() -> sepia()
                        3.toLong() -> polaroid()
                        4.toLong() -> brownie()
                        5.toLong() -> vintagePinhole()
                        6.toLong() -> kodachrome()
                        7.toLong() -> technicolor()
                        8.toLong() -> desaturateLuminance()
                        9.toLong() -> brightness(0.7.toFloat())
                        10.toLong() -> night(0.9.toFloat())
                        //11.toLong() -> lsd()
                        11.toLong() -> maskBlur()
                    }
                }
            }

//            <item>通常</item>
//            <item>GrayScale</item>
//            <item>Sepia</item>
//            <item>Poraroid</item>
//            <item>brownie</item>
//            <item>vintagePinhole</item>
//            <item>kodachrome</item>
            //<item>technicolor</item>
//            <item>desaturateLuminance</item>
//            <item>brightness</item>

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinner.onItemSelectedListener = listener
    }

    internal fun normal() {
        //Toast.makeText(baseContext, "normal", Toast.LENGTH_LONG).show()
    }

    internal fun grayScale() {
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0.0f)
        val matrix = colorMatrix.array
//        for (color in matrix) {
//            Log.d("ColorMatrixActivity", color.toString())
//        }
        img.colorFilter = ColorMatrixColorFilter(colorMatrix)
    }

    internal fun sepia() {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                0.393f, 0.769f, 0.189f, 0.0f, 0.0f,
                0.349f, 0.686f, 0.168f, 0.0f, 0.0f,
                0.272f, 0.534f, 0.131f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))

        img.colorFilter = ColorMatrixColorFilter(colorMatrix)

//        // debug
//        val matrix = colorMatrix.array
//        for (color in matrix) {
//            Log.d("ColorMatrixActivity", color.toString())
//        }
    }

    internal fun desaturateLuminance() {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                0.2764723f, 0.9297080f, 0.0938197f, 0.0f, -37.1f,
                0.2764723f, 0.9297080f, 0.0938197f, 0.0f, -37.1f,
                0.2764723f, 0.9297080f, 0.0938197f, 0.0f, -37.1f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        colorFilter(colorMatrix)
    }

    internal fun polaroid() {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                1.438f, -0.062f, -0.062f, 0.0f, 0.0f,
                -0.122f, 1.378f, -0.122f, 0.0f, 0.0f,
                -0.016f, -0.016f, 1.483f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        colorFilter(colorMatrix)
    }

    internal fun brownie() {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                0.5997023498159715f, 0.34553243048391263f, -0.2708298674538042f, 0.0f, 47.43192855600873f,
                -0.037703249837783157f, 0.8609577587992641f, 0.15059552388459913f, 0.0f, -36.96841498319127f,
                0.24113635128153335f, -0.07441037908422492f, 0.44972182064877153f, 0.0f, -7.562075277591283f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        img.colorFilter = ColorMatrixColorFilter(colorMatrix)
    }

    internal fun vintagePinhole() {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                0.6279345635605994f, 0.3202183420819367f, -0.03965408211312453f, 0.0f, 9.651285835294123f,
                0.02578397704808868f, 0.6441188644374771f, 0.03259127616149294f, 0.0f, 7.462829176470591f,
                0.0466055556782719f, -0.0851232987247891f, 0.5241648018700465f, 0.0f, 5.159190588235296f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        img.colorFilter = ColorMatrixColorFilter(colorMatrix)
    }

    internal fun kodachrome() {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                1.1285582396593525f, -0.3967382283601348f, -0.03992559172921793f, 0.0f, 63.72958762196502f,
                -0.16404339962244616f, 1.0835251566291304f, -0.05498805115633132f, 0.0f, 24.732407896706203f,
                -0.167860107061557639f, -0.5603416277695248f, 1.6014850761964943f, 0.0f, 35.62982807460946f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        colorFilter(colorMatrix)
    }

    internal fun technicolor() {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                1.9125277891456083f, -0.8545344976951645f, -0.09155508482755585f, 0.0f, 11.793603434377337f,
                -0.3087833385928097f, 1.7658908555458428f, -0.10601743074722245f, 0.0f, -70.35205161461398f,
                -0.231103377548616f, -0.7501899197440212f, 1.847597816108189f, 0.0f, 30.950940869491138f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        colorFilter(colorMatrix)
//        1.9125277891456083,-0.8545344976951645,-0.09155508482755585,0,11.793603434377337,
//        -0.3087833385928097,1.7658908555458428,-0.10601743074722245,0,-70.35205161461398,
//        -0.231103377548616,-0.7501899197440212,1.847597816108189,0,30.950940869491138,
    }

    /**
     * Night effect
     * http://pixijs.download/v4.4.0/docs/filters_colormatrix_ColorMatrixFilter.js.html
     */
    internal fun night(intensity: Float) {
        //TODO fedfactoring
        //val intensity = intensity
        //if (intensity < 0) {
        //    intensity = 0.1
        //}
        //val intensity = intensity || 0.1
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                intensity * -2.0f, -intensity, 0.0f, 0.0f, 0.0f,
                -intensity, 0.0f, intensity, 0.0f, 0.0f,
                0.0f, intensity, intensity * 2.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        img.colorFilter = ColorMatrixColorFilter(colorMatrix)
//        intensity * (-2.0), -intensity, 0, 0, 0,
//        -intensity, 0, intensity, 0, 0,
//        0, intensity, intensity * 2.0, 0, 0,
//        0, 0, 0, 1, 0,
    }

    /**
     * http://pixijs.download/v4.4.0/docs/filters_colormatrix_ColorMatrixFilter.js.html
     */
    internal fun lsd() {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                2.0f, -0.4f, 0.5f, 0.0f, 0.0f,
                -0.5f, 2.0f, -0.4f, 0.0f, 0.0f,
                -0.4f, -0.5f, 3.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        img.colorFilter = ColorMatrixColorFilter(colorMatrix)
    }


    // https://www.codeproject.com/Articles/33838/Image-Processing-using-C
    internal fun brightness(brightness: Float) {
        val b = brightness + 1
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                b, 0.0f, 0.0f, 0.0f, 0.0f,
                0.0f, b, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, b, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        img.colorFilter = ColorMatrixColorFilter(colorMatrix)
    }

    internal fun contrast(amount : Float ) {
//        if (amount < 0) {
//            amount = 0
//        }
        val v = Math.max(amount, 0f) + 1f
        Log.d("ColorMatrixActivity " , v.toString());
        val o = -128 * (v - 1);
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                v, 0.0f, 0.0f, 0.0f, o,
                0.0f, v, 0.0f, 0.0f, o,
                0.0f, 0.0f, v, 0.0f, o,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        img.colorFilter = ColorMatrixColorFilter(colorMatrix)
    }

    // binray
    // http://chiuki.github.io/android-shaders-filters/#/16
    internal fun maskBlur() {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                1.0f, 0.0f, 0.0f, 0.5f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        img.colorFilter = ColorMatrixColorFilter(colorMatrix)
    }

    internal fun colorFilter(colorMatrix: ColorMatrix) {
        val filter = ColorMatrixColorFilter(colorMatrix)
        img.colorFilter = filter
    }

}
