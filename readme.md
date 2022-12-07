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
