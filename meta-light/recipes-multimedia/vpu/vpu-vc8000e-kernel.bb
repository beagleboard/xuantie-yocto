DESCRIPTION = "Light Video Encode kernel mode driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

COMPATIBLE_MACHINE = "light-*"

SRC_URI = " \
            git://git@gitee.com/thead-yocto/vpu-vc8000e-kernel.git;branch=master;protocol=ssh \
          "

THEAD_BSP_TAG ?= "${AUTOREV}"
SRCREV = "${THEAD_BSP_TAG}"

S = "${WORKDIR}/git"

DEPENDS += " linux-thead "

export SYSROOT_DIR="${PKG_CONFIG_SYSROOT_DIR}"

export ARCH?="riscv"
export BOARD_NAME="${MACHINEOVERRIDES}"
export CROSS_COMPILE="riscv64-linux-"

export TOOLCHAIN_DIR?="${EXTERNAL_TOOLCHAIN}"
export LINUX_DIR?="${STAGING_KERNEL_BUILDDIR}"

export PATH="/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"

export PROJECT_DIR?="${COREBASE}/.."
export KERNEL_VERSION="$(cat ${BASE_WORKDIR}/kernel_version)"

PARALLEL_MAKEINST = "-j1"

do_install() {
    install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
    install -d ${D}${sysconfdir}/modules-load.d
    install -d ${D}${includedir}/vc8000e

    install -m 0644 ${S}/output/rootfs/bsp/venc/ko/*.ko       ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
    install -m 0755 ${S}/output/rootfs/bsp/venc/ko/*.conf     ${D}${sysconfdir}/modules-load.d
    install -m 0644 ${S}/linux/kernel_module/vc8000_driver.h  ${D}${includedir}/vc8000e
    install -m 0644 ${S}/linux/kernel_module/hantrommu.h      ${D}${includedir}/vc8000e
}

PACKAGES = "${PN}"
FILES_${PN} = "${base_libdir} ${includedir} ${sysconfdir}"

INSANE_SKIP_${PN} += " debug-files staticdev "
