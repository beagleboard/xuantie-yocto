SUMMARY = "A library to abstract stream I/O like serial port, TCP, telnet, etc"
HOMEPAGE = "https://github.com/cminyard/gensio"
LICENSE = "GPL-2.0 & LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=a0fd36908af843bcee10cb6dfc47fa67 \
                    file://COPYING;md5=bae3019b4c6dc4138c217864bd04331f \
                    "

SRCREV = "4ec19138aa968dea34e4bc4e022c1ad7ed4c43c0"

SRC_URI = "git://github.com/cminyard/gensio;protocol=https \
           file://0001-Modify-parameter-of-function-sctp_gensio_alloc.patch"

S = "${WORKDIR}/git"

inherit autotools

PACKAGECONFIG ??= "openssl tcp-wrappers"

PACKAGECONFIG[openssl] = "--with-openssl=${STAGING_DIR_HOST}${prefix},--without-openssl, openssl"
PACKAGECONFIG[tcp-wrappers] = "--with-tcp-wrappers,--without-tcp-wrappers, tcp-wrappers"
PACKAGECONFIG[swig] = "--with-swig,--without-swig, swig"

EXTRA_OECONF = "--without-python"

RDEPENDS_${PN} += "bash"
