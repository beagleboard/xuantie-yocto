IMAGE_INSTALL += " \
    gdb \
    gdbserver \
    strace \
    glibc-mtrace \
    trace-cmd \
    memtool \
    kmod \
    binutils \
"

IMAGE_FEATURES += "ssh-server-openssh tools-debug dev-pkgs debug-tweaks"

inherit core-image