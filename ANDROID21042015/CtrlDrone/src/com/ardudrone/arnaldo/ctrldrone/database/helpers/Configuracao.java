package com.ardudrone.arnaldo.ctrldrone.database.helpers;

import com.ardudrone.arnaldo.ctrldrone.DroneApp;
import com.ardudrone.arnaldo.ctrldrone.database.dao.DataManager;
import com.ardudrone.arnaldo.ctrldrone.utils.DroneJR;

public class Configuracao {
    private DroneApp app;

    private int AIXS_SIZE        = 0,
            AIXS_STICK_SIZE  = 0,
            AIXS_A_VAL_MAX   = 0,
            AIXS_B_VAL_MAX   = 0,
            AIXS_OFFSET      = 0,
            AIXS_MARGIN_L    = 0,
            AIXS_MARGIN_B    = 0,
            CTRL_VOO_WIGHT   = 0,
            CTRL_VOO_HEIGHT  = 0,
            MOTOR_RPM_MAX    = 0,
            MOTOR_RPM_MIN	 = 0,
            MOTOR_RPM_EST    = 0,
            MOTOR_RPM_DES_MAX = 0,
            MOTOR_RPM_DES_MIM = 0,
            DRONE_PORT        = 0;

    private float CTRL_VOO_GRD_POS1   = 0.35f,
            CTRL_VOO_GRD_POS2   = 0.90f,
            CTRL_VOO_GRD_POS3   = 0.35f,
            CTRL_VOO_GRD_TAM_X1 = 0.00f,
            CTRL_VOO_GRD_TAM_Y1 = 50.00f,
            CTRL_VOO_GRD_TAM_X2 = 0.00f,
            CTRL_VOO_GRD_TAM_Y2 = 250.00f,
            CTRL_VOO_GYROSCOPE_POS_INI = -360f,
            CTRL_VOO_GYROSCOPE_POS_VL = 360f;

    private String DRONE_IP = "",
            DRONE_NOME = "",
            DRONE_SENHA = "";

    private static DroneJR loadDrone = new DroneJR();

    private static Configuracao cfg;

    public static void SetInstance(Configuracao value)
    {
        cfg = value;
    }

    public static Configuracao GetInstance()
    {
        return cfg;
    }

    public static void SetDroneJR(DroneJR val)
    {
        loadDrone = val;
    }
    public DroneJR GetDroneInfo()
    {
        return loadDrone;
    }

    public Configuracao(DroneApp app)
    {
        this.app = app;
    }

    public void Initialize()
    {
        AIXS_A_VAL_MAX = Integer.parseInt(app.getDataManager().GetParams(DataManager.AIXS_A_VAL_MAX));
        AIXS_B_VAL_MAX = Integer.parseInt(app.getDataManager().GetParams(DataManager.AIXS_B_VAL_MAX));
        AIXS_SIZE = Integer.parseInt(app.getDataManager().GetParams(DataManager.AIXS_SIZE));
        AIXS_STICK_SIZE = Integer.parseInt(app.getDataManager().GetParams(DataManager.AIXS_STICK_SIZE));
        AIXS_OFFSET = Integer.parseInt(app.getDataManager().GetParams(DataManager.AIXS_OFFSET));
        AIXS_MARGIN_L = Integer.parseInt(app.getDataManager().GetParams(DataManager.AIXS_MARGIN_L));
        AIXS_MARGIN_B = Integer.parseInt(app.getDataManager().GetParams(DataManager.AIXS_MARGIN_B));
        CTRL_VOO_WIGHT = Integer.parseInt(app.getDataManager().GetParams(DataManager.CTRL_VOO_WIDHT));
        CTRL_VOO_HEIGHT = Integer.parseInt(app.getDataManager().GetParams(DataManager.CTRL_VOO_HEIGHT));
		CTRL_VOO_GRD_POS1 = Float.valueOf(app.getDataManager().getConfiguracao(DataManager.CTRL_VOO_GRD_POS1).getValor());
		CTRL_VOO_GRD_POS2 = Float.valueOf(app.getDataManager().getConfiguracao(DataManager.CTRL_VOO_GRD_POS2).getValor());
		CTRL_VOO_GRD_POS3 = Float.valueOf(app.getDataManager().getConfiguracao(DataManager.CTRL_VOO_GRD_POS3).getValor());
		CTRL_VOO_GRD_TAM_X1 = Float.valueOf(app.getDataManager().getConfiguracao(DataManager.CTRL_VOO_GRD_TAM_X1).getValor());
		CTRL_VOO_GRD_TAM_Y1 = Float.valueOf(app.getDataManager().getConfiguracao(DataManager.CTRL_VOO_GRD_TAM_Y1).getValor());
		CTRL_VOO_GRD_TAM_X2 = Float.valueOf(app.getDataManager().getConfiguracao(DataManager.CTRL_VOO_GRD_TAM_X2).getValor());
		CTRL_VOO_GRD_TAM_Y2 = Float.valueOf(app.getDataManager().getConfiguracao(DataManager.CTRL_VOO_GRD_TAM_Y2).getValor());
		CTRL_VOO_GYROSCOPE_POS_INI = Float.valueOf(app.getDataManager().getConfiguracao(DataManager.CTRL_VOO_GYROSCOPE_POS_INI).getValor());
		CTRL_VOO_GYROSCOPE_POS_VL = Float.valueOf(app.getDataManager().getConfiguracao(DataManager.CTRL_VOO_GYROSCOPE_POS_VL).getValor());

        MOTOR_RPM_MAX = Integer.parseInt(app.getDataManager().GetParams(DataManager.MOTOR_RPM_MAX));
        MOTOR_RPM_MIN = Integer.parseInt(app.getDataManager().GetParams(DataManager.MOTOR_RPM_MIN));
        MOTOR_RPM_EST = Integer.parseInt(app.getDataManager().GetParams(DataManager.MOTOR_RPM_EST));
        MOTOR_RPM_DES_MAX = Integer.parseInt(app.getDataManager().GetParams(DataManager.MOTOR_RPM_DES_MAX));
        MOTOR_RPM_DES_MIM = Integer.parseInt(app.getDataManager().GetParams(DataManager.MOTOR_RPM_DES_MIM));
        DRONE_PORT = Integer.parseInt(app.getDataManager().GetParams(DataManager.DRONE_PORT));

        DRONE_IP = app.getDataManager().GetParams(DataManager.DRONE_IP);
        DRONE_NOME = app.getDataManager().GetParams(DataManager.DRONE_NOME);
        DRONE_SENHA = app.getDataManager().GetParams(DataManager.DRONE_SENHA);
    }

