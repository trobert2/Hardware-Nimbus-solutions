package br.gov.serpro.adventure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class EventListAdapter extends ArrayAdapter<Event> {

	private Context context;
	private Event[] values;

	public EventListAdapter(Context context, Event[] values) {

		super(context, R.layout.event_list_rowlayout, values);

		this.context = context;
		this.values = values;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.event_list_rowlayout, parent, false);

		TextView data = (TextView) rowView.findViewById(R.id.data);
		data.setText(values[position].getData());
		
		TextView nome = (TextView) rowView.findViewById(R.id.nome);
		nome.setText(values[position].getNome());
		
		return rowView;

	}
	
}
