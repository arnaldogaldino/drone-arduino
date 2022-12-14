#ifndef HMC5883L_h
#define HMC5883L_h

#include <Arduino.h>
#include <Wire.h>

#define HMC5883L_Address 0x1E
#define ConfigurationRegisterA 0x00
#define ConfigurationRegisterB 0x01
#define ModeRegister 0x02
#define DataRegisterBegin 0x03

#define Measurement_Continuous 0x00
#define Measurement_SingleShot 0x01
#define Measurement_Idle 0x03

#define ErrorCode_1 "Entered scale was not valid, valid gauss values are: 0.88, 1.3, 1.9, 2.5, 4.0, 4.7, 5.6, 8.1"
#define ErrorCode_1_Num 1

struct GyroscopeScaled
{
	float XAxis;
	float YAxis;
	float ZAxis;
};

struct GyroscopeRaw
{
	int XAxis;
	int YAxis;
	int ZAxis;
};

class HMC5883L
{
	public:
		HMC5883L();	
		GyroscopeRaw ReadRawAxis();
		GyroscopeScaled ReadScaledAxis();
		GyroscopeScaled ReadDegreesAxis();
		int SetMeasurementMode(uint8_t mode);
		int SetScale(float gauss);
		char* GetErrorText(int errorCode);
                void calibrateOffset(float x_axis_offset, float y_axis_offset, float z_axis_offset);
	protected:
		void Write(int address, int byte);
		uint8_t* Read(int address, int length);
	private:
        	float _x_axis_offset;
        	float _y_axis_offset;
        	float _z_axis_offset;
		float m_Scale;
};
#endif
