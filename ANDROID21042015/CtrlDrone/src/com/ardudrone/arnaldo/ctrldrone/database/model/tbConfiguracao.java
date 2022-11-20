package com.ardudrone.arnaldo.ctrldrone.database.model;

import org.droidpersistence.annotation.Column;
import org.droidpersistence.annotation.PrimaryKey;
import org.droidpersistence.annotation.Table;

@Table(name="CONFIGURACAO")
public class tbConfiguracao  {

    @PrimaryKey(autoIncrement=true)
    @Column(name="IDCONFIGURACAO")
    private long idConfiguracao;

    @Column(name="CHAVE")
    private String chave;

    @Column(name="VALOR")
    private String valor;

    public Long getIdConfiguracao() {
        return this.idConfiguracao;
    }

    public void setIdConfiguracao(long idConfiguracao) {
        this.idConfiguracao = idConfiguracao;
    }

    public String getChave() {
        return this.chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
