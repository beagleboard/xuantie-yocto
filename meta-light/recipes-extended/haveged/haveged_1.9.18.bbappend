FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://${BPN}.service"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "haveged.service"

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_system_unitdir}
        install -m 0644 ${WORKDIR}/haveged.service ${D}${systemd_system_unitdir}
    fi
}
