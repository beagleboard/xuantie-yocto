
do_install:append() {
	lnr ${D}${libdir}/libc.so ${D}${base_libdir}/ld-musl-riscv64xthead.so.1
}

FILES:${PN} += "${base_libdir}/ld-musl-riscv64xthead.so.1"
