#inherit light bsp related packages
inherit thead-image-bsp
#inherit grapic related packages
# inherit thead-image-weston
#inherit security related packages
inherit thead-image-security
#inherit vidio related packages, disable for public release
#inherit thead-image-vision
#inherit debug related packages
inherit thead-image-debug

#WIFI & BT related packages
IMAGE_INSTALL += " wpa-supplicant bluez5 light-bt "

#Android tools e.g. adb etc.
IMAGE_INSTALL += " android-tools android-tools-conf "

#debug tools, e.g. collecting crash image for analyzing etc.
IMAGE_INSTALL += " kdump procps "

#multi-media related support
IMAGE_INSTALL += " alsa-utils alsa-lib alsa-tools pulseaudio-server "
IMAGE_INSTALL += " ffmpeg "

# produce utils
IMAGE_INSTALL += " prd-utils "
IMAGE_INSTALL += " sqlite3 "

# enable for public release
IMAGE_INSTALL:remove = "csi-hal-vcodec rambus-os-ik-150 gpu-bxm-4-64 npu-ax3386 thead-fce "
IMAGE_INSTALL:remove = "thead-ddr-pmu isp-isp8000l libgal-viv libcsi-g2d vpu-omxil "
IMAGE_INSTALL += " image-proprietary "

# BeagleBoard.org Release stuff
IMAGE_INSTALL += " cockpit libgpiod nano "

#IMAGE_FSTYPES:remove = "cpio.gz cpio cpio.gz.u-boot cpio.bz2"
