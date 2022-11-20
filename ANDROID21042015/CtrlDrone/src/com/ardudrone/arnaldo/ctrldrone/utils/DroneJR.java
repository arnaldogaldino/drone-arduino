package com.ardudrone.arnaldo.ctrldrone.utils;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import com.ardudrone.arnaldo.ctrldrone.database.helpers.Configuracao;

import java.util.Date;
import java.util.List;

public class DroneJR {

	/*
    private float gyroscopeX = 0;
    public void SetGyroscopeX(float val)
    {
        gyroscopeX = val;
    }
    public float GetGyroscopeX()
    {
        return gyroscopeX;
    }

    private float gyroscopeY = 0;
    public void SetGyroscopeY(float val)
    {
        gyroscopeY = val;
    }
    public float GetGyroscopeY()
    {
        return gyroscopeY;
    }

    private float gyroscopeZ = 0;
    public void SetGyroscopeZ(float val)
    {
        gyroscopeZ = val;
    }
    public float GetGyroscopeZ()
    {
        return gyroscopeZ;
    }

    private float accelerometerX = 0;
    private float accelerometerY = 0;
    private float accelerometerZ = 0;
    public void SetAccelerometerX(int val)
    {
        accelerometerX = val;
    }
    public float GetAccelerometerX()
    {
        return accelerometerX;
    }

    public void SetAccelerometerY(int val)
    {
        accelerometerY = val;
    }
    public float GetAccelerometerY()
    {
        return accelerometerY;
    }

    public void SetAccelerometerZ(int val)
    {
        accelerometerZ = val;
    }
    public float GetAccelerometerZ()
    {
        return accelerometerZ;
    }
	*/
	
	private String   comm     = "";
	private float    accX     = 0;   
    private float    accY     = 0;   
    private float    accZ     = 0;   
    private float    gyrX     = 0;   
    private float    gyrY     = 0;   
    private float    gyrZ     = 0;   
    private float    barTemp  = 0; 
    private int      barPres  = 0;
    private int      barAtmo  = 0;
    private float    barAlt   = 0;
    private boolean  gpsLocV  = false;
    private float    gpsLat   = 0; 
    private float    gpsLng   = 0; 
    private long     gpsAge   = 0; 
    private boolean  gpsSpdV  = false; 
    private double   gpsKmph  = 0;
    private boolean  gpsSatsV = false;
    private int      gpsSats  = 0;
    private boolean  gpsDateTimeV = false;
    private Date 	 gpsDateTime = new Date();
    private boolean  success = false;
    
    public String   getComm     () { return comm     ; }
	public float    getAccX     () { return accX     ; }
    public float    getAccY     () { return accY     ; }
    public float    getAccZ     () { return accZ     ; }
    public float    getGyrX     () { return gyrX     ; }
    public float    getGyrY     () { return gyrY     ; }
    public float    getGyrZ     () { return gyrZ     ; }
    public float    getBarTemp  () { return barTemp  ; }
    public int      getBarPres  () { return barPres  ; }
    public int      getBarAtmo  () { return barAtmo  ; }
    public float    getBarAlt   () { return barAlt   ; }
    public boolean  getGpsLocV  () { return gpsLocV  ; }
    public float    getGpsLat   () { return gpsLat   ; }
    public float    getGpsLng   () { return gpsLng   ; }
    public long     getGpsAge   () { return gpsAge   ; }
    public boolean  getGpsSpdV  () { return gpsSpdV  ; } 
    public double   getGpsKmph  () { return gpsKmph  ; }
    public boolean  getGpsSatsV () { return gpsSatsV ; }
    public int      getGpsSats  () { return gpsSats  ; }
    
    public boolean  getGpsDateTimeV () { return gpsDateTimeV ; }
    public Date     getGpsDateTime  () { return gpsDateTime  ; }
    
    public boolean  getSuccess  () { return success   ; }
    
