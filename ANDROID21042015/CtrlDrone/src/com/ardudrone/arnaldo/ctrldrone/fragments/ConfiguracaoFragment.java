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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ardudrone.arnaldo.ctrldrone.CtrlActivity;
import com.ardudrone.arnaldo.ctrldrone.DroneApp;
import com.ardudrone.arnaldo.ctrldrone.R;
import com.ardudrone.arnaldo.ctrldrone.adapters.ConfiguracaoArrayAdapter;
import com.ardudrone.arnaldo.ctrldrone.database.dao.impl.tbConfiguracaoDao;
import com.ardudrone.arnaldo.ctrldrone.database.helpers.tbConfiguracaoComparatorById;
import com.ardudrone.arnaldo.ctrldrone.database.model.tbConfiguracao;

import java.util.Collections;
import java.util.List;

public class ConfiguracaoFragment extends Fragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private DroneApp droneApp;
    private ListView lsConfiguracao;
    private tbConfiguracao[] listConfiguracao;
    private Context context;
    private FragmentManager fragmentManager;
    private List<tbConfiguracao> list;

    public static ConfiguracaoFragment newInstance(int sectionNumber) {
        ConfiguracaoFragment fragment = new ConfiguracaoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_configuracao, container, false);
        setHasOptionsMenu(true);
        context = rootView.getContext();
        fragmentManager = getActivity().getSupportFragmentManager();
        droneApp = (DroneApp) getActivity().getApplication();

        list = droneApp.getDataManager().getConfiguracaoList();
        Collections.sort(list, new tbConfiguracaoComparatorById());

        listConfiguracao = new tbConfiguracao[list.size()];
        list.toArray(listConfiguracao);

        lsConfiguracao = (ListView)rootView.findViewById(R.id.lvConfiguracao);

        ConfiguracaoArrayAdapter adapter =
                new ConfiguracaoArrayAdapter(context, listConfiguracao);

        lsConfiguracao.setAdapter(adapter);

        lsConfiguracao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterViewCompat, View view, int i, long l) {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ConfiguracaoDetailFragment.newInstance(1, list.get(i).getIdConfiguracao()))
                        .commit();
            }
        });

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
        menu.findItem(R.id.action_editar).setVisible(false);
        menu.findItem(R.id.action_excluir).setVisible(false);
        menu.findItem(R.id.action_gravar).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_novo:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ConfiguracaoDetailFragment.newInstance(1, 0))
                        .commit();
                return true;
            case R.id.action_voltar:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ControllerFragment.newInstance(1))
                        .commit();
                return true;
            case R.id.action_reset:

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                            	droneApp.getDataManager().getConfiguracaoDao().ConfiguracaoTruncate(droneApp);
                                tbConfiguracaoDao.ConfiguracaoInitialize(droneApp);
                                
                                fragmentManager.beginTransaction()
                                        .replace(R.id.container, ConfiguracaoFragment.newInstance(1))
                                        .commit();

                                Toast.makeText(getActivity(), "As configurações foram reiniciadas.", Toast.LENGTH_SHORT).show();

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                onResume();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("Deseja reiniciar as configuraÃ§Ã£o?")
                        .setPositiveButton("Sim", dialogClickListener)
                        .setNegativeButton("NÃ£o", dialogClickListener)
                        .show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}