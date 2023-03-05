SUMMARY = "thead gstreamer plugin proprietary"
DESCRIPTION = "thead gstreamer plugin proprietary"
HOMEPAGE = "git@gitee.com:thead-yocto/gst1-plugins-thead-proprietary.git"
LICENSE = "CLOSED"
#LIC_FILES_CHKSUM = ""

COMPATIBLE_MACHINE = "(light-*|fire-*)"

inherit pkgconfig
DEPENDS = " "

SRC_URI = "git://git@gitee.com/thead-yocto/gst1-plugins-thead-proprietary.git;branch=master;protocol=http"
THEAD_LINUX_TAG ?= "${AUTOREV}"
SRCREV = "${THEAD_LINUX_TAG}"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${includedir}
    install -d ${D}${libdir}/csi_hal
    install -d ${D}${libdir}/gstreamer-1.0
    install -d ${D}${includedir}/gst-plugin
    install -d ${D}${datadir}/gst-app

    install -m 0755 ${S}/lib/gstreamer-1.0/*.so                             ${D}${libdir}/gstreamer-1.0
    install -m 0755 ${S}/include/gst-plugin/thead_camera/*.h                ${D}${includedir}/gst-plugin
    install -m 0755 ${S}/share/gst-app/thead_camera/thead_camera_app1       ${D}${datadir}/gst-app

    install -m 0755 ${S}/csi-camera-hal/libcommon.so                        ${D}${libdir}/csi_hal
    install -m 0755 ${S}/csi-camera-hal/libhal_camera.so                    ${D}${libdir}/csi_hal
}

FILES:${PN} += " ${libdir} "
FILES:${PN} += " ${includedir}"
FILES:${PN} += " ${datadir} "

INSANE_SKIP:${PN} += "debug-files file-rdeps dev-deps already-stripped"
INSANE_SKIP:${PN}-dev += "dev-elf"

do_package_qa[noexec] = "1"
EXCLUDE_FROM_SHLIBS = "1"
PACKAGES = "${PN}"
