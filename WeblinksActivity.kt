package com.hellopharmacy.views

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.hellopharmacy.R
import com.hellopharmacy.utilities.common.BaseActivity
import kotlinx.android.synthetic.main.activity_weblinks.*

class WeblinksActivity : BaseActivity() , View.OnClickListener
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weblinks)
        initializations()
        clickListeners()
    }

    private fun initializations()
    {

        weblinkWebview.setWebViewClient(MyBrowser())
        weblinkWebview.getSettings().setLoadsImagesAutomatically(true)
        weblinkWebview.getSettings().setJavaScriptEnabled(true)
        weblinkWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
        weblinkProgressBar.visibility = View.VISIBLE

        if(intent?.extras?.getString("type").equals("terms",ignoreCase = true))
        {
            weblinkTitleTxt.text = "Terms and Conditions"
            weblinkWebview.loadUrl("https://www.qarint.com/terms_condition")
        }

        else if(intent?.extras?.getString("type").equals("privacy",ignoreCase = true))
        {
            weblinkTitleTxt.text = "Privacy Policy"
            weblinkWebview.loadUrl("https://www.qarint.com/privacy_policy")
        }
    }


    private fun clickListeners()
    {
        weblinkBackBtn.setOnClickListener(this)
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
            if(weblinkProgressBar!=null)
                weblinkProgressBar?.visibility = View.GONE
        }
    }

    override fun onClick(v: View?)
    {
        when(v?.id)
        {
            R.id.weblinkBackBtn->
            {
                finish()
            }
        }
    }
}