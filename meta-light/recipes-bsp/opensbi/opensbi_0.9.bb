DESCRIPTION = "Light Opensbi Components"
LICENSE = "CLOSED"

inherit deploy
DEPENDS = "e2fsprogs-native"

COMPATIBLE_MACHINE = "light-*"

SRC_URI = " \
            git://git@gitee.com/thead-yocto/opensbi.git;branch=master;protocol=http \
            file://light_aon_fpga.bin;md5=9f0d26a65b54d7f72a21be519c1c09b4 \
            file://light_aon_fpga.elf;md5=447b37b51877dce013b21d845c6c602a \
            file://light_c906_audio.bin;md5=d11d1c42e6cbe432279286eac1eae940 \
            file://light_c906_audio.elf;md5=2663d4dab3fa10ec614467bf4a124fbe \
            file://lightA/light_aon_fpga.bin;md5=93d0bd559cda10adfd50463a142f8a37 \
            file://lightA/light_aon_fpga.elf;md5=11f6f5293f0b54e738d058116c4b9e98 \
            file://lightA/light_c906_audio.bin;md5=d11d1c42e6cbe432279286eac1eae940 \
            file://lightA/light_c906_audio.elf;md5=2663d4dab3fa10ec614467bf4a124fbe \
            file://lightB/light_aon_fpga.bin;md5=709d447f7973dc9789af54c1c820f383 \
            file://lightB/light_aon_fpga.elf;md5=87f55a3f0af394ea703eab379204dbb8 \
            file://lightB/light_c906_audio.bin;md5=67141bb799ccad041dd34c49081d6905 \
            file://lightB/light_c906_audio.elf;md5=f07966e101cb8c047b7b93c183dde9ac \
            file://light-ant-ref/light_aon_fpga.bin;md5=f87aa7be3675914d1842806f54f8a8d8 \
            file://light-ant-ref/light_aon_fpga.elf;md5=fa0df5c3f47a2475882aa171b7a72e96 \
            file://light-ant-ref/light_c906_audio.bin;md5=9124f7306370eb0d9e3f6178da1fa4fd \
            file://light-ant-ref/light_c906_audio.elf;md5=b4206da84148852df208088c96e4b2b3 \
            file://light-ant-discrete/light_aon_fpga.bin;md5=9b98df64aa4b95a32b1939e822ac912c \
            file://light-ant-discrete/light_aon_fpga.elf;md5=96d1e845e69d2a9b4e014d87e9e1e15a \
            file://light-ant-discrete/light_c906_audio.bin;md5=3c5d2ccb84c98fb1dbcacec72e49ee96 \
            file://light-ant-discrete/light_c906_audio.elf;md5=b4206da84148852df208088c96e4b2b3 \
            file://light-beagle/light_aon_fpga.bin;md5=6486aed6f815af4118e4e4a840ec5482 \
            file://light-beagle/light_aon_fpga.elf;md5=f447400d1df5ab59f23c738fe147260a \
            file://light-beagle/light_c906_audio.bin;md5=9124f7306370eb0d9e3f6178da1fa4fd \
            file://light-beagle/light_c906_audio.elf;md5=b4206da84148852df208088c96e4b2b3 \
            file://light-lpi4a/light_aon_fpga.bin;md5=6486aed6f815af4118e4e4a840ec5482 \
            file://light-lpi4a/light_aon_fpga.elf;md5=f447400d1df5ab59f23c738fe147260a \
            file://light-lpi4a/light_c906_audio.bin;md5=384e8de853ea93cc71fef6379a630fae \
            file://light-lpi4a/light_c906_audio.elf;md5=7ba187b91846cad61e5026bc63f1af6e \
            file://light-b-ref/light_aon_fpga.bin;md5=aaacb9a4382298f73472bb26a4c73a42 \
            file://light-b-ref/light_aon_fpga.elf;md5=501e828bb48bc6795bfd391518352fc2 \
            file://light-b-ref/light_c906_audio.bin;md5=9124f7306370eb0d9e3f6178da1fa4fd \
            file://light-b-ref/light_c906_audio.elf;md5=b4206da84148852df208088c96e4b2b3 \
            file://light-a-ref/light_aon_fpga.bin;md5=dfabc70fda5b845ee9d7123358a39bc8 \
            file://light-a-ref/light_aon_fpga.elf;md5=f8be0d51e17ad3fddd6cc721be42807d \
            file://light-a-ref/light_c906_audio.bin;md5=d11d1c42e6cbe432279286eac1eae940 \
            file://light-a-ref/light_c906_audio.elf;md5=2663d4dab3fa10ec614467bf4a124fbe \
	    file://light-b-power/light_aon_fpga.bin;md5=701b9540f0585ea8030139d36960e75b \
	    file://light-b-power/light_aon_fpga.elf;md5=b60dc93a181e09de2773c145ff04f79c \
	    file://light-b-power/light_c906_audio.bin;md5=9124f7306370eb0d9e3f6178da1fa4fd \
	    file://light-b-power/light_c906_audio.elf;md5=b4206da84148852df208088c96e4b2b3 \
          "

