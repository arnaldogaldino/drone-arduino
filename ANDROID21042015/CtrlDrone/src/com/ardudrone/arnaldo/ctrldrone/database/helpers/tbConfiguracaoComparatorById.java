package com.ardudrone.arnaldo.ctrldrone.database.helpers;

import com.ardudrone.arnaldo.ctrldrone.database.model.tbConfiguracao;

import java.util.Comparator;

public class tbConfiguracaoComparatorById implements Comparator<tbConfiguracao> {
    @Override
    public int compare(tbConfiguracao o1, tbConfiguracao o2) {
        return o1.getIdConfiguracao().compareTo(o2.getIdConfiguracao());
    }
}
