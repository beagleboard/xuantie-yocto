IMAGE_INSTALL += "image-proprietary"

IMAGE_INSTALL += " \
    memtool \
"

IMAGE_INSTALL += " \
   gstreamer1.0 \
   gstreamer1.0-plugins-base \
   gstreamer1.0-plugins-bad \
   gstreamer1.0-plugins-good \
   gstreamer1.0-plugins-ugly \
   gstreamer1.0-omx \
   gstreamer-plugin-private-proprietary \
"


IMAGE_FSTYPES:remove = "cpio.gz cpio cpio.gz.u-boot cpio.bz2"
