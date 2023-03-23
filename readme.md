# Use Ubuntu 20.04 (Debian 11.x issues..)

# Install

```
sudo apt install bison build-essential cmake chrpath diffstat flex gawk git git-lfs python3-distutils python-is-python3 rsync zstd
```

# No Dash...
```
sudo dpkg-reconfigure dash
```

# Yocto

```
git clone -b Linux_SDK_V1.1.2-light-beagle git@git.beagleboard.org:beaglev-ahead/xuantie-yocto.git xuantie-yocto ; cd ./xuantie-yocto
```

# Configure Build

```
source openembedded-core/oe-init-build-env thead-build/light-fm
```

# Build Options
```
You can now run 'bitbake <target>'

Common targets are:
    thead-image-gui
    thead-image-multimedia
    thead-image-linux
    thead-image-linux-test

machines:
    light-a-val
    light-beagle
    light-lpi4a
    light-b-product
```

# Copy mirrored downloads:

```
mkdir ../downloads ; rsync -av /mnt/yocto-cache/beaglev-ahead/Linux_SDK_V1.1.2/downloads/ ../downloads/
```

# Download missing files:

```
MACHINE=light-beagle bitbake thead-image-linux --runall=fetch
rsync -a ../downloads/ /mnt/yocto-cache/beaglev-ahead/Linux_SDK_V1.1.2/downloads/
```
