# scat-app
Android application for signalling collection. 
Basically, it is automated version of the process described in [Quip document](https://private-tech.quip.com/XpEyAKfb7yBe/Collecting-UE-Signaling-Traffic-Using-DM-Tools)

## Prerequite 
### Rooted Device

To enable the DM loggig script on Pixel-7 device, the device should be rooted. See this [link](https://www.xda-developers.com/how-to-unlock-bootloader-root-magisk-google-pixel-7-pro/#how-to-root-the-google-pixel-7-and-7-pro) and [video](https://www.youtube.com/watch?v=6JQ8xrpW2iU&) for setting the rooted envrionment on Pixel 7.

Note that the current application has been tested only over Pixel-7. 

### Installing Logging script on the device

TODO: The following procedure would be included by application soon.

1. Download the necessary logging scripts from [Google Drive](https://drive.google.com/file/d/1kAUHO2SY7MwVUvjJDD21iNbdvqLXCxSb/view?usp=drive_link)
2. Use ADB to push the logging script tarball to the device sdcard.
    1. `adb push logger_scripts.tar.gz /sdcard/.`
3. Open a ADB shell to the device and navigate to the sdcard.
    1. `adb shell`
        `cd /sdcard`
4. Extract the logging_scripts tarball.
    1. `tar xfz logging_scripts.tar.gz`
5. Copy the modem directory from inside of extracted logging_script directory into /sdcard
    1. `mv /sdcard/logger_scripts/modem /sdcard`
6. Switch to root and navigate to the logging_scripts directory.
    1. `cd /sdcard/logging_scripts`

## SCAT-running web server
