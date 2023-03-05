FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
        file://80-wifi-station.network \
        file://90-system.conf \
        file://0001-Optimize-startup-performance.patch \
"

do_install:append:light-fm () {
	install -m 0644 ${WORKDIR}/80-wifi-station.network ${D}${systemd_unitdir}/network/80-wifi-station.network
}

do_install:append () {
        ln -sf ../systemd-modules-load.service ${D}${systemd_unitdir}/system/graphical.target.wants/systemd-modules-load.service
        install -d ${D}{sysconfdir}/sysctl.d/
        install -m 0644 ${WORKDIR}/90-system.conf          ${D}${sysconfdir}/sysctl.d/
}

FILES:${PN} += " ${sysconfdir} "

EXTRA_OEMESON += "-Ddefault-locale=en_GB.UTF-8"