THEAD_BSP_TAG ?= "${AUTOREV}"
SRCREV = "${THEAD_BSP_TAG}"

S = "${WORKDIR}/git"

export ARCH?="riscv"
export CROSS_COMPILE="riscv64-linux-"
export PLATFORM="generic"
export TOOLCHAIN_DIR?="${EXTERNAL_TOOLCHAIN}"

PARALLEL_MAKEINST = "-j1"

do_compile() {
	oe_runmake
}

do_deploy () {
	install -m 0755 ${S}/build/platform/generic/firmware/fw_dynamic.bin ${DEPLOYDIR}/
	install -m 0755 ${S}/build/platform/generic/firmware/fw_dynamic.elf ${DEPLOYDIR}/

    if  echo "${MACHINE}" | grep -Eq "light-a-val|light-a-public-release"; then
		echo "Firmware INFO: light-evb opensbi and e902 c906 firmware build MACHINE = ${MACHINE}"
		install -m 0755 ${WORKDIR}/light_aon_fpga.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light_aon_fpga.elf ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light_c906_audio.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light_c906_audio.elf ${DEPLOYDIR}/
	elif echo "${MACHINE}" | grep -q "light-ant-discrete"; then
		echo "Firmware INFO: light ant-discrete  opensbi and e902 c906 firmware build MACHINE = ${MACHINE}"
		install -m 0755 ${WORKDIR}/light-ant-discrete/light_aon_fpga.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-ant-discrete/light_aon_fpga.elf ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-ant-discrete/light_c906_audio.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-ant-discrete/light_c906_audio.elf ${DEPLOYDIR}/
	elif echo "${MACHINE}" | grep -q "light-beagle"; then
		echo "Firmware INFO: light beagle  opensbi and e902 c906 firmware build MACHINE = ${MACHINE}"
		install -m 0755 ${WORKDIR}/light-beagle/light_aon_fpga.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-beagle/light_aon_fpga.elf ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-beagle/light_c906_audio.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-beagle/light_c906_audio.elf ${DEPLOYDIR}/
	elif echo "${MACHINE}" | grep -q "light-lpi4a"; then
		echo "Firmware INFO: light lpi4a  opensbi and e902 c906 firmware build MACHINE = ${MACHINE}"
		install -m 0755 ${WORKDIR}/light-lpi4a/light_aon_fpga.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-lpi4a/light_aon_fpga.elf ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-lpi4a/light_c906_audio.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-lpi4a/light_c906_audio.elf ${DEPLOYDIR}/
	elif echo "${MACHINE}" | grep -q "light-a-ref"; then
		echo "Firmware INFO: light-a-ref  opensbi and e902 c906 firmware build MACHINE = ${MACHINE}"
		install -m 0755 ${WORKDIR}/light-a-ref/light_aon_fpga.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-a-ref/light_aon_fpga.elf ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-a-ref/light_c906_audio.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-a-ref/light_c906_audio.elf ${DEPLOYDIR}/
	elif echo "${MACHINE}" | grep -q "light-b-ref"; then
		echo "Firmware INFO: light-b-ref  opensbi and e902 c906 firmware build MACHINE = ${MACHINE}"
		install -m 0755 ${WORKDIR}/light-b-ref/light_aon_fpga.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-b-ref/light_aon_fpga.elf ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-b-ref/light_c906_audio.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-b-ref/light_c906_audio.elf ${DEPLOYDIR}/
	elif echo "${MACHINE}" | grep -Eq "light-ant-ref|light-ant-ref-release"; then
		echo "Firmware INFO: light ant-ref  opensbi and e902 c906 firmware build MACHINE = ${MACHINE}"
		install -m 0755 ${WORKDIR}/light-ant-ref/light_aon_fpga.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-ant-ref/light_aon_fpga.elf ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-ant-ref/light_c906_audio.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-ant-ref/light_c906_audio.elf ${DEPLOYDIR}/
	elif echo "${MACHINE}" | grep -q "light-a-product"; then
		echo "Firmware INFO: light-a opensbi and e902 c906 firmware build MACHINE = ${MACHINE}"
		install -m 0755 ${WORKDIR}/lightA/light_aon_fpga.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/lightA/light_aon_fpga.elf ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/lightA/light_c906_audio.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/lightA/light_c906_audio.elf ${DEPLOYDIR}/
	elif echo "${MACHINE}" | grep -q "light-b-product-release"; then
		echo "Firmware INFO: light b opensbi and e902 c906 firmware build MACHINE = ${MACHINE}"
		install -m 0755 ${WORKDIR}/lightB/light_aon_fpga.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/lightB/light_aon_fpga.elf ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/lightB/light_c906_audio.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/lightB/light_c906_audio.elf ${DEPLOYDIR}/
	elif echo "${MACHINE}" | grep -Eq "light-b-product|light-b-public"; then
		echo "Firmware INFO: light b opensbi and e902 c906 firmware build MACHINE = ${MACHINE}"
		install -m 0755 ${WORKDIR}/lightB/light_aon_fpga.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/lightB/light_aon_fpga.elf ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/lightB/light_c906_audio.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/lightB/light_c906_audio.elf ${DEPLOYDIR}/
	elif echo "${MACHINE}" | grep -q "light-b-power"; then
		echo "Firmware INFO: light b power opensbi and e902 c906 firmware build MACHINE = ${MACHINE}"
		install -m 0755 ${WORKDIR}/light-b-power/light_aon_fpga.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-b-power/light_aon_fpga.elf ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-b-power/light_c906_audio.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light-b-power/light_c906_audio.elf ${DEPLOYDIR}/
	else
		echo "Firmware INFO: light general opensbi and e902 c906 firmware build MACHINE = ${MACHINE}"
		install -m 0755 ${WORKDIR}/light_aon_fpga.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light_aon_fpga.elf ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light_c906_audio.bin ${DEPLOYDIR}/
		install -m 0755 ${WORKDIR}/light_c906_audio.elf ${DEPLOYDIR}/
	fi

    if [ ! -d ${DEPLOY_DIR_IMAGE}/.boot ]; then
       mkdir -p ${DEPLOY_DIR_IMAGE}/.boot
    fi

    if [ -f ${DEPLOYDIR}/fw_dynamic.bin ]; then
       cp -f ${DEPLOYDIR}/fw_dynamic.bin ${DEPLOY_DIR_IMAGE}/.boot
    fi

    if [ -f ${DEPLOYDIR}/light_aon_fpga.bin ]; then
       cp -f ${DEPLOYDIR}/light_aon_fpga.bin ${DEPLOY_DIR_IMAGE}/.boot
    fi

    if [ -f ${DEPLOYDIR}/light_c906_audio.bin ]; then
       cp -f ${DEPLOYDIR}/light_c906_audio.bin ${DEPLOY_DIR_IMAGE}/.boot
    fi

    dd if=/dev/zero of=${DEPLOY_DIR_IMAGE}/boot.ext4 count=10000 bs=4096
    mkfs.ext4 -F  ${DEPLOY_DIR_IMAGE}/boot.ext4 -d ${DEPLOY_DIR_IMAGE}/.boot
}

do_deploy[nostamp] = "1"
addtask deploy before do_build after do_install

FILES:${PN} += " ${datadir} "
PACKAGES = "${PN}"

INSANE_SKIP:${PN} += " debug-files "


