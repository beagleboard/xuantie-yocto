FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " file://0001-Add-special-workaround-to-gdm-service-for-fpga-test.patch"
