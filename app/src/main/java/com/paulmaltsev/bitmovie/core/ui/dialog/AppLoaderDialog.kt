package com.paulmaltsev.bitmovie.core.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.paulmaltsev.bitmovie.R

/**
 * The Class uses inside AndroidView to display a progress bar.
 */
class AppLoaderDialog(
    private val isDarkMode: Boolean,
    context: Context,
    listener: DialogInterface.OnClickListener
) {
    private val color = ContextCompat.getColor(context, R.color.main)
    private val progressBar: ProgressBar by lazy {
        ProgressBar(context).apply {
            indeterminateTintList = ColorStateList.valueOf(color)
        }
    }
    private val title: TextView by lazy {
        TextView(context).apply {
            text = context.getString(R.string.loading)
            textSize = 24f
            setPadding(48)
            setTextColor(color)
        }
    }

    private var dialog: AlertDialog? = AlertDialog.Builder(context)
        .setView(progressBar)
        .setCustomTitle(title)
        .setPositiveButton(context.getString(R.string.close), listener)
        .create()

    fun show() {
        setAppColor()
        dialog?.show()
    }

    fun dismiss() {
        dialog?.dismiss()
        dialog = null
    }

    private fun setAppColor() {
        dialog?.setOnShowListener {
            // Button color
            dialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(color)
            // Background color
            dialog?.window?.setBackgroundDrawableResource(
                if (isDarkMode) {
                    android.R.color.background_dark
                } else {
                    android.R.color.background_light
                }
            )
        }
    }
}