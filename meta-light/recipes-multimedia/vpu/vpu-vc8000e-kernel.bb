DESCRIPTION = "Light Video Encode kernel mode driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

COMPATIBLE_MACHINE = "light-*"

SRC_URI = " \
            git://github.com/thead-yocto-mirror/vpu-vc8000e-kernel.git;branch=master;protocol=https \
            file://vc8000e.service \
            file://98-vc8000e.preset \
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
    install -d ${D}${includedir}/vc8000e
    install -d ${D}${datadir}/vc8000e
    install -d ${D}/lib/systemd/system
    install -d ${D}/lib/systemd/system-preset

    install -m 0644 ${S}/output/rootfs/bsp/venc/ko/*.ko       ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
    install -m 0644 ${S}/linux/kernel_module/vc8000_driver.h  ${D}${includedir}/vc8000e
    install -m 0644 ${S}/linux/kernel_module/hantrommu.h      ${D}${includedir}/vc8000e
    install -m 0755 ${S}/output/rootfs/bsp/venc/ko/*.sh       ${D}${datadir}/vc8000e
    install -m 0755 ${WORKDIR}/98-vc8000e.preset              ${D}/lib/systemd/system-preset
    install -m 0755 ${WORKDIR}/vc8000e.service                ${D}/lib/systemd/system
}

PACKAGES = "${PN}"
FILES:${PN} = "${base_libdir} ${includedir} ${sysconfdir} ${datadir} "

INSANE_SKIP:${PN} += " debug-files staticdev "
