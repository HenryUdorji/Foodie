package com.henryudorji.foodie.ui.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.henryudorji.foodie.R
import com.henryudorji.foodie.databinding.FragmentYoutubeBinding
import com.henryudorji.foodie.ui.MainActivity

//
// Created by  on 3/11/2021.
//
class YoutubeFragment: Fragment(R.layout.fragment_youtube) {
    private lateinit var binding: FragmentYoutubeBinding
    private val args: YoutubeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentYoutubeBinding.bind(view)
        val activityMainBinding = (activity as MainActivity).binding

        val toolbar = activityMainBinding.toolbar
        toolbar.visibility = View.VISIBLE
        (activity as MainActivity).setSupportActionBar(toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        activityMainBinding.lottieSwitch.visibility = View.GONE
        activityMainBinding.toolbarText.apply {
            visibility = View.VISIBLE
            text = "Youtube"
        }
        showYoutubeVideo(args.youtube)
    }

    private fun showYoutubeVideo(youtubeLink: String) {
        val webViewClient: WebViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
            }
        }

        binding.youtubeWebView.apply {
            this.webViewClient = webViewClient
            loadUrl(youtubeLink)
        }
    }
}