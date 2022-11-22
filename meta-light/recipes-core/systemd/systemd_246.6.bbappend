FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
        file://80-wifi-station.network \
        file://90-system.conf \
"

do_install_append_light-fm () {
	install -m 0644 ${WORKDIR}/80-wifi-station.network ${D}${systemd_unitdir}/network/80-wifi-station.network
}

do_install_append () {
        ln -sf ../systemd-modules-load.service ${D}${systemd_unitdir}/system/graphical.target.wants/systemd-modules-load.service
        install -d ${D}{sysconfdir}/sysctl.d/
        install -m 0644 ${WORKDIR}/90-system.conf          ${D}${sysconfdir}/sysctl.d/
}

FILES_${PN} += " ${sysconfdir} "
