IMAGE_INSTALL += " \
    weston \
    gtk+3 \
    gpgme \
    gnupg \
    gpu-bxm-4-64 \
    gpu-bxm-4-64-gpl \
    haveged \
    util-linux \
    gfx-examples \
    tiff \
    skia \
    source-han-sans-cn-fonts \
"

inherit core-image features_check

REQUIRED_DISTRO_FEATURES = "wayland opengl vulkan opencl"
IMAGE_FEATURES += "dev-pkgs ssh-server-openssh"
DISTRO_FEATURES:remove = " x11"
PREFERERRED_VERSION_llvm = "10.0.2"

#IMAGE_ROOTFS_EXTRA_SPACE = "10240"
SYSTEMD_DEFAULT_TARGET = "graphical.target"
