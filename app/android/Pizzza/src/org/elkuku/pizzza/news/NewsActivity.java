package org.elkuku.pizzza.news;

import java.util.List;

import org.elkuku.pizzza.R;
import org.elkuku.pizzza.R.layout;
import org.elkuku.pizzza.types.TNews;

import android.app.ListActivity;
import android.os.Bundle;

public class NewsActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.news);

		NewsDataSource datasource = new NewsDataSource(this);

		datasource.open();
		List<TNews> list = datasource.getAllEntries();
		datasource.close();

		// TODO we are creating a dummy list - learn java => ArrayAdapter ;(
		String[] dummylist = new String[list.size()];

		NewsAdapter adapter = new NewsAdapter(this, list, dummylist);

		setListAdapter(adapter);
	}

}