/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * presented by http://java-lang-programming.com/
 */

package com.java_lang_programming.android_image_demo.article88

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import com.java_lang_programming.android_image_demo.R

/**
 * ColorMatrix Sample
 */
class ColorMatrixActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_matrix)

        img = findViewById(R.id.img)
        spinner = findViewById(R.id.filters)
        val adapter = ArrayAdapter.createFromResource(applicationContext, R.array.filters, R.layout.filters_spinner_item)
        adapter.setDropDownViewResource(R.layout.filters_spinner_dropdown_item)
        spinner.adapter = adapter

        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                parent?.let {
                    when (it.getItemIdAtPosition(position)) {
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
                        11.toLong() -> lsd()
                        12.toLong() -> contrast(2.0f)
                        13.toLong() -> binary()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinner.onItemSelectedListener = listener
    }

    internal fun grayScale() {
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0.0f)
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
    }

    internal fun night(intensity: Float) {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                intensity * -2.0f, -intensity, 0.0f, 0.0f, 0.0f,
                -intensity, 0.0f, intensity, 0.0f, 0.0f,
                0.0f, intensity, intensity * 2.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        img.colorFilter = ColorMatrixColorFilter(colorMatrix)
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

    internal fun contrast(amount: Float = 0.0f) {
        val v = Math.max(amount, 0f) + 1f
        val o = -128 * (v - 1)
        val colorMatrix = ColorMatrix()
        colorMatrix.set(floatArrayOf(
                v, 0.0f, 0.0f, 0.0f, o,
                0.0f, v, 0.0f, 0.0f, o,
                0.0f, 0.0f, v, 0.0f, o,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        img.colorFilter = ColorMatrixColorFilter(colorMatrix)
    }

    internal fun binary() {
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0.0f)
        val m = 255f
        val t = -255f * 128f
        val threshold = ColorMatrix()
        threshold.set(floatArrayOf(
                m, 0.0f, 0.0f, 1.0f, t,
                0.0f, m, 0.0f, 1.0f, t,
                0.0f, 0.0f, m, 1.0f, t,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
        colorMatrix.postConcat(threshold)
//        val colorMatrix = ColorMatrix()
//        colorMatrix.set(floatArrayOf(
//                54.315f, 182.325f, 18.359999f, 1.0f, -32640.0f,
//                54.315f, 182.325f, 18.359999f, 1.0f, -32640.0f,
//                54.315f, 182.325f, 18.359999f, 1.0f, -32640.0f,
//                0.0f, 0.0f, 0.0f, 1.0f, 0.0f))
//        val matrix = colorMatrix.array
//        for (color in matrix) {
//            Log.d("ColorMatrixActivity", color.toString())
//        }
        img.colorFilter = ColorMatrixColorFilter(colorMatrix)
    }


    internal fun colorFilter(colorMatrix: ColorMatrix) {
        val filter = ColorMatrixColorFilter(colorMatrix)
        img.colorFilter = filter
    }

}
