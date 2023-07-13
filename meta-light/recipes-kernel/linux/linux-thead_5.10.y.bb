inherit kernel
require recipes-kernel/linux/linux-yocto.inc
DEPENDS = "e2fsprogs-native opensbi"

SRC_URI = " \
            git://github.com/beagleboard/linux.git;branch=beaglev-v5.10.113-1.1.2;protocol=https \
"

KERNEL_DEFCONFIG = "arch/riscv/configs/beaglev_defconfig"

# crop the kernel based on the defconfig
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI:light-b-product-release = " \
            git://git@gitee.com/thead-yocto/kernel.git;branch=master;protocol=http \
"
SRC_URI:light-b-product-release += "file://cvl1.cfg"
SRC_URI:light-b-product-release += "file://cvl2.cfg"
# SRC_URI:light-b-product-release += "file://cvl3.cfg"

KERNEL_VERSION_SANITY_SKIP="1"

THEAD_BSP_TAG ?= "${AUTOREV}"
THEAD_LINUX_TAG ?= "${THEAD_BSP_TAG}"
SRCREV = "beaglev-v5.10.113-1.1.2"

SSTATE_SKIP_CREATION = "1"

LICENSE = "CLOSED"

DEPENDS += "elfutils-native"

COMPATIBLE_MACHINE = "light-*"

S = "${WORKDIR}/linux-${PV}"

do_configure:append() {
   [ -d ${STAGING_KERNEL_DIR} ] && rm -rf ${STAGING_KERNEL_DIR}
   [ -f ${STAGING_KERNEL_DIR} ] && rm -rf ${STAGING_KERNEL_DIR}

   ln -s ${S}  ${STAGING_KERNEL_DIR}
}
do_install:append() {

   [ -f ${STAGING_KERNEL_BUILDDIR} ] && rm -rf ${STAGING_KERNEL_BUILDDIR}
   [ -d ${STAGING_KERNEL_BUILDDIR} ] && rm -rf ${STAGING_KERNEL_BUILDDIR}

   ln -s ${B}  ${STAGING_KERNEL_BUILDDIR}

   if [ ! -d ${DEPLOY_DIR_IMAGE}/.boot ]; then
      mkdir -p ${DEPLOY_DIR_IMAGE}/.boot
   fi

   if [ ! -d ${DEPLOY_DIR_IMAGE}/.boot/overlays ]; then
      mkdir -p ${DEPLOY_DIR_IMAGE}/.boot/overlays
   fi

   if [ -f ${B}/arch/riscv/boot/Image ]; then
      cp -f ${B}/arch/riscv/boot/Image ${DEPLOY_DIR_IMAGE}/.boot
   fi

   dtbfiles=`ls -lt ${B}/arch/riscv/boot/dts/thead/light-*.dtb | awk '{print $9}'`
   for i in $dtbfiles;
   do
      if [ -f $i ]; then
         cp ${i} ${DEPLOY_DIR_IMAGE}/.boot
      fi
   done

   overlayfiles=`ls -lt ${B}/arch/riscv/boot/dts/thead/overlays/*.dtbo | awk '{print $9}'`
   for i in $overlayfiles;
   do
      if [ -f $i ]; then
         cp ${i} ${DEPLOY_DIR_IMAGE}/.boot/overlays
      fi
   done

   install -d ${D}${sysconfdir}
   head=$(git --git-dir=${S}/.git rev-parse --verify HEAD 2>/dev/null)
   echo "commit-id:"${head} > ${DEPLOY_DIR_IMAGE}/.boot/kernel-release
   cp ${DEPLOY_DIR_IMAGE}/.boot/kernel-release ${D}${sysconfdir}
   dd if=/dev/zero of=${DEPLOY_DIR_IMAGE}/boot.ext4 bs=1 count=0 seek=190M
   mkfs.ext4 -F  ${DEPLOY_DIR_IMAGE}/boot.ext4 -d ${DEPLOY_DIR_IMAGE}/.boot

   rm -f ${BASE_WROKDIR}/kernel_version
   touch ${BASE_WORKDIR}/kernel_version
   echo ${KERNEL_VERSION_PKG_NAME} > ${BASE_WORKDIR}/kernel_version
}

do_install[nostamp] = "1"
KCONFIG_MODE="--alldefconfig"

FILES:${KERNEL_PACKAGE_NAME}-base += "${sysconfdir}"
