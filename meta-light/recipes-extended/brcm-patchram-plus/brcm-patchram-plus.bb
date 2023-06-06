#@DESCRIPTION: Variscite brcm_patchram_plus network apps"
#
# http://www.variscite.com

PR = "r0"
LICENSE = "GPLv2"

S = "${WORKDIR}"

LIC_FILES_CHKSUM = "file://brcm_patchram_plus.c;md5=afaa8fd9d26e2b698ca9110492f03265"
SRC_URI = "file://brcm_patchram_plus.c"

do_compile() {
        ${CC} ${CFLAGS} ${LDFLAGS} -o brcm_patchram_plus brcm_patchram_plus.c
}

do_install() {
        install -d ${D}${bindir}/
        install -m 0755 ${S}/brcm_patchram_plus ${D}${bindir}/
}

FILES:${PN} = "${bindir}/brcm_patchram_plus"
