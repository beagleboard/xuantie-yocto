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

# Pre Download mirror:

```
mkdir ../downloads ; rsync -av /mnt/ti-processor-sdk/thead/downloads/ ../downloads/
```

# Download

```
MACHINE=light-beagle bitbake light-fm-image-linux --runall=fetch
rsync -av ../downloads/ /mnt/ti-processor-sdk/thead/downloads/ --delete
```

# Builds

```
MACHINE=light-beagle bitbake light-fm-image-linux
```
