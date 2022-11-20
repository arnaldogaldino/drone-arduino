package com.ardudrone.arnaldo.ctrldrone.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ardudrone.arnaldo.ctrldrone.CtrlActivity;
import com.ardudrone.arnaldo.ctrldrone.DroneApp;
import com.ardudrone.arnaldo.ctrldrone.R;
import com.ardudrone.arnaldo.ctrldrone.database.model.tbConfiguracao;

public class ConfiguracaoDetailFragment extends Fragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static DroneApp app;
    private static tbConfiguracao configuracao;
    private FragmentManager fragmentManager;
    private Context context;

    public static ConfiguracaoDetailFragment newInstance(int sectionNumber, long idConfiguracao) {
        ConfiguracaoDetailFragment fragment = new ConfiguracaoDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        configuracao = new tbConfiguracao();
        configuracao.setIdConfiguracao(idConfiguracao);

        return fragment;
    }

    EditText etChave, etValor;
    TextView etIdConfiguracao;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_configuracao_detail, container, false);
        setHasOptionsMenu(true);
        context = rootView.getContext();

        app = (DroneApp) getActivity().getApplication();

        etIdConfiguracao = (TextView)rootView.findViewById(R.id.etIdConfiguracao);
        etChave = (EditText)rootView.findViewById(R.id.etChave);
        etValor = (EditText)rootView.findViewById(R.id.etValor);

        if(configuracao.getIdConfiguracao() != 0)
        {
            configuracao = app.getDataManager().getConfiguracao(configuracao.getIdConfiguracao());
            etIdConfiguracao.setText(String.valueOf(configuracao.getIdConfiguracao()));
            etChave.setText(configuracao.getChave());
            etValor.setText(configuracao.getValor());
        }

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((CtrlActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.configuracao, menu);

        menu.findItem(R.id.action_novo).setVisible(false);
        menu.findItem(R.id.action_editar).setVisible(false);
        menu.findItem(R.id.action_excluir).setVisible(false);
        menu.findItem(R.id.action_reset).setVisible(false);

        if(configuracao.getIdConfiguracao() != 0)
        {
            menu.findItem(R.id.action_gravar).setVisible(false);
            menu.findItem(R.id.action_excluir).setVisible(true);
            menu.findItem(R.id.action_editar).setVisible(true);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        fragmentManager = getActivity().getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.action_gravar:
                configuracao.setChave(etChave.getText().toString());
                configuracao.setValor(etValor.getText().toString());

                app.getDataManager().saveConfiguracao(configuracao);

                Toast.makeText(getActivity(), "Gravado com sucesso...", Toast.LENGTH_SHORT).show();

                fragmentManager.beginTransaction()
                        .replace(R.id.container, ConfiguracaoFragment.newInstance(-1))
                        .commit();

                return true;
            case R.id.action_editar:
                configuracao.setChave(etChave.getText().toString());
                configuracao.setValor(etValor.getText().toString());

                app.getDataManager().updateConfiguracao(configuracao);

                Toast.makeText(getActivity(), "Gravado com sucesso...", Toast.LENGTH_SHORT).show();

                fragmentManager.beginTransaction()
                        .replace(R.id.container, ConfiguracaoFragment.newInstance(-1))
                        .commit();

                return true;
            case R.id.action_excluir:

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                app.getDataManager().deleteConfiguracao(configuracao.getIdConfiguracao());

                                Toast.makeText(getActivity(), "Configuração foi excluida.", Toast.LENGTH_SHORT).show();

                                fragmentManager.beginTransaction()
                                        .replace(R.id.container, ConfiguracaoFragment.newInstance(-1))
                                        .commit();

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                onResume();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("Deseja excluir esta configuração?")
                        .setPositiveButton("Sim", dialogClickListener)
                        .setNegativeButton("Não", dialogClickListener)
                        .show();

                return true;
            case R.id.action_voltar:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ConfiguracaoFragment.newInstance(-1))
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}