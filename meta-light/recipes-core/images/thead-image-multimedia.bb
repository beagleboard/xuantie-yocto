DESCRIPTION = "multimedia Image for Light FM, including gnome destop and gsteamer etc."
LICENSE = "MIT"

inherit thead-image-linux thead-image-weston thead-image-gstreamer

IMAGE_INSTALL += " gstreamer1.0-libav "

python do_rootfs:append:riscv64 () {
    import subprocess

    srcdir=d.getVar('IMAGE_ROOTFS')

    cmd = 'rm -rf %s/boot/* ' % (srcdir)
    subprocess.check_call(cmd, shell=True)
}

IMAGE_FSTYPES:remove = "cpio.gz cpio cpio.gz.u-boot cpio.bz2"
export IMAGE_BASENAME = "thead-image-multimedia"
