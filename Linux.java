1、操作系统内核功能：
	进程管理
	内存管理
	文件系统
	网络功能
	硬件驱动
	安全机制
2、批处理系统，是现代造作系统的前身
3、Linux基本原则：
	由目的单一的小程序组成，组合小程序完成复杂功能
	一切皆文件
	尽量避免捕获用户接口，尽量不予用户进行交互，没有消息就是最好的消息
	配置文件保存为存文本格式
命令 选项 参数，的形式进行，选项是用来改变命令执行形式的，参数是命令作用对象
-字母  短选项
--单词 长选项
magic number魔数，程序执行的入口，一个程序的一些特殊字节
linux隐藏文件使用.开头的
ls -a 可以显示所有文件 .代表当前文件 ..父目录
ls -i显示文件的索引节点号 每个文件都有一个index node 简写为inode
ls -R 递归显示
cal 显示日历
tree 显示目录树
mkdir -p /usr/local/a/y/z 可以创建多级目录
mkdir -v 显示详细信息 
Linux命令类型
	type显示指定命令类型例如 type echo
	内置命令(shell内建)
	外部命令：是在某个路径下面的与命令名相同的可执行文件，比如安装了sublime text软件，对应的
		启动命令就是subl，在某个路径下面就有一个名为subl的可执行文件
printenv 显示环境
date 显示系统时间
whatis command显示命令的摘要以及在哪个章节中
命令帮助
	command --help
命令手册的使用方式 manual
	man command
	分章节
		1、用户命令
		2、系统调用
		3、库调用
		4、特殊文件(设备文件)
		5、文件格式(配置文件语法)
		6、游戏
		7、其他杂项
		8、管理命令(会修改硬件参数)
linux 文件系统：
	/boot 系统启动相关文件，如内核以及grub(bootloader)引导加载器等
	/dev  设备文件，Linux重要哲学思想之一就是一切皆文件，因此所有硬件设备也被识别为文件，作为设备访问的入口而存在
		块设备：随机访问，按数据块进行访问，例如磁盘，可以随机访问其中的某个文件，而不用逐个进行访问
		字符设备：线性访问，按字符为单位进行访问
	/etc 配置文件主要存放位置，主要是文本类型的文件，Linux有一个设计特点是配置文件保存为存文本格式
	/home 用户家目录，默认为home/username
	/lib 库文件，静态库.a，动态库.so(shared object)
		共享库在内存中存储一份即可，大大节约内存空间，静态库会直接链接到程序地址空间，作为程序的一部分存在的
		有些程序在一个机子上可以运行，但移植到另一个机子上就执行失败，可能是缺少相应地库文件，因此要补充相应的动态链接库
	/media 挂载点目录，光盘放入光驱，要与当前文件系统树上的某个节点相关联
		将某个设备与当前文件系统树上的某个文件相关联成为挂载
		该目录通常用于挂载移动设备，
	/mnt 用于挂载临时文件
	/opt 可选目录，一般是第三方文件的安装目录
	/proc 伪文件系统，存放内核映射文件，一般是内核中可调参数或者工作的统计数据，他们不是文件，
		只是表现出文件的样子而已，性能调优，性能数据监控有关
	/sys 伪文件系统，跟硬件设备相关的属性映射文件
	/tmp 临时文件，会定期清空
	/var 可变文件
	/bin 可执行文件，用户命令
	/sbin 可执行文件，管理命令
	/usr 全局共享只读文件 universal shared read-only
		/usr/bin
		/usr/sbin
		/usr/lib
		/usr/local/bin 一般是第三方东西，格式化后不影响系统启动
		/usr/local/sbin
		/usr/local/lib 

linux特点：
1、免费开源
2、支持多线程，多用户
3、安全性好
4、对内存和文件管理优越

Linux文件系统采用层级式的
/boot 引导文件存放目录
/root 超级用户相关文件存放目录
/home 普通用户相关文件存放目录
/usr 全局共享只读
/bin Linux常见普通命令所在的目录
/sbin 管理命令，需要一定的权限
/dev 设备文件，Linux设计哲学之一就是一切皆文件，硬件设备也被识别为文件
		作为设备的访问入口而存在于该目录下面，可以方便的访问设备，和访问文件和目录一样方便
/lib 库文件，这个目录是用来存放系统动态连接共享库的，几乎所有的应用程序都会用到
		这个目录下的共享库，共享库在内存中存储一份即可，大大节约内存空间
