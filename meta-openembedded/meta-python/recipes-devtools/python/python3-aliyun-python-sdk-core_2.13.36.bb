SUMMARY = "Aliyun Python SDK is the official software development kit. It makes things easy to integrate your Python application, library, or script with Aliyun services."
HOMEPAGE = "https://pypi.org/project/aliyun-python-sdk-core/"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fdec12b464a317e8c3aa36497bc97e8d"

SRC_URI[sha256sum] = "20bd54984fa316da700c7f355a51ab0b816690e2a0fcefb7b5ef013fed0da928"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-jmespath python3-cryptography"
