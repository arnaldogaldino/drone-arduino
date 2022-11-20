#include <Arduino.h> 
#include "HMC5883L.h"

GyroscopeScaled offSet;

HMC5883L::HMC5883L()
{
	m_Scale = 1;
	offSet.XAxis = 0;
	offSet.ZAxis = 0;
	offSet.YAxis = 0;
}

GyroscopeRaw HMC5883L::ReadRawAxis()
{
	uint8_t* buffer = Read(DataRegisterBegin, 6);
	GyroscopeRaw raw = GyroscopeRaw();
	raw.XAxis = ((buffer[0] << 8) | buffer[1]) + _x_axis_offset;
	raw.YAxis = ((buffer[4] << 8) | buffer[5]) + _y_axis_offset;
	raw.ZAxis = ((buffer[2] << 8) | buffer[3]) + _z_axis_offset;	
	return raw;
}

void HMC5883L::calibrateOffset(float x_axis_offset, float y_axis_offset, float z_axis_offset)
{
	_x_axis_offset = x_axis_offset;
	_y_axis_offset = y_axis_offset;
	_z_axis_offset = z_axis_offset;
}

GyroscopeScaled HMC5883L::ReadScaledAxis()
{
	GyroscopeScaled scaled = GyroscopeScaled();

		GyroscopeRaw raw = ReadRawAxis();	
		scaled.ZAxis += (raw.ZAxis) * m_Scale;
		scaled.XAxis += (raw.XAxis) * m_Scale;
		scaled.YAxis += (raw.YAxis) * m_Scale;

	return scaled;
}

int HMC5883L::SetScale(float gauss)
{
	uint8_t regValue = 0x00;
	if(gauss == 0.88)
	{
		regValue = 0x00;
		m_Scale = 0.73;
	}
	else if(gauss == 1.3)
	{
		regValue = 0x01;
		m_Scale = 0.92;
	}
	else if(gauss == 1.9)
	{
		regValue = 0x02;
		m_Scale = 1.22;
	}
	else if(gauss == 2.5)
	{
		regValue = 0x03;
		m_Scale = 1.52;
	}
	else if(gauss == 4.0)
	{
		regValue = 0x04;
		m_Scale = 2.27;
	}
	else if(gauss == 4.7)
	{
		regValue = 0x05;
		m_Scale = 2.56;
	}
	else if(gauss == 5.6)
	{
		regValue = 0x06;
		m_Scale = 3.03;
	}
	else if(gauss == 8.1)
	{
		regValue = 0x07;
		m_Scale = 4.35;
	}
	else return ErrorCode_1_Num;
	
	// Setting is in the top 3 bits of the register.
	regValue = regValue << 5;
	Write(ConfigurationRegisterB, regValue);
}

int HMC5883L::SetMeasurementMode(uint8_t mode)
{
	Write(ModeRegister, mode);
}

void HMC5883L::Write(int address, int data)
{
	Wire.beginTransmission(HMC5883L_Address);
	Wire.write(address);
	Wire.write(data);
	Wire.endTransmission();
}

uint8_t* HMC5883L::Read(int address, int length)
{
	Wire.beginTransmission(HMC5883L_Address);
	Wire.write(address);
	Wire.endTransmission();
	
	Wire.beginTransmission(HMC5883L_Address);
	Wire.requestFrom(HMC5883L_Address, length);
	
	uint8_t buffer[length];
	if(Wire.available() == length)
	{
		for(uint8_t i = 0; i < length; i++)
		{
			buffer[i] = Wire.read();
		}
	}
	Wire.endTransmission();
	
	return buffer;
}

char* HMC5883L::GetErrorText(int errorCode)
{
	if(errorCode == 1) return ErrorCode_1;
	return "Error not defined";
}
