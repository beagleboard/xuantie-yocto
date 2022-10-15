#!/bin/bash

sudo fastboot flash ram ./u-boot-with-spl.bin
sudo fastboot reboot
sleep 10
sudo fastboot flash uboot ./u-boot-with-spl.bin
sudo fastboot flash boot ./boot.ext4
sudo fastboot flash root ./light-fm-image-linux-light-beagle.ext4
sudo fastboot reboot
