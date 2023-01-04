SUMMARY = "CSI G2D HAL interfaces for using Vivante G2D module"
DESCRIPTION = "The runtime library for using services provide by Vivante G2D module."
HOMEPAGE = "https://code.alibaba-inc.com/light_sdk/gc620-test"
LICENSE = "CLOSED"
COMPATIBLE_MACHINE = "light-*"
#DEPENDS = " openssl cmake-native python3 zlib boost linux-thead process-linker xtensa-dsp vi-kernel"
RDEPENDS_${PN} += " process-linker"
PROVIDES = "gc620-test"
S = "${WORKDIR}/git"
export ARCH="riscv"
export SYSROOT_DIR="${PKG_CONFIG_SYSROOT_DIR}"
export TOOLCHAIN_DIR?="${EXTERNAL_TOOLCHAIN}/bin"
export KERNELDIR?="${STAGING_KERNEL_BUILDDIR}"
export TOOLCHAIN_PREFIX?="riscv64-unknown-linux-gnu-"
export CROSS_COMPILE?="${TOOLCHAIN_DIR}/${TOOLCHAIN_PREFIX}"
export PATH="${PKG_CONFIG_SYSROOT_DIR}/${includedir}:${PKG_CONFIG_SYSROOT_DIR}/${libdir}:${SYSROOT_DIR}:${SYSROOT_DIR}/usr/include:${SYSROOT_DIR}/usr/lib:${SYSROOT_DIR}/lib:${SYSROOT_DIR}/include:${RECIPE_SYSROOT_NATIVE}/usr/bin/riscv64-oe-linux:${COREBASE}/scripts:${COREBASE}/bitbake/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/snap/bin"
THEAD_BSP_TAG ?= "${AUTOREV}"
SRCREV = "${THEAD_BSP_TAG}"
SRC_URI = "git://git@gitee.com/thead-yocto/gc620-test.git;branch=master;protocol=http \
          "

EXCLUDE_FROM_SHLIBS = "1"
EXTRA_OEMAKE += " install"
EXTRA_OEMAKE += " BUILD_SYSTEM='YOCTO_BUILD'"
do_compile() {
    oe_runmake install
}
do_install() {
    install -d ${D}${datadir}/scene
    install -d ${D}${datadir}/scene/g2d
    install -d ${D}${datadir}/scene/g2d/preview
    install -m 0755 ${S}/scene/usr/share/vi/isp/test/*.cfg              ${D}${datadir}/scene/g2d/
    install -m 0755 ${S}/build/sdk/samples/hal/unit_test/galRunTest     ${D}${datadir}/scene/g2d/
    install -m 0755 ${S}/build/sdk/samples/hal/unit_test/libgal2DFilterBlit101.so   ${D}${datadir}/scene/g2d/
    install -m 0755 ${S}/build/sdk/samples/hal/unit_test/libgalUtil.so            ${D}${datadir}/scene/g2d/
    install -m 0755 ${S}/build/sdk/samples/hal/unit_test/galRunTest2IN  ${D}${datadir}/scene/g2d/preview/
    install -m 0755 ${S}/build/sdk/samples/hal/unit_test/libgal2DFilterBlit100.so   ${D}${datadir}/scene/g2d/preview/
}
CFLAGS_append = " -I${STAGING_INCDIR}/gal-viv"
PACKAGES = "${PN}"
FILES_${PN} += " ${datadir}/scene/ "
DEPENDS += " image-proprietary process-linker"
#INSANE_SKIP_${PN} += " debug-files"
INSANE_SKIP_${PN} += " already-stripped useless-rpaths file-rdeps libdir "
