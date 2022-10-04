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

```
console=ttyS0,115200 root=PARTUUID=80a5a8e9-c744-491a-93c1-4f4194fd690a rootfstype=ext4 rdinit=/sbin/init rootwait rw earlycon clk_ignore_unused loglevel=7 eth= rootrw=PARTLABEL=data init=/init rootinit=/sbin/init rootrwoptions=rw,noatime rootrwreset=yes crashkernel=500M
```

```
root@light-beagle:~# lsblk
NAME         MAJ:MIN RM  SIZE RO TYPE MOUNTPOINT
mmcblk0      179:0    0 14.6G  0 disk 
|-mmcblk0p1  179:1    0    2M  0 part 
|-mmcblk0p2  179:2    0  200M  0 part 
|-mmcblk0p3  179:3    0  3.9G  0 part /media/rfs/ro
|-mmcblk0p4  179:4    0  200M  0 part 
|-mmcblk0p5  179:5    0  3.9G  0 part 
`-mmcblk0p6  179:6    0  6.4G  0 part /media/rfs/rw
mmcblk0boot0 179:8    0    4M  1 disk 
mmcblk0boot1 179:16   0    4M  1 disk 
```

```
root@light-beagle:~# partx -s /dev/mmcblk0
NR    START      END  SECTORS SIZE NAME  UUID
 1       34     4095     4062   2M table acfdc713-3579-4362-9f28-aeafad5c1a3e
 2     4096   413695   409600 200M boot  beeb5861-89a0-4df9-876a-48f66b160698
 3   413696  8605695  8192000 3.9G root  80a5a8e9-c744-491a-93c1-4f4194fd690a
 4  8605696  9015295   409600 200M bootB 217b61a1-4398-464e-9464-7828493ba4bb
 5  9015296 17207295  8192000 3.9G rootB 80a5a8e9-c744-491a-93c1-4f4194fd690b
 6 17207296 30621662 13414367 6.4G data  f0050e0c-a816-4c5a-9c29-46d5ee84d11c
```

```
root@light-beagle:~# partx -s /dev/mmcblk0 --output-all
NR    START      END  SECTORS SIZE NAME  UUID                                                                 TYPE FLAGS SCHEME
 1       34     4095     4062   2M table acfdc713-3579-4362-9f28-aeafad5c1a3e ebd0a0a2-b9e5-4433-87c0-68b6b72699c7 0x0   gpt
 2     4096   413695   409600 200M boot  beeb5861-89a0-4df9-876a-48f66b160698 bc13c2ff-59e6-4262-a352-b275fd6f7172 0x0   gpt
 3   413696  8605695  8192000 3.9G root  80a5a8e9-c744-491a-93c1-4f4194fd690a 0fc63daf-8483-4772-8e79-3d69d8477de4 0x0   gpt
 4  8605696  9015295   409600 200M bootB 217b61a1-4398-464e-9464-7828493ba4bb bc13c2ff-59e6-4262-a352-b275fd6f7172 0x0   gpt
 5  9015296 17207295  8192000 3.9G rootB 80a5a8e9-c744-491a-93c1-4f4194fd690b 0fc63daf-8483-4772-8e79-3d69d8477de4 0x0   gpt
 6 17207296 30621662 13414367 6.4G data  f0050e0c-a816-4c5a-9c29-46d5ee84d11c 0fc63daf-8483-4772-8e79-3d69d8477de4 0x0   gpt
```

```
root@light-beagle:~# mount
devtmpfs on /dev type devtmpfs (rw,relatime,size=582728k,nr_inodes=145682,mode=755)
proc on /proc type proc (rw,relatime)
sysfs on /sys type sysfs (rw,relatime)
/dev/mmcblk0p3 on /media/rfs/ro type ext4 (rw,noatime,nodiratime)
/dev/mmcblk0p6 on /media/rfs/rw type ext4 (rw,noatime)
overlay on / type overlay (rw,relatime,lowerdir=/media/rfs/ro,upperdir=/media/rfs/rw/upperdir,workdir=/media/rfs/rw/work)
tmpfs on /dev/shm type tmpfs (rw,nosuid,nodev)
devpts on /dev/pts type devpts (rw,relatime,gid=5,mode=620,ptmxmode=666)
tmpfs on /run type tmpfs (rw,nosuid,nodev,size=358900k,nr_inodes=819200,mode=755)
tmpfs on /sys/fs/cgroup type tmpfs (ro,nosuid,nodev,noexec,size=4096k,nr_inodes=1024,mode=755)
cgroup2 on /sys/fs/cgroup/unified type cgroup2 (rw,nosuid,nodev,noexec,relatime,nsdelegate)
cgroup on /sys/fs/cgroup/systemd type cgroup (rw,nosuid,nodev,noexec,relatime,xattr,name=systemd)
none on /sys/fs/bpf type bpf (rw,nosuid,nodev,noexec,relatime,mode=700)
hugetlbfs on /dev/hugepages type hugetlbfs (rw,relatime,pagesize=2M)
mqueue on /dev/mqueue type mqueue (rw,nosuid,nodev,noexec,relatime)
debugfs on /sys/kernel/debug type debugfs (rw,nosuid,nodev,noexec,relatime)
tracefs on /sys/kernel/tracing type tracefs (rw,nosuid,nodev,noexec,relatime)
tmpfs on /tmp type tmpfs (rw,nosuid,nodev,nr_inodes=409600)
tmpfs on /var/volatile type tmpfs (rw,relatime)
fusectl on /sys/fs/fuse/connections type fusectl (rw,nosuid,nodev,noexec,relatime)
configfs on /sys/kernel/config type configfs (rw,nosuid,nodev,noexec,relatime)
adb on /dev/usb-ffs/adb type functionfs (rw,relatime)
tmpfs on /run/user/0 type tmpfs (rw,nosuid,nodev,relatime,size=179448k,nr_inodes=44862,mode=700)
```
