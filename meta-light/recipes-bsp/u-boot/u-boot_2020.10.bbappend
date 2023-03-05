COMPATIBLE_MACHINE = "light-*"

SRC_URI = " \
     git://git@gitee.com/thead-yocto/u-boot.git;branch=master;protocol=http \ 
     file://fw_env.config \
     file://0001-no-strip-fw_printenv.patch \
"

THEAD_BSP_TAG ?= "${AUTOREV}"
THEAD_LINUX_TAG ?= "${THEAD_BSP_TAG}"
SRCREV = "${THEAD_LINUX_TAG}"
LICENSE = "CLOSED"

do_configure:append() {
	mkdir ${B}/lib/
	cp ${S}/lib/sec_library ${B}/lib/ -rf
}

do_compile:append () {
	oe_runmake ${UBOOT_MACHINE}
	oe_runmake envtools
}

SRC_URI += "file://fw_env.config"

do_install:append() {
    install -d ${D}${sysconfdir}
    install -d ${D}${bindir}
    install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}
    install -m 0755 ${B}/tools/env/fw_printenv ${D}${bindir}
    ln -rsf ${D}${bindir}/fw_printenv ${D}${bindir}/fw_setenv
}

FILES:${PN} += " ${bindir} "
INSANE_SKIP:${PN} += "installed-vs-shipped"