/etc 配置文件主要存放位置，主要是文本类型的文件，Linux有一个设计特点是配置文件保存为存文本格式
/mnt 用于挂载临时文件
/media 挂载点目录，将某个设备与当前文件系统树上的某个文件相关联称为挂载
/var 存放经常变化的文件

pwd 显示当前目录 Print Working Directory
shell:用户和Linux内核之间的壳，将用户键入的命令翻译成了Linux内核可以执行的指令

设置Linux运行级别
修改配置文件 /etc/inittab  id:5:initdefault 中的数字
	0：关机
	1：单用户
	2：多用户无网络
	3：多用户状态有网络服务
	4：系统未使用保留给用户
	5：gui
	6：系统重启  将数字设置为6 系统会重启再重启

ls -a 显示隐藏文件
mkdir 创建目录
rmdir 删除空文件夹

1、|管道命令 前面命令的结果交给|后的命令来处理
2、find / -name a.out   在根目录中按文件名查找 a.out的文件
3、grep "xx" a.out
	grep -n "xx" a.out    在a.out文件中查找 "xx"的内容，并用-n显示行号
4、> < 输入输出重定向
	ls -a > a.txt 将结果写入到文件中 覆盖写
	ls -a >> a.txt 将结果写入到文件中 追加写
	grep -n "xx" a.out > result.txt 在a.out文件中查找 "xx"的内容，-n显示行号，并将结果写到文件中

Linux每个用户必须属于一个组，一个用户可以属于多个组,不同的组拥有不同的操作权限 ****
当将一个用户添加到一个组的时候，就会拥有该组所具有的权限 **
	所有者:创建该文件的用户,谁创建的文件，天然就是该文件的所有者
	所在组:创建该文件的用户所在组，就是该文件的所在组
	其他组:除了文件的所有者和所属组的用户，系统的其他用户就是该文件的其他组

创建组 
	groupadd mygroup 创建一个mygroup 的组
	groupadd mygroup2 创建一个mygroup2 的组
	vim /etc/group
	cat /etc/group 查看组信息
	显示的信息是：
		...
		mygroup:X:1002:
		mygroup2:X:1003:
		... 其中1002、1003是创建的组的 ID号,是唯一的，不能重复
向某个组内添加用户
	useradd -g mygroup gw  将用户gw 添加到mygroup 组内
	useradd -g mygroup2 ggww  将用户ggww 添加到mygroup2 组内
	useradd -g mygroup guest
	查看所有用户的信息：
	vim /etc/passwd
	cat /etc/passwd
	...
	gw:X:1002:1002::/home/gw:		用户gw在ID号为1002的组内
	ggww:X:1003:1003::/home/ggww:
	guest:X:1004:1002::/home/guest:
	...
	分别是
		用户名:密码(已加密):用户ID:组ID可以看到用户gw和用户guest在同一个组内，组ID都是1002
		后面的空::是注释信息没有给出，在后面是用户的目录
创建用户
	useradd xingyue 添加一个名为xingyue的用户
	passwd xingyue 为xingyue设置密码
	userdel xingyue 删除xingyue用户
	userdel -r xingyue 删除xingyue用户以及用户家目录 **
	userdel jb51 注：删除用户jb51，但不删除其家目录及文件
	userdel -r jb51 注：删除用户jb51，其家目录及文件一并删除
	groupdel mygroup 删除用户组，注意只能将用户组的用户全部删除后才能删除用户组，否则不能直接删除用户组

- rwx rw- r-- 
rwxrw-r--  --- 
	第一个 - 代表文件类型	-代表普通文件	d代表目录	l代表链接文件
	文件所有者对该文件的权限 文件所属组对该文件的权限 其他组的用户对该文件的权限
	表示文件所有者对该文件拥有rwx权限，文件所属组中的用户对该文件拥有rw权限，
	其他组的用户对该文件拥有r权限
usermod -g mygroup ggww 将用户ggww从mygrou2 移动到组mygroup 中

chown xingue 文件名  修改文件的所有者为xingyue
chrgrp superuser 文件名 修改文件的所属组为superuser

