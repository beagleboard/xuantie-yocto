DESCRIPTION = "plink-AI test demo"
HOMEPAGE = "https://code.aone.alibaba-inc.com/light_sdk/plink_AI"
LICENSE = "CLOSED"
#LIC_FILES_CHKSUM = ""

COMPATIBLE_MACHINE = "light-*"

DEPENDS = " process-linker video-memory pnna "
RDEPENDS_${PN} += " process-linker video-memory pnna "

SRC_URI = " \
            git://git@gitee.com/thead-yocto/plink_AI.git;branch=master;protocol=http \
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

EXTRA_OEMAKE+="BUILD_SYSTEM='YOCTO_BUILD'"

PARALLEL_MAKEINST = "-j1"

do_compile() {
    oe_runmake
}

do_install() {
    install -d ${D}${datadir}/scene/plink_AI
    install -d ${D}${datadir}/scene/plink_AI/test
    install -d ${D}${datadir}/scene/plink_AI/model/face_detect
    install -d ${D}${datadir}/scene/plink_AI/input/face_detect

    install -m 0755 ${S}/output/rootfs/plink_AI/*.sh                         ${D}${datadir}/scene/plink_AI
    install -m 0755 ${S}/output/rootfs/plink_AI/test/*                       ${D}${datadir}/scene/plink_AI/test
    install -m 0755 ${S}/output/rootfs/plink_AI/model/face_detect/*          ${D}${datadir}/scene/plink_AI/model/face_detect
    install -m 0755 ${S}/output/rootfs/plink_AI/input/face_detect/*          ${D}${datadir}/scene/plink_AI/input/face_detect
}

FILES_${PN} += " ${datadir} "

PACKAGES = "${PN}"

INSANE_SKIP_${PN} += " debug-files already-stripped"

