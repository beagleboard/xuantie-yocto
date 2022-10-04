# Use Ubuntu 20.04 (Debian 11.x issues..)


# Yocto

```
git clone -b light-beagle-wip-20221003 git@git.beagleboard.org:RobertCNelson/xuantie-yocto.git xuantie-yocto ; cd ./xuantie-yocto
```

# Configure Build
```
source openembedded-core/oe-init-build-env build/light-fm
```

# Copy mirrored downloads:

```
mkdir ../downloads ; rsync -av /mnt/ti-processor-sdk/thead/downloads/ ../downloads/
```

# Download missing files:

```
MACHINE=light-beagle bitbake light-fm-image-linux --runall=fetch
rsync -av ../downloads/ /mnt/ti-processor-sdk/thead/downloads/ --delete
```

# Start Build

```
MACHINE=light-beagle bitbake light-fm-image-linux
```


# install (android fastboot tools)

```
fastboot --version
fastboot version 28.0.2-debian
Installed as /usr/lib/android-sdk/platform-tools/fastboot
```

Insert USB Cable, hold reset button, hold USB, lift up reset button..


Serial should show:
```
brom_ver 7
[APP][E] protocol_connect failed, exit.
```

Then run:

```
sudo fastboot flash ram ./tmp-glibc/deploy/images/light-beagle/u-boot-with-spl.bin
sudo fastboot reboot
sleep 10
sudo fastboot flash uboot ./tmp-glibc/deploy/images/light-beagle/u-boot-with-spl.bin
sudo fastboot flash boot ./tmp-glibc/deploy/images/light-beagle/boot.ext4
sudo fastboot flash root ./tmp-glibc/deploy/images/light-beagle/light-fm-image-linux-light-beagle-*.rootfs.ext4
sudo fastboot reboot
```
