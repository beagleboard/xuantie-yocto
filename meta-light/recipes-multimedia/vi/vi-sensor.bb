DESCRIPTION = "thead VI customized sensor drivers"
HOMEPAGE = "https://gitee.com/thead-yocto/vi-sensor/"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "light-*"

DEPENDS += "image-proprietary vi-kernel"

SRC_URI = " \
            git://git@gitee.com/thead-yocto/vi-sensor.git;branch=master;protocol=http \
            file://0001-dependency-fix.patch \
          "

THEAD_BSP_TAG ?= "${AUTOREV}"
SRCREV = "${THEAD_BSP_TAG}"

S = "${WORKDIR}/git"

export SYSROOT_DIR="${RECIPE_SYSROOT}"

export PROJECT_DIR?="${COREBASE}/.."
export ARCH?="riscv"
export BOARD_NAME="${MACHINEOVERRIDES}"
export BUILD_ROOT?="${TOPDIR}"
export BUILDROOT_DIR?="${BUILD_ROOT}"
export CROSS_COMPILE="riscv64-linux-"
export TOOLCHAIN_DIR?="${EXTERNAL_TOOLCHAIN}"
export LINUX_DIR?="${STAGING_KERNEL_BUILDDIR}"
export INSTALL_DIR_ROOTFS?="${IMAGE_ROOTFS}"
export INSTALL_DIR_SDK?="${SDK_DEPLOY}" 
export PATH="${SYSROOT_DIR}:${SYSROOT_DIR}/usr/include:${SYSROOT_DIR}/usr/lib:${SYSROOT_DIR}/lib:${SYSROOT_DIR}/include:${RECIPE_SYSROOT_NATIVE}/usr/bin/riscv64-oe-linux:${COREBASE}/scripts:${COREBASE}/bitbake/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/snap/bin"
export KERNEL_VERSION="$(cat ${BASE_WORKDIR}/kernel_version)"

#EXTRA_OEMAKE+="BUILD_SYSTEM='YOCTO_BUILD' PLATFORM=light -C src/platform/light 'CFLAGS=${CFLAGS}'"
export EXTRA_OEMAKE+="BUILD_SYSTEM='YOCTO_BUILD' PLATFORM=light "
export EXTRA_OEMAKE+="VISYS_SYM_PATH=${PKG_CONFIG_SYSROOT_DIR}"

PARALLEL_MAKEINST = "-j8"

do_compile() {
    oe_runmake > sensor-build.log
}

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${datadir}/vi/config
    install -m 0644 ${S}/build/rootfs/usr/lib/lib*.so*                          ${D}${libdir}
    install -m 0644 ${S}/build/rootfs/usr/share/vi/isp/test/*    ${D}${datadir}/vi/config
    install -m 0644 ${S}/build/rootfs/usr/share/vi/tuningtool/bin/*    ${D}${datadir}/vi/config
    chrpath -d ${D}${libdir}/*.so*
    chrpath -d ${D}${datadir}/vi/config/*.drv
}

FILES_${PN} += " ${base_libdir} "
FILES_${PN} += " ${libdir} "
FILES_${PN} += " ${datadir} "
FILES_${PN} += " ${includedir} "
INSANE_SKIP_${PN} += " debug-files staticdev file-rdeps "

PACKAGES = "${PN}"

# RDEPENDS_${PN} = " "
