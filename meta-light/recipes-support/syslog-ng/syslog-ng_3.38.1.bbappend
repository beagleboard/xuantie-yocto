do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        sed -i 's,Wants=,#Wants=,' ${D}${systemd_unitdir}/system/${BPN}@.service
        sed -i 's,After=,#After=,' ${D}${systemd_unitdir}/system/${BPN}@.service
    fi
}
