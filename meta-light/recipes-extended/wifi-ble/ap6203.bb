DESCRIPTION = "Light BeagleV Board Wifi/Ble Modules"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "light-beagle"


SRC_URI = " \
			file://BCM43013A0_001.001.006.1073.1102.hcd;md5=bbc4044aa3b25e4289269f29ae5d325f \
			file://nvram_ap6203bm.txt;md5=db67fde8ec22c90fb3073180bbb592bc \
			file://clm_bcm43013c1_ag.blob;md5=803973131733e41f36f6181c7336c95d \
			file://nvram_ap6203bm-edg-trigger.txt;md5=db67fde8ec22c90fb3073180bbb592bc \
			file://fw_bcm43013c1_ag.bin;md5=c0d141a1e6f78b9c55a7b167e816de0a \
			file://nvram_ap6203bm-offical.txt;md5=ac2d5e92965774647d99eed96e66793c \
         "

S = "${WORKDIR}"

inherit allarch

CLEANBROKEN = "1"

do_configure () {
    :
}

do_install() {
    echo "install wifi/ble dependent file"
    install -d ${D}${nonarch_base_libdir}/firmware

    install -m 0755 BCM43013A0_001.001.006.1073.1102.hcd     ${D}${nonarch_base_libdir}/firmware
    install -m 0755 nvram_ap6203bm.txt                       ${D}${nonarch_base_libdir}/firmware
    install -m 0755 clm_bcm43013c1_ag.blob                   ${D}${nonarch_base_libdir}/firmware
    install -m 0755 nvram_ap6203bm-edg-trigger.txt           ${D}${nonarch_base_libdir}/firmware
    install -m 0755 fw_bcm43013c1_ag.bin                     ${D}${nonarch_base_libdir}/firmware
    install -m 0755 nvram_ap6203bm-offical.txt               ${D}${nonarch_base_libdir}/firmware
}

FILES:${PN} += "${nonarch_base_libdir}/firmware/"

PACKAGES = "${PN}"
