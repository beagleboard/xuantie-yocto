EXTRA_OECONF:remove = "--enable-secure-rpc CPPFLAGS=`pkg-config --cflags libtirpc`"

EXTRA_OECONF:append = " --disable-secure-rpc"
