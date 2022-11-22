SUMMARY = "Enable the kdump service"
DESCRIPTION = "kdump service"
LICENSE = "CLOSED"

TARGET_CC_ARCH += "${LDFLAGS}"

DEPENDS = "linux-thead"

SRC_URI = " \
	   file://kexec \
	   file://vmcore-dmesg \
	   file://kdump-tools \
	   file://kdump-conf \
	   file://kdump.service \
	   file://rootfs.cpio.gz;unpack=0 \
	   "
inherit bin_package systemd

SRCREV = "${AUTOREV}"
board ?= ""
board_a_name ?= "light-a-val"
board_a_path ?= "light_a_val"
board_a_pub_name ?= "light-a-public-release"
board_a_pub_path ?= "light-a-public-release"
board_b_product_name ?= "light-b-product"
board_b_product_path ?= "light_b_product"
board_b_release_name ?= "light-b-product-release"
board_b_release_path ?= "light_b_product_release"
board_b_pub_name ?= "light-b-public"
board_b_pub_path ?= "light-b-public"
board_ant_ref_name ?= "light-ant-ref"
board_ant_ref_path ?= "light_ant_ref"
board_beagle_name ?= "light-beagle"
board_beagle_path ?= "light_beagle"
VMLINUXPATH ?= ""

python() {
    if  d.getVar('MACHINE') == d.getVar('board_a_name'):
        d.setVar('board', d.getVar('board_a_path'))
    elif d.getVar('MACHINE') == d.getVar('board_a_pub_name'):
        d.setVar('board', d.getVar('board_a_pub_path'))
    elif d.getVar('MACHINE') == d.getVar('board_b_product_name'):
        d.setVar('board', d.getVar('board_b_product_path'))
    elif d.getVar('MACHINE') == d.getVar('board_b_release_name'):
        d.setVar('board', d.getVar('board_b_release_path'))
    elif d.getVar('MACHINE') == d.getVar('board_b_pub_name'):
        d.setVar('board', d.getVar('board_b_pub_path'))
    elif d.getVar('MACHINE') == d.getVar('board_ant_ref_name'):
        d.setVar('board', d.getVar('board_ant_ref_path'))
    elif d.getVar('MACHINE') == d.getVar('board_beagle_name'):
        d.setVar('board', d.getVar('board_beagle_path'))
    else:
        bb.warn("invalid MACHINE")

    d.setVar('VMLINUXPATH', "${WORKDIR}/../../../${board}-oe-linux/linux-thead/5.10.y-r0/linux-${board}-standard-build")
}

do_install () {
    install -d ${D}${bindir}
    install -d ${D}${sysconfdir}/default
    install -d ${D}${systemd_system_unitdir}
    install -d ${D}${localstatedir}/lib/kdump

    install -m 0755 ${WORKDIR}/kexec  ${D}${bindir}
    install -m 0755 ${WORKDIR}/vmcore-dmesg  ${D}${bindir}
    install -m 0755 ${WORKDIR}/kdump-conf ${D}${sysconfdir}/default
    install -m 0755 ${WORKDIR}/kdump-tools ${D}${bindir}
    install -m 0755 ${WORKDIR}/rootfs.cpio.gz ${D}${localstatedir}/lib/kdump

    if [ -f ${VMLINUXPATH}/vmlinux ]; then
        install -m 0755 ${VMLINUXPATH}/vmlinux ${D}${localstatedir}/lib/kdump
    else
        echo "vmlinux not exit"
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -m 0644 ${WORKDIR}/kdump.service ${D}${systemd_system_unitdir}/kdump.service
    fi

}

SYSTEMD_SERVICE_${PN} = "kdump.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"

FILES_${PN} += " ${bindir} "
FILES_${PN} += " ${sysconfdir} "
FILES_${PN} += " ${systemd_system_unitdir} "
FILES_${PN} += " ${localstatedir} "

PACKAGES = "${PN}"

INSANE_SKIP_${PN} += " debug-files already-stripped rpaths "
