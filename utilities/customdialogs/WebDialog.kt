package com.hellopharmacy.utilities.customdialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import com.github.ybq.android.spinkit.SpinKitView
import com.hellopharmacy.R

class WebDialog(var con : Context , var type : String)
{

    var dialog: Dialog? = null
    var  progressBar:SpinKitView ?  = null

    init {
        dialog = Dialog(con, R.style.NewDialogTheme)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setCanceledOnTouchOutside(true)
        dialog!!.setContentView(R.layout.terms_policy_layout)

        val webview = dialog!!.findViewById<WebView>(R.id.termsDialogWebview)
        val closeBtn = dialog!!.findViewById<ImageView>(R.id.termsDialogCloseBtn)
        val  dialogTitleTxt = dialog!!.findViewById<TextView>(R.id.termsDialogTitleTxt)
        val  progressBar = dialog!!.findViewById<SpinKitView>(R.id.termsDialogProgressBar)


        closeBtn.setOnClickListener {
            dialog!!.dismiss()
        }

        webview.setWebViewClient(MyBrowser())
        webview.getSettings().setLoadsImagesAutomatically(true)
        webview.getSettings().setJavaScriptEnabled(true)
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
        progressBar.visibility = View.VISIBLE

        if(type.equals("terms",ignoreCase = true))
        {
            dialogTitleTxt.text = "Terms and Conditions"
            webview.loadUrl("https://www.qarint.com/terms_condition")
        }

        else if(type.equals("privacy" , ignoreCase = true))
        {
            dialogTitleTxt.text = "Privacy Policy"
            webview.loadUrl("https://www.qarint.com/privacy_policy")
        }

        dialog!!.show()

      /*
        dialogTitleTxt = dialog!!.findViewById<View>(R.id.dialogTitleTxt) as TextView

        dialogNegativeBtn =
            dialog!!.findViewById<View>(R.id.dialogNegativeBtn) as Button*/

    }

    private inner class MyBrowser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            if(progressBar!=null)
                progressBar?.visibility = View.GONE
        }
    }
}