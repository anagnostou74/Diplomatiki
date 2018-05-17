package gr.mobap.rss.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

import gr.mobap.rss.Constants;
import gr.mobap.rss.Item;
import gr.mobap.rss.Parser;

public class DrastService extends IntentService {

	public static final String RSS_LINK = "https://www.hellenicparliament.gr/rssfeed/RssCommitteesActivities.aspx";
	public static final String ITEMS = "items";
	public static final String RECEIVER = "receiver";

	public DrastService() {
		super("DrastService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(Constants.TAG, "DrastService started");
		List<Item> items = null;
		try {
			Parser parser = new Parser();
			items = parser.parse(getInputStream(RSS_LINK));
		} catch (XmlPullParserException e) {
			Log.w(e.getMessage(), e);
		} catch (IOException e) {
			Log.w(e.getMessage(), e);
		}
		Bundle bundle = new Bundle();
		bundle.putSerializable(ITEMS, (Serializable) items);
		ResultReceiver receiver = intent.getParcelableExtra(RECEIVER);
		receiver.send(0, bundle);
	}

	public InputStream getInputStream(String link) {
		try {
			URL url = new URL(link);
			return url.openConnection().getInputStream();
		} catch (IOException e) {
			Log.w(Constants.TAG, "Exception while retrieving the input stream", e);
			return null;
		}
	}
}
