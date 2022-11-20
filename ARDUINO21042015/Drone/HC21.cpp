#include <Arduino.h> 
#include "HC21.h"

HC21::HC21() {
  LED_SENDER = 9;
  LED_RECEIVER = 10;
}

void HC21::init(HardwareSerial *param) {
	hardwareSerial = param;
}

String HC21::Receiver() {
	String result;
        digitalWrite(LED_RECEIVER, HIGH);
	while (hardwareSerial->available() > 0) {
		char msg;
		msg = hardwareSerial->read();
		result += msg;	
	}
	digitalWrite(LED_RECEIVER, LOW);
	hardwareSerial->flush();
	return result;
}

void HC21::Sender(String str) {
	digitalWrite(LED_SENDER, HIGH);
	int sizeBuf = 1024;
	char charBuf[sizeBuf];
        str = str + "\n";        
	str.toCharArray(charBuf, sizeBuf);
	hardwareSerial->write(charBuf);
	hardwareSerial->flush();
	digitalWrite(LED_SENDER, LOW);
}
