DESCRIPTION = "Light plink dpu client module"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "light-*"

SRC_URI = " \
            git://git@gitee.com/thead-yocto/plink_dpu_client.git;branch=master;protocol=http \
          "

THEAD_BSP_TAG ?= "${AUTOREV}"
SRCREV = "${THEAD_BSP_TAG}"

S = "${WORKDIR}/git"

DEPENDS = "process-linker libdrm"
RDEPENDS_${PN} = "process-linker libdrm"

export SYSROOT_DIR="${PKG_CONFIG_SYSROOT_DIR}"

export ARCH?="riscv"
export BOARD_NAME="${MACHINEOVERRIDES}"
export CROSS_COMPILE="riscv64-linux-"

export TOOLCHAIN_DIR?="${EXTERNAL_TOOLCHAIN}"
export LINUX_DIR?="${STAGING_KERNEL_BUILDDIR}"

export PATH="/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"

export EXTRA_OEMAKE = "INC_PATH=${PKG_CONFIG_SYSROOT_DIR}/${includedir} LIB_PATH=${PKG_CONFIG_SYSROOT_DIR}/${libdir}"

do_install() {
    install -d ${D}${datadir}/dpu/test/bin

    install -m 0755 ${S}/output/plink_dpu_client   ${D}${datadir}/dpu/test/bin
}

FILES_${PN} = "${libdir} ${datadir} ${includedir}"
PACKAGES = "${PN}"

INSANE_SKIP_${PN} += " debug-files staticdev "
