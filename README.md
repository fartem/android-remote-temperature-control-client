# android-remote-temperature-control-client

Remote client for [Arduino temperature project](https://github.com/fartem/arduino-temperature-control).

## 1. Before install

__Step-by-step guide for run and use project:__

1. Install and run [Arduino project]((https://github.com/fartem/arduino-temperature-control));
2. Pair your Android device and Arduino Bluetooth module;
3. Install and run Android application and select Arduino Bluetooth module from devices list.

## 2. Application structure

### 2.1. MonitorBluetoothConnection

Class for control Bluetooth connection and notify about new Arduino Monitor data available.

Required [SignalTarget](https://github.com/fartem/android-remote-temperature-control-client/blob/master/app/src/main/java/com/smlnskgmail/jaman/remotetemperaturecontrol/entities/signaltype/SignalTarget.kt) to send new raw Arduino Monitor data.

### 2.2. TemperatureDataController

Provider between Monitor connection and [ControllerFragment](https://github.com/fartem/android-remote-temperature-control-client/blob/master/app/src/main/java/com/smlnskgmail/jaman/remotetemperaturecontrol/navigation/fragments/ControllerFragment.kt).

Required [ControllerTarget](https://github.com/fartem/android-remote-temperature-control-client/blob/master/app/src/main/java/com/smlnskgmail/jaman/remotetemperaturecontrol/monitor/controller/ControllerTarget.kt) for send formatted strings to [ControllerFragment](https://github.com/fartem/android-remote-temperature-control-client/blob/master/app/src/main/java/com/smlnskgmail/jaman/remotetemperaturecontrol/navigation/fragments/ControllerFragment.kt).

## 3. Screenshots

## 4. About