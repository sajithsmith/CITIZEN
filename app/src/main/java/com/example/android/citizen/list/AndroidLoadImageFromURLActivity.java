package com.example.android.citizen.list;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.citizen.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class AndroidLoadImageFromURLActivity extends Activity {
	String pid, pic, name, rew,status;
	int state = 0;
	TextView t1, t2,stattext;
	Button b1;
	Button ib;

	ProgressDialog prgDialog;
	RequestParams params = new RequestParams();

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);



		b1 = (Button) findViewById(R.id.uphold);
        stattext=(TextView)findViewById(R.id.textView5);
		ib = (Button)findViewById(R.id.button3);


		Intent i = getIntent();

		// getting product id (pid) from intent
		pid = i.getStringExtra("pid");
		pic = i.getStringExtra("pic");
		name = i.getStringExtra("name");
		rew = i.getStringExtra("rew");
        status = i.getStringExtra("status");

		t1 = (TextView) findViewById(R.id.textView1);
		t2 = (TextView) findViewById(R.id.textView2);
		t1.setText(name);
		t2.setText(rew);
        stattext.setText(status);


		// Loader image - will be shown before loading image
		loadImage();

        ib.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, "I just found this issue on CITIZEN APP and realized that it is worth a share. Check it in the link given below\n\n"+name +"\n\n http://192.168.43.85:8084/PWD_SERVER/index_1.jsp" );
				sendIntent.setType("text/plain");
				startActivity(sendIntent);
			}
		});


		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				params.put("cid",pid);

				if (state == 0) {
					state = 1;
					b1.setBackgroundColor(0xFF00FF00);
					makeHTTPCall();

				} else if (state == 1) {
					state = 0;
					b1.setBackgroundColor(0xFFFFFFFF);
					makeuHTTPCall();
				}
			}
		});


	}

	private void loadImage() {
		// TODO Auto-generated method stub
		int loader = R.drawable.loader;

		// Imageview to show
		ImageView image = (ImageView) findViewById(R.id.image);

		// Image url
		String image_url = "http://192.168.43.85/PWD(php)/uploadedimages/" + pic;

		// ImageLoader class instance
		ImageLoader imgLoader = new ImageLoader(getApplicationContext());

		// whenever you want to load an image from url
		// call DisplayImage function
		// url - image url to load
		// loader - loader image, will be displayed before getting image
		// image - ImageView
		imgLoader.DisplayImage(image_url, loader, image);
	}


	public void makeHTTPCall() {

		AsyncHttpClient client = new AsyncHttpClient();
		// Don't forget to change the IP address to your LAN address. Port no as well.
		client.post("http://192.168.43.85/phptrigger/upload.php",
				params, new AsyncHttpResponseHandler() {
					// When the response returned by REST has Http
					// response code '200'
					@Override
					public void onSuccess(String response) {
						// Hide Progress Dialog

						Toast.makeText(getApplicationContext(), response,
								Toast.LENGTH_LONG).show();

					}

					// When the response returned by REST has Http
					// response code other than '200' such as '404',
					// '500' or '403' etc
					@Override
					public void onFailure(int statusCode, Throwable error,
										  String content) {
						// Hide Progress Dialog

						// When Http response code is '404'
						if (statusCode == 404) {
							Toast.makeText(getApplicationContext(),
									"Requested resource not found",
									Toast.LENGTH_LONG).show();
						}
						// When Http response code is '500'
						else if (statusCode == 500) {
							Toast.makeText(getApplicationContext(),
									"Something went wrong at server end",
									Toast.LENGTH_LONG).show();
						}
						// When Http response code other than 404, 500
						else {
							Toast.makeText(
									getApplicationContext(),
									"Error Occured \n Most Common Error: \n1. Device not connected to Internet\n2. Web App is not deployed in App server\n3. App server is not running\n HTTP Status code : "
											+ statusCode, Toast.LENGTH_LONG)
									.show();
						}
					}
				});
	}
	public void makeuHTTPCall() {

		AsyncHttpClient client = new AsyncHttpClient();
		// Don't forget to change the IP address to your LAN address. Port no as well.
		client.post("http://192.168.43.85/phptrigger/uploadminus.php",
				params, new AsyncHttpResponseHandler() {
					// When the response returned by REST has Http
					// response code '200'
					@Override
					public void onSuccess(String response) {
						// Hide Progress Dialog

						Toast.makeText(getApplicationContext(), response,
								Toast.LENGTH_LONG).show();

					}

					// When the response returned by REST has Http
					// response code other than '200' such as '404',
					// '500' or '403' etc
					@Override
					public void onFailure(int statusCode, Throwable error,
										  String content) {
						// Hide Progress Dialog

						// When Http response code is '404'
						if (statusCode == 404) {
							Toast.makeText(getApplicationContext(),
									"Requested resource not found",
									Toast.LENGTH_LONG).show();
						}
						// When Http response code is '500'
						else if (statusCode == 500) {
							Toast.makeText(getApplicationContext(),
									"Something went wrong at server end",
									Toast.LENGTH_LONG).show();
						}
						// When Http response code other than 404, 500
						else {
							Toast.makeText(
									getApplicationContext(),
									"Error Occured \n Most Common Error: \n1. Device not connected to Internet\n2. Web App is not deployed in App server\n3. App server is not running\n HTTP Status code : "
											+ statusCode, Toast.LENGTH_LONG)
									.show();
						}
					}
				});
	}

}