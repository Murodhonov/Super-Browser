package com.example.superbrowser

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var exit = false

    override fun onBackPressed() {

        if (web_view.canGoBack()) {

            web_view.goBack(); exit = false
            error_msg.visibility = View.GONE

        } else if (!exit) {

            web_view.visibility = View.GONE;
            exit = true
            edt_1.text.clear()
            del_btn2.visibility = View.GONE
            go_image2.visibility = View.GONE
            back_bg.visibility = View.VISIBLE
            url1.visibility = View.VISIBLE
            url2.visibility = View.VISIBLE
            url3.visibility = View.VISIBLE
            url4.visibility = View.VISIBLE
            search_image2.visibility = View.VISIBLE
            more_image2.visibility = View.VISIBLE
            bottom_nav.visibility = View.VISIBLE
            bottom_nav2.visibility = View.GONE

        } else super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, SplashScreen::class.java))
        supportActionBar?.hide()

        var url_new = ""

        btn_1.setOnClickListener {
            home.visibility =View.VISIBLE
            history.visibility = View.GONE
            about.visibility = View.GONE
            lottie.visibility = View.GONE
            lottie2.visibility  =View.GONE
        }

        btn_2.setOnClickListener {
            home.visibility =View.GONE
            history.visibility = View.VISIBLE
            about.visibility = View.GONE
            lottie.visibility = View.GONE
            lottie2.visibility  =View.GONE
        }


        btn_3.setOnClickListener {
            home.visibility =View.GONE
            lottie.visibility = View.GONE
            lottie2.visibility  =View.GONE
            history.visibility = View.GONE
            about.visibility = View.VISIBLE
        }


        url1.setOnClickListener {
            web_view.loadUrl("https://google.com")
            url1.visibility = View.GONE
            url2.visibility = View.GONE
            url3.visibility = View.GONE
            url4.visibility = View.GONE
            search_image2.visibility = View.GONE
            more_image2.visibility = View.GONE
            bottom_nav.visibility = View.GONE
            bottom_nav2.visibility = View.VISIBLE
            del_btn2.visibility = View.VISIBLE
            go_image2.visibility = View.VISIBLE
        }
        url2.setOnClickListener {
            web_view.loadUrl("https://twitter.com")
            url1.visibility = View.GONE
            url2.visibility = View.GONE
            url3.visibility = View.GONE
            url4.visibility = View.GONE
            search_image2.visibility = View.GONE
            more_image2.visibility = View.GONE
            bottom_nav.visibility = View.GONE
            bottom_nav2.visibility = View.VISIBLE
            del_btn2.visibility = View.VISIBLE
            go_image2.visibility = View.VISIBLE
        }
        url3.setOnClickListener {
            web_view.loadUrl("https://facebook.com")
            url1.visibility = View.GONE
            url2.visibility = View.GONE
            url3.visibility = View.GONE
            url4.visibility = View.GONE
            search_image2.visibility = View.GONE
            more_image2.visibility = View.GONE
            bottom_nav.visibility = View.GONE
            bottom_nav2.visibility = View.VISIBLE
            del_btn2.visibility = View.VISIBLE
            go_image2.visibility = View.VISIBLE
        }
        url4.setOnClickListener {
            web_view.loadUrl("https://youtube.com")
            url1.visibility = View.GONE
            url2.visibility = View.GONE
            url3.visibility = View.GONE
            url4.visibility = View.GONE
            search_image2.visibility = View.GONE
            more_image2.visibility = View.GONE
            bottom_nav.visibility = View.GONE
            bottom_nav2.visibility = View.VISIBLE
            del_btn2.visibility = View.VISIBLE
            go_image2.visibility = View.VISIBLE
        }

        del_btn2.setOnClickListener {
            edt_1.text.clear();
            lottie.visibility = View.GONE
            del_btn2.visibility = View.GONE
            go_image2.visibility = View.GONE
            back_bg.visibility = View.VISIBLE
            url1.visibility = View.VISIBLE
            url2.visibility = View.VISIBLE
            url3.visibility = View.VISIBLE
            url4.visibility = View.VISIBLE
            search_image2.visibility = View.VISIBLE
            more_image2.visibility = View.VISIBLE
            web_view.visibility = View.GONE
            error_msg.visibility = View.GONE
            bottom_nav.visibility = View.VISIBLE
            bottom_nav2.visibility = View.GONE
        }
        go_image2.setOnClickListener {

            url_new = ""

            url_new = edt_1.text.toString()

            if (!url_new.endsWith(".uz") && !url_new.endsWith(".com") && !url_new.endsWith(".ru")){
                url_new = "https://www.google.com/search?q=$url_new"
            }else{
                if (!url_new.startsWith("http://") && !url_new.startsWith("https://")){
                    url_new = "https://${edt_1.text}"
                }
            }

            error_msg.visibility = View.GONE
            web_view.loadUrl(url_new)
            edt_1.setText(url_new)
            lottie.visibility = View.VISIBLE
            bottom_nav.visibility = View.GONE
            bottom_nav2.visibility = View.VISIBLE

        }
        more_image2.setOnClickListener { popupMenu() }

        web_view.webChromeClient = object  : WebChromeClient()
        {
            override fun onProgressChanged(view: WebView?, newProgress: Int)
            {
                super.onProgressChanged(view, newProgress)
                progress_horizontal.progress = newProgress
                lottie.visibility = View.VISIBLE
            }
        }

        web_view.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String?,
                failingUrl: String?
            ) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                error_msg.visibility = View.VISIBLE
                Snackbar.make(root,"No internet connection !",Snackbar.LENGTH_LONG).show()
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                back_bg.visibility = View.GONE
                url1.visibility = View.GONE
                url2.visibility = View.GONE
                url3.visibility = View.GONE
                url4.visibility = View.GONE
                search_image2.visibility = View.GONE
                more_image2.visibility = View.GONE
                del_btn2.visibility = View.VISIBLE
                go_image2.visibility = View.VISIBLE
                lottie.visibility = View.VISIBLE
                bottom_nav.visibility = View.GONE
                bottom_nav2.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                lottie.visibility = View.GONE
                web_view.visibility = View.VISIBLE
                bottom_nav.visibility = View.GONE
                bottom_nav2.visibility = View.VISIBLE
            }
        }

        edt_1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isEmpty()) {
                    del_btn2.visibility = View.GONE
                    go_image2.visibility = View.GONE
                    back_bg.visibility = View.VISIBLE
                    url1.visibility = View.VISIBLE
                    url2.visibility = View.VISIBLE
                    url3.visibility = View.VISIBLE
                    url4.visibility = View.VISIBLE
                    search_image2.visibility = View.VISIBLE
                    more_image2.visibility = View.VISIBLE
                } else {
                    del_btn2.visibility = View.VISIBLE
                    go_image2.visibility = View.VISIBLE
                    back_bg.visibility = View.GONE
                    url1.visibility = View.GONE
                    url2.visibility = View.GONE
                    url3.visibility = View.GONE
                    url4.visibility = View.GONE
                    search_image2.visibility = View.GONE
                    more_image2.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })


    }

    private fun popupMenu() {
        val popupMenu = PopupMenu(this, more_image2)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.add_bookmark -> {
                    Toast.makeText(this, "add bookmark", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.bookmark_list -> {
                    Toast.makeText(this, "bookmark list", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.help -> {
                    Toast.makeText(this, "help center", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.about -> {
                    Toast.makeText(this, "about us", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.settings -> {
                    Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.history -> {
                    Toast.makeText(this, "history", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.exit-> {
                    finish()
                    true
                }
                else -> true
            }
        }

        more_image2.setOnClickListener {
            try {
                val popup = PopupMenu::class.java.getDeclaredField("mPopup")
                popup.isAccessible = true
                val menu = popup.get(popup)
                menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(menu, true)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                popupMenu.show()
            }
        }
    }
}