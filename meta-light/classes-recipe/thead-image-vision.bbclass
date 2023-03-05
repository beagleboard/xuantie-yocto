IMAGE_INSTALL += " npu-ax3386-gpl "
IMAGE_INSTALL += " ffmpeg openh264 util-linux zeromq protobuf rapidjson syslog-ng libc-mtrace "

# disable below packages for public release
IMAGE_INSTALL += " libcsi-g2d thead-fce csi-hal-vcodec npu-ax3386 "
IMAGE_INSTALL += " tmedia visual-ai "

inherit core-image
