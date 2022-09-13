DESCRIPTION = "The Vulkan Samples is collection of resources to help develop optimized Vulkan applications."
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=48aa35cefb768436223a6e7f18dc2a2a"

SRC_URI = "gitsm://github.com/KhronosGroup/Vulkan-Samples.git \
           file://0001-CMakeLists.txt-do-not-hardcode-lib-as-installation-t.patch \
           "
UPSTREAM_CHECK_COMMITS = "1"
SRCREV = "f52361d3cd6ac8c30fc3365a464b4e220c32cfd6"

UPSTREAM_CHECK_GITTAGREGEX = "These are not the releases you're looking for"
S = "${WORKDIR}/git"

REQUIRED_DISTRO_FEATURES = 'vulkan'

inherit cmake features_check

FILES_${PN} += "${datadir}"
