SUMMARY = "Alibaba Cloud Object Storage Python SDK 2.x. This version is not compatible with the previous version (Version 0.x). The package name is oss2 to avoid conflict with previous versions."
HOMEPAGE = "https://pypi.org/project/oss2/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6b3b4a911517b57999a946a456701c4f"

SRC_URI[sha256sum] = "335c52bec7d8f7ee05f3e816d18a125a86a0a6d014c9e9045ca3b91a2484bbd4"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-aliyun-python-sdk-kms"
