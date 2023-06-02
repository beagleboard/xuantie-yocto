SUMMARY = "THead produce utils"
DESCRIPTION = "THead produce utils"
LICENSE = "CLOSED"
HOMEPAGE = "https://gitee.com/thead-yocto/thead-linux/"

THEAD_LINUX_TAG ?= "${AUTOREV}"
SRCREV = "${THEAD_LINUX_TAG}"

#SRC_URI = "git://git@gitee.com/thead-yocto/prd-utils.git;branch=master;protocol=http"
SRC_URI = "git://github.com/thead-yocto-mirror/prd-utils.git;branch=master;protocol=https"

S = "${WORKDIR}/git"

# Need tools(fw_printenv) in u-boot, but not depends when compile
# DEPENDS = " u-boot "

inherit cmake

do_compile() {
    oe_runmake
}

# install below files to ${WORKDIR}/image
do_install() {
    install -d ${D}${bindir}
    install -d ${D}${libdir}
    install -d ${D}${includedir}/prd-utils

    install -m 0755 ${WORKDIR}/build/utils/prd_utils_*      ${D}${bindir}
    install -m 0755 ${WORKDIR}/build/test/test_prd_utils_*  ${D}${bindir}
    install -m 0755 ${WORKDIR}/build/platform/*/lib*.so     ${D}${libdir}
    install -m 0644 ${S}/include/*.h                        ${D}${includedir}/prd-utils
}

OECMAKE_GENERATOR = "Unix Makefiles"
OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}"
OECMAKE_CXX_FLAGS += "-I${STAGING_INCDIR}"
OECMAKE_C_LINK_FLAGS += "-L${STAGING_LIBDIR} --no-sysroot-suffix --sysroot=${STAGING_DIR_TARGET}"
OECMAKE_CXX_LINK_FLAGS += "-L${STAGING_LIBDIR} --no-sysroot-suffix --sysroot=${STAGING_DIR_TARGET}"

EXTRA_OECMAKE += " \
    -DBOARD_NAME="${MACHINEOVERRIDES}" \
    -DCMAKE_INSTALL_PREFIX=/usr \
    -DCMAKE_VERBOSE_MAKEFILE=ON \
"

FILES:${PN} += " ${bindir} ${includedir} ${libdir}"

PROVIDES = "${PN}"
PACKAGES = "${PN}"

INSANE_SKIP:${PN} += "debug-files file-rdeps dev-deps installed-vs-shipped"

