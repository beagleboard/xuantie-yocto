# Use Ubuntu 20.04 (Debian 11.x issues..)

# Install

```
sudo apt install build-essential chrpath diffstat gawk git python3-distutils rsync
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
