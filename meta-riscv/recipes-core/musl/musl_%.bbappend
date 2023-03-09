FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:riscv32 = "\
    file://0007-Emulate-wait4-using-waitid.patch \
    file://0008-riscv-Fall-back-to-syscall-__riscv_flush_icache.patch \
    file://0009-riscv32-Target-and-subtarget-detection.patch \
    file://0010-riscv32-add-arch-headers.patch \
    file://0011-riscv32-Add-fenv-and-math.patch \
    file://0012-riscv32-Add-dlsym.patch \
    file://0013-riscv32-Add-jmp_buf-and-sigreturn.patch \
    file://0014-riscv32-Add-thread-support.patch \
    file://0015-Change-definitions-of-F_GETLK-F_SETLK-F_SETLKW.patch \
    file://0016-riscv32-Wire-new-syscalls.patch \
    file://0017-Add-bits-reg.h-for-riscv32.patch \
    file://0001-riscv32-fix-inconsistent-ucontext_t-struct-tag.patch \
"
