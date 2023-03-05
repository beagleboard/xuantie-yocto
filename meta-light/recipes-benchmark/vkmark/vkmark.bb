SUMMARY = "Vulkan benchmark"
DESCRIPTION = "vkmark is a benchmark for Vulkan"
HOMEPAGE = "https://github.com/vkmark/vkmark"
BUGTRACKER = "https://github.com/vkmark/vkmark/issues"

LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING-LGPL2.1;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = " glm assimp vulkan-loader vulkan-headers wayland wayland-native wayland-protocols"

SRC_URI = "git://github.com/vkmark/vkmark.git;protocol=https \
           file://0001-Fix-do-configure-error.patch \
"
SRCREV = "30d2cd37f0566589d90914501fc7c51a4e51f559"

S = "${WORKDIR}/git"

inherit meson pkgconfig
