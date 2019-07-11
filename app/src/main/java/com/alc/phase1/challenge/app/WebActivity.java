package com.alc.phase1.challenge.app;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("NewApi")
public class WebActivity extends AppCompatActivity {
	private WebView wv1;
	
	String url;
	ProgressDialog dialog;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	   protected void onCreate(Bundle savedInstanceState) {
		final Activity activity = this;
		
		super.onCreate(savedInstanceState);
	     getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	 
	      setContentView(R.layout.web_layout);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME |
				ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_USE_LOGO);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("About ALC");

		getWindow().setFeatureInt(Window.FEATURE_PROGRESS,
				Window.PROGRESS_VISIBILITY_ON);

		wv1=(WebView)findViewById(R.id.webView);
	      wv1.setWebViewClient(new MyBrowser());
	      
	      /** Showing Indeterminate progress bar in the title bar*/
          activity.setProgressBarIndeterminateVisibility(true);
          wv1.getSettings().setLoadsImagesAutomatically(true);
          try {
			wv1.getSettings().setJavaScriptEnabled(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		wv1.setInitialScale(1);
		//wv1.getSettings().setBuiltInZoomControls(true);
		wv1.getSettings().setUseWideViewPort(true);
		wv1.setWebViewClient(new WebViewClient());
		WebSettings webSettings = wv1.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

		//wv1.setWebViewClient(new WebActivity.MyBrowser());
		//wv1.getSettings().setJavaScriptEnabled(true);
		wv1.loadUrl("https://andela.com/alc/");
        dialog= ProgressDialog.show(WebActivity.this, null, "Please wait...", true, true, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                wv1.stopLoading();
                finish();
            }
        });

	   }

	   private class MyBrowser extends WebViewClient {
	      @Override
	      public boolean shouldOverrideUrlLoading(WebView view, String url) {
	         view.loadUrl(url);
	         return true;
	      }
	      /** Callback method, executed when the page is completely loaded */
          @Override
          public void onPageFinished(WebView view, String url) {
               super.onPageFinished(view, url);

			  if(dialog!=null) dialog.dismiss();
               /*Toast.makeText(getBaseContext(),
                              "Completed, wait a moment!",
                              Toast.LENGTH_SHORT).show();
				*/
              /** Hiding Indeterminate Progress Bar in the title bar*/
              WebActivity.this.setProgressBarIndeterminateVisibility(false);

          }

		   @Override
		   public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError er) {
			   handler.proceed();
			   // Ignore SSL certificate errors
		   }

	   }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
		if (Integer.parseInt(Build.VERSION.SDK) > 5
				&& keyCode == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {
			Log.d("CDA", "onKeyDown Called");
			onBackPressed();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public void onBackPressed() {
		FragmentManager fm1 = getSupportFragmentManager();
		if (fm1.getBackStackEntryCount() > 1) {
			Log.d("WebActivity", "popping backstack");
			FragmentManager fm = getSupportFragmentManager();
			if (fm.getBackStackEntryCount() > 2) {
				Log.d("WebActivity", "popping backstack");
				String title=fm.getBackStackEntryAt(fm.getBackStackEntryCount()-1).getName();
				fm.popBackStack();
				getSupportActionBar().setTitle(title);

			} else {
				String title=fm.getBackStackEntryAt(fm.getBackStackEntryCount()-1).getName();
				fm.popBackStack();
				getSupportActionBar().setTitle(title);
			}

		}else {
			finish();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.webmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				// API 5+ solution
				onBackPressed();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem item= (MenuItem) menu.findItem(R.id.action_settings);
		item.setVisible(false);
		return super.onPrepareOptionsMenu(menu);
	}
}
