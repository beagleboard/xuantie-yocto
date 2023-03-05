IMAGE_INSTALL += " packagegroup-core-boot kernel-modules "
IMAGE_INSTALL += " resizefs initscripts-readonly-rootfs-overlay "
IMAGE_INSTALL += " tzdata "
IMAGE_INSTALL += " glibc-gconv-gbk "

IMAGE_LINGUAS += "zh-cn zh-cn.gb2312 zh-cn.gbk"

DISTRO_FEATURES:remove = " ipv6 pcmcia usbgadget usbhost pci 3g nfc vfat "

inherit core-image
