package com.ardudrone.arnaldo.ctrldrone.database.dao.impl;

import android.database.sqlite.SQLiteDatabase;
import com.ardudrone.arnaldo.ctrldrone.DroneApp;
import com.ardudrone.arnaldo.ctrldrone.database.dao.DataManager;
import com.ardudrone.arnaldo.ctrldrone.database.model.tbConfiguracao;
import org.droidpersistence.dao.DroidDao;
import org.droidpersistence.dao.TableDefinition;

public class tbConfiguracaoDao extends DroidDao<tbConfiguracao, Long> {

    public tbConfiguracaoDao(TableDefinition<tbConfiguracao> tableDefinition, SQLiteDatabase database) {
        super(tbConfiguracao.class, tableDefinition, database);
    }

    public static void ConfiguracaoInitialize(DroneApp droneApp)
    {
        DataManager dataManager;
        dataManager = droneApp.getDataManager();

        tbConfiguracao cfgAixsAValMax = dataManager.getConfiguracao(DataManager.AIXS_A_VAL_MAX);
        if(cfgAixsAValMax == null)
        {
            cfgAixsAValMax = new tbConfiguracao();
            cfgAixsAValMax.setChave(DataManager.AIXS_A_VAL_MAX);
            cfgAixsAValMax.setValor("180");
            dataManager.saveConfiguracao(cfgAixsAValMax);
        }

        tbConfiguracao cfgAixsBValMax = dataManager.getConfiguracao(DataManager.AIXS_B_VAL_MAX);
        if(cfgAixsBValMax == null)
        {
            cfgAixsBValMax = new tbConfiguracao();
            cfgAixsBValMax.setChave(DataManager.AIXS_B_VAL_MAX);
            cfgAixsBValMax.setValor("180");
            dataManager.saveConfiguracao(cfgAixsBValMax);
        }

        tbConfiguracao cfgAixsSize = dataManager.getConfiguracao(DataManager.AIXS_SIZE);
        if(cfgAixsSize == null)
        {
            cfgAixsSize = new tbConfiguracao();
            cfgAixsSize.setChave(DataManager.AIXS_SIZE);
            cfgAixsSize.setValor("400");
            dataManager.saveConfiguracao(cfgAixsSize);
        }

        tbConfiguracao cfgAixsMarginB = dataManager.getConfiguracao(DataManager.AIXS_MARGIN_B);
        if(cfgAixsMarginB == null)
        {
            cfgAixsMarginB = new tbConfiguracao();
            cfgAixsMarginB.setChave(DataManager.AIXS_MARGIN_B);
            cfgAixsMarginB.setValor("25");
            dataManager.saveConfiguracao(cfgAixsMarginB);
        }

        tbConfiguracao cfgAixsMarginL = dataManager.getConfiguracao(DataManager.AIXS_MARGIN_L);
        if(cfgAixsMarginL == null)
        {
            cfgAixsMarginL = new tbConfiguracao();
            cfgAixsMarginL.setChave(DataManager.AIXS_MARGIN_L);
            cfgAixsMarginL.setValor("25");
            dataManager.saveConfiguracao(cfgAixsMarginL);
        }

        tbConfiguracao cfgAixsOffSet = dataManager.getConfiguracao(DataManager.AIXS_OFFSET);
        if(cfgAixsOffSet == null)
        {
            cfgAixsOffSet = new tbConfiguracao();
            cfgAixsOffSet.setChave(DataManager.AIXS_OFFSET);
            cfgAixsOffSet.setValor("30");
            dataManager.saveConfiguracao(cfgAixsOffSet);
        }

        tbConfiguracao cfgAixsStickSize = dataManager.getConfiguracao(DataManager.AIXS_STICK_SIZE);
        if(cfgAixsStickSize == null)
        {
            cfgAixsStickSize = new tbConfiguracao();
            cfgAixsStickSize.setChave(DataManager.AIXS_STICK_SIZE);
            cfgAixsStickSize.setValor("50");
            dataManager.saveConfiguracao(cfgAixsStickSize);
        }

        tbConfiguracao cfgCtrlWidht = dataManager.getConfiguracao(DataManager.CTRL_VOO_WIDHT);
        if(cfgCtrlWidht == null)
        {
            cfgCtrlWidht = new tbConfiguracao();
            cfgCtrlWidht.setChave(DataManager.CTRL_VOO_WIDHT);
            cfgCtrlWidht.setValor("500");
            dataManager.saveConfiguracao(cfgCtrlWidht);
        }

        tbConfiguracao cfgCtrlHeight = dataManager.getConfiguracao(DataManager.CTRL_VOO_HEIGHT);
        if(cfgCtrlHeight == null)
        {
            cfgCtrlHeight = new tbConfiguracao();
            cfgCtrlHeight.setChave(DataManager.CTRL_VOO_HEIGHT);
            cfgCtrlHeight.setValor("400");
            dataManager.saveConfiguracao(cfgCtrlHeight);
        }

        tbConfiguracao cfgCtrlVooGrdTamX1 = dataManager.getConfiguracao(DataManager.CTRL_VOO_GRD_TAM_X1);
        if(cfgCtrlVooGrdTamX1 == null)
        {
            cfgCtrlVooGrdTamX1 = new tbConfiguracao();
            cfgCtrlVooGrdTamX1.setChave(DataManager.CTRL_VOO_GRD_TAM_X1);
            cfgCtrlVooGrdTamX1.setValor("0.00f");
            dataManager.saveConfiguracao(cfgCtrlVooGrdTamX1);
        }

        tbConfiguracao cfgCtrlVooGrdTamY1 = dataManager.getConfiguracao(DataManager.CTRL_VOO_GRD_TAM_Y1);
        if(cfgCtrlVooGrdTamY1 == null)
        {
            cfgCtrlVooGrdTamY1 = new tbConfiguracao();
            cfgCtrlVooGrdTamY1.setChave(DataManager.CTRL_VOO_GRD_TAM_Y1);
            cfgCtrlVooGrdTamY1.setValor("50.00f");
            dataManager.saveConfiguracao(cfgCtrlVooGrdTamY1);
        }

        tbConfiguracao cfgCtrlVooGrdTamX2 = dataManager.getConfiguracao(DataManager.CTRL_VOO_GRD_TAM_X2);
        if(cfgCtrlVooGrdTamX2 == null)
        {
            cfgCtrlVooGrdTamX2 = new tbConfiguracao();
            cfgCtrlVooGrdTamX2.setChave(DataManager.CTRL_VOO_GRD_TAM_X2);
            cfgCtrlVooGrdTamX2.setValor("0.00f");
            dataManager.saveConfiguracao(cfgCtrlVooGrdTamX2);
        }

        tbConfiguracao cfgCtrlVooGrdTamY2 = dataManager.getConfiguracao(DataManager.CTRL_VOO_GRD_TAM_Y2);
        if(cfgCtrlVooGrdTamY2 == null)
        {
            cfgCtrlVooGrdTamY2 = new tbConfiguracao();
            cfgCtrlVooGrdTamY2.setChave(DataManager.CTRL_VOO_GRD_TAM_Y2);
            cfgCtrlVooGrdTamY2.setValor("250.00f");
            dataManager.saveConfiguracao(cfgCtrlVooGrdTamY2);
        }

        tbConfiguracao cfgCtrlVooGrdPos1 = dataManager.getConfiguracao(DataManager.CTRL_VOO_GRD_POS1);
        if(cfgCtrlVooGrdPos1 == null)
        {
            cfgCtrlVooGrdPos1 = new tbConfiguracao();
            cfgCtrlVooGrdPos1.setChave(DataManager.CTRL_VOO_GRD_POS1);
            cfgCtrlVooGrdPos1.setValor("0.35f");
            dataManager.saveConfiguracao(cfgCtrlVooGrdPos1);
        }

        tbConfiguracao cfgCtrlVooGrdPos2 = dataManager.getConfiguracao(DataManager.CTRL_VOO_GRD_POS2);
        if(cfgCtrlVooGrdPos2 == null)
        {
            cfgCtrlVooGrdPos2 = new tbConfiguracao();
            cfgCtrlVooGrdPos2.setChave(DataManager.CTRL_VOO_GRD_POS2);
            cfgCtrlVooGrdPos2.setValor("0.90f");
            dataManager.saveConfiguracao(cfgCtrlVooGrdPos2);
        }

        tbConfiguracao cfgCtrlVooGrdPos3 = dataManager.getConfiguracao(DataManager.CTRL_VOO_GRD_POS3);
        if(cfgCtrlVooGrdPos3 == null)
        {
            cfgCtrlVooGrdPos3 = new tbConfiguracao();
            cfgCtrlVooGrdPos3.setChave(DataManager.CTRL_VOO_GRD_POS3);
            cfgCtrlVooGrdPos3.setValor("0.35f");
            dataManager.saveConfiguracao(cfgCtrlVooGrdPos3);
        }

        tbConfiguracao cfgCtrlVooGyroscopePosIni = dataManager.getConfiguracao(DataManager.CTRL_VOO_GYROSCOPE_POS_INI);
        if(cfgCtrlVooGyroscopePosIni == null)
        {
            cfgCtrlVooGyroscopePosIni = new tbConfiguracao();
            cfgCtrlVooGyroscopePosIni.setChave(DataManager.CTRL_VOO_GYROSCOPE_POS_INI);
            cfgCtrlVooGyroscopePosIni.setValor("-360.00f");
            dataManager.saveConfiguracao(cfgCtrlVooGyroscopePosIni);
        }

        tbConfiguracao cfgCtrlVooGyroscopePosVl = dataManager.getConfiguracao(DataManager.CTRL_VOO_GYROSCOPE_POS_VL);
        if(cfgCtrlVooGyroscopePosVl == null)
        {
            cfgCtrlVooGyroscopePosVl = new tbConfiguracao();
            cfgCtrlVooGyroscopePosVl.setChave(DataManager.CTRL_VOO_GYROSCOPE_POS_VL);
            cfgCtrlVooGyroscopePosVl.setValor("360.00f");
            dataManager.saveConfiguracao(cfgCtrlVooGyroscopePosVl);
        }

        tbConfiguracao cfgDroneIp = dataManager.getConfiguracao(DataManager.DRONE_IP);
        if(cfgDroneIp == null)
        {
            cfgDroneIp = new tbConfiguracao();
            cfgDroneIp.setChave(DataManager.DRONE_IP);
            cfgDroneIp.setValor("192.168.2.1");
            dataManager.saveConfiguracao(cfgDroneIp);
        }

        tbConfiguracao cfgDronePort = dataManager.getConfiguracao(DataManager.DRONE_PORT);
        if(cfgDronePort == null)
        {
            cfgDronePort = new tbConfiguracao();
            cfgDronePort.setChave(DataManager.DRONE_PORT);
            cfgDronePort.setValor("8500");
            dataManager.saveConfiguracao(cfgDronePort);
        }

        tbConfiguracao cfgDroneNome = dataManager.getConfiguracao(DataManager.DRONE_NOME);
        if(cfgDroneNome == null)
        {
            cfgDroneNome = new tbConfiguracao();
            cfgDroneNome.setChave(DataManager.DRONE_NOME);
            cfgDroneNome.setValor("DroneJR");
            dataManager.saveConfiguracao(cfgDroneNome);
        }

        tbConfiguracao cfgDroneSenha = dataManager.getConfiguracao(DataManager.DRONE_SENHA);
        if(cfgDroneSenha == null)
        {
            cfgDroneSenha = new tbConfiguracao();
            cfgDroneSenha.setChave(DataManager.DRONE_SENHA);
            cfgDroneSenha.setValor("123456");
            dataManager.saveConfiguracao(cfgDroneSenha);
        }

        tbConfiguracao cfgMotorQtd = dataManager.getConfiguracao(DataManager.MOTOR_QTD);
        if(cfgMotorQtd == null)
        {
            cfgMotorQtd = new tbConfiguracao();
            cfgMotorQtd.setChave(DataManager.MOTOR_QTD);
            cfgMotorQtd.setValor("4");
            dataManager.saveConfiguracao(cfgMotorQtd);
        }

        tbConfiguracao cfgMotorRpmMax = dataManager.getConfiguracao(DataManager.MOTOR_RPM_MAX);
        if(cfgMotorRpmMax == null)
        {
            cfgMotorRpmMax = new tbConfiguracao();
            cfgMotorRpmMax.setChave(DataManager.MOTOR_RPM_MAX);
            cfgMotorRpmMax.setValor("12250");
            dataManager.saveConfiguracao(cfgMotorRpmMax);
        }

        tbConfiguracao cfgMotorRpmMin = dataManager.getConfiguracao(DataManager.MOTOR_RPM_MIN);
        if(cfgMotorRpmMin == null)
        {
            cfgMotorRpmMin = new tbConfiguracao();
            cfgMotorRpmMin.setChave(DataManager.MOTOR_RPM_MIN);
            cfgMotorRpmMin.setValor("100");
            dataManager.saveConfiguracao(cfgMotorRpmMin);
        }

        tbConfiguracao cfgMotorRpmEst = dataManager.getConfiguracao(DataManager.MOTOR_RPM_EST);
        if(cfgMotorRpmEst == null)
        {
            cfgMotorRpmEst = new tbConfiguracao();
            cfgMotorRpmEst.setChave(DataManager.MOTOR_RPM_EST);
            cfgMotorRpmEst.setValor("10");
            dataManager.saveConfiguracao(cfgMotorRpmEst);
        }

        tbConfiguracao cfgMotorRpmDesMin = dataManager.getConfiguracao(DataManager.MOTOR_RPM_DES_MIM);
        if(cfgMotorRpmDesMin == null)
        {
            cfgMotorRpmDesMin = new tbConfiguracao();
            cfgMotorRpmDesMin.setChave(DataManager.MOTOR_RPM_DES_MIM);
            cfgMotorRpmDesMin.setValor("5");
            dataManager.saveConfiguracao(cfgMotorRpmDesMin);
        }

        tbConfiguracao cfgMotorRpmDesMax = dataManager.getConfiguracao(DataManager.MOTOR_RPM_DES_MAX);
        if(cfgMotorRpmDesMax == null)
        {
            cfgMotorRpmDesMax = new tbConfiguracao();
            cfgMotorRpmDesMax.setChave(DataManager.MOTOR_RPM_DES_MAX);
            cfgMotorRpmDesMax.setValor("20");
            dataManager.saveConfiguracao(cfgMotorRpmDesMax);
        }
    }

    public void ConfiguracaoTruncate(DroneApp droneApp)
    {
        DroneApp app = droneApp;
        DataManager dataManager = app.getDataManager();
        dataManager.Truncate("CONFIGURACAO");
    }

}