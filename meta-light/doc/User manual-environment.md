Yocto 开发环境推荐使用Linux + docker 的方式，请先安装Linux 基础OS，用户根据自己的需要选择Ubuntu，Centos等Linux 发行版本，具体安装方法本文不做详细介绍。安装完成后，在此OS 的基础上继续安装docker，然后构建docker image，将相关的开发环境构建在docker 里面，后续的开发都基于docker 内的环境进行，具体的构建方式如下。
​

# 安装docker
使用官方安装脚本自动安装
```bash
curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun
```
# 下载dockerfile
点击下载 [linux-dev-master.7z](linux-dev-master.7z) 
解压后进入linux-dev-master 目录，打开Dockerfile ，修改用户名和ID，"your the same user name asyour host"修改成用户host os的用户名：

```bash
ENV DOCKER_USER2 "your the same user name asyour host"
```
 "your user id" 的值记录在/etc/exports，打开后搜索自己的用户名所在的行，anonuid 字段的值即使ID。
```bash
ENV USER2_ID "your user id"
```
# 构建docker image
运行以下命令构建自己的环境。
```bash
docker build -t linux-dev-base:base .
```
这个docker镜像可以编译thead发布的 buildroot、yocto等Linux SDK。
默认密码为 `123`
​

查看构建的dokcer image，正常情况下可以看到如下结果：
```bash
xie@xie:~$ docker images
REPOSITORY       TAG       IMAGE ID       CREATED          SIZE
linux-dev-base   base      32207ad97b7a   12 minutes ago   2.13GB
ubuntu           18.04     886eca19e611   2 weeks ago      63.1MB
```
# 启动 docker
docker 启动可以使用 docker run 命令
```bash
docker run -u thead -dt --name linux-dev-{your_name}  -v {your_lock_home}:{your_home} linux-dev-base:base /bin/bash
```
注意：可以通过-v 选项挂载host 的一个或多个目录，起到类似共享文件的作用，其中：
your_name:		docker container 名称，起一个自己的名字，不要和别人重名
your_lock_home： host 本地路径
your_home： 		本地路径在docker 里的mount 路径
​

查询启动的docker container
```bash
docker ps |grep linux-dev-base
```
正常情况下能看到：
```bash
xie@xie:~$ docker ps |grep linux-dev-base
CONTAINER ID   IMAGE                 COMMAND       CREATED         STATUS         PORTS     NAMES
017e0217cab0   linux-dev-base:base   "/bin/bash"   3 minutes ago   Up 3 minutes   22/tcp    linux-dev
```


# 登录docker
执行如下命令登录docker。
```bash
docker exec -it linux-dev-{your_name} /bin/bash
```
此时已ssh 登录到docker 里的ubuntu 18.04，运行以下命令查询系统版本
```bash
thead@017e0217cab0:/$ lsb_release -a
No LSB modules are available.
Distributor ID: Ubuntu
Description:    Ubuntu 18.04.6 LTS
Release:        18.04
Codename:       bionic
```
在启动docker 一节中，对host 本地$HOME目录和 docker guest $HOME 做了映射，因此在docker 的home/thead目录下能看host $HOME 目录下的所有内容，并且所有在docker 该目录下的文件与host 完全同步，docker 关闭或删除都不受影响。



# docker 镜像迁移

在没有Docker Registry时,可以通过docker save 和 docker load 命令完成镜像迁移的过程，先将镜像保存为压缩包，然后在其他位置再加载压缩包。

在网络不受限制的host上执行以下命令生成docker image

```
cd linux-dev-master
docker build -t linux-dev-base:base .
```

查看生成的docker image，确认image 生成

```
docker images linux-dev-base:base
```

开始保存image

```
docker save  linux-dev-base:base| gzip >linux-dev-base:base.tar.gz
```

这步后可以在命令执行的当前目录下生成linux-dev-base:base.tar.gz

将tar包 拷贝linux-dev-base:base.tar.gz 到目标机上，然后用docker load 命令导入image

```
docker load -i linux-dev-base\:base.tar.gz
```

此时在迁移目标机上就可以看到image 了。

```
docker images linux-dev-base:base
```

迁移完毕后，就可以启动docker container 了，比如：

```
sudo docker run --network=host -u jinxie.jx -dt --name jinxie.jx_docker-ubuntu18 -v /home/jinxie.jx:/home/jinxie.jx -v /disk11/jinxie.jx:/home/jinxie.jx/workspace linux-dev-base:base /bin/bash
```

再：

```
sudo docker exec -it jinxie.jx_docker-ubuntu18  /bin/bash
```




# 其他环境配置
在日常开发中，用户需设置ssh key，git 配置等其他内容，请配置请参考《新手上手指南》
​