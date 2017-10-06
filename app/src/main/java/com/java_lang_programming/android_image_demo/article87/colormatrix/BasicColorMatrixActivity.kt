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

package com.java_lang_programming.android_image_demo.article87.colormatrix

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import com.java_lang_programming.android_image_demo.R

/**
 * How to use Basic ColorMatrix
 */
class BasicColorMatrixActivity : AppCompatActivity() {

    private lateinit var btn_filtering: Button
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_color_matrix)

        btn_filtering = findViewById(R.id.btn_filter)
        btn_filtering.setOnClickListener {
            filter()
        }

        img = findViewById(R.id.img)
    }

    private fun filter() {
        val colorMatrix = ColorMatrix()
        colorMatrix.setScale(0.5f, 1.0f, 1.0f, 1.0f)
        img.colorFilter = ColorMatrixColorFilter(colorMatrix)
    }

}
