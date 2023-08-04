package com.hellopharmacy.utilities.customdialogs

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.hellopharmacy.utilities.customdialogs.DialogListenerInterface.onNegetiveClickListener
import com.hellopharmacy.utilities.customdialogs.DialogListenerInterface.onPositiveClickListener
import com.hellopharmacy.R

class CustomDialog(var con: Context) {
    var dialog: Dialog?
    var dialogPositiveBtn: Button?
    var dialogNegativeBtn: Button?
    var dialogAlertTxt: TextView?
    var dialogTitleTxt: TextView
    fun setTitle(title: String?) {
        if (dialogAlertTxt != null) dialogTitleTxt.text = title
    }

    fun setMessage(message: String?) {
        if (dialogAlertTxt != null) dialogAlertTxt!!.text = message
    }

    fun setPositiveButton(
        text: String?,
        positiveListener: onPositiveClickListener
    ) {
        if (dialogPositiveBtn != null) {
            dialogPositiveBtn!!.text = text
            dialogPositiveBtn!!.visibility = View.VISIBLE
        }
        if (dialogPositiveBtn != null) {
            dialogPositiveBtn!!.setOnClickListener {
                if (dialog != null) dialog!!.dismiss()
                positiveListener.onPositiveClick()
            }
        }
    }

    fun setNegativeButton(
        text: String?,
        negetiveListener: onNegetiveClickListener
    ) {
        if (dialogNegativeBtn != null) {
            dialogNegativeBtn!!.text = text
            dialogNegativeBtn!!.visibility = View.VISIBLE
        }
        if (dialogNegativeBtn != null) {
            dialogNegativeBtn!!.setOnClickListener {
                if (dialog != null) dialog!!.dismiss()
                negetiveListener.onNegetiveClick()
            }
        }
    }

    fun show() {
        if (dialog != null) dialog!!.show()
    }

    fun dismiss() {
        if (dialog != null) dialog!!.dismiss()
    }

    init {
        dialog = Dialog(con, R.style.NewDialogTheme)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(false)
        dialog!!.setContentView(R.layout.custom_dialog_layout)
        dialogAlertTxt = dialog!!.findViewById<View>(R.id.dialogAlertTxt) as TextView
        dialogTitleTxt = dialog!!.findViewById<View>(R.id.dialogTitleTxt) as TextView
        dialogPositiveBtn =
            dialog!!.findViewById<View>(R.id.dialogPositiveBtn) as Button
        dialogNegativeBtn =
            dialog!!.findViewById<View>(R.id.dialogNegativeBtn) as Button
    }
}