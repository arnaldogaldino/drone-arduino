package com.ardudrone.arnaldo.ctrldrone.controls;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

@SuppressLint("ResourceAsColor")
public class CtrlHud {

	private ViewGroup viewsGroup;
	private final Paint paint_line = new Paint(Paint.ANTI_ALIAS_FLAG);
	private final Paint paint_text = new Paint(Paint.ANTI_ALIAS_FLAG);
	private final Paint paint_text_left = new Paint(Paint.ANTI_ALIAS_FLAG);
	private final Paint paint_text_right = new Paint(Paint.ANTI_ALIAS_FLAG);
	private pitchDrawHud hudPitch;
	private fixedDrawHud hudFixed;
	private rollDrawHud hudRoll;
	private yawDrawHud hudYaw;
	
	public CtrlHud(Context context, ViewGroup views)
	{
		this.viewsGroup = views;
		
		this.paint_line.setColor(Color.WHITE);
		this.paint_line.setStyle(Paint.Style.FILL_AND_STROKE);
		this.paint_line.setAntiAlias(true);
		this.paint_line.setStrokeWidth(4);
		
		this.paint_text.setColor(Color.WHITE);
		this.paint_text.setTextAlign(Align.CENTER);
		this.paint_text.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
		this.paint_text.setStyle(Paint.Style.FILL_AND_STROKE);
		this.paint_text.setAntiAlias(true);
		this.paint_text.setTextSize(35);

		this.paint_text_left.setColor(Color.WHITE);
		this.paint_text_left.setTextAlign(Align.LEFT);
		this.paint_text_left.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
		this.paint_text_left.setStyle(Paint.Style.FILL_AND_STROKE);
		this.paint_text_left.setAntiAlias(true);
		this.paint_text_left.setTextSize(30);	
		
		this.paint_text_right.setColor(Color.WHITE);
		this.paint_text_right.setTextAlign(Align.RIGHT);
		this.paint_text_right.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
		this.paint_text_right.setStyle(Paint.Style.FILL_AND_STROKE);
		this.paint_text_right.setAntiAlias(true);
		this.paint_text_right.setTextSize(30);	
		
		this.hudPitch = new pitchDrawHud(context);
		this.viewsGroup.addView(hudPitch);
		
		this.hudFixed = new fixedDrawHud(context);
		this.viewsGroup.addView(hudFixed);

		this.hudRoll = new rollDrawHud(context);
		this.viewsGroup.addView(hudRoll);
				
		this.hudYaw = new yawDrawHud(context);
		this.viewsGroup.addView(hudYaw);	
	}

	double fGccX = 0;
	double fGccY = 0;
	double fGccZ = 0;
	double fGyrX = 0;
	double fGyrY = 0;
	double fGyrZ = 0;
	double alpha = 0.5f;
	
	public void setHudPitch(float accX, float accY, float accZ, float gyrX, float gyrY, float gyrZ)
	{
		
		double degressPitch = 0;
		double degressRoll = 0;
		
		fGccX = (accX * alpha + (fGccX * (1.0 - alpha)));
		fGccY = (accY * alpha + (fGccY * (1.0 - alpha)));
		fGccZ = (accZ * alpha + (fGccZ * (1.0 - alpha)));
	
		degressRoll  = (Math.atan2(-fGccY, fGccZ) * 180.0) / Math.PI;
		degressPitch = (Math.atan2(fGccX, Math.sqrt(fGccY * fGccY + fGccZ * fGccZ)) * 180.0) / Math.PI;
	    
		Log.i("HudPitch", "accX=" + String.valueOf(accX) + ";accY=" + String.valueOf(accY) + ";accZ=" + String.valueOf(accZ) +  ";degressPitch=" + String.valueOf(degressPitch));
				
		this.hudPitch.setPitch((float)degressPitch, (float)degressRoll); 		
	}
	
	public void setHudRoll(float accX, float accY, float accZ, float gyrX, float gyrY, float gyrZ)
	{
		double degressRoll = 0;

		fGccX = (accX * alpha + (fGccX * (1.0 - alpha)));
		fGccY = (accY * alpha + (fGccY * (1.0 - alpha)));
		fGccZ = (accZ * alpha + (fGccZ * (1.0 - alpha)));
	    
		degressRoll  = (Math.atan2(-fGccY, fGccZ) * 180.0) / Math.PI;
		
		Log.i("HudRoll", "accX=" + String.valueOf(accX) + ";accY=" + String.valueOf(accY) + ";accZ=" + String.valueOf(accZ) +  ";degressRoll=" + String.valueOf(degressRoll));
		
		this.hudRoll.setRoll((float)degressRoll);		
	}	
	
