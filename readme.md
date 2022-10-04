# Yocto

```
git clone -b light-beagle-wip-20221003 git@git.beagleboard.org:RobertCNelson/xuantie-yocto.git xuantie-yocto
```

```
cd ./xuantie-yocto
```

```
source openembedded-core/oe-init-build-env build/light-fm
```

# Download

```
MACHINE=light-beagle bitbake light-fm-image-linux --runall=fetch
```

# Builds

```
MACHINE=light-beagle bitbake light-fm-image-linux
```
