DESCRIPTION = "Linux Image for Light FM with Test tools"
LICENSE = "MIT"

inherit thead-image-linux thead-image-weston thead-image-test

python do_rootfs:append:riscv64 () {
    import subprocess

    srcdir=d.getVar('IMAGE_ROOTFS')

    cmd = 'rm -rf %s/boot/* ' % (srcdir)
    subprocess.check_call(cmd, shell=True)
}

export IMAGE_BASENAME = "thead-image-linux-test"
