do_install:append() {
    rm ${D}${datadir}/fonts/truetype/SourceHanSansCN-ExtraLight.otf
    rm ${D}${datadir}/fonts/truetype/SourceHanSansCN-Heavy.otf
    rm ${D}${datadir}/fonts/truetype/SourceHanSansCN-Light.otf
    rm ${D}${datadir}/fonts/truetype/SourceHanSansCN-Medium.otf
    rm ${D}${datadir}/fonts/truetype/SourceHanSansCN-Regular.otf
}
