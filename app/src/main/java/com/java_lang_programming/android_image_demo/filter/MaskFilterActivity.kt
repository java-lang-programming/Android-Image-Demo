package com.java_lang_programming.android_image_demo.filter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Spinner
import com.java_lang_programming.android_image_demo.R

/**
 * http://android-er.blogspot.jp/2013/10/example-to-apply-blurmaskfilter-on.html
 */
class MaskFilterActivity : AppCompatActivity() {

    private lateinit var blur_filters: Spinner
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mask_filter)

        img = findViewById(R.id.img)
        blur_filters = findViewById(R.id.blur_filters)
    }


}
