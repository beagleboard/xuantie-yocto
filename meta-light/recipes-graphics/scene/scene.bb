HOMEPAGE = "https://code.alibaba-inc.com/light_sdk/scene"
LICENSE = "CLOSED"
COMPATIBLE_MACHINE = "light-*"
PROVIDES = "scene"
S = "${WORKDIR}/git"
THEAD_BSP_TAG ?= "${AUTOREV}"
SRCREV = "${THEAD_BSP_TAG}"
SRC_URI = "git://git@gitee.com/thead-yocto/scene.git;branch=master;protocol=http \
          "
RDEPENDS_${PN} += " gc620-test plink-ai plink-dpu-client"

EXCLUDE_FROM_SHLIBS = "1"
do_install() {
    install -d ${D}${datadir}/scene
    install -m 0755 ${S}/usr/share/vi/isp/test/*.sh               ${D}${datadir}/scene/
    install -m 0755 ${S}/3in1/usr/share/vi/isp/test/*.sh          ${D}${datadir}/scene/
}

PACKAGES = "${PN}"
FILES_${PN} += " ${datadir}/scene/ "
DEPENDS += " gc620-test plink-dpu-client"
INSANE_SKIP_${PN} += " already-stripped useless-rpaths file-rdeps libdir "
