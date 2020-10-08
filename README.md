# Nick-Hurst-Capstone
Project for an Android app and Arduino code to control electronic devices via CEC.

## Android
The app has a few built in commands to control TVs and other devices like media players and game systems. It connects to the Arduino via HC-05 bluetooth seriel channel. 

## Arduino
The code is largely based off of the [CEC library by Floe](https://github.com/floe/CEC) but has been modified to use the Bluetooth HC-05 module to read one character commands and send that to a TV via CEC
