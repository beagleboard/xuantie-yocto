FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

COMPATIBLE_MACHINE:append = "|qemuriscv32"

SRC_URI:append:riscv32 = " file://0001-perf-Alias-SYS_futex-with-SYS_futex_time64-on-32-bit.patch"
