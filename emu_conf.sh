#!/usr/bin/env bash

echo "PlayStore.enabled=false\nabi.type=x86\navd.ini.encoding=UTF-8\nhw.accelerometer=yes\nhw.audioInput=yes\nhw.battery=yes\nhw.cpu.arch=x86\nhw.dPad=no\nhw.device.manufacturer=Google\nhw.device.name=Nexus 4\nhw.gps=yes\nhw.lcd.density=420\nhw.lcd.height=1920\nhw.lcd.width=1080\nhw.mainKeys=no\nhw.sdCard=yes\nhw.sensors.orientation=yes\nhw.sensors.proximity=yes\nhw.trackBall=no\nimage.sysdir.1=system-images/android-29/google_apis/x86/\nsdcard.size=512G\ntag.display=Google APIs\ntag.id=google_apis" > ~/.android/avd/emu.avd/config.ini


