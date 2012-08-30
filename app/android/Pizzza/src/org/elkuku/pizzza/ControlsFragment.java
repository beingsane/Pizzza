package org.elkuku.pizzza;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class ControlsFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.controls, container, false);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		Button btnCarta = (Button) getActivity().findViewById(R.id.btnCarta);

		btnCarta.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});

		Button btnPromos = (Button) getActivity().findViewById(R.id.btnPromos);

		btnPromos.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), PromosActivity.class);
				startActivity(i);
			}
		});

		Button btnNews = (Button) getActivity().findViewById(R.id.btnNews);

		Log.w("PIZZZA", "News Button: "+btnNews);

		btnNews.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), NewsActivity.class);
				startActivity(i);
			}
		});

		Button btnPedidos = (Button) getActivity().findViewById(R.id.btnPedidos);

		btnPedidos.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), PedidosActivity.class);
				startActivity(i);
			}
		});

		/*
		 * T E S T ing
		 */

		/* Delete db */
		Button button2 = (Button) getActivity().findViewById(R.id.btnKillDb);

		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				MenuListFragment fragment = (MenuListFragment) getFragmentManager().findFragmentById(R.id.listFragment);

				SQLiteHelper dbhelper = new SQLiteHelper(getActivity());

				dbhelper.resetDb();

				Toast.makeText(getActivity(), "Database has been deleted", Toast.LENGTH_LONG).show();

				fragment.onActivityCreated(null);
			}
		});

		/* Refresh db */
		Button btnRefresh = (Button) getActivity().findViewById(R.id.btnRefresh);

		btnRefresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				MenuListFragment fragment = (MenuListFragment) getFragmentManager().findFragmentById(R.id.listFragment);

				MenuDataSource menuDatasource = new MenuDataSource(getActivity(), fragment);
				PedidosDataSource pedidosDatasource = new PedidosDataSource(getActivity());
				PromosDataSource promosDatasource = new PromosDataSource(getActivity());
				NewsDataSource newsDatasource = new NewsDataSource(getActivity());

				try {
					menuDatasource.update();
					pedidosDatasource.update();
					promosDatasource.update();
					newsDatasource.update();
				} catch (Exception e) {
					Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});
	}

}
