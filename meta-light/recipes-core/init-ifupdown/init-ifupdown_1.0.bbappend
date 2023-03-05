FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append_d1 = " \
    file://interfaces \
"