	public void setHudYaw(float accX, float accY, float accZ, float gyrX, float gyrY, float gyrZ)
	{	
		double degressYaw = 0;
		
		degressYaw = Math.atan2(gyrY, gyrX);
		
		if(degressYaw < 0)
			degressYaw += 2 * Math.PI;
		
		degressYaw = (degressYaw * 180 / Math.PI);
		
		//Log.i("HudYaw", "gyrY=" + String.valueOf(gyrY) + ";gyrX=" + String.valueOf(gyrY)  + ";degressYaw=" + String.valueOf(degressYaw));
		
		this.hudYaw.setYaw((float)degressYaw);
	}
	
	// Y => Yaw (Guinada)
	public class yawDrawHud extends View {
		private float posicao_inicial = -750;
		private float yaw = posicao_inicial;
		
		public yawDrawHud(Context context) {
			super(context);
			this.setLayoutParams(new LayoutParams(100, 30));
			this.setX((viewsGroup.getLayoutParams().width / 2)-50);
		}
				
		@Override
		public void onDraw(Canvas canvas) {	
			super.onDraw(canvas);
			float posicao_y = this.yaw;
			String letra = "N";
			
			for(int i = 0; i <= 14; i++)
			{
				posicao_y = posicao_y + 25;
				canvas.drawLine(posicao_y, 15, posicao_y, 30, paint_line); // .

				posicao_y = posicao_y + 25;
				canvas.drawLine(posicao_y, 5, posicao_y, 30, paint_line); // |

				posicao_y = posicao_y + 25;
				canvas.drawLine(posicao_y, 15, posicao_y, 30, paint_line); // .

				posicao_y = posicao_y + 25;

				// .|.E.|.S.|.W.|.N.|
				// .|.L.|.S.|.O.|.N.|				
				if (letra == "E")
					letra = "S";
				else if (letra == "S")
					letra = "W";
				else if (letra == "W")
					letra = "N";
				else if (letra == "N")
					letra = "E";

				canvas.drawText(letra, posicao_y, 30, paint_text);
			}
			invalidate();
		}		
	
		public void setYaw(float degressYaw)
		{
			float f = 360;
			float t = 400;
			float m = degressYaw;
			float g = m * t / f;
			
			this.yaw = g + this.posicao_inicial;
			
            this.drawableStateChanged();
		}
	}
	
	public class fixedDrawHud extends View {
		public fixedDrawHud(Context context) {
			super(context);
			this.setLayoutParams(new LayoutParams(viewsGroup.getLayoutParams().width, viewsGroup.getLayoutParams().height - 30));			
			this.setY(30);
		}
		
		@Override
		public void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, 10, paint_line);
			
			int grau = -45;
			
			for(int i = 0; i < 7; i++)
			{
				Canvas ast = canvas;
				ast.save();
				ast.rotate(grau, (this.getWidth() / 2), (this.getHeight() / 2));
				
				ast.drawLine((this.getWidth() / 2) - 10, 10, (this.getWidth() / 2), 10 + 20,
						paint_line);
				ast.drawLine((this.getWidth() / 2), 10 + 20, (this.getWidth() / 2) + 10, 10,
						paint_line);
				
				ast.restore();				
				grau = grau + 15;
			}
			