一个用户gw创建了一个文件file.txt，文件的权限是rwx------， ****
也就是说只有文件的所有者具有rwx权限，其他用户sgw如何访问这个文件？
	首先文件的所有者gw先login，修改文件的权限给其他用户访问，然后再logout，
	然后其他用户sgw 再login，这样才能访问该文件
	注意当某个用户对某个文件不具有访问权限的时候，只能是文件的所有者或者root用户
	去修该文件的权限给其他用户访问，当修改了文件的权限之后，其他用户数才能进行访问，
	而且其他用户是不能直接修改文件的权限的

BIOS 中启用了相关的 CPU 虚拟化功能，否则将开启不了虚拟机
BIOS是英文"Basic Input Output System"的缩略词，"基本输入输出系统",
它是一组固化到计算机内主板上一个ROM芯片上的程序，它保存着计算机最重要的基本输入输出的程序、
开机后自检程序和系统自启动程序，它可从 CMOS 中读写系统设置的具体信息
其主要功能是为计算机提供最底层的、最直接的硬件设置和控制

linux下重命名文件或文件夹的命令mv既可以重命名，又可以移动文件或文件夹
	mv /home/w/Desktop/hello.java  /home/w/Desktop/build/
		将文件hello.java 移动到路径/home/w/Desktop/build/ 下面,文件名不变化
	mv /home/w/Desktop/hello.java  /home/w/Desktop/build/hello.c
		将文件hello.java 移动到路径/home/w/Desktop/build/ 下面,并重命名为hello.c 
		但是若hello.c存在，是文件夹，那么就将hello.java 移动到hello.c文件夹下，不存在就重命名
	文件夹和文件操作一样
	mv /home/w/Desktop/test/ /home/w/Desktop/build/ 
		将文件夹test移动到/home/w/Desktop/build/ 路径下面
	mv /home/w/Desktop/test/ /home/w/Desktop/build/tttest  
		但是若tttest存在，是文件夹，那么就将test移动到tttest文件夹下
	mv /home/w/Desktop/test/ /home/w/Desktop/build/tttest/ 
		将文件夹test移动到/home/w/Desktop/build/ 路径下面,并修改文件夹名字为 tttest
		但是若tttest存在，是文件夹，那么就将test移动到tttest文件夹下
	关键看最后一级是否存在文件夹，如果存在就移动到文件夹中，否则就重命名

vim 编辑器编译C文件
	在某个路径下面：
	vi hello.java 创建文件名为hello.c 的文件
	键入 i 进入插入模式 并输入代码
	键入Esc 进入命令模式
	输入: wq 保存并退出vim  q!退出vim，但不保存内容
	gcc -o hello hello.c 编译出可执行文件hello
	./hello 执行文件

Linux 安装JDK
	1、下载安装文件 例如 jdk1.7.0_13.tar.gz
	2、在某个路径下进行解压缩 tar -zxvf jdk1.7.0_13.tar.gz **
	3、配置环境变量  vim /etc/profile
		JAVA_HOME=/usr/java/jdk1.8.0_60
		CLASSPATH=$JAVA_HOME/lib/
		PATH=$PATH:$JAVA_HOME/bin
		export PATH JAVA_HOME CLASSPATH
		将以上键入profile文件末尾
	4、让/etc/profile文件修改后立即生效 source /etc/profile
	5、测试 java -version

linux中df命令的功能是用来检查linux服务器的文件系统的磁盘空间占用情况
df -l
df -h
df -h /boot/ 	查看/boot在哪个分区
Filesystem	Size	Used	Avial	Use%	Mounted on
/dev/sda1   18G    	4.0G    13G 	24%     	/
发现 /boot挂载在 /dev/sda1 分区下面,挂载点是 /

cd /boot
ls -h 显示的信息是和启动引导相关的文件
	abi-3.130-24-generic		memtest86+.elf
	config-3.13.0-24-generic	memtest86+_muliboot.bin
	grub						System.map-3.13.0-24-generic
	....
但是通过卸载命令
umount /boot/
cd /boot
ls -h
发现 /boot下面没有任何东西
mkdir test
mount /dev/sda1 /test  将分区挂载在 /test中
cd test
ls -l
发现test中和原来/boot中的内容完全一样

mount	[参数]	设备名称或者文件	挂载点
umount	设备名称或者文件

理解Linux文件系统 ***
	windows有几个分区，就有几个驱动器，每个分区会获得一个字母标识符，可以选用字母
	指定的分区上的文件和目录，文件结构是相互独立的
	对于Linux 无论有几个分区，分给哪一个目录使用，归根结底只有一个根目录，
	一个独立且唯一的文件结构，Linux每个分区都是组成整个文件系统的一部分 **
	采用一种挂载的处理方式，整个文件系统包含一整套的文件和目录，并且将一个分区和一个目录联系起来