    public void setComm         (String  _comm    ) { comm     = _comm    ; }
	public void setAccX         (float   _accX    ) { accX     = _accX    ; }
    public void setAccY         (float   _accY    ) { accY     = _accY    ; }
    public void setAccZ         (float   _accZ    ) { accZ     = _accZ    ; }
    public void setGyrX         (float   _gyrX    ) { gyrX     = _gyrX    ; }
    public void setGyrY         (float   _gyrY    ) { gyrY     = _gyrY    ; }
    public void setGyrZ         (float   _gyrZ    ) { gyrZ     = _gyrZ    ; }
    public void setBarTemp      (float   _barTemp ) { barTemp  = _barTemp ; }
    public void setBarPres      (int     _barPres ) { barPres  = _barPres ; }
    public void setBarAtmo      (int     _barAtmo ) { barAtmo  = _barAtmo ; }
    public void setBarAlt       (float   _barAlt  ) { barAlt   = _barAlt  ; }
    public void setGpsLocV      (boolean _gpsLocV ) { gpsLocV  = _gpsLocV ; }
    public void setGpsLat       (float   _gpsLat  ) { gpsLat   = _gpsLat  ; }
    public void setGpsLng       (float   _gpsLng  ) { gpsLng   = _gpsLng  ; }
    public void setGpsAge       (long    _gpsAge  ) { gpsAge   = _gpsAge  ; }
    public void setGpsSpdV      (boolean _gpsSpdV ) { gpsSpdV  = _gpsSpdV ; } 
    public void setGpsKmph      (double  _gpsKmph ) { gpsKmph  = _gpsKmph ; }
    public void setGpsSatsV     (boolean _gpsSatsV) { gpsSatsV = _gpsSatsV; }
    public void setGpsSats      (int     _gpsSats ) { gpsSats  = _gpsSats ; }
    public void setGpsDateTimeV (boolean _gpsDateTimeV) { gpsSatsV = _gpsDateTimeV; }
    public void setGpsDateTime  (Date    _gpsDateTime ) { gpsDateTime  = _gpsDateTime ; }
    public void setSuccess      (boolean _success ) { success  = _success ; }
    
    private int axisDirX = 0;
    private int axisDirY = 0;
    private int axisEsqX = 0;
    private int axisEsqY = 0;    

    public void setAxisEsqX(int val)
    {
    	axisEsqX = val;
    }
    public int getAxisEsqX()
    {
        return axisEsqX;
    }

    public void setAxisEsqY(int val)
    {
    	axisEsqY = val;
    }
    public int getAxisEsqY()
    {
        return axisEsqY;
    }

    public void setAxisDirX(int val)
    {
    	axisDirX = val;
    }
    public int getAxisDirX()
    {
        return axisDirX;
    }

    public void setAxisDirY(int val)
    {
    	axisDirY = val;
    }
    public int getAxisDirY()
    {
        return axisDirY;
    }
    
    public void ConectarWifiDrone(Context context, Configuracao cfg)
    {
        online = false;
        WifiConfiguration wifiCfg = new WifiConfiguration();

        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);

        if(wifiCfg.SSID != null) {
            if(!online) {
                Toast.makeText(context, "Dispositivo conectado no Drone!", Toast.LENGTH_LONG).show();
                online = true;
            }
        }

        if(!wifiManager.getConnectionInfo().getSSID().replace("\"", "").equalsIgnoreCase(cfg.GetDroneNome()))
        {
            Toast.makeText(context, "Procurando Drone!", Toast.LENGTH_LONG).show();
            wifiCfg.SSID = "\"" + cfg.GetDroneNome() + "\"";
            wifiCfg.wepKeys[0] = "\"" + cfg.GetDroneSenha() + "\"";
            wifiCfg.preSharedKey = "\"" + cfg.GetDroneSenha() + "\"";
            wifiCfg.wepTxKeyIndex = 0;
            wifiCfg.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            wifiCfg.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);

            try
            {
                wifiManager.addNetwork(wifiCfg);

                List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                for( WifiConfiguration i : list ) {
                    if(i.SSID != null && i.SSID.equals("\"" + cfg.GetDroneNome() + "\"")) {
                        wifiManager.disconnect();
                        wifiManager.enableNetwork(i.networkId, true);
                        wifiManager.reconnect();

                        if(wifiCfg.SSID != null)
                        {
                            online = true;
                            Toast.makeText(context, "Dispositivo conectado no Drone!", Toast.LENGTH_LONG).show();
                        }

                        break;
                    }
                }
            }
            catch(Exception e)
            {
                Toast.makeText(context, "Wifi desligado", Toast.LENGTH_LONG).show();
                return;
            }
        } else {
            online = true;
            Toast.makeText(context, "Dispositivo conectado no Drone!", Toast.LENGTH_LONG).show();
        }
    }

    private Boolean online = false;
}