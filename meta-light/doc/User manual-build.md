# 准备代码
切换到home 目录，创建工程目录，如yocto 

```bash
mkdir yocto
cd yocto
```


repo init
```bash
git clone git@gitlab.alibaba-inc.com:light_sdk/yocto.git
```
```bash
@docker-ubuntu18:~/yocto$ ls
meta-external-toolchain  meta-openembedded  meta-riscv  openembedded-core  riscv-toolchain  build
```


# 开始编译

## 设置环境变量

代码根目录下执行以下命令：
注： 其中第二参数是与board相关，请正确选择，如light-fm：

```bash
source openembedded-core/oe-init-build-env build/light-fm 
```

终端上将看到如下log：

```bash
### Shell environment set up for builds. ###

You can now run 'bitbake <target>'

Common targets are:
    core-image-minimal
    core-image-sato
    meta-toolchain
    meta-ide-support

You can also run generated qemu images with a command like 'runqemu qemux86'.

Other commonly useful commands are:
 - 'devtool' and 'recipetool' handle common recipe tasks
 - 'bitbake-layers' handles common layer tasks
 - 'oe-pkgdata-util' handles common target package tasks
```


## 共享downloads

yocto 编译过程中难免会下载开源软件包，下载的时间依不同的网络和网速而不同切差异很大，为了尽可能加速这一过程，可以通过共享downloads目录的方式大大缩短下载过程。下载的这些包会统一保存在build/downloads，可以在build 目录创建软链接到已有的downloads 目录下，具体方法为：

```bash
ln -s /yocto-downloads ../downloads
```

**​**



## 完整编译
开始编译
```bash
MACHINE=light-fm bitbake  light-fm-image
```
等待编译完成，产生的image在:
~/yocto/thead-/light-fm/tmp-glibc/deploy/images/light-fm$