history 命令，用于显示之前使用过的指令
history 10  显示最近使用过的10条指令
!4 直接执行数字4代表的指令

更改下BIOS安全选项设置：开机按 F2 进入BIOS，Boot---Lunch CSM---设置为 enable，
Security---secure boot control---设置为disable，然后按 F10 保存退出

rpm包 红帽包管理
rpm -qa  查询所有的rpm包
rpm -q mysql 查询MySQL是否安装
rpm -qi  包名  查询包的信息
rpm -i /home/w/xxx.rpm  安装
rpm -ivh /home/w/xxx.rpm  安装  v=vorbose  h=hash

 ps 命令就是最基本同时也是非常强大的进程查看命令
 ps -A 显示所有进程
 ps -a 显示现行终端机下的所有程序，包括其他用户的程序
 ps -u 以用户格式显示进程信息
 ps -x 显示没有控制终端的进程,也就是后台进程
 ps -aux

 运行 ps aux 的到如下信息：

root:# ps aux
USER      PID       %CPU    %MEM    VSZ    RSS    TTY    STAT    START    TIME    COMMAND
smmsp    3521    0.0    0.7    6556    1616    ?    Ss    20:40    0:00    sendmail: Queue runner@01:00:00 f

USER    用户名
UID    用户ID（User ID）
PID    进程ID（Process ID）
PPID   父进程的进程ID（Parent Process id）
SID    会话ID（Session id）
%CPU   进程的cpu占用率
%MEM   进程的内存占用率
VSZ    进程所使用的虚存的大小（Virtual Size）
RSS    进程使用的驻留集大小或者是实际内存的大小，Kbytes字节
TTY    与进程关联的终端（tty）
STAT    进程的状态：进程状态使用字符表示的（STAT的状态码）
START    进程启动时间和日期
TIME    进程使用的总cpu时间
COMMAND    正在执行的命令行命令

杀死某个进程
杀死进程最安全的方法是单纯使用kill命令，不加修饰符，不带标志 
	kill 123 终止进程号为123的进程
	killall 234 杀死234以及234所有子进程 
	kill -9 这个强大和危险的命令迫使进程在运行时突然终止，进程在结束后不能自我清理
	危害是导致系统资源无法正常释放，一般不推荐使用，除非其他办法都无效 
	当使用此命令时，一定要通过ps -ef确认没有剩下任何僵尸进程。只能通过终止父进程来消除僵尸进程
------------------------------------------------------------------------------------------------
top - 01:06:48 up  1:22,  1 user,  load average: 0.06, 0.60, 0.48
Tasks:  29 total,   1 running,  28 sleeping,   0 stopped,   0 zombie
Cpu(s):  0.3% us,  1.0% sy,  0.0% ni, 98.7% id,  0.0% wa,  0.0% hi,  0.0% si
Mem:    191272k total,   173656k used,    17616k free,    22052k buffers
Swap:   192772k total,        0k used,   192772k free,   123988k cached

PID USER      PR  NI  VIRT  RES  SHR S %CPU %MEM    TIME+  COMMAND
1379 root      16   0  7976 2456 1980 S  0.7  1.3   0:11.03 sshd
14704 root      16   0  2128  980  796 R  0.7  0.5   0:02.72 top
1 root      16   0  1992  632  544 S  0.0  0.3   0:00.90 init
2 root      34  19     0    0    0 S  0.0  0.0   0:00.00 ksoftirqd/0
3 root      RT   0     0    0    0 S  0.0  0.0   0:00.00 watchdog/0

01:06:48    当前时间
up 1:22    系统运行时间，格式为时:分
1 user    当前登录用户数
load average: 0.06, 0.60, 0.48    系统负载，即任务队列的平均长度，三个数值分别为 1分钟、5分钟、15分钟前到现在的平均值

total 进程总数
running 正在运行的进程数
sleeping 睡眠的进程数
stopped 停止的进程数
zombie 僵尸进程数
Cpu(s): 
0.3% us 用户空间占用CPU百分比
1.0% sy 内核空间占用CPU百分比
0.0% ni 用户进程空间内改变过优先级的进程占用CPU百分比
98.7% id 空闲CPU百分比
0.0% wa 等待输入输出的CPU时间百分比
0.0%hi：硬件CPU中断占用百分比
0.0%si：软中断占用百分比
0.0%st：虚拟机占用百分比

