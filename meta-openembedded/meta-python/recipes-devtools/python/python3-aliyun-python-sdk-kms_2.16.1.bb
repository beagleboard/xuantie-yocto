SUMMARY = "Aliyun Python SDK is the official software development kit. It makes things easy to integrate your Python application, library, or script with Aliyun services.."
HOMEPAGE = "https://pypi.org/project/aliyun-python-sdk-kms/"
SECTION = "devel/python"
LICENSE = "Apache"
LIC_FILES_CHKSUM = "file://LICENSE;md5=24b76166491e4a1f8ec8cf6c0b355baf"

SRC_URI[sha256sum] = "a372737715682014bace68bd40fe83332f4fd925009a3eb110d41bc66f270e7a"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-aliyun-python-sdk-core"
