package com.spencerpotter.web_browser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val history = LinkedList();
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val topLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        }

        val websiteGetter = EditText(this).apply {
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                weight = 1f
            }
        }

        val webView = WebView(this).apply {
        }

        val backButton = Button(this).apply {
            setText("B")
            setOnClickListener{
                val inputWebsite = history.back()
                websiteGetter.setText(inputWebsite)
                setWebsite(inputWebsite, webView)
            }
            layoutParams = LinearLayout.LayoutParams(100, 100 ).apply {

            }
        }

        val fowardButton = Button(this).apply {
            setText("F")
            setOnClickListener{
                val inputWebsite = history.forward()
                websiteGetter.setText(inputWebsite)
                setWebsite(inputWebsite, webView)

            }
            layoutParams = LinearLayout.LayoutParams(100, 100 ).apply {
            }


        }


        val goButton = Button(this).apply {
            setText("G")
            setOnClickListener{
                val inputWebsite = websiteGetter.text.toString()
                setWebsite(inputWebsite, webView)
                history.insert(inputWebsite)
            }
            layoutParams = LinearLayout.LayoutParams(100, 100 ).apply {
            }

        }


        topLayout.addView(backButton)
        topLayout.addView(fowardButton)
        topLayout.addView(websiteGetter)
        topLayout.addView(goButton)
        mainLayout.addView(topLayout)
        mainLayout.addView(webView)

        setContentView(mainLayout)
    }

    fun setWebsite (websiteName: String, webView : WebView){
        if(websiteName == ""){
            return
        }
        webView.loadUrl(websiteName)
        webView.webViewClient = WebViewClient()

    }
}