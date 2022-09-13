DESCRIPTION = "A demo image for light fm commerical image"

require light-fm-image-bsp.bb
require light-fm-image-security.bb

# // for public release only, disabled by default for internal release
IMAGE_INSTALL += " image-proprietary "
IMAGE_INSTALL_remove += "csi-hal-vcodec rambus-os-ik-150 gpu-bxm-4-64 npu-ax3386 thead-fce "
IMAGE_INSTALL_remove += "thead-ddr-pmu isp-isp8000l libgal-viv libcsi-g2d vpu-omxil "

export IMAGE_BASENAME = "light-fm-image-ant"
