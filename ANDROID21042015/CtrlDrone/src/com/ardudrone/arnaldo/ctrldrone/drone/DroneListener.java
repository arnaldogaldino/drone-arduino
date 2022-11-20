package com.ardudrone.arnaldo.ctrldrone.drone;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.ardudrone.arnaldo.ctrldrone.database.helpers.Configuracao;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DroneListener implements Runnable {

    private static Configuracao configuracao;
    private static DroneListener instance;
    private static boolean started = false;
    private static boolean wifi_connected = false;
    private static Context context;
    private String ip;
	private int port;
	private String sender_command;
	private Thread listener;

    public static DroneListener GetInstance(Context c) {
        configuracao = Configuracao.GetInstance();
        context = c;

        if(instance == null)
            instance = new DroneListener();

        return instance;
    }

    private DroneListener() 
    {
    	sender_command = "command=ctrl";
    }

    public void Start() {
        if(listener == null) {
            listener = new Thread(DroneListener.GetInstance(context));
        }

        if(!listener.isAlive() && !started) {
            started = true;
            listener.start();
        }
    }
	
    public boolean Conectar()
    {
        WifiConfiguration wifi_configuration = new WifiConfiguration();
        WifiManager wifi_manager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifi_into = wifi_manager.getConnectionInfo();
        
        ConnectivityManager connectivity_manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network_info = connectivity_manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        		
        if(!wifi_manager.isWifiEnabled()) {
            Toast.makeText(context, "Dispositivo com wifi desligado!", Toast.LENGTH_SHORT).show();
            wifi_connected = false;
            return false;
        } else {
            if(network_info.isConnected() && wifi_into.getSSID().equalsIgnoreCase("\""+configuracao.GetDroneNome()+"\"")) {
                Toast.makeText(context, "Dispositivo já esta conectado no drone!", Toast.LENGTH_SHORT).show();
                wifi_connected = true;
                return true;
            } else {
        		List<WifiConfiguration> list_wifi = wifi_manager.getConfiguredNetworks();
                for( WifiConfiguration wifi : list_wifi ) {
                	if(wifi.SSID != null && wifi.SSID.equals("\"" + configuracao.GetDroneNome() + "\"")) {
	                	wifi_manager.disconnect();
	                    wifi_manager.enableNetwork(wifi.networkId, true);
	                    if(wifi_manager.reconnect())
	                    {
	                    	while(!wifi_into.getSSID().equalsIgnoreCase("\""+configuracao.GetDroneNome()+"\"")){
	                    		try {
									Thread.sleep(500);
									wifi_into = wifi_manager.getConnectionInfo();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	                    	}
	                    }
	                    
	                    if(network_info.isConnected() && wifi_into.getSSID().equalsIgnoreCase("\"" + configuracao.GetDroneNome() + "\"")) {
	                    	Toast.makeText(context, "Dispositivo foi conectado no drone!", Toast.LENGTH_SHORT).show();
	                    	wifi_connected = true;
	                    	return true;
	                    }
                	}
                }
                
                wifi_into = wifi_manager.getConnectionInfo();
                if(network_info.isConnected() && !wifi_into.getSSID().equalsIgnoreCase("\"" + configuracao.GetDroneNome() + "\"")) {
                    wifi_configuration.SSID = "\"" + configuracao.GetDroneNome() + "\"";
                    wifi_configuration.wepKeys[0] = "\"" + configuracao.GetDroneSenha() + "\"";
                    wifi_configuration.preSharedKey = "\"" + configuracao.GetDroneSenha() + "\"";
                    wifi_configuration.wepTxKeyIndex = 0;
                    wifi_configuration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                    wifi_configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);

                    wifi_manager.addNetwork(wifi_configuration);
                    List<WifiConfiguration> list_wifi_s = wifi_manager.getConfiguredNetworks();
                    for( WifiConfiguration wifi : list_wifi_s ) {
                    	if(wifi.SSID != null && wifi.SSID.equals("\"" + configuracao.GetDroneNome() + "\"")) {
    	                	wifi_manager.disconnect();
    	                    wifi_manager.enableNetwork(wifi.networkId, true);
    	                    if(wifi_manager.reconnect())
    	                    {
    	                    	while(!wifi_into.getSSID().equalsIgnoreCase("\""+configuracao.GetDroneNome()+"\"")){
    	                    		try {
    									Thread.sleep(500);
    									wifi_into = wifi_manager.getConnectionInfo();
    								} catch (InterruptedException e) {
    									// TODO Auto-generated catch block
    									e.printStackTrace();
    								}
    	                    	}
    	                    }
    	                    
    	                    if(network_info.isConnected() && wifi_into.getSSID().equalsIgnoreCase("\"" + configuracao.GetDroneNome() + "\"")) {
    	                    	Toast.makeText(context, "Dispositivo foi conectado no drone!", Toast.LENGTH_SHORT).show();
    	                    	wifi_connected = true;
    	                    	return true;
    	                    }
                    	}
                    }
                    if(network_info.isConnected() && !wifi_into.getSSID().equalsIgnoreCase("\"" + configuracao.GetDroneNome() + "\"")) {
                       	Toast.makeText(context, "Drone não encontrado!", Toast.LENGTH_SHORT).show();
                       	wifi_connected = false;
                    	return false;
                    }
                }               
            }            	
        }
        
        return false;
    }

    public boolean IsConected()
    {
    	if(!wifi_connected) {    		
            WifiManager wifi_manager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifi_into = wifi_manager.getConnectionInfo();
            
            ConnectivityManager connectivity_manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo network_info = connectivity_manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            
            if(network_info.isConnected() && wifi_into.getSSID().equalsIgnoreCase("\""+configuracao.GetDroneNome()+"\"")) {
                wifi_connected = true;
                return true;
            }            
    	}
    	
        return wifi_connected;
    }

    public boolean IsStarted()
    {
        return started;
    }

    @Override
    public void run() {
    	Looper.prepare();
    	
        ip = configuracao.GetDroneIP();
        port = configuracao.GetDronePort();
        
        while (started && !listener.isInterrupted()) {
            try {
                Socket socket = new Socket(ip, port);
                
                // Enviar
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())),
                        true);
            	
                if(sender_command.split(";")[0].split("=")[1].equalsIgnoreCase("ctrl")) {
                	out.print("comm=ctrl" +
                	";AxisDirX=" + configuracao.GetDroneInfo().getAxisDirX() +
                	";AxisDirY=" + configuracao.GetDroneInfo().getAxisDirY() +
                	";AxisEsqX=" + configuracao.GetDroneInfo().getAxisEsqX() +
                	";AxisEsqY=" + configuracao.GetDroneInfo().getAxisEsqY());
                	out.flush();
            	}
                
                if(sender_command.split(";")[0].split("=")[1].equalsIgnoreCase("gyroscope_calibrate")) {
                	out.println(sender_command);
                	out.flush();
                	sender_command = "command=ctrl";
                	socket.close();
					try {
						Thread.sleep(500);						
					}
					catch(Exception ex) {
						
					}
					continue;
                }
                
                // receber
                String response = "";
                socket.setSoTimeout(32000);
                
//                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                response = inFromServer.readLine();
//                Thread.sleep(250);	
/*                
                if(!ProcessReceiver(response))
                {
                	Log.i("DroneErro", "Erros Stream");
                    socket.close();                        
                	continue;
                }
*/
                
                int sizeBytes = socket.getReceiveBufferSize();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(sizeBytes);
                byte[] buffer = new byte[sizeBytes];
                int bytesRead;
                
                InputStream inputStream = socket.getInputStream();
                Thread.sleep(250);
                
                if ((bytesRead = inputStream.read(buffer)) != -1) {
                	byteArrayOutputStream.write(buffer, 0, bytesRead);
                    response = byteArrayOutputStream.toString("UTF-8");
                    
                    if(!ProcessReceiver(response))
                    {
                    	Log.i("Drone", "Erros Stream");
                    	byteArrayOutputStream.flush();
                        socket.close();                        
                    	continue;
                    }
                    
                    byteArrayOutputStream.flush();
                    socket.close();
                }
                
                
                
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    public void SendMsg(String msg) {
    	sender_command = msg;
    }

    private boolean ProcessReceiver(String val){
    	if(val == null || val == "") 
    		return false;
    	
    	String[] params = val.split(";");
    	
    	String   comm     = "";
    	float    accX     = 0;   
    	float    accY     = 0;   
    	float    accZ     = 0;   
    	float    gyrX     = 0;   
    	float    gyrY     = 0;   
    	float    gyrZ     = 0;   
    	float    barTemp  = 0; 
    	int      barPres  = 0;
    	int      barAtmo  = 0;
    	float    barAlt   = 0;
    	boolean  gpsLocV  = false;
    	float    gpsLat   = 0; 
    	float    gpsLng   = 0; 
    	long     gpsAge   = 0; 
    	boolean  gpsSpdV  = false; 
    	double   gpsKmph  = 0;
    	boolean  gpsSatsV = false;
    	int      gpsSats  = 0;
    	boolean  gpsDateTimeV = false;
    	Date     gpsDateTime  = new Date();    	
    	boolean  success = false;
    	
    	if(params.length < 21)
    		return false;
    /*
    	if(!params[0].split("=")[0].equalsIgnoreCase("comm"))      return false;
    	if(!params[1].split("=")[0].equalsIgnoreCase("AccX"))      return false;
    	if(!params[2].split("=")[0].equalsIgnoreCase("AccY"))      return false;
    	if(!params[3].split("=")[0].equalsIgnoreCase("AccZ"))      return false;
    	if(!params[4].split("=")[0].equalsIgnoreCase("GyrX"))      return false;
    	if(!params[5].split("=")[0].equalsIgnoreCase("GyrY"))      return false;
    	if(!params[6].split("=")[0].equalsIgnoreCase("GyrZ"))      return false;
    	if(!params[7].split("=")[0].equalsIgnoreCase("BarTemp"))   return false;
    	if(!params[8].split("=")[0].equalsIgnoreCase("BarPres"))   return false;
    	if(!params[9].split("=")[0].equalsIgnoreCase("BarAtmo"))   return false;
    	if(!params[10].split("=")[0].equalsIgnoreCase("BarAlt"))   return false;
    	if(!params[11].split("=")[0].equalsIgnoreCase("GpsLocV"))  return false;
    	if(!params[12].split("=")[0].equalsIgnoreCase("GpsLat"))   return false;
    	if(!params[13].split("=")[0].equalsIgnoreCase("GpsLng"))   return false;
    	if(!params[14].split("=")[0].equalsIgnoreCase("GpsAge"))   return false;
    	if(!params[15].split("=")[0].equalsIgnoreCase("GpsSpdV"))  return false;
    	if(!params[16].split("=")[0].equalsIgnoreCase("GpsKmph"))  return false;
    	if(!params[17].split("=")[0].equalsIgnoreCase("GpsSatsV")) return false;
    	if(!params[18].split("=")[0].equalsIgnoreCase("GpsSats"))  return false;
    	if(!params[19].split("=")[0].equalsIgnoreCase("GpsDateTimeV")) return false;
    	if(!params[20].split("=")[0].equalsIgnoreCase("GpsDateTime"))  return false;
    	if(!params[21].split("=")[0].equalsIgnoreCase("Success"))  return false;
    	*/
    	
	    if(!params[0].split("=")[0].equalsIgnoreCase("co"))      return false;
    	if(!params[1].split("=")[0].equalsIgnoreCase("AX"))      return false;
    	if(!params[2].split("=")[0].equalsIgnoreCase("AY"))      return false;
    	if(!params[3].split("=")[0].equalsIgnoreCase("AZ"))      return false;
    	if(!params[4].split("=")[0].equalsIgnoreCase("GX"))      return false;
    	if(!params[5].split("=")[0].equalsIgnoreCase("GY"))      return false;
    	if(!params[6].split("=")[0].equalsIgnoreCase("GZ"))      return false;
    	if(!params[7].split("=")[0].equalsIgnoreCase("BTemp"))   return false;
    	if(!params[8].split("=")[0].equalsIgnoreCase("BPres"))   return false;
    	if(!params[9].split("=")[0].equalsIgnoreCase("BAtmo"))   return false;
    	if(!params[10].split("=")[0].equalsIgnoreCase("BAlt"))   return false;
    	if(!params[11].split("=")[0].equalsIgnoreCase("GLocV"))  return false;
    	if(!params[12].split("=")[0].equalsIgnoreCase("GLat"))   return false;
    	if(!params[13].split("=")[0].equalsIgnoreCase("GLng"))   return false;
    	if(!params[14].split("=")[0].equalsIgnoreCase("GAge"))   return false;
    	if(!params[15].split("=")[0].equalsIgnoreCase("GSpdV"))  return false;
    	if(!params[16].split("=")[0].equalsIgnoreCase("GKmph"))  return false;
    	if(!params[17].split("=")[0].equalsIgnoreCase("GSatsV")) return false;
    	if(!params[18].split("=")[0].equalsIgnoreCase("GSats"))  return false;
    	if(!params[19].split("=")[0].equalsIgnoreCase("GDteV")) return false;
    	if(!params[20].split("=")[0].equalsIgnoreCase("GDte"))  return false;
    	if(!params[21].split("=")[0].equalsIgnoreCase("Suc"))  return false;
    	
    	comm     = params[0].split("=")[1];
    	accX     = Float.parseFloat(params[1].split("=")[1]);    	
    	accY     = Float.parseFloat(params[2].split("=")[1]);
    	accZ     = Float.parseFloat(params[3].split("=")[1]);
    	gyrX     = Float.parseFloat(params[4].split("=")[1]);
    	gyrY     = Float.parseFloat(params[5].split("=")[1]);
    	gyrZ     = Float.parseFloat(params[6].split("=")[1]);
    	barTemp  = Float.parseFloat(params[7].split("=")[1]);
    	barPres  = Integer.parseInt(params[8].split("=")[1]);
    	barAtmo  = Integer.parseInt(params[9].split("=")[1]);
    	barAlt   = Float.parseFloat(params[10].split("=")[1]);
    	gpsLocV  = (params[11].split("=")[1].equalsIgnoreCase("1") ? true : false);
    	gpsLat   = Float.parseFloat(params[12].split("=")[1]);
    	gpsLng   = Float.parseFloat(params[13].split("=")[1]);
    	gpsAge   = Long.parseLong(params[14].split("=")[1]);
    	gpsSpdV  = (params[15].split("=")[1].equalsIgnoreCase("1") ? true : false);
    	gpsKmph  = Double.parseDouble(params[16].split("=")[1]);
    	gpsSatsV = (params[17].split("=")[1].equalsIgnoreCase("1") ? true : false);
    	gpsSats  = Integer.parseInt(params[18].split("=")[1]);    	
    	gpsDateTimeV = (params[19].split("=")[1].equalsIgnoreCase("1") ? true : false);
    	
    	if(gpsDateTimeV)
    	{
	    	String target = params[20].split("=")[1];
	        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:sss");
	
			try {
				gpsDateTime = df.parse(target);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
    	}
    	
    	success  = Boolean.parseBoolean(params[21].split("=")[1]);
    	    	
    	configuracao.GetDroneInfo().setComm(comm);
    	configuracao.GetDroneInfo().setAccX(accX);
    	configuracao.GetDroneInfo().setAccY(accY);
    	configuracao.GetDroneInfo().setAccZ(accZ);
    	configuracao.GetDroneInfo().setGyrX(gyrX);
    	configuracao.GetDroneInfo().setGyrY(gyrY);
    	configuracao.GetDroneInfo().setGyrZ(gyrZ);
    	configuracao.GetDroneInfo().setBarTemp(barTemp);
    	configuracao.GetDroneInfo().setBarPres(barPres);
    	configuracao.GetDroneInfo().setBarAtmo(barAtmo);
    	configuracao.GetDroneInfo().setBarAlt(barAlt);
    	configuracao.GetDroneInfo().setGpsLocV(gpsLocV);
    	configuracao.GetDroneInfo().setGpsLat(gpsLat);
    	configuracao.GetDroneInfo().setGpsLng(gpsLng);
    	configuracao.GetDroneInfo().setGpsAge(gpsAge);
    	configuracao.GetDroneInfo().setGpsSpdV(gpsSpdV);
    	configuracao.GetDroneInfo().setGpsKmph(gpsKmph);
    	configuracao.GetDroneInfo().setGpsSatsV(gpsSatsV);
    	configuracao.GetDroneInfo().setGpsSats(gpsSats);
    	configuracao.GetDroneInfo().setGpsDateTimeV(gpsDateTimeV);
    	configuracao.GetDroneInfo().setGpsDateTime(gpsDateTime);    	
    	configuracao.GetDroneInfo().setSuccess(success);
    	
    	return true;
    }
    
}
