package com.ardudrone.arnaldo.ctrldrone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ardudrone.arnaldo.ctrldrone.R;
import com.ardudrone.arnaldo.ctrldrone.database.model.tbConfiguracao;

public class ConfiguracaoArrayAdapter extends ArrayAdapter<tbConfiguracao> {
    private final Context context;
    private final tbConfiguracao[] values;

    public ConfiguracaoArrayAdapter(Context context, tbConfiguracao[] values) {
        super(context, R.layout.list_configuracao_opcao, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_configuracao_opcao, parent, false);

        TextView tvIdConfiguracao = (TextView) rowView.findViewById(R.id.tvIdConfiguracao);
        TextView tvChave = (TextView) rowView.findViewById(R.id.tvChave);
        TextView tvValor = (TextView) rowView.findViewById(R.id.tvValor);

        tvIdConfiguracao.setText(String.valueOf(values[position].getIdConfiguracao()));
        tvChave.setText(values[position].getChave());
        tvValor.setText(values[position].getValor());

        return rowView;
    }
}
