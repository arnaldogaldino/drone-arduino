/**************************************************
 * Arnaldo Galdino de Oliveira                    *
 * Data: 12/02/2015                               *
 * Shield: HC-21 (WIFI)                           *
 **************************************************
 * 08 - GND       8 . . 4                         *
 * 07 - 19 RX1    7 . . 3                         *
 * 06 - 18 TX1    6 . . 2                         *
 * 05 - VCC 3.3v  5 . . 1                         *
 **************************************************
 * Gyroscope: HMC5883L-GY-273                     *
 **************************************************
 * 01 - VCC  - VCC 3.3V (Alimentação)             *
 * 02 - GND  - GND (Alimentação)                  *
 * 03 - SCL  - SCL Dado                           *
 * 04 - SDA  - SDA Dado                           *
 * 05 - DRDY - DRDY Dado                          *
 **************************************************
 * Address                                        *
 **************************************************
 * Wire address 0x1D = Accelerometer -> MMA7455   *
 * Wire address 0x1E = Gyroscope     -> HMC5883L  *
 * Wire address 0x77 = Barometer     -> BMP085    *
 * Serial TX1 = Wifi = Wifi          -> HC21      *
 * Serial TX2 = GPS  = GPS           -> NEO6MV2   *
 **************************************************/
#include <Wire.h>
#include "HC21.h"
#include "HMC5883L.h"
#include <string.h>
#include <ctype.h>
#include <SoftwareSerial.h>
#include "TinyGPS.h"
#include "MMA7455.h"
#include "BMP085.h"

HC21 wifi;
HMC5883L gyroscope;
MMA7455 accelerometer;
BMP085 barometer;
TinyGPSPlus gps;

int error = 0;
int LED_RX = 8;         // 10 - azul
int LED_SENDER = 9;     // 9 - amarelo
int LED_RECEIVER = 10;  // 8 - branco

void setup() {
	Serial.begin(9600);
	Serial.println("Start Drone.");
	
	pinMode(LED_RX, OUTPUT);
	pinMode(LED_SENDER, OUTPUT);
	pinMode(LED_RECEIVER, OUTPUT);
	
	digitalWrite(LED_RX, LOW);
	digitalWrite(LED_SENDER, LOW);
	digitalWrite(LED_RECEIVER, LOW);

	wifi = HC21();
	Serial1.begin(115200); //115200
	wifi.init(&Serial1);

	gps = TinyGPSPlus();
	Serial2.begin(9600);
	updateGPS();

	Wire.begin();	
	accelerometer = MMA7455();
	accelerometer.initSensitivity(2);

	gyroscope = HMC5883L(); 
	error = gyroscope.SetScale(1.3); 
	if(error != 0) 
		Serial.println(gyroscope.GetErrorText(error));
	
	Serial.println("Setting measurement mode to continous.");
        error = gyroscope.SetMeasurementMode(Measurement_Continuous); 
	if(error != 0) 
		Serial.println(gyroscope.GetErrorText(error));

	barometer = BMP085();
	if(!barometer.begin()) {
		Serial.println("Barometer not starting...");
	}
}

void loop() {
	digitalWrite(LED_RX, HIGH);

	String strSender; 
	String strReceiver = wifi.Receiver();
	String strCommand = Slipt(Slipt(strReceiver, ';', 0), '=', 1);

	if(strCommand == "ctrl") {
		strSender = getCommandCtrl();
	} else if (strCommand == "gyroscope_calibrate") {		
		strSender = getCommandCtrl();
                CalibararAccelerometer();
	}

        strSender = getCommandCtrl();
        Serial.println(strSender);
        
	wifi.Sender(strSender);	
	updateGPS();
	digitalWrite(LED_RX, LOW);	
        delay(10);
}

