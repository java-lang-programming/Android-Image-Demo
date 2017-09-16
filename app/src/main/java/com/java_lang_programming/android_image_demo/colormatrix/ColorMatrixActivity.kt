package com.java_lang_programming.android_image_demo.colormatrix

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
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
                        //1.toLong() -> grayScale()
                        1.toLong() -> paint()
                        2.toLong() -> sepia2()
                        3.toLong() -> polaroid()
                        4.toLong() -> brownie()
                        5.toLong() -> vintagePinhole()
                        6.toLong() -> kodachrome()
                        7.toLong() -> desaturateLuminance()
                        8.toLong() -> brightness()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinner.onItemSelectedListener = listener
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (it.itemId) {
                R.id.action_select_filter -> showSpinner()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    internal fun showSpinner() {
        Toast.makeText(baseContext, "aaaaaa", Toast.LENGTH_LONG).show()
    }

    internal fun normal() {
        Toast.makeText(baseContext, "normal", Toast.LENGTH_LONG).show()
    }

    // https://www.codeproject.com/Articles/33838/Image-Processing-using-C
    internal fun paint() {
        val colorMatrix = ColorMatrix()
        colorMatrix.setScale(0.5f, 1.0f, 1.0f, 1.0f);

        // debug
        val matrix = colorMatrix.array
        for (color in matrix) {
            Log.d("ColorMatrixActivity", color.toString())
        }

        colorFilter(colorMatrix)
    }

    internal fun grayScale() {
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0.0f)

        val filter = ColorMatrixColorFilter(colorMatrix)
        img.colorFilter = filter
    }

    internal fun sepia() {
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0.0f)

        val colorScale = ColorMatrix()
        colorScale.setScale(1.07f, 0.74f, 0.43f, 1.0f);

        colorMatrix.postConcat(colorScale)

        // debug
        val matrix = colorMatrix.array
        for (color in matrix) {
            Log.d("ColorMatrixActivity", color.toString())
        }

        colorFilter(colorMatrix)
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

    internal fun sepia2() {
        val colorScale = ColorMatrix()
        colorScale.set(floatArrayOf(
                0.393f, 0.769f, 0.189f, 0.0f, 0.0f,
                0.349f, 0.686f, 0.168f, 0.0f, 0.0f,
                0.272f, 0.534f,	0.131f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))

        // debug
        val matrix = colorScale.array
        for (color in matrix) {
            Log.d("ColorMatrixActivity", color.toString())
        }

        colorFilter(colorScale)
    }

    internal fun polaroid() {
        //val colorMatrix = ColorMatrix()
        //colorMatrix.setSaturation(0.0f)

        val colorScale = ColorMatrix()
        colorScale.set(floatArrayOf(
                1.438f, -0.062f, -0.062f, 0.0f, 0.0f,
                -0.122f, 1.378f, -0.122f, 0.0f, 0.0f,
                -0.016f, -0.016f, 1.483f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
//        1.438,-0.062,-0.062,0,0,
//        -0.122,1.378,-0.122,0,0,
//        -0.016,-0.016,1.483,0,0,
//        0,0,0,1,0

        //colorMatrix.postConcat(colorScale)

        // debug
        val matrix = colorScale.array
        for (color in matrix) {
            Log.d("ColorMatrixActivity", color.toString())
        }

        colorFilter(colorScale)
    }

    internal fun brownie() {
//        val colorMatrix = ColorMatrix()
//        colorMatrix.setSaturation(0.0f)

        val colorScale = ColorMatrix()
        colorScale.set(floatArrayOf(
                0.5997023498159715f, -0.34553243048391263f, -0.2708298674538042f, 0.0f, 43192855600873f,
                -0.037703249837783157f, 0.8609577587992641f, 0.15059552388459913f, 0.0f, -36.96841498319127f,
                0.24113635128153335f, -0.07441037908422492f, 0.44972182064877153f, 0.0f, 7.562075277591283f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))

        //colorMatrix.postConcat(colorScale)

        colorFilter(colorScale)

//        0.5997023498159715,0.34553243048391263,-0.2708298674538042,0,47.43192855600873,
//        -0.037703249837783157,0.8609577587992641,0.15059552388459913,0,-36.96841498319127,
//        0.24113635128153335,-0.07441037908422492,0.44972182064877153,0,-7.562075277591283,
//        0,0,0,1,0
    }

    internal fun vintagePinhole () {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                0.6279345635605994f, 0.3202183420819367f, -0.03965408211312453f, 0.0f, 9.651285835294123f,
                0.02578397704808868f, 0.6441188644374771f, 0.03259127616149294f, 0.0f, 7.462829176470591f,
                0.0466055556782719f, -0.0851232987247891f, 0.5241648018700465f, 0.0f, 5.159190588235296f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        colorFilter(colorMatrix)
    }

    internal fun kodachrome () {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                1.1285582396593525f, -0.3967382283601348f, -0.03992559172921793f, 0.0f, 63.72958762196502f,
                -0.16404339962244616f, 1.0835251566291304f, -0.05498805115633132f, 0.0f, 24.732407896706203f,
                -0.167860107061557639f, -0.5603416277695248f, 1.6014850761964943f, 0.0f, 35.62982807460946f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        colorFilter(colorMatrix)
    }

    // https://www.codeproject.com/Articles/33838/Image-Processing-using-C
    internal fun brightness() {
        //var b = (brightness || 0) + 1
        val brightness = 1.5.toFloat()
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                brightness, 0.0f, 0.0f, 0.0f, 0.0f,
                0.0f, brightness, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, brightness, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        colorFilter(colorMatrix)
    }

    internal fun colorFilter(colorMatrix: ColorMatrix) {
        val filter = ColorMatrixColorFilter(colorMatrix)
        img.colorFilter = filter
    }

}
