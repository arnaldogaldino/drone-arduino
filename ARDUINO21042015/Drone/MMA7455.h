// MMA7455.h - 3 Axis Accelerometer Library
// Moritz Kemper, IAD Physical Computing Lab
// moritz.kemper@zhdk.ch
// ZHdK, 03/04/2012
// Released under Creative Commons Licence

#ifndef MMA7455_h
#define MMA7455_h

#include "Arduino.h"
#include "Wire.h"

struct AccelerometerScaled
{
	float XAxis;
	float YAxis;
	float ZAxis;
};

class MMA7455
{
  public:
    MMA7455();
    void initSensitivity(int sensitivity);
    void calibrateOffset(float x_axis_offset, float y_axis_offset, float z_axis_offset);
    unsigned char readAxis(char axis);
	AccelerometerScaled ReadScaledAxis();
  private:
    unsigned char _buffer;
	float _x_axis_offset;
	float _y_axis_offset;
	float _z_axis_offset;
};

#endif
