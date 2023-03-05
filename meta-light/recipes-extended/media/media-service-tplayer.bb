DESCRIPTION = "Media service files"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit bin_package systemd
DEPENDS:append = " update-rc.d-native"

COMPATIBLE_MACHINE = "(^d1*)"

RDEPENDS:${PN} += " alsa-lib dbus libcap liblzma openssl libevent log4cpp jsoncpp libidn2 \
                    libopus libcurl gnutls nettle zlib libunistring gmp libgmpxx "

DEPENDS = "libevent"

SRC_URI = "\
    file://media_service.sh \
    file://media_service_tplayer \
    file://tplayerdemo \
    file://media.service \
    file://media-dbus.conf \
    "

S = "${WORKDIR}"

do_install() {
    install -d ${D}${base_libdir}/firmware
    install -d ${D}${bindir}
    install -d ${D}${libdir}
    install -d ${D}${sysconfdir}/dbus-1/system.d/

    install -m 0755 ${WORKDIR}/media_service_tplayer ${D}${bindir}/media_service
    install -m 0755 ${WORKDIR}/tplayerdemo ${D}${bindir}/tplayerdemo
    install -m 0644 ${WORKDIR}/media-dbus.conf ${D}${sysconfdir}/dbus-1/system.d/media-dbus.conf

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_system_unitdir}
        install -m 0644 ${WORKDIR}/media.service ${D}${systemd_system_unitdir}/media.service
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${WORKDIR}/media_service.sh ${D}${sysconfdir}/init.d
        update-rc.d -r ${D} media_service.sh start 99 5 3 2 .
    fi
}

SYSTEMD_SERVICE:${PN} = "media.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

FILES:${PN} += " ${base_libdir} "
FILES:${PN} += " ${bindir} "
FILES:${PN} += " ${libdir} "
FILES:${PN} += " ${systemd_system_unitdir} "
FILES:${PN} += " ${sysconfdir} "

PACKAGES = "${PN}"

INSANE_SKIP:${PN} += " debug-files file-rdeps already-stripped "
