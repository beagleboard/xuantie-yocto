FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
RDEPENDS:${PN} = " libgles1-mesa"

# SRC_URI:append = " \
#                   file://0001-Only-launch-wayland-for-gnome-shell.patch \
#                   file://0001-Remove-hot-corner-application-due-to-crash-issue.patch \
# "
