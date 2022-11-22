DESCRIPTION = "This image includes debug tools."
LICENSE = "MIT"

IMAGE_INSTALL += "gdb \
    strace \
    glibc-mtrace \
    trace-cmd \
    memtool \
    kmod \
    binutils \
"

python do_rootfs_append_riscv64 () {
    import subprocess

    srcdir=d.getVar('IMAGE_ROOTFS')

    cmd = 'rm -rf %s/boot/* ' % (srcdir)
    subprocess.check_call(cmd, shell=True)
}

IMAGE_FEATURES += "ssh-server-openssh tools-debug dev-pkgs debug-tweaks"

export IMAGE_BASENAME = "light-fm-image-debug"

inherit core-image