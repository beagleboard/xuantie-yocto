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
git clone -b Linux_SDK_V1.0.3-light-beagle git@git.beagleboard.org:beaglev-ahead/xuantie-yocto.git xuantie-yocto ; cd ./xuantie-yocto
```

# Configure Build

```
source openembedded-core/oe-init-build-env build/light-fm
```