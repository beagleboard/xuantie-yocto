DESCRIPTION = "Linux Image for Light FM"
LICENSE = "MIT"

inherit thead-image-linux thead-image-weston

python do_rootfs:append:riscv64 () {
    import subprocess

    srcdir=d.getVar('IMAGE_ROOTFS')

    cmd = 'rm -rf %s/boot/* ' % (srcdir)
    subprocess.check_call(cmd, shell=True)
}

export IMAGE_BASENAME = "thead-image-linux"
