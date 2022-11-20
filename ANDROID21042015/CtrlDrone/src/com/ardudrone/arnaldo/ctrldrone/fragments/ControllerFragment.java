package com.ardudrone.arnaldo.ctrldrone.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ardudrone.arnaldo.ctrldrone.CtrlActivity;
import com.ardudrone.arnaldo.ctrldrone.NavigationDrawerFragment;
import com.ardudrone.arnaldo.ctrldrone.R;
import com.ardudrone.arnaldo.ctrldrone.controls.CtrlHud;
import com.ardudrone.arnaldo.ctrldrone.controls.Joystick;
import com.ardudrone.arnaldo.ctrldrone.database.helpers.Configuracao;
import com.ardudrone.arnaldo.ctrldrone.drone.DroneListener;

public class ControllerFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RelativeLayout ctrl_joystick_esquerdo, ctrl_joystick_direito, ctrl_hud;

    private Joystick joystick_esquerdo, joystick_direito;
    private Configuracao configuracao;
    private DroneListener droneListener;
    private Handler handlerAnimationHud;
    private CtrlHud ctrlHud;
    private Context context;
    private View view;
    
    private TextView lblAcelleromiter;
    private TextView lblGyroscope;
    private TextView lblBarometer;
    private TextView lblGPS;
    private static RelativeLayout pnlInfo;
    private static RelativeLayout pnlMaps;
    
    public static ControllerFragment newInstance(int sectionNumber) {
        ControllerFragment fragment = new ControllerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ControllerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ctrl, container, false);
        this.view = rootView;
        
        context = rootView.getContext();
        
        configuracao = Configuracao.GetInstance();
        droneListener = DroneListener.GetInstance(context);
          
        lblAcelleromiter = (TextView)rootView.findViewById(R.id.lblAcelleromiter);
        lblGyroscope = (TextView)rootView.findViewById(R.id.lblGyroscope);
        lblBarometer = (TextView)rootView.findViewById(R.id.lblBarometer);
        lblGPS = (TextView)rootView.findViewById(R.id.lblGPS);        
        pnlInfo = (RelativeLayout)rootView.findViewById(R.id.pnlInfo);
        pnlMaps = (RelativeLayout)rootView.findViewById(R.id.pnlMaps);
        
        ctrl_joystick_esquerdo = (RelativeLayout) rootView.findViewById(R.id.ctrl_joystick_esquerdo);
        ctrl_joystick_direito = (RelativeLayout) rootView.findViewById(R.id.ctrl_joystick_direito);
        ctrl_hud = (RelativeLayout) rootView.findViewById(R.id.ctrl_hud);
        
        ctrlHud = new CtrlHud(rootView.getContext(), ctrl_hud);
        
        joystick_esquerdo = new Joystick(rootView.getContext(), ctrl_joystick_esquerdo);
        joystick_esquerdo.SetAixsValMax(configuracao.GetAxisAValMax());
        ConfigurarJoystick(joystick_esquerdo);

        joystick_direito = new Joystick(rootView.getContext(), ctrl_joystick_direito);
        joystick_direito.SetAixsValMax(configuracao.GetAxisBValMax());
        ConfigurarJoystick(joystick_direito);

        handlerAnimationHud = new Handler(Looper.getMainLooper());
        SetOnTouchListenerJoystick();
        AnimationHud();
        return rootView;
    }

    private void AnimationHud()
    {	
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
            	handlerAnimationHud.post(new Runnable() {
                    public void run() {
                        if(droneListener.IsStarted()) {
                        	float accX = Configuracao.GetInstance().GetDroneInfo().getAccX();
                        	float accY = Configuracao.GetInstance().GetDroneInfo().getAccY();
                        	float accZ = Configuracao.GetInstance().GetDroneInfo().getAccZ();
                        	float gyrX = Configuracao.GetInstance().GetDroneInfo().getGyrX();
                        	float gyrY = Configuracao.GetInstance().GetDroneInfo().getGyrY();
                        	float gyrZ = Configuracao.GetInstance().GetDroneInfo().getGyrZ();
                        	
                        	ctrlHud.setHudPitch(accX, accY, accZ, gyrX, gyrY, gyrZ);
                        	ctrlHud.setHudRoll(accX, accY, accZ, gyrX, gyrY, gyrZ);                        	
                        	ctrlHud.setHudYaw(accX, accY, accZ, gyrX, gyrY, gyrZ);                        	
                        }
                        
                        if(droneListener.IsConected()) {
                        	NavigationDrawerFragment.changeIconMenuItem(R.id.action_connection, R.drawable.ic_connection_online);
                        	NavigationDrawerFragment.changeVisibleMenuItem(R.id.action_wifi, true);
                        	NavigationDrawerFragment.changeVisibleMenuItem(R.id.action_gps, true);
                        	NavigationDrawerFragment.changeVisibleMenuItem(R.id.action_info, true);
                        } else {
                        	NavigationDrawerFragment.changeIconMenuItem(R.id.action_connection, R.drawable.ic_connection_offline);
                        	NavigationDrawerFragment.changeVisibleMenuItem(R.id.action_wifi, false);
                        	NavigationDrawerFragment.changeVisibleMenuItem(R.id.action_gps, false);
                        	NavigationDrawerFragment.changeVisibleMenuItem(R.id.action_info, false);
                        }
                        
                        if(Configuracao.GetInstance().GetDroneInfo().getGpsLocV()) {
                        	NavigationDrawerFragment.changeIconMenuItem(R.id.action_gps, R.drawable.ic_gps_conected);
                        	if(Configuracao.GetInstance().GetDroneInfo().getGpsSatsV()) {
                        		NavigationDrawerFragment.changeIconMenuItem(R.id.action_gps, R.drawable.ic_gps_online);	
                        	}
                        } else {
                        	NavigationDrawerFragment.changeIconMenuItem(R.id.action_gps, R.drawable.ic_gps_offline);
                        }
                        
                        lblAcelleromiter.setText("Acelerometro: X=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getAccX()) + 
                        		", Y=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getAccY()) +
                        		", Z=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getAccZ()));
                        
                        lblGyroscope.setText("Giroscopio: X=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getGyrX()) + 
                        		", Y=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getGyrY()) +
                        		", Z=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getGyrZ()));

                        lblBarometer.setText("Barometro: Temp=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getBarTemp()) + 
                        		", Pres=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getBarPres()) +
                        		", Atmo=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getBarAtmo()) +
                        		", Alt=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getBarAlt()));
                        
                        lblGPS.setText("GPS: Alt=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getGpsLat()) + 
                        		", Lng=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getGpsLng()) +
                        		", Loc=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getGpsLocV()) +                        		
                        		", Age=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getGpsAge()) +                        		
                        		", Speed=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getGpsKmph()) +
                        		", SpdV=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getGpsSpdV()) +
                        		", Satelites=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getGpsSats()) +
                        		", SatV=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getGpsSatsV()) +
                        		", Data=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getGpsDateTime()) +
                        		", DteV=" + String.valueOf(Configuracao.GetInstance().GetDroneInfo().getGpsDateTimeV()));
                    }                    
                });
            	handlerAnimationHud.postDelayed(this, 250);
            }
        };
        handlerAnimationHud.postDelayed(runnable, 250);
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((CtrlActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onResume() {
       super.onResume();
    }

    public void onPause() {
        super.onPause();
    }
    
    public static void GetInfoVisible()
    {    	
    	if(pnlInfo.getVisibility() == pnlInfo.VISIBLE) {
    		pnlInfo.setVisibility(pnlInfo.INVISIBLE);
    	} else if(pnlInfo.getVisibility() == pnlInfo.INVISIBLE) {
    		pnlInfo.setVisibility(pnlInfo.VISIBLE);
    	}
    }
    
    public static void GetMapsVisible()
    {    	
    	if(pnlMaps.getVisibility() == pnlMaps.VISIBLE) {
    		pnlMaps.setVisibility(pnlMaps.INVISIBLE);
    	} else if(pnlMaps.getVisibility() == pnlMaps.INVISIBLE) {
    		pnlMaps.setVisibility(pnlMaps.VISIBLE);
    	}
    }
    
    private void ConfigurarJoystick(final Joystick joystick)
    {
        joystick.setStickFixed(true);
        joystick.setStickCenterFixed(true);
        joystick.setStickSize(configuracao.GetAixsStickSize(), configuracao.GetAixsStickSize());
        joystick.setLayoutSize(configuracao.GetAixsSize(), configuracao.GetAixsSize());
        joystick.setLayoutAlpha(200);
        joystick.setStickAlpha(250);
        joystick.setOffset(configuracao.GetAixsOffSet());
        joystick.setMinimumDistance(10);

        if(joystick.isStickFixed()) joystick.Initialize();
    }

	private void SetOnTouchListenerJoystick()
    {
        ctrl_joystick_esquerdo.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                joystick_esquerdo.drawStick(arg1);
                
                if (arg1.getAction() == MotionEvent.ACTION_DOWN
                        || arg1.getAction() == MotionEvent.ACTION_MOVE) {
                 	configuracao.GetDroneInfo().setAxisEsqX(joystick_esquerdo.getXp());
                 	configuracao.GetDroneInfo().setAxisEsqY(joystick_esquerdo.getYp() * -1);
                } else if(arg1.getAction() == MotionEvent.ACTION_UP) {
                	if(joystick_esquerdo.isStickCenterFixed())
                	{
                        if(joystick_esquerdo.get8Direction() == Joystick.STICK_NONE) {
                        	configuracao.GetDroneInfo().setAxisEsqX(0);
                         	configuracao.GetDroneInfo().setAxisEsqY(0);
                        }
                	}
                }
                return true;
            }
        });

        ctrl_joystick_direito.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                joystick_direito.drawStick(arg1);

                if (arg1.getAction() == MotionEvent.ACTION_DOWN
                        || arg1.getAction() == MotionEvent.ACTION_MOVE) {
                 	configuracao.GetDroneInfo().setAxisDirX(joystick_direito.getXp());
                 	configuracao.GetDroneInfo().setAxisDirY(joystick_direito.getYp() * -1);
                } else if(arg1.getAction() == MotionEvent.ACTION_UP) {
                	if(joystick_direito.isStickCenterFixed())
                	{
                        if(joystick_direito.get8Direction() == Joystick.STICK_NONE) {
                        	configuracao.GetDroneInfo().setAxisDirX(0);
                         	configuracao.GetDroneInfo().setAxisDirY(0);
                        }
                	}
                }
                return true;
            }
        });
    }
	
	
}
