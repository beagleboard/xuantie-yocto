echo 1 > /sys/devices/platform/light-wdt/aon_sys_wdt
aon_wdt_app -d /dev/watchdog0 -t 33 -kalive 4
