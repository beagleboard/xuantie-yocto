# Yocto

```
git clone -b hacks-20221016 git@git.beagleboard.org:RobertCNelson/xuantie-yocto.git xuantie-yocto
```

```
cd ./xuantie-yocto
```

```
source openembedded-core/oe-init-build-env build/light-fm
```

# Download

```
MACHINE=light-a-public-release bitbake light-fm-image-linux --runall=fetch
```

# Builds

```
MACHINE=light-a-public-release bitbake light-fm-image-linux
```
