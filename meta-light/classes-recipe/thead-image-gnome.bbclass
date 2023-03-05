IMAGE_INSTALL += " \
    packagegroup-core-x11 \
    packagegroup-core-x11-base \
    packagegroup-core-x11-xserver \
    packagegroup-gnome-desktop3 \
    glmark2 \
    mixbench \
    net-tools \
    alsa-utils \
    gpgme \
    gnupg \
    gpu-bxm-4-64 \
    gpu-bxm-4-64-gpl \
    utouch-evemu \
    kmscube \
    haveged \
    gnome-settings-daemon \
    packagegroup-gnome-apps3-dev \
    packagegroup-gnome-desktop3-dev \
"
# gnome-shell_42.0 need install
# IMAGE_INSTALL += "gnome-shell-gsettings"

# remove "vkmark \" after glmark2 because build error, to fix it,
# /usr/include/vulkan/vulkan.hpp should exists on build host,
# and fix it by install libvulkan-dev packet on host.

inherit core-image features_check

IMAGE_FEATURES += "dev-pkgs ssh-server-openssh"

REQUIRED_DISTRO_FEATURES = "x11 wayland opengl vulkan opencl"
PREFERERRED_VERSION_llvm = "10.0.2"
# IMAGE_ROOTFS_EXTRA_SPACE = "10240"
SYSTEMD_DEFAULT_TARGET = "graphical.target"
