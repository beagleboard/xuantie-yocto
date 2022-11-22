DESCRIPTION = "This image includes light extend debs."

LICENSE = "MIT"

IMAGE_INSTALL += " python3 python3-numpy python3-pip python3-psutil python3-ruamel-yaml python3-pytest python3-lxml "


python do_rootfs_append_riscv64 () {
    import subprocess

    srcdir=d.getVar('IMAGE_ROOTFS')

    cmd = 'rm -rf %s/boot/* ' % (srcdir)
    subprocess.check_call(cmd, shell=True)
}

#DISTRO_FEATURES_remove = " ipv6 pcmcia usbgadget usbhost pci 3g nfc vfat "


export IMAGE_BASENAME = "light-extend-debs"

inherit core-image