			invalidate();
		}
	}
	
	// Z => Roll (Rotação)
	public class rollDrawHud extends View {
		public rollDrawHud(Context context) {
			super(context);
			this.setLayoutParams(new LayoutParams(viewsGroup.getLayoutParams().width - 80, viewsGroup.getLayoutParams().height - 100));
			this.setX((viewsGroup.getLayoutParams().width - this.getLayoutParams().width) / 2);
			this.setY(((viewsGroup.getLayoutParams().height - this.getLayoutParams().height) / 2) + 15);
		}
		
		@Override
		public void onDraw(Canvas canvas) {	
			super.onDraw(canvas);
			
			canvas.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, 25, paint_line);
			
			canvas.drawLine((this.getWidth() / 2) - 10, (this.getHeight() / 2), (this.getWidth() / 2), (this.getHeight() / 2) + 10, paint_line);
			canvas.drawLine((this.getWidth() / 2) + 10, (this.getHeight() / 2), (this.getWidth() / 2) + 20, (this.getHeight() / 2), paint_line);
			canvas.drawLine((this.getWidth() / 2) - 20, (this.getHeight() / 2), (this.getWidth() / 2) - 10, (this.getHeight() / 2), paint_line);
			canvas.drawLine((this.getWidth() / 2) + 10, (this.getHeight() / 2), (this.getWidth() / 2), (this.getHeight() / 2) + 10, paint_line);
			
			invalidate();
		}
		
		public void setRoll(float degressRoll)
		{
			float f = 360;
			float t = 360;
			float m = degressRoll;
			float g = m * t / f;
			this.setRotation(g);
			
			this.drawableStateChanged();
		}
	}
	
	// X => Pitch (Inclinação)
	public class pitchDrawHud extends View {
		
		private float posicao_inicial = 125;
		private float pitch = posicao_inicial;
		
		public pitchDrawHud(Context context) {
			super(context);
			this.setLayoutParams(new LayoutParams(viewsGroup.getLayoutParams().width - 180, viewsGroup.getLayoutParams().height - 200));
			this.setX((viewsGroup.getLayoutParams().width - (viewsGroup.getLayoutParams().width - 180)) / 2);
			this.setY(((viewsGroup.getLayoutParams().height - (viewsGroup.getLayoutParams().height - 200)) / 2) + 15);
		}

		@Override
		public void onDraw(Canvas canvas) {	
			super.onDraw(canvas);
			
			Boolean alternar = false;
			Boolean inverter = false;
			
			float posicao_x = this.pitch;

			canvas.drawLine((this.getWidth() / 2) - 25, posicao_x, (this.getWidth() / 2) - (this.getWidth() / 2), posicao_x, paint_line);
			canvas.drawLine((this.getWidth() / 2) + 25, posicao_x, (this.getWidth() / 2) + (this.getWidth() / 2), posicao_x, paint_line);
			int angulo = 0;
			
			for (int i = 0; i <= 28; i++)
			{
				if (alternar)
				{
					canvas.drawLine((this.getWidth() / 2) - 25, posicao_x - (i * 25), (this.getWidth() / 2) - 35, posicao_x - (i * 25), paint_line);
					canvas.drawLine((this.getWidth() / 2) + 25, posicao_x - (i * 25), (this.getWidth() / 2) + 35, posicao_x - (i * 25), paint_line);

					canvas.drawLine((this.getWidth() / 2) - 25, posicao_x + (i * 25), (this.getWidth() / 2) - 35, posicao_x + (i * 25), paint_line);
					canvas.drawLine((this.getWidth() / 2) + 25, posicao_x + (i * 25), (this.getWidth() / 2) + 35, posicao_x + (i * 25), paint_line);
				}
				else
				{
					canvas.drawLine((this.getWidth() / 2) - 25, posicao_x - (i * 25), (this.getWidth() / 2) - 45, posicao_x - (i * 25), paint_line);
					canvas.drawLine((this.getWidth() / 2) + 25, posicao_x - (i * 25), (this.getWidth() / 2) + 45, posicao_x - (i * 25), paint_line);
					
					canvas.drawLine((this.getWidth() / 2) - 25, posicao_x + (i * 25), (this.getWidth() / 2) - 45, posicao_x + (i * 25), paint_line);
					canvas.drawLine((this.getWidth() / 2) + 25, posicao_x + (i * 25), (this.getWidth() / 2) + 45, posicao_x + (i * 25), paint_line);
					
					if(angulo != 0 && angulo != 180 && angulo != -180)
					{
						canvas.drawText(String.valueOf(angulo * -1), (this.getWidth() / 2) + 50, posicao_x + ((i * 25) + 10), paint_text_left);
						canvas.drawText(String.valueOf(angulo * -1), (this.getWidth() / 2) - 50, posicao_x + ((i * 25) + 10), paint_text_right);
						
						canvas.drawText(String.valueOf(angulo), (this.getWidth() / 2) + 50, posicao_x - ((i * 25) - 10), paint_text_left);
						canvas.drawText(String.valueOf(angulo), (this.getWidth() / 2) - 50, posicao_x - ((i * 25) - 10), paint_text_right);
					} 
					
					if(angulo == 0 || angulo == 180 || angulo == -180)
					{
						canvas.drawLine((this.getWidth() / 2) - 25, posicao_x - (i * 25), (this.getWidth() / 2) - (this.getWidth() / 2), posicao_x - (i * 25), paint_line);
						canvas.drawLine((this.getWidth() / 2) + 25, posicao_x - (i * 25), (this.getWidth() / 2) + (this.getWidth() / 2), posicao_x - (i * 25), paint_line);
						
						canvas.drawLine((this.getWidth() / 2) - 25, posicao_x + (i * 25), (this.getWidth() / 2) - (this.getWidth() / 2), posicao_x + (i * 25), paint_line);
						canvas.drawLine((this.getWidth() / 2) + 25, posicao_x + (i * 25), (this.getWidth() / 2) + (this.getWidth() / 2), posicao_x + (i * 25), paint_line);
						
						if(angulo != 0)
						{
							angulo = angulo * -1;
							inverter = !inverter;
						}
					}
					
					angulo = angulo + 45;
				}

				alternar = !alternar;
			}
			invalidate();
		}
		
		public void setPitch(float degressPitch, float degressRoll)
		{			
            float fx = 360;
			float tx = 360;
			float mx = degressRoll;
			float gx = mx * tx / fx;
			this.setRotation(gx);

            float fy = 180;
  			float ty = 400;
  			float my = degressPitch;
  			float gy = my * ty / fy;
            this.pitch = this.posicao_inicial + gy;
            
            this.drawableStateChanged();
		}		
	}
	
	
}