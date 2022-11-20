#include <Arduino.h> 
#include <Wire.h>

class HC21
{
	public:
		HC21();
		void init(HardwareSerial *param);
		String Receiver();
		void Sender(String str);
	private:
		HardwareSerial *hardwareSerial;
		int LED_SENDER;
		int LED_RECEIVER;
		void writeReg();	
};

