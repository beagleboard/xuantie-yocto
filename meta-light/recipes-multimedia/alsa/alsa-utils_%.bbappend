FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = "\
    file://asound_light.conf \
"

do_install:append() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/asound_light.conf ${D}${sysconfdir}/asound.conf
}

FILES:${PN} += "\
    ${sysconfdir} \
"
