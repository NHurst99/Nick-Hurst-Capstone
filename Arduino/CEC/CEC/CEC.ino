
#include <power_mgt.h>
#include <platforms.h>
#include <pixeltypes.h>
#include <pixelset.h>
#include <noise.h>
#include <lib8tion.h>
#include <led_sysdefs.h>
#include <hsv2rgb.h>
#include <fastspi_types.h>
#include <fastspi_ref.h>
#include <fastspi_nop.h>
#include <fastspi_dma.h>
#include <fastspi_bitbang.h>
#include <fastspi.h>
#include <fastpin.h>
#include <fastled_progmem.h>
#include <fastled_delay.h>
#include <fastled_config.h>
#include <FastLED.h>
#include <dmx.h>
#include <cpp_compat.h>
#include <controller.h>
#include <colorutils.h>
#include <colorpalettes.h>
#include <color.h>
#include <chipsets.h>
#include <bitswap.h>
#include <CEC.h>
#include <CEC_Device.h>
#include <CEC_Electrical.h>
#include <Common.h>

char **command;      // a String to hold incoming data
boolean stringComplete = false;  // whether the string is complete
String inputString = "";         // a String to hold incoming data
char str[48];;
int intArray[16]; // Maximum number of hex numbers.

void setup() {
	// initialize serial:
	Serial.begin(9600);

	// reserve 200 bytes for the  str.reserve(200);
	inputString.reserve(200);
}

void loop() {

	// print the string when a newline arrives:
	if (stringComplete) {

		// clear the string:
		//Serial.println(F(inputString));
		inputString.toCharArray(str, inputString.length(), 0);
		int counter = 0;
		char * pch = strtok(str, ":");

		while (pch != NULL) {
			intArray[counter] = strtol(pch, NULL, 16);
			pch = strtok(NULL, ":");
			counter++;
			//Serial.println(pch);
		}
		Serial.print("Item Count = ");
		Serial.println(counter);

		for (int i = 0; i < counter; i++) {
			Serial.println(intArray[i]);
		}
		stringComplete = false;
	}
}

/*
  SerialEvent occurs whenever a new data comes in the hardware serial RX. This
  routine is run between each time loop() runs, so using delay inside loop can
  delay response. Multiple bytes of data may be available.
*/

void serialEvent() {
	while (Serial.available()) {
		// get the new byte:
		//char inChar = (char)Serial.read();
		inputString = Serial.readStringUntil('\n');
		// add it to the inputString:
		// if the incoming character is a newline, set a flag so the main loop can
		// do something about it:

		//if (inChar == '\n') {
		stringComplete = true;
		//  Serial.println("String completed. String is \""+inputString+"\"");
		//}else{

		//inputString += inChar;
		//  Serial.println(inputString);
	}
}