```
build/light-fm/tmp-glibc/deploy/images/light-fm$ ls -l

Image -> Image--5.10.4-r0-light-fm-20210831132909.bin
Image--5.10.4-r0-light-fm-20210831132909.bin
Image-light-fm.bin -> Image--5.10.4-r0-light-fm-20210831132909.bin
light-fm-image-light-fm-20210831021806.rootfs.cpio.gz
light-fm-image-light-fm-20210831021806.rootfs.ext4
light-fm-image-light-fm-20210831021806.rootfs.manifest
light-fm-image-light-fm-20210831021806.testdata.json
light-fm-image-light-fm.cpio.gz -> light-fm-image-light-fm-20210831021806.rootfs.cpio.gz
light-fm-image-light-fm.ext4 -> light-fm-image-light-fm-20210831021806.rootfs.ext4
light-fm-image-light-fm.manifest -> light-fm-image-light-fm-20210831021806.rootfs.manifest
light-fm-image-light-fm.testdata.json -> light-fm-image-light-fm-20210831021806.testdata.json
light-fm-image-minimal-light-fm-20210831132909.rootfs.cpio.gz
light-fm-image-minimal-light-fm-20210831132909.rootfs.ext4
light-fm-image-minimal-light-fm-20210831132909.rootfs.manifest
light-fm-image-minimal-light-fm-20210831132909.testdata.json
light-fm-image-minimal-light-fm.cpio.gz -> light-fm-image-minimal-light-fm-20210831132909.rootfs.cpio.gz
light-fm-image-minimal-light-fm.ext4 -> light-fm-image-minimal-light-fm-20210831132909.rootfs.ext4
light-fm-image-minimal-light-fm.manifest -> light-fm-image-minimal-light-fm-20210831132909.rootfs.manifest
light-fm-image-minimal-light-fm.testdata.json -> light-fm-image-minimal-light-fm-20210831132909.testdata.json
fw_jump.bin
fw_jump.elf
light-fm-emu--5.10.4-r0-light-fm-20210831132909.dtb
light-fm-emu-dsi0--5.10.4-r0-light-fm-20210831132909.dtb
light-fm-emu-dsi0.dtb -> light-fm-emu-dsi0--5.10.4-r0-light-fm-20210831132909.dtb
light-fm-emu-dsi0-light-fm.dtb -> light-fm-emu-dsi0--5.10.4-r0-light-fm-20210831132909.dtb
light-fm-emu-dsp--5.10.4-r0-light-fm-20210831132909.dtb
light-fm-emu-dsp.dtb -> light-fm-emu-dsp--5.10.4-r0-light-fm-20210831132909.dtb
light-fm-emu-dsp-light-fm.dtb -> light-fm-emu-dsp--5.10.4-r0-light-fm-20210831132909.dtb
light-fm-emu.dtb -> light-fm-emu--5.10.4-r0-light-fm-20210831132909.dtb
light-fm-emu-gpu--5.10.4-r0-light-fm-20210831132909.dtb
light-fm-emu-gpu.dtb -> light-fm-emu-gpu--5.10.4-r0-light-fm-20210831132909.dtb
light-fm-emu-gpu-light-fm.dtb -> light-fm-emu-gpu--5.10.4-r0-light-fm-20210831132909.dtb
light-fm-emu-light-fm.dtb -> light-fm-emu--5.10.4-r0-light-fm-20210831132909.dtb
light-fm-emu-npu-fce--5.10.4-r0-light-fm-20210831132909.dtb
light-fm-emu-npu-fce.dtb -> light-fm-emu-npu-fce--5.10.4-r0-light-fm-20210831132909.dtb
light-fm-emu-npu-fce-light-fm.dtb -> light-fm-emu-npu-fce--5.10.4-r0-light-fm-20210831132909.dtb
u-boot-initial-env -> u-boot-initial-env-light-fm-2020.10-r0
u-boot-initial-env-light-fm -> u-boot-initial-env-light-fm-2020.10-r0
u-boot-initial-env-light-fm-2020.10-r0
u-boot-light-fm-2020.10-r0.bin
u-boot-light-fm-2020.10-r0.elf
u-boot-light-fm.bin -> u-boot-light-fm-2020.10-r0.bin
u-boot-light-fm.elf -> u-boot-light-fm-2020.10-r0.elf
u-boot-spl -> u-boot-spl-light-fm-2020.10-r0
u-boot-spl-light-fm -> u-boot-spl-light-fm-2020.10-r0
u-boot-spl-light-fm-2020.10-r0
u-boot-with-spl.bin -> u-boot-light-fm-2020.10-r0.bin
u-boot.elf -> u-boot-light-fm-2020.10-r0.elf
uImage -> uImage--5.10.4-r0-light-fm-20210819095322.bin
uImage--5.10.4-r0-light-fm-20210819095322.bin
uImage-light-fm.bin -> uImage--5.10.4-r0-light-fm-20210819095322.bin
```
#### 
其中:
kernel Image: 

- 	Image--5.10.4-r0-light-fm-20210819095322.bin

kernel dtb: 

- 	light-fm-xxx.dtb

uboot：

- 	u-boot-light-fm-2020.10-r0.bin

opensbi:

- 	fw_jump.bin

Yocto full image rootfs:

- 	xxx.rootfs.cpio.gz
- 	xxx.rootfs.ext4



查看其他可编译的镜像：
```bash
bitbake-layers show-recipes "*-image-*"
```
## 
 单独编译
除了一键完成整个SDK 的编译外，在开发过程中，也可以单独编译某个模块以节约编译时间，调试过程如果要单独编译某个package，可以使用以下命令快速单独编译该包。
注意：-C 是大写的C
```bash
bitbake “your package name” -C compile
```


清除某个package 编译过程中产生的中间文件方法：
```bash
bitbake linux-thead -c clean
```


在清除中间文件的同时删除下载的源码包（本地有修改的时候**慎用**）
```bash
bitbake linux-thead -c cleanall
```


### 编译kernel
yocto 编译Linux kernel 过程中会从gitlab 上动态下载内核源码，下载的源码放置在tmp-glibc/work/${board}-oe-linux/linux-thead/${PREFERRED_VERSION}-${PVR}/linux-${PREFERRED_VERSION} 目录下，其中${PREFERRED_VERSION}： 内核版本号，如5.10.4 等
${PVR}： 内核修订版本号，如rc0 等
这些变量通常定义在meta-${board}/conf/machine/${board}.conf  里。
​

