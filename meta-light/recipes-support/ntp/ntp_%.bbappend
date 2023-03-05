FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

COMPATIBLE_MACHINE = "(^d1*)"

SRC_URI:append = " file://ntp.conf \
                   file://ntpd \
                 "