Mem:
191272k total    物理内存总量
173656k used    使用的物理内存总量
17616k free    空闲内存总量
22052k buffers    用作内核缓存的内存量
Swap: 
192772k total    交换区总量
0k used    使用的交换区总量
192772k free    空闲交换区总量
123988k cached    缓冲的交换区总量,内存中的内容被换出到交换区，而后又被换入到内存，但使用过的交换区尚未被覆盖，该数值即为这些内容已存在于内存中的交换区的大小,相应的内存再次被换出时可不必再对交换区写入。

序号  列名    含义
a    PID     进程id
b    PPID    父进程id
c    RUSER   Real user name
d    UID     进程所有者的用户id
e    USER    进程所有者的用户名
f    GROUP   进程所有者的组名
g    TTY     启动进程的终端名。不是从终端启动的进程则显示为 ?
h    PR      优先级
i    NI      nice值。负值表示高优先级，正值表示低优先级
j    P       最后使用的CPU，仅在多CPU环境下有意义
k    %CPU    上次更新到现在的CPU时间占用百分比
l    TIME    进程使用的CPU时间总计，单位秒
m    TIME+   进程使用的CPU时间总计，单位1/100秒
n    %MEM    进程使用的物理内存百分比
o    VIRT    进程使用的虚拟内存总量，单位kb。VIRT=SWAP+RES
p    SWAP    进程使用的虚拟内存中，被换出的大小，单位kb。
q    RES     进程使用的、未被换出的物理内存大小，单位kb。RES=CODE+DATA
r    CODE    可执行代码占用的物理内存大小，单位kb
s    DATA    可执行代码以外的部分(数据段+栈)占用的物理内存大小，单位kb
t    SHR     共享内存大小，单位kb
u    nFLT    页面错误次数
v    nDRT    最后一次写入到现在，被修改过的页面数。
w    S       进程状态(D=不可中断的睡眠状态,R=运行,S=睡眠,T=跟踪/停止,Z=僵尸进程)
x    COMMAND 命令名/命令行
y    WCHAN   若该进程在睡眠，则显示睡眠中的系统函数名
z    Flags   任务标志，参考 sched.h

netstat 命令用来显示整个系统目前的网络状况
netstat -an     netstat -anp 
route 命令可以直接使用，用来显示本机路由情况，kernel IP routing table
traceroute  命令可以直接使用，用来显示数据包在网络上的传输过程，显示本机到被trace的远程主机数据包在网络中走过的路径
	traceroute www.baidu.com 
	traceroute 127.0.0.1 trace本机
	记录按序列号从1开始，每个纪录就是一跳 ，每跳表示一个网关，有三个时间，单位是 ms
	其实就是-q 3默认参数，探测数据包向每个网关发送三个数据包后，网关响应后返回的时间
	有时我们traceroute 某台主机时，会看到有一些行是以***表示的，这样的情况，可能是防火墙封掉了
	ICMP的返回信息，所以我们得不到什么相关的数据包返回数据
	有时我们在某一网关处延时比较长，有可能是某台网关比较阻塞，也可能是物理设备本身的原因
	当然如果某台DNS出现问题时，不能解析主机名、域名时，也会有延时长的现象，可以加-n 参数来避免DNS解析，以IP格式输出数据
路由就是数据从源网络传送到目的网络的操作，数据在传输的过程中可能经过若干网络节点 **
网络中数据的传递是经过不同的路由器的，每个路由器在内部维护一个路由表，路由表保存了与该路由器相连的其他路由器信息

在任何路径下要使用某个命令(例如命令在/usr/local/mysoftware/x/y/bin等)，要修改环境变量
./mysql -u root -p 
mysql数据库的使用
	创建数据库 建表 添加数据 查询
	create database my_database;
	use my_database; 使用数据库my_database，而不是使用其他数据库
	create table user_table(userID int, userName vchar(20));
	insert into user_table values(0001, "张三");
	select * from user_table;
备份与恢复数据库
	./mysqldump -u root -p123456 my_database > /home/w/my_data.bak  -p123456中间不要打空格，将数据库备份到/home/w/my_data.bak文件
	mysql -u root -p123456 my_database < /home/w/my_data.bak

