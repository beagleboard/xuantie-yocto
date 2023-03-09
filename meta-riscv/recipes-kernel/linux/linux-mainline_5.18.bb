require recipes-kernel/linux/linux-mainline-common.inc

LINUX_VERSION ?= "5.18+"
KERNEL_VERSION_SANITY_SKIP="1"
PV = "${LINUX_VERSION}+git${SRCPV}"

BRANCH = "linux-5.18.y"
SRCREV = "${AUTOREV}"
# SRCPV sometimes fails to get,the requested URL returned error: 503.
# SRCPV = "${@bb.fetch2.get_srcrev(d)}"
SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${BRANCH} \
"
