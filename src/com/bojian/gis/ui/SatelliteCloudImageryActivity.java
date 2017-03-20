package com.bojian.gis.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bojian.gis.R;
import com.bojian.gis.util.UrlLib;

public class SatelliteCloudImageryActivity extends Activity implements
		OnClickListener {
	private Button backBtn;
	private TextView titleTv;
	private Button otherBtn;
	private WebView mWebView;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.satellite_cloud_imagery);
		init_titlebar();
		init_webview();
		startLoad();
	}

	@SuppressLint({ "SetJavaScriptEnabled", "NewApi" })
	private void init_webview() {
		// TODO Auto-generated method stub
		mWebView = (WebView) findViewById(R.id.webView1);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		mWebView.setHorizontalScrollBarEnabled(true);
		mWebView.getSettings().setSupportZoom(true);
		mWebView.getSettings().setBuiltInZoomControls(true);
		mWebView.setInitialScale(50);
		mWebView.setHorizontalScrollbarOverlay(true);
		mWebView.getSettings().setUseWideViewPort(true);
		mWebView.getSettings().setLoadWithOverviewMode(true);
	}

	private void startLoad() {
		// TODO Auto-generated method stub
		if (getIntent().getFlags()==8)
		{
			titleTv.setText(R.string.WXYT);
		mWebView.loadUrl(UrlLib.CLI);
		}
		else if (getIntent().getFlags()==5)
		{
			titleTv.setText(R.string.reservior); 
			mWebView.loadUrl(UrlLib.CLI5);	
		}	
		
		mWebView.setWebViewClient(new WebViewClient() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * android.webkit.WebViewClient#shouldOverrideUrlLoading(android
			 * .webkit.WebView, java.lang.String)
			 */

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * android.webkit.WebViewClient#onPageFinished(android.webkit.WebView
			 * , java.lang.String)
			 */
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
			}
		});

		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
				progressBar.setProgress(newProgress);
				progressBar.postInvalidate();
				if(newProgress==100)
					progressBar.setVisibility(View.GONE);

			}
		});

	}

	private void init_titlebar() {
		// TODO Auto-generated method stub
		backBtn = (Button) findViewById(R.id.titlebar_back);
		titleTv = (TextView) findViewById(R.id.titlebar_title);
		otherBtn = (Button) findViewById(R.id.titlebar_other);

		backBtn.setOnClickListener(this);
		titleTv.setText(R.string.WXYT);
		otherBtn.setVisibility(View.GONE);
		progressBar = (ProgressBar) findViewById(R.id.progressbar);
		progressBar
				.setScrollBarStyle(android.R.attr.progressBarStyleHorizontal);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == backBtn)
			finish();
	}

}