相对路径 从当前目录访问某个文件或文件夹
绝对路径 从根访问某个文件或文件夹
链接文件 类似于window下面的快捷方式
ln -s /build/Sort.java  /test/ln_Sort.java  	ln -s 源文件 目的文件
ls -l /test
	会显示  ln_Sort.java->Sort.java

.sh文件是脚本文件，文本文件，命令的集合，有执行权限
./mmmm.sh 执行mmmm.sh脚本
用户登录后会自动执行shell脚本文件
	.bashrc 位于主目录下面，
	它之前会执行系统脚本/etc/bashrc（主要是基本配置数据）
	.bash_profile位于主目录下面
	它之前会执行系统脚本/etc/profile（主要是配置环境变量）

希望系统登录就执行相应东西，就将他放到某个用户下的.bashrc下面
用户环境变量以及系统环境变量（linux下每个用户都有自己的一套相关配置文件）

alias 显示系统当前定义的alias，给命令定义一个别名
alias ls = 'ls -al'
alias dumpmysql = './mysqldump -u root -p123456 my_database > /home/w/data.bak'

压缩文件
zip file.zip /home/w/Sort.java  将文件Sort.java 压缩为file.zip
zip file.zip /home/w/Sort.java /home/w/Sort_copy.java  压缩多个文件
若压缩的是文件夹则加参数 -r
unzip 文件  解压文件

创建文件 
	vim hello.txt
	touch hello.txt

iwconfig
iw dev wlan0 scan 扫描AP
iw list 列出WiFi网卡信息
iw wlan0 disconnect  断开连接

linux下使用 sudo 命令，可以让普通用户也能执行一些或者全部的 root 命令

再谈文件的权限 **
- rwx rw- r--
分别是文件的所有者、所属组、其他组对该文件的操作权限
文件的所有者，哪个用户创建了该文件，天生就是该文件的所有者
用户w创建了文件a.cpp ，该文件的所有者是w ,以root创建了a.cpp ，该文件的所有者是root
用户w创建了文件a.cpp，修改文件的权限为 rw- r-- r-- ,然后w还能修改文件的内容，这是因为w是文件的所有者，
对文件具有rw 权限，并不是修改了文件权限为 rw- r-- r--，root用户具有rw权限，其他用户具有r 权限，
是root用户和文件所有者w都具有rw 权限,其他用户只具有r权限
区别文件所有者与其他用户对文件的访问权限问题

一个很长的命令需要root权限才能执行，不用再次输入才能执行，而是
sudo !!  q其中!!代表上一条命令

编辑一个文件，而忘了申请权限，不用强制退出，再次编辑，而是
:w !sudo tee %

安装Ubuntu 进行分区完毕选项  device for installation bootloader  选择/ 分区,不要选择/boot 分区

执行Linux命令时，我们既想把输出保存到文件中，又想在屏幕上看到输出内容，就可以使用tee命令
ls -al | tee /home/w/hello.txt

关于ifconfig命令用来配置网络
eth0 表示第一块网卡， 其中 HWaddr 表示网卡的物理地址，可以看到目前这个网卡的物理地址(MAC地址）是 00:50:56:BF:26:20
inet addr 用来表示网卡的IP地址，此网卡的 IP地址是 192.168.120.204，广播地址， Bcast:192.168.120.255，掩码地址Mask:255.255.255.0 
lo 是表示主机的回坏地址，这个一般是用来测试一个网络程序，但又不想让局域网或外网的用户能够查看，只能在此台主机上运行和查看所用的网络接口。比如把 HTTPD服务器的指定到回坏地址，在浏览器输入 127.0.0.1 就能看到你所架WEB网站了。但只是您能看得到，局域网的其它主机或用户无从知道。
第一行：连接类型：Ethernet（以太网）HWaddr（硬件mac地址）
第二行：网卡的IP地址、子网、掩码
第三行：UP（代表网卡开启状态）RUNNING（代表网卡的网线被接上）MULTICAST（支持组播）MTU:1500（最大传输单元）：1500字节
第四、五行：接收、发送数据包情况统计
第七行：接收、发送数据字节数统计信息
用ifconfig命令配置的网卡信息，在网卡重启后机器重启后，
配置就不存在。要想将上述的配置信息永远的存的电脑里，那就要修改网卡的配置文件