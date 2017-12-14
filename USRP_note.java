/*
To implement processing, the user must write a "work" routine that reads inputs,
processes, and writes outputs.

A PMT is a special data type in gnuradio to serialize arbitrary data(序列化任意数据类型)

Public Header Files
Implementation Header File
Implementation Source File

All member variables are declared private and use the prefix 'd_'. 
As much as possible, all variables should have a set and get function.
The set function looks like void set_var(dtype var), and the get function looks like 
dtype var(). It does not always make sense to have a set or get for a particular variable,
but all efforts should be made to accommodate it

This file defines the make function for the public class. This is a member of the class,
which means that we can do interesting things, define multiple factor functions, etc. 
Most of the time, this simply returns an sptr to the implementation class.

int work(int noutput_items,
         gr_vector_const_void_star &input_items,
         gr_vector_void_star &output_items)
{}
noutput_items: total number of items in each output buffer 代表输出缓冲中元素的数目
input_items: vector of input buffers, where each element corresponds to an input port
output_items: vector of output buffers, where each element corresponds to an output port
buffer的向量，每一个buffer和一个端口相对应

  int general_work(int noutput_items,
                   gr_vector_int &ninput_items,
                   gr_vector_const_void_star &input_items,
                   gr_vector_void_star &output_items)
  {}
ninput_items是一个向量，向量中的元素是描述每个输入buffer的大小
This class overloads the general_work() method, not work()
The general work has a parameter: ninput_items
ninput_items is a vector describing the length of each input buffer
Before return, general_work must manually consume the used inputs
The number of items in the input buffers is assumed to be noutput_items
Users may alter this behaviour by overloading the forecast() method
若使每一个输入buffer的大小等于第一个参数 noutput_items，就得使用forecast()


A key, which can be used to identify a certain tag
A value, which can be any PMT
(Optional) A source ID, which helps identify the origin of this specific tag.

Messages are pure PMTs and they have no offset 
we have a different type of port for messages, which can't be sent to regular streaming ports.
We call these new types of ports message ports
Dotted lines denote connected message ports, as opposed to solid lines, 
which denote connected streaming ports

Another interesting fact is that we can connect more than one message output port 
to a single message input port, which is not possible with streaming ports

In fact, blocks that don't have streaming ports usually don't even have a work function.
对于同时具有消息端口和流式端口的块，这两个选项中的任何一个都可以，这取决于应用程序
我们强烈建议不要在函数内部处理消息，而是建议使用消息处理程序。在工作功能中使用消息
鼓励我们阻止工作等待消息到达。 这是一个工作功能的不良行为，它不应该阻止
如果块取决于要操作的消息，则使用消息处理器概念来接收消息，然后可以在调用工作功能时
用于通知块的动作

Unlike streaming ports, message ports are not defined in the I/O signature, 
but are declared with the message_port_register_* functions.
// Put this into the constructor to create message ports
message_port_register_in(pmt::mp("in_port_name"));
message_port_register_out(pmt::mp("out_port_name"));

To set up a function as the message handler for an input message port 
that is called every time we receive a message, we add line like this:
// Put this into the constructor after the input port definition:
set_msg_handler(
    pmt::mp("in_port_name"), // This is the port identifier
    boost::bind(&block_class_name::msg_handler_method, this, _1)  // [FIXME class name] Bind the class method
);

构建OOT意味着找到安装的GNU Radio库和头文件 以及其他依赖项
OOT只需要GNU Radio运行时库和头文件，如libgnuradio-runtime.so和gnuradio/block.h
更复杂的块可能使用GNU无线电系统的其他部分，例如FIR滤波器内核或FFT块
因此我们需要知道在哪里可以找到这些库和标头。 
我们还将链接到特定版本的GNU Radio，因此我们需要指定此版本，
以确保安装的GNU Radio满足项目的要求

gnuradio-config-info 查看gnuradio相关信息

扩展OOT模块
	1、因为我们将使用一个FIR滤波器，我们需要有一个数据成员代表使用的对象
	首先编辑头文件gr-howto/lib/derivative_ff_impl.h
	我们将包括fir_filter.h头文件，并创建一个类的私有成员d_fir
	2、我们现在打开C++源代码文件，derivative_ff_impl.cc实现对应的头文件
	需要在我们的构造函数中构建FIR滤波器块。
	通过创建一个新的gr::filter::kernel::fir_filter_fff来构建FIR滤波器
	我们在这里使用指针，所以我们还需要在析构函数中清理这个内存
	但是我们还要记住，为了使FIR滤波器工作，它需要处理所有N个抽头的输入数据以产生1个输出
	我们将OutOfTreeModules中描述的历史设置为2，以便在工作函数中查看1个样本
	3、构造函数中创建，work函数中使用
	4、在我们编译和测试这段代码之前，我们必须记住，我们现在使用libgnuradio-filter
	所以我们必须告诉cmake，因此，编辑gr-howto/CMakeLists.txt并将
	“FILTER”添加为“GR_REQUIRED_COMPONENTS”组件，也设置GNU Radio的最低版本号
	一般来说，它最好只使用当前使用的GNU Radio版本，对于额外的警惕，
	我们可以回到支持API和我们在任何块中需要的行为的最早版本
		set(GR_REQUIRED_COMPONENTS RUNTIME FILTER)  设置所需要的模块，这里是RUNTIME以及FILTER模块
		find_package(Gnuradio "3.7.3" REQUIRED)
	5、在原来编译的基础上，再次进入build文件夹并键入make，因为我们在添加新块和调整时
	编辑CMakeFiles，cmake知道重新运行自己来更新Makefile，现在运行“make test”或只是
	“ctest”运行我们构建的新的QA代码，以确保它的工作
	

gr_unittest添加了对检查浮点和复数的元组的近似相等性的支持
系统需要知道需要多少数据来确保每个输入数组中的有效性。 
如前所述，forecast（）方法提供了这些信息，因此无论何时你写
gr::block导数（对于同步块，这是隐式的），你必须覆盖它

虽然1：1实现适用于my_qpsk_demod_cb，但它不适用于在noutput_items
和输入要求之间具有更复杂关系的内插器，抽取器或块，也就是说，通过从
gr::sync_block，gr::sync_interpolator或gr::sync_decimator派生类而不是gr::block

.xml文件在GRC中显示的OOT模块和源代码之间提供用户界面，XML文件定义了
传递模块特定参数的接口，要访问GRC中的模块，要手动修改XML文件
应该修改由<sink>和<source>标签表示的输入和输出端口的数据类型

qa_文件执行通过调用此函数开始，gr_unittest以特定顺序自动调用函数def setUp（self）
用于在开始创建顶部块，tearDown（self）用于删除结束处的顶部块
在setUp和tearDown之间执行定义的测试用例。从前缀test_开始的方法被gr_unittest识别为测试用例
我们定义了两个测试用例test_001_gray_code_enabled和test_002_gray_code_disabled
测试用例的通常结构包括已知的输入数据和预期输出，创建流程图以包括源（输入数据
要测试的块（处理器）和宿（结果输出数据）
最后，将预期输出与结果输出数据进行比较

您可以拥有的最小历史记录是1，即，对于每个输出项目，您需要1个输入项。 
如果选择较大的值N，则表示您的输出项目是从当前输入项目和N-1个以前的输入项目计算的
调度程序为您处理这个。 如果将历史记录设置为长度N，则输入缓冲区中的前N个项目
包括N-1个先前的项目（即使您已经使用它们）历史记录存储在变量d_history中。
set_history（）在gnuradio / gnuradio-runtime / block.cc中定义

同步块允许用户写入每个端口消耗和产生相等数量的项的块。 同步块可以具有任何数量的输入或输出
当同步块具有零输入时，其被称为源。 当同步块具有零输出时，其称为汇

组件在命名空间gr::foo下描述
在Python中，每个TLC是gnuradio模块下的组件，所以要访问foo中的块，请执行：
from gnuradio import foo
foo是我们创建的顶级模块

Block naming conventions <name>_<input><ouput>

gri_：实现原语，有时我们有一个gr_ <foo>和一个gri_ <foo> 在这种情况下，
gr_ <foo>将从gr_block派生，gri_ <foo>将是函数的低级内核
qa_：Quality Assurance 测试代码

All class static data members shall begin with “s_” as in “s_<bar>”
数据成员以 d_ 前缀开头，且是私有成员，静态成员用s_开头
微数据成员提供  get set 方法

存储管理：强烈考虑使用boost智能指针模板，scoped_ptr和shared_ptr
scoped_ptr应该用于包含当我们退出当前作用域时需要删除的对象的指针的本地化
shared_ptr实现透明引用计数，是一个主要的胜利。 你永远不必担心调用删除。

std :: runtime_error：发生运行时错误时
std :: out_of_range：当要设置的变量不在给定范围内时
std :: invalid_argument：当参数设置为不正确的值时

QT GUI entry 
QT GUI label 两个作用相当于text，值可以使常亮，也可以是某个模块的ID
function probe模块和probe signal 搭配使用 

WIFIMAMAGER

A better way is to take your signal stream and compute:
     10*LOG10(DECIMATE(SINGLE_POLE_IIR_FILTER(COMPLEX_TO_MAG**2(SIGNAL))))

Only a few of the motherboards have an analog RSSI on the analog
receiver chips, and then exposed through one of the low-speed ADCs.

Analog RSSI (we can read it using AUX ADC)
Digital RSSI in FPGA (from output of ADCs)
Digital RSSI in host (computed however you like,
 from the channel zed signal sent over the bus by the USRP)
If you want to use the measured analog RSSI, it will measure
whatever passes through the analog channel filter. 
You can only change that bandwidth by changing inductors and capacitors.
 Note also that analog RSSI is only on the RFX900, RFX1200, RFX1800, 
 and RFX2400. The RFX400, TVRX, and DBSRX do not have that capability.

 From my understanding, the AGC (automatic gain control) makes getting RSSI readings
 difficult because your signal is getting amplified before it gets sampled. 
 So if you don't know the gain the AGC sets (which varies with time) it's hard to do RSSI.

 iNode客户端测量RSSI，通过iNode客户端采集RSSI
 利用无线网卡进行接收信号强度的测量
 无线客户端主要指的是待定位的无线终端，例如智能手机，带有无线网卡的笔记本电脑，
 可以向服务器发出定位请求
安装有无线网卡的笔记本电脑台式机、支持的智能手机以及等设备都有捕获环境中的无线信号的能力

Additionally, the rank of the transmission matrix – rank(H)rank(H) indicates how many data streams
 can be spatially multiplexed on a MIMO link. Thus the rank and the condition number 
 of the transmission matrix play an important role in a MIMO system design.
 
Ubuntu下MATLAB的安装：
1、	切换到root用户并进行挂载iso
su root
mount -o loop /media/w/G/matlabR2015b.iso /mnt
matlab 安装源是在 计算机G盘下面，需要进行挂载
2、	进入mnt并运行安装文件
cd /mnt
./install 
根据界面提示进行安装，安装时选不联网，并使用序破解里面提供的列号
3、	激活matlab，进入matlab的安装路径，安装路径是在上一步骤中选择的，
cd /uar/local/MATLAB/R2015b/bin
./matlab
这时会弹出激活界面，在Enter the full path to your license file, including the file name:
选择证书路径，license_standalone.lic进行激活。证书license_standalone.lic在破解中
并将破解文件中2015b文件夹里的那三个lib文件覆盖matlab的安装路径下/usr/local/MATLAB/R2015B/bin/glnxa64中
4、	如果在终端无法启动MATLAB，就进行链接
ln -s /usr/local/MATLAB/R2015b/bin/matlab  /usr/local/bin/matlab
前一个是matlab的安装位置，后一个是添加软链接的位置
终端键入matlab就可以启动MATLAB

当接收数据时，设备以恒定速率生成样本。当主机不能足够快地消耗数据时，会发生溢出
当UHD软件检测到溢出时，它将打印“O”或“D”到标准输出并将内联数据包推送到接收流中

当发送数据时，设备以恒定的速率消耗样品。当主机不能足够快地生成数据时，会发生下溢
当UHD软件检测到下溢时，它将打印“U”到标准输出并将消息包推送到异步消息流中
联系WIFI发射机，打印UUUUU,原因是USRP设备恒定的速率消耗样品，主机以恒定速率
生产数据，但是使用了2.0接口，导致数据不能及时交给USRP处理消耗，导致下溢
注意：“O”和“U”消息通常是无害的，但仅仅意味着主机无法跟上请求的速率
从三个方面考虑，USRP设备，主机处理性能，以及传输线，是否是传输线的制约造成的问题

USRP设备采用两个参考信号，以便同步时钟和时间：
10 MHz参考，为两个设备提供单个频率参考 REF IN
每秒脉冲（PPS），以跨设备同步采样时间  PPS IN
MIMO电缆将编码的时间消息从一个设备传输到另一个设备

USRP B200 B210 指示灯及其含义
电源指示 LED600  
	off 无电源供给
	红 外部电源供给
	蓝 USB电源供给
Channel 2 RX2
	off 无电源
	绿	接受信号
Channel 2 TX/RX
	off 无电源
	绿 接受
	红 发射
	橘黄 在接受和发射之间切换
Channel 2 RX2
Channel 1 TX/RX
	和以上一致

RMS 均方根   RMS＝（X1平方＋X2平方＋......＋Xn平方）/n 的1/2次方

car cdr 是在Lisp编程语言中引入的cons单元的基本操作,一个cons单元由两个指针组成; car操作提取第一个指针，并且cdr操作提取第二个指针
当cons单元用于实现单链表（而不是树和其他更复杂的结构）时，car操作返回列表的第一个元素，
而cdr返回列表的其余部分

流标签
流标签是一个消息，可以附着在任意的item上面，可以同步的发送item的元数据
attach any metadata to specific items，可以给任意的item(例如符合某个条件的item)添加流标签
	key 识别一个特定的流标签
	value  可以使任何的PMT类型
	SRCID 标签的来源
	添加流标签
	add_item_tag(0, // Port number
	                 nitems_written(0) + i, // Offset
	                 pmt::mp("amplitude_warning"), // Key
	                 pmt::from_double(std::abs(in[i])) // Value
	    		);
offset 标签偏移是一个绝对值，特定于某个端口，通过某个block的第一个item具有偏移量为0
nitems_read(port_num)和nitems_written(port_num)方法轻松地将相对样本索引转换为绝对样本索引
读取流标签
	std::vector tags;
	get_tags_in_range(
	    tags, // tags是将读取的标签存储到vector中
	    0, // Port 0
	    nitems_read(0), // Start of range
	    nitems_read(0) + 100, // End of range，和前100个item相关联的标签都会被读取
	    pmt::mp("my_tag_key") // Optional: Only find tags with key "my_tag_key" 过滤特定的标签
	);
	std::vector tags;
	get_tags_in_window( // Note the different method name
	    tags, // Tags will be saved here
	    0, // Port 0
	    0, // Start of range (relative to nitems_read(0))
	    100, // End of relative range
	    pmt::mp("my_tag_key") // Optional: Only find tags with key "my_tag_key"
	);

流标签传输策略
	TPP_ALL_TO_ALL：任何端口上输入的任何标签都会自动传播到所有输出端口（这是默认设置）
	TPP_ONE_TO_ONE：在端口N上输入的标签传播到输出端口N.仅适用于具有相同数量的输入和输出端口的块。
	TPP_DONT：输入块的标签不会自动传播。只有在此块中创建的标签(使用add_item_tag())才会显示在输出流上。
	我们通常使用@set_tag_propagation_policy在块的构造函数中设置标签传播策略
	当标签传播策略设置为TPP_ALL_TO_ALL或TPP_ONE_TO_ONE时，GNU无线电调度器使用
	任何可用的信息来确定哪个输出项对应于哪个输入项
	块可以读取它们并添加新的标签，但是现有的标签将以适当的方式自动下移到下游 ***

消息 纯PMT类型，没有偏移量的概念
消息端口有不同的类型，不能和流端口进行通信
流和item相关，消息则和PDU相关
消息一旦发布到块，会发生什么？这取决于实际的块实现，但有两种可能性：
	1）调用一个消息处理程序，它立即处理消息
	2）消息被写入FIFO缓冲区，并且块通常在work函数中可以使用它
与流端口不同，消息端口未在I / O签名中定义，而是使用message_port_register_ *函数声明
消息端口还具有标识符**而不是端口号**
// 将下面的代码添加到构造函数中，创建消息端口
message_port_register_in(pmt::mp("in_port_name"));
message_port_register_out(pmt::mp("out_port_name"));
// Put this into the constructor after the input port definition:
set_msg_handler(
    pmt::mp("in_port_name"), // This is the port identifier
    boost::bind(&block_class_name::msg_handler_method, this, _1) 
    // [FIXME class name] Bind the class method
);

文件sink使用stdio将原始样本写入文件。默认情况下一定数量的样本将在写入文件之前进行缓冲 样品数量因操作系统而异
如果只有少量的数据进入该文件接收器，则可以在流程图停止后找到该文件为空。 对于这种情况，
我们可以使用“unbuffered”选项，这将绕过缓冲操作，并将所有样本直接写入文件

gnuradio和matlab之间的通信 *
gnuradio中stream与vector之间的转换 *
gnuradio中设置top_block模块的QT或者WX属性，以及显示模块 *
Python调用matlab安装相应类库

并非所有的接受的数据包都会记录CSI数据
从接受的数据包中测量CSI数据
client模式，连接的AP发送数据包，目的是本地地址或者广播
AP模式，连接的client发送数据包
AD-HOC 模式 
monitor模式
如果发送非HT速率（例如，802.11a / g 6 Mbps速率），则也不会从连接的AP记录CSI数据 
由于实际上只有“一个”前导码，所以每802.11n A-MPDU（聚合批处理）只有一个CSI测量
除了ap模式，CSI Tool还提供monitor模式的用法。监听模式不需要路由器
但是至少需要2台安装Intel 5300网卡的电脑，它比ap模式更加稳定
可以发送指定数量的包，可以设置发送包之间的间隔，以及信道和带宽

import cv2
from mpl_toolkits.mplot3d import Axes3D
import numpy as np
import matplotlib.pyplot as plt

src = cv2.imread('./test.png')
temp = cv2.imread('./temp.png')

data = cv2.matchTemplate(src, temp,cv2.TM_CCOEFF_NORMED)
#下面才是正题
x = np.linspace(0,data.shape[1]-1,data.shape[1])
y = np.linspace(0,data.shape[0]-1,data.shape[0])

X,Y = np.meshgrid(x,y)
Z = []
#for i in range(len(x)):
        #Z.append(data[int(x[i])][int(y[i])])

fig = plt.figure()
ax = fig.add_subplot(111,projection='3d')

#ax.scatter(X,Y,data)
surf = ax.plot_surface(X,Y,data,  rstride=1, cstride=1, cmap='jet',
        linewidth=0, antialiased=False)
plt.colorbar(surf)
plt.savefig('result1.png',dpi=200)
plt.show()