String getCommandCtrl() {
	GyroscopeScaled gyroscopeScaled = gyroscope.ReadScaledAxis();
	AccelerometerScaled accelerometerScaled = accelerometer.ReadScaledAxis();

/*
	String result = "comm=ctrl";
	result += (";AccX="     + String(accelerometerScaled.XAxis));
	result += (";AccY="     + String(accelerometerScaled.YAxis));
	result += (";AccZ="     + String(accelerometerScaled.ZAxis));
	result += (";GyrX="     + String(gyroscopeScaled.XAxis));
	result += (";GyrY="     + String(gyroscopeScaled.YAxis));
	result += (";GyrZ="     + String(gyroscopeScaled.ZAxis));
	result += (";BarTemp="  + String(barometer.readTemperature()));
	result += (";BarPres="  + String(barometer.readPressure()));
	result += (";BarAtmo="  + String(barometer.readSealevelPressure()));
	result += (";BarAlt="   + String(barometer.readAltitude(101500)));
	result += (";GpsLocV="  + String(gps.location.isValid()));
	result += (";GpsLat="   + String(gps.location.lat(), 6));
	result += (";GpsLng="   + String(gps.location.lng(), 6));
	result += (";GpsAge="   + String(gps.location.age()));
	result += (";GpsSpdV="  + String(gps.speed.isValid()));
	result += (";GpsKmph="  + String(gps.speed.kmph(), 6));
	result += (";GpsSatsV=" + String(gps.satellites.isValid()));
	result += (";GpsSats="  + String(gps.satellites.value()));
	result += (";GpsDateTimeV=" + String(gps.date.isValid()));
	result += (";GpsDateTime="  + String(gps.date.day()) + "/" + String(gps.date.month()) + "/" + String(gps.date.year()) + " " + String(gps.time.hour()) + ":" + String(gps.time.minute()) + ":" + String(gps.time.second()));
	result += (";Success=true");
*/

	String result = "co=ctrl"; 
	result += (";AX="     + String(accelerometerScaled.XAxis));
	result += (";AY="     + String(accelerometerScaled.YAxis));
	result += (";AZ="     + String(accelerometerScaled.ZAxis));
	result += (";GX="     + String(gyroscopeScaled.XAxis));
	result += (";GY="     + String(gyroscopeScaled.YAxis));
	result += (";GZ="     + String(gyroscopeScaled.ZAxis));
	result += (";BTemp="  + String(barometer.readTemperature()));
	result += (";BPres="  + String(barometer.readPressure()));
	result += (";BAtmo="  + String(barometer.readSealevelPressure()));
	result += (";BAlt="   + String(barometer.readAltitude(101500)));
	result += (";GLocV="  + String(gps.location.isValid()));
	result += (";GLat="   + String(gps.location.lat(), 6));
	result += (";GLng="   + String(gps.location.lng(), 6));
	result += (";GAge="   + String(gps.location.age()));
	result += (";GSpdV="  + String(gps.speed.isValid()));
	result += (";GKmph="  + String(gps.speed.kmph(), 6));
	result += (";GSatsV=" + String(gps.satellites.isValid()));
	result += (";GSats="  + String(gps.satellites.value()));
	result += (";GDteV=" + String(gps.date.isValid()));
	result += (";GDte="  + String(gps.date.day()) + "/" + String(gps.date.month()) + "/" + String(gps.date.year()) + " " + String(gps.time.hour()) + ":" + String(gps.time.minute()) + ":" + String(gps.time.second()));
	result += (";Suc=true");
	return result;
}

String Slipt(String str, char separator, int idx) {
	int found = 0;
	int strIdx[] = {0, -1};
	int maxIdx = str.length()-1;

	for(int i = 0; i <= maxIdx && found <= idx; i++) {
		if(str.charAt(i) == separator || i == maxIdx) {
			found++;
			strIdx[0] = strIdx[1]+1;
			strIdx[1] = (i == maxIdx)?i+1:i;
		}
	}
	return found>idx?str.substring(strIdx[0], strIdx[1]) : "";
}

void CalibararAccelerometer() {
Serial.println("Calibando Accelerometer...");
    digitalWrite(LED_RX, LOW);
    
    float x_axis_offset = 0;
    float y_axis_offset = 0;
    float z_axis_offset = 0;
    
    for(int i = 0; i <= 1000; i++)
    {
      digitalWrite(LED_RX, HIGH);
      AccelerometerScaled accelerometerScaled = accelerometer.ReadScaledAxis();
      x_axis_offset += accelerometerScaled.XAxis;
      y_axis_offset += accelerometerScaled.YAxis;
      z_axis_offset += accelerometerScaled.ZAxis;
      digitalWrite(LED_RX, LOW);
      delay(1);
    }
    
    x_axis_offset = ((x_axis_offset / 1000) * -1) ;
    y_axis_offset = ((y_axis_offset / 1000) * -1) ;
    z_axis_offset = ((z_axis_offset / 1000) * -1) ;
        
    accelerometer.calibrateOffset(x_axis_offset, y_axis_offset, z_axis_offset);
    
    Serial.print(String(x_axis_offset) + ";\t");
    Serial.print(String(y_axis_offset) + ";\t");
    Serial.print(String(z_axis_offset));
    Serial.println();

}

void getpitchroll(double ax, double ay, double az, double *pitch, double *roll) {
	double magnitude = sqrt((ax * ax) + (ay * ay) + (az * az));
	ax = ax / magnitude;
	ay = ay / magnitude;
	az = az / magnitude;
	*pitch = -atan2(ax, sqrt(pow(ay,2) + pow(az, 2)));
	*roll = atan2(ay, az);
}

void updateGPS() {
    String result;
	while (Serial2.available() > 0) {
		char msg;
		msg = Serial2.read();
		result += msg;		
                gps.encode(msg);
	}
	Serial2.flush();
}
