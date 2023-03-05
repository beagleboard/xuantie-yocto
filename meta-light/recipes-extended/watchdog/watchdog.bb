DESCRIPTION = "publish system wdt solution"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://aon_wdt_app;md5=6cdf1e225cc87188ec8aefbbfde5983d"

COMPATIBLE_MACHINE = "light-*"

DEPENDS = " linux-thead "

SRC_URI = "\
    file://aon_wdt_app \
    file://98-aon_wdt.preset \
    file://aon_wdt.service \
    file://aon_wdt_start_release.sh \
    file://aon_wdt_start_debug.sh \
    file://aon_wdt_stop.sh \
"

THEAD_BSP_TAG ?= "${AUTOREV}"
SRCREV = "${THEAD_BSP_TAG}"

S = "${WORKDIR}"

do_install() {

      install -d ${D}${sbindir}
      install -d ${D}${datadir}/aon_wdt
      install -d ${D}${systemd_unitdir}/system
      install -d ${D}${systemd_unitdir}/system-preset
      
      install -m 0777 ${WORKDIR}/aon_wdt_app                                                 ${D}${sbindir}
      install -m 0777 ${WORKDIR}/aon_wdt_stop.sh                                             ${D}${datadir}/aon_wdt
      install -m 0777 ${WORKDIR}/aon_wdt.service                                             ${D}${systemd_unitdir}/system
      install -m 0755 ${WORKDIR}/98-aon_wdt.preset     	                                     ${D}${systemd_unitdir}/system-preset
   if  echo "${RELEASE_BUILD}" | grep -q "1"; then
      install -m 0777 ${WORKDIR}/aon_wdt_start_release.sh                                    ${D}${datadir}/aon_wdt/aon_wdt_start.sh
   else
      install -m 0777 ${WORKDIR}/aon_wdt_start_debug.sh                                      ${D}${datadir}/aon_wdt/aon_wdt_start.sh
      
   fi
}

FILES:${PN} += " ${sbindir} "
FILES:${PN} += " ${datadir} "
FILES:${PN} += " ${systemd_unitdir} "

PACKAGES = "${PN}"

INSANE_SKIP:${PN} += " debug-files already-stripped dev-deps file-rdeps "
