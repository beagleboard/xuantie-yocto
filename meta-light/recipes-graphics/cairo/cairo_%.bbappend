PACKAGECONFIG:append = " ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'glesv2 egl', '', d)}"
PACKAGECONFIG:remove = "opengl"