    public int GetAixsSize()
    {
        return AIXS_SIZE;
    }

    public int GetAixsStickSize()
    {
        return AIXS_STICK_SIZE;
    }

    public int GetAxisAValMax()
    {
        return AIXS_A_VAL_MAX;
    }

    public int GetAxisBValMax()
    {
        return AIXS_B_VAL_MAX;
    }

    public int GetAixsOffSet()
    {
        return AIXS_OFFSET;
    }

    public int GetAixsMarginL()
    {
        return AIXS_MARGIN_L;
    }

    public int GetAixsMarginB()
    {
        return AIXS_MARGIN_B;
    }

    public int GetCtrlVooWight()
    {
        return CTRL_VOO_WIGHT;
    }

    public int GetCtrlVooHeight()
    {
        return CTRL_VOO_HEIGHT;
    }

    public float GetCtrlVooGrdPos1()
    {
        return CTRL_VOO_GRD_POS1;
    }

    public float GetCtrlVooGrdPos2()
    {
        return CTRL_VOO_GRD_POS2;
    }

    public float GetCtrlVooGrdPos3()
    {
        return CTRL_VOO_GRD_POS3;
    }

    public float GetCtrlVooGrdTamX1()
    {
        return CTRL_VOO_GRD_TAM_X1;
    }

    public float GetCtrlVooGrdTamX2()
    {
        return CTRL_VOO_GRD_TAM_X2;
    }

    public float GetCtrlVooGrdTamY1()
    {
        return CTRL_VOO_GRD_TAM_Y1;
    }

    public float GetCtrlVooGrdTamY2()
    {
        return CTRL_VOO_GRD_TAM_Y2;
    }

    public float GetCtrlVooGyroscopePosIni()
    {
        return CTRL_VOO_GYROSCOPE_POS_INI;
    }

    public float GetCtrlVooGyroscopePosVl()
    {
        return CTRL_VOO_GYROSCOPE_POS_VL;
    }

    public int GetMotorRpmMax()
    {
        return MOTOR_RPM_MAX;
    }

    public int GetMotorRpmMin()
    {
        return MOTOR_RPM_MIN;
    }

    public int GetMotorRpmEst()
    {
        return MOTOR_RPM_EST;
    }

    public int GetMotorRpmDesMim()
    {
        return MOTOR_RPM_DES_MIM;
    }

    public int GetMotorRpmDesMax()
    {
        return MOTOR_RPM_DES_MAX;
    }

    public String GetDroneNome()
    {
        return DRONE_NOME;
    }

    public String GetDroneSenha()
    {
        return DRONE_SENHA;
    }

    public String GetDroneIP()
    {
        return DRONE_IP;
    }

    public int GetDronePort()
    {
        return DRONE_PORT;
    }

}
