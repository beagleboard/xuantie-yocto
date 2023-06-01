COMPATIBLE_MACHINE = "light-*"

SRC_URI = " \
     git://git@git.beagleboard.org/beaglev-ahead/beaglev-ahead-u-boot.git;branch=beaglev-v2020.01-1.1.2;protocol=ssh \
"

THEAD_BSP_TAG ?= "${AUTOREV}"
THEAD_LINUX_TAG ?= "${THEAD_BSP_TAG}"
SRCREV = "beaglev-v2020.01-1.1.2"
LICENSE = "CLOSED"

do_configure:append() {
	mkdir ${B}/lib/
	cp ${S}/lib/sec_library ${B}/lib/ -rf
}

do_compile:append () {
	oe_runmake ${UBOOT_MACHINE}
	oe_runmake envtools
}

do_install:append() {
    install -d ${D}${sysconfdir}
    install -d ${D}${bindir}
}

FILES:${PN} += " ${bindir} "
INSANE_SKIP:${PN} += "installed-vs-shipped"