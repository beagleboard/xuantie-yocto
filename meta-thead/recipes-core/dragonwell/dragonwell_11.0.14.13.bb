DESCRIPTION = "Dragonwell JDK for riscv"
LICENSE = "CLOSED"

DEPENDS = " zip-native unzip-native libx11 libxext libxrender libxrandr libxtst libxt cups fontconfig alsa-utils"
RDEPENDS:${PN} = " libx11 libxext libxrender libxrandr libxtst libxt cups fontconfig alsa-utils"

SRC_URI = " \
        git://github.com/alibaba/dragonwell11.git;name=src;branch=backport-openjdk19-riscv-port;protocol=https \
        git://git@gitlab.alibaba-inc.com/yunyao.zxl/riscv-thead-docker-dragonwell11-build.git;destsuffix=git/lib;name=lib;protocol=ssh \
        https://github.com/alibaba/dragonwell11/releases/download/dragonwell_11.0.7.2_GA/Alibaba_Dragonwell_11.0.7.2+9_Linux_x64.tar.gz;name=bootjdk \
"

SRCREV_src = "d391870de5f34958013d4b20fe8ffd786094bfe7"
SRCREV_lib = "e3abd3312710a2a48a8da682214f39412830474d"
SRC_URI[bootjdk.sha256sum] = "4b11fd0f08d496ef9c6aeb537c2366073bdb8434173956908f65cae12e96e22c"


S = "${WORKDIR}/git"


export DEBUG_LEVEL="release"
export IMAGES="images"
export JDK_VARIANT="thead"
export DRAGONWELL_VERSION="3"
export JVM_VARIANTS="server"
export VERSION_INTERIM="0"
export VERSION_UPDATE="14"
export VERSION_PATCH="13"

do_configure() {
    BUILD_NUMBER=0
    LC_ALL=C
    BOOTJDK=${WORKDIR}/dragonwell_11.0.7.2
    SYSROOT=${WORKDIR}/recipe-sysroot
    CONF_LEVEL=linux-riscv64-${JDK_VARIANT}-${JVM_VARIANTS}-${DEBUG_LEVEL}
    CXX=${TARGET_ARCH}-${TARGET_OS}-g++
    CC=${TARGET_ARCH}-${TARGET_OS}-gcc

    bash ./configure --openjdk-target=${TARGET_ARCH}-${TARGET_OS} \
                        --with-sysroot=${SYSROOT} \
                        --with-native-debug-symbols=external \
                        --with-boot-jdk=${BOOTJDK} \
                        --with-build-jdk=${BOOTJDK} \
                        --disable-warnings-as-errors \
                        --with-freetype=system \
                        --enable-unlimited-crypto \
                        --with-jvm-variants=${JVM_VARIANTS} \
                        --with-jdk-variant=${JDK_VARIANT} \
                        --with-debug-level=${DEBUG_LEVEL} \
                        --with-debug-level="release" \
                        --with-version-feature="11" \
                        --with-vendor-name="Alibaba" \
                        --with-vendor-url="http://www.alibabagroup.com" \
                        --with-vendor-bug-url="mailto:jvm@list.alibaba-inc.com" \
                        --with-version-pre="AJDK" \
                        --with-version-opt="Alibaba" \
                        --with-version-build="${BUILD_NUMBER}" \
                        --with-version-interim="${VERSION_INTERIM}" \
                        --with-version-update="${VERSION_UPDATE}" \
                        --with-version-patch="${VERSION_PATCH}" \
                        --with-version-date="$(date +%Y-%m-%d)" \
                        --with-zlib=bundled
}

do_compile() {
    LC_ALL=C
    HSDIS_SRC=${S}/lib/hsdis-riscv64-thead.so
    CONF_LEVEL=linux-riscv64-${JDK_VARIANT}-${JVM_VARIANTS}-${DEBUG_LEVEL}
    BUILD_PATH=build/${CONF_LEVEL}
    JDK_BUILD_DIR=`pwd`/build/${CONF_LEVEL}
    JDK_IMAGES_DIR=${JDK_BUILD_DIR}/${IMAGES}
    PRODUCT_NAME=dragonwell_11.${VERSION_INTERIM}.${VERSION_UPDATE}.${VERSION_PATCH}-GA

    make CONF=${CONF_LEVEL} ${IMAGES} # LOG=cmdlines

    if [ x"$USE_JENKINS" != "x" ]; then
        # for jenkins workflow around
        # these only work under jenkins pipeline
        # build test libraries
        make CONF=${CONF_LEVEL} LOG=cmdlines build-test-hotspot-jtreg-native
        make CONF=${CONF_LEVEL} LOG=cmdlines build-test-jdk-jtreg-native
    fi

    HSDIS_NAME=hsdis-riscv64.so
    cp -f ${HSDIS_SRC} ${JDK_BUILD_DIR}/jdk/lib/${HSDIS_NAME}
    if [ x"${IMAGES}" != "x" ]; then
        cp -f ${HSDIS_SRC} ${JDK_IMAGES_DIR}/jdk/lib/${HSDIS_NAME}
    fi
    rm -rf ${BUILD_PATH}/images/jdk/demo
    rm -rf ${BUILD_PATH}/images/jdk/lib/src.zip
    find ${BUILD_PATH}/images/jdk -name "*.debuginfo" -exec rm {} \;
    # tar -czvf ${PRODUCT_NAME}.tar.gz -C ${BUILD_PATH}/images jdk/
}

do_install() {
    CONF_LEVEL=linux-riscv64-${JDK_VARIANT}-${JVM_VARIANTS}-${DEBUG_LEVEL}
    BUILD_PATH=build/${CONF_LEVEL}
    mkdir -p ${D}/${libdir}/${PN}-${PV}
    cp -rf ${S}/${BUILD_PATH}/images/jdk/* ${D}/${libdir}/${PN}-${PV} 
}

INSANE_SKIP:${PN} = "libdir"

FILES:${PN} += " ${libdir} "
FILES:${PN}-dbg += " ${libdir}/.debug " 

PACKAGES = "${PN} ${PN}-dbg"
