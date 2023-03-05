FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
  
SRC_URI:append = " \
                  file://0001-Fix-light-b-close-weston-terminal-crash-need-check-l.patch \
"