**清除Linux 内核**
```bash
bitbake linux-thead -c clean
```


在清除中间文件的同时删除下载的源码包（本地有修改的时候**慎用**）
```bash
bitbake linux-thead -c cleanall
```


**单独编译Linux 内核**
```bash
bitbake linux-thead -C compile
```


### 编译u-boot
yocto 编译u-boot 过程中会从gitlab 上动态下载内核源码，下载的源码放置在tmp-glibc/work/${board}-oe-linux/u-boot/${PREFERRED_VERSION_u-boot}-${PVR}/git 目录下，其中
${PREFERRED_VERSION_u-boot}： u-boot版本号，如1_2020.10等
${PVR}： 内核修订版本号，如rc0 等
这些变量通常定义在meta-${board}/conf/machine/${board}.conf  里。
​

**清除u-boot**
```bash
bitbake u-boot -c clean
```


在清除中间文件的同时删除下载的源码包（本地有修改的时候**慎用**）
```bash
bitbake u-boot -c cleanall
```


**单独编译Linux 内核**
```bash
bitbake u-boot -C compile
```



# 调试

具体请参考《软件开发调试基础》

# Q&A


## yocto 代码提交找不到origin
```bash
@docker-ubuntu18:~/yocto/build$ git push origin HEAD:refs/for/master
fatal: 'origin' does not appear to be a git repository
fatal: Could not read from remote repository.

Please make sure you have the correct access rights
and the repository exists.
```


解决方法：
```bash
@docker-ubuntu18:~/yocto/build$ git remote -v
aone    git@gitlab.alibaba-inc.com:yocto/meta-thead-conf (fetch)
aone    git@gitlab.alibaba-inc.com:yocto/meta-thead-conf (push)
@docker-ubuntu18:~/yocto/build$ git remote remove orign
fatal: No such remote: orign
@docker-ubuntu18:~/yocto/build$ git remote add origin git@gitlab.alibaba-inc.com:yocto/meta-thead-conf
```
注意：具体仓库地址会有不同，请酌情更改。


## git commit –amend 找不到编辑器
git commit –amend，出现如下错误：
```bash
error: There was a problem with the editor ‘vi’.
Please supply the message using either -m or -F option.
```
原因是vi有问题，需要为 git 换一个默认的编辑器，比如 vim，如下进行配置即正常了。
```bash
git config --global core.editor "vim"
```


fs.inotify.max_user_watches 不足的错误
```bash
# 错误信息
ERROR: No space left on device or exceeds fs.inotify.max_user_watches?
ERROR: To check max_user_watches: sysctl -n fs.inotify.max_user_watches.
ERROR: To modify max_user_watches: sysctl -n -w fs.inotify.max_user_watches=<value>.
ERROR: Root privilege is required to modify max_user_watches.
```
规避方法：
```bash
# 设置 fs.inotify.max_user_watches
sudo sysctl -n -w fs.inotify.max_user_watches=524288
```
## mktemp 没法创建临时文件夹
bitbake 时，遇到如下错误：
```
configure: error:
| '/home/mah/light_sdk/yocto/build/light-fm/tmp-glibc/hosttools/mktemp -d' does not create temporary directories.
```
如果单独执行 mktemp，则错误信息如下：
```
$ mktemp -d
mktemp: failed to create directory via template `/tmp/tmp.XXXXXXXXXX': Permission denied
```
**解决方法：修改 /tmp 文件夹权限**
```
$ sudo chmod a=rwx,u+t /tmp
```


## locale 未设置
bitbake xxx 时遇到如下错误：
```
Please use a locale setting which supports UTF-8 (such as LANG=en_US.UTF-8).
Python can't change the filesystem locale after loading so we need a UTF-8 when Python starts or things won't work.
```
vim ~/.bashrc， 在文件末尾添加如下一行
```
source /etc/profile
```

