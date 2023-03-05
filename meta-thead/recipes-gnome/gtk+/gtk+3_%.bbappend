FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
                  file://0001-Support-xdg-top-levle-window-position-for-wayland.patch  \
"
