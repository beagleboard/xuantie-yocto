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

# Partition layout:

```
C910 Light# mmc part

Partition Map for MMC device 0  --   Partition Type: EFI

Part	Start LBA	End LBA		Name
	Attributes
	Type GUID
	Partition GUID
  1	0x00000022	0x00000fff	"table"
	attrs:	0x0000000000000000
	type:	ebd0a0a2-b9e5-4433-87c0-68b6b72699c7
	type:	data
	guid:	6545ff52-ccb1-44f0-825a-bda574a34ed3
  2	0x00001000	0x00064fff	"boot"
	attrs:	0x0000000000000000
	type:	bc13c2ff-59e6-4262-a352-b275fd6f7172
	type:	boot
	guid:	b70f4bc8-49d6-4a9e-a811-b15c22e211ac
  3	0x00065000	0x00834fff	"root"
	attrs:	0x0000000000000000
	type:	0fc63daf-8483-4772-8e79-3d69d8477de4
	type:	linux
	guid:	80a5a8e9-c744-491a-93c1-4f4194fd690a
  4	0x00835000	0x00898fff	"bootB"
	attrs:	0x0000000000000000
	type:	bc13c2ff-59e6-4262-a352-b275fd6f7172
	type:	boot
	guid:	eda2db75-ee47-4631-939d-c4ce64afb4f0
  5	0x00899000	0x01068fff	"rootB"
	attrs:	0x0000000000000000
	type:	0fc63daf-8483-4772-8e79-3d69d8477de4
	type:	linux
	guid:	80a5a8e9-c744-491a-93c1-4f4194fd690b
  6	0x01069000	0x01d33fde	"data"
	attrs:	0x0000000000000000
	type:	0fc63daf-8483-4772-8e79-3d69d8477de4
	type:	linux
	guid:	ca3a1b1a-0bc9-4ad5-9e0c-5609b8bf78d1
```
