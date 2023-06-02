DESCRIPTION = "Open Portable TEE binary"
LICENSE = "CLOSED"

PV = "0.1"
PR = "r0"
DEPENDS = "e2fsprogs-native linux-thead"
COMPATIBLE_MACHINE = "(light-*|fire-*)"

SRC_URI = " \
        git://git@git.beagleboard.org/beaglev-ahead/xuantie-secure-system-image-release.git;branch=master;protocol=ssh \
          "
THEAD_LINUX_TAG ?= "${AUTOREV}"
SRCREV = "${THEAD_LINUX_TAG}"

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${bindir}
    install -d ${D}${sbindir}
    install -d ${D}${base_libdir}/optee_armtz
    install -d ${D}${libdir}/tee-supplicant/plugins
    install -m 0755 ${WORKDIR}/git/prebuild/ree-related/lib/* ${D}${libdir}
    install -m 0755 ${WORKDIR}/git/prebuild/ree-related/tee-supplicant/plugins/* ${D}${libdir}/tee-supplicant/plugins
    install -m 0755 ${WORKDIR}/git/prebuild/ree-related/bin/* ${D}${bindir}
    install -m 0755 ${WORKDIR}/git/prebuild/ree-related/sbin/* ${D}${sbindir}
    install -m 0755 ${WORKDIR}/git/prebuild/ree-related/optee_armtz/* ${D}${base_libdir}/optee_armtz

    if [ -f ${WORKDIR}/git/prebuild/images/light-fm-a/trust_firmware.bin ]; then
        cp ${WORKDIR}/git/prebuild/images/light-fm-a/trust_firmware.bin ${DEPLOY_DIR_IMAGE}/
    fi

    if [ -f ${WORKDIR}/git/prebuild/images/light-fm-a/tf.ext4 ]; then
        cp ${WORKDIR}/git/prebuild/images/light-fm-a/tf.ext4 ${DEPLOY_DIR_IMAGE}/
    fi

    if [ -f ${WORKDIR}/git/prebuild/images/light-fm-a/stashtf.ext4 ]; then
        cp ${WORKDIR}/git/prebuild/images/light-fm-a/stashtf.ext4 ${DEPLOY_DIR_IMAGE}/
    fi

    if [ -f ${WORKDIR}/git/prebuild/images/light-fm-a/tee.ext4 ]; then
        cp ${WORKDIR}/git/prebuild/images/light-fm-a/tee.ext4 ${DEPLOY_DIR_IMAGE}/
    fi

    if [ -f ${WORKDIR}/git/prebuild/images/light-fm-a/stashtee.ext4 ]; then
        cp ${WORKDIR}/git/prebuild/images/light-fm-a/stashtee.ext4 ${DEPLOY_DIR_IMAGE}/
    fi


    if [ -f ${WORKDIR}/git/prebuild/images/light-fm-a/tee.bin ]; then
       cp ${WORKDIR}/git/prebuild/images/light-fm-a/tee.bin ${DEPLOY_DIR_IMAGE}/
    fi

    if [ -d ${WORKDIR}/git/Tsec_dev_kit ]; then
       cp -rf ${WORKDIR}/git/Tsec_dev_kit ${DEPLOY_DIR_IMAGE}/
    fi
}

FILES:${PN} += " ${base_libdir} "
FILES:${PN} += " ${libdir} "
FILES:${PN} += " ${includedir} "
FILES:${PN} += " ${datadir} "
FILES:${PN} += " ${bindir} "
FILES:${PN} += " ${sbindir} "

do_install[nostamp] = "1"

PACKAGES = "${PN}"
INSANE_SKIP:${PN} += " debug-files already-stripped rpaths "
