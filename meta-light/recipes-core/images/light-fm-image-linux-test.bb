DESCRIPTION = "Linux Image for Light FM with Test tools"
LICENSE = "MIT"

require light-fm-image-linux.bb
require light-fm-image-test.bb

export IMAGE_BASENAME = "light-fm-image-linux-test"
