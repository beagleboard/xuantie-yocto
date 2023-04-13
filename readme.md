# Use Ubuntu 20.04 (Debian 11.x issues..)

# Install

```
sudo apt install build-essential cmake chrpath diffstat gawk git git-lfs python3-distutils python-is-python3 rsync
```

# No Dash...
```
sudo dpkg-reconfigure dash
```

# Yocto

```
git clone -b Linux_SDK_V1.0.2-light-beagle git@git.beagleboard.org:beaglev-ahead/xuantie-yocto.git xuantie-yocto ; cd ./xuantie-yocto
```

# Configure Build

```
source openembedded-core/oe-init-build-env build/light-fm
```

# Copy mirrored downloads:

```
mkdir ../downloads ; rsync -av /mnt/yocto-cache/beaglev-ahead/Linux_SDK_V1.0.2/downloads/ ../downloads/
```

# Copy mirrored sstate-cache

```
mkdir ../sstate-cache ; rsync -av /mnt/yocto-cache/beaglev-ahead/Linux_SDK_V1.0.2/sstate-cache/ ../sstate-cache/
```

# Download missing files:

```
MACHINE=light-beagle bitbake light-fm-image-linux --runall=fetch
rsync -a ../downloads/ /mnt/yocto-cache/beaglev-ahead/Linux_SDK_V1.0.2/downloads/ --delete
```

# Start Build

```
MACHINE=light-beagle bitbake -k light-fm-image-linux
```

# Save cache

```
rsync -av ../sstate-cache/ /mnt/yocto-cache/beaglev-ahead/Linux_SDK_V1.0.2/sstate-cache/ --delete
```

# Options:
```
Common targets are:
    light-extend-debs
    riscv-initramfs-image
    light-fm-image-linux
    light-fm-image-weston
    light-fm-image-minimal
    light-fm-image-miniapp
    thead-image
    light-fm-image-bsp
    light-fm-image-linux-test
    light-fm-image-debug
    light-fm-image-tools
    light-fm-image-vision
    light-fm-image-test
    light-fm-image
    light-fm-image-ant
    light-fm-image-security
    meta-oe-image-base
    meta-oe-ptest-image
    meta-oe-image
    core-image-gnome
    core-image-minimal-gnome
    core-image-gnome
    core-image-minimal-gnome
    core-image-xfce
    core-image-minimal-xfce
    initramfs-debug-image
    initramfs-kexecboot-klibc-image
    meta-initramfs-image
    initramfs-kexecboot-image
    meta-webserver-image-base
    meta-webserver-image
    meta-perl-image
    meta-perl-base
    meta-perl-ptest-image
    meta-networking-image
    meta-networking-image-base
    meta-filesystems-image
    meta-filesystems-image-base
    meta-python-image
    meta-python-image-base
    meta-python-ptest-image
    meta-multimedia-image
    meta-multimedia-image-base
    multimedia-libcamera-image
    riscv-initramfs-image
```
