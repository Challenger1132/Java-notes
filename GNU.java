阅读关于CSI相关论文
对用USRP获取CSI数据进行处理
用USRP实现RSSI的计算
关注CSI tool
专业术语看不懂就查资料，英语不好就多看，密密麻麻全是中文得翻译
每天坚持看两篇与自己所做领域相关的英语文章，一直坚持到最后，。。。。
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

关于MATLAB：
有符号整数型：int8，int16，int32，int64
无符号整数型：uint8，uint16，uint32，uint64
单精度浮点型：single
双精度浮点型：double
logical: true false
字符串型：char
单元数组型：cell
结构体型：struct
函数句柄型：function_handle

matlab中小数取整的函数大约有四个：floor、ceil、round、fix
若 A = [-2.0, -1.9, -1.55, -1.45, -1.1, 1.0, 1.1, 1.45, 1.55,  1.9, 2.0];
floor:朝负无穷方向靠近最近的整数；
floor(A)
ans =
    -2    -2    -2    -2    -2     1     1     1     1     1     2
ceil:朝正无穷方向靠近最近的整数；
ceil(A)
ans =
    -2    -1    -1    -1    -1     1     2     2     2     2     2
round:取最近的整数（相当于四舍五入）
round(A)
ans =
    -2    -2    -2    -1    -1     1     1     1     2     2     2
fix:取离0最近的整数
 fix(A)
ans =
    -2    -1    -1    -1    -1     1     1     1     1     1     2

length计算素组的长度，矩阵的行和列中最大值
size 返回行和列

row 是横向的， 排的意思
column 是纵向的， 列的意思

numel
功能：计算一个数组所有的元素数或指定下标的元素总数

x是二维矩阵：
x(1, :) 提取矩阵的第一行
x(:, 1) 提取矩阵的第一列
x(1:3, :) 提取矩阵的前三行
x(:, 1:3) 提取矩阵的前三列
x([1,4,6], :) 提取矩阵的第1 4 6行
x(:, [1,4,6]) 提取矩阵的第1 4 6列
x(3,4) 取出矩阵第三行第四列处的元素
x([2,4], [4,5]) 画方格，提取矩阵第2,4行与第4,5列出的元素

>> x = 1:10
x =
     1     2     3     4     5     6     7     8     9    10
>> y = 4:5
y =
     4     5
>> [m,n] = meshgrid(x,y)
m =
     1     2     3     4     5     6     7     8     9    10
     1     2     3     4     5     6     7     8     9    10
n =
     4     4     4     4     4     4     4     4     4     4
     5     5     5     5     5     5     5     5     5     5
meshgrid 函数的作用是取格点，x的坐标是1到10，y的坐标是4到5，在x和y规定的区域划格点
反映在m和n中，注意坐标元素对应
注意矩阵是先行再列，正好和常规坐标轴y轴和x轴相对应，而meshgrid第一个参数是x,第二个参数是y，
这里要注意

MATLAB中的reshape函数对矩阵进行变形是按照列进行的，无论源数据是一个行向量还是列向量
都是源数据先满足第一列，而后满足第二列进行，在必要的时候需要进行转置
gnuradio写数据是实数虚数交替进行，在进行读取数据的时候 t = fread (f, [2, count], 'float');
刚好转换成第一行是实数，第二行全是虚数的形式，
源数据：
I
Q
I
Q
I
Q
...
I I I I I
Q Q Q Q Q ...
t = fread (f, [2, count], 'float');实际对源数据进行了reshape(ori, 2,count)

MATLAB cftool是曲线拟合工具箱
	从工作空间选择数据，设置参数进行拟合，残差图、排除异常值(exclude outliers)等等
	将你和的结果导入到工作空间：在底部的table of fits 中选中行右键，save ... to Workspace
	选择file -- print to Figure 设置图像的显示参数等等、当前界面下生成图像的code
	或者将拟合过程封装成函数，方便调用 -- file -- generate code 

	结果中的R-square = (sst - sse) / sst
	R-square是“确定系数”是通过数据的变化来表征一个拟合的好坏，正常取值范围为[0 1]越接近1，表明方程的变量对y的解释能力越强，这个模型对数据拟合的也较好

数理统计中，残差是指实际观察值与估计值（拟合值）之间的差
MATLAB中的所有元素都是按列进行排列的 A(:)是将所有元素按列排序，矩阵转化为向量
std是标准差，var是方差，方差和标准差都有有偏和无偏估计
var(data)和var(data,0)效果一样，是无偏方差，除以的是N - 1
var(data,1)表示有偏方差，除以的是 N
var第三个参数表示对于矩阵是按行求，还是案列求，1,2分别表示按列和按行进行求
同理std是标准差的函数，与方差参数类似

方差的公式是：实际值与期望值之差的平方和再除以N
这个公式是针对度量总体(population)的，是全体数据，
而matlab的函数var和std认为操作数是度量总体的一个样本(sample)
所以使用的公式为实际值与期望值之差的平方和再除以 N-1
一种是度量总体，一种是度量中体的一个样本

自己求方差和标准差和MATLAB自带函数不一致实际是除以N以及除以N-1的问题
关于N和N-1的问题
因为样本我们认为是i.i.d的，N个样本求均值时，是除以N，而求方差时除以N-1，是因为这个时候只有N-1个样本是独立的
（N*mean=N1+N2+.....）,因为求方差的时候我们引进了mean,mean和每个样本有关系

当X是一个向量 var(X) 和cov(X) 是一样的，计算X的方差
当X时一个矩阵的时候 cov(X) 计算矩阵的协方差矩阵，以矩阵的每一行一个单位，每一行是一个项，
每一列是项的值
cov(x, y) 计算x和y的协方差矩阵，协方差矩阵有4个元素 c(x,x) c(x,y)
													c(y,x) c(y,y)
						当x和y是元素数目相同的矩阵的时候，计算cov(x(:), y(:))
						
	>> x = rand(4,4);
	>> y = rand(1,16);
	>> cov(x,y)
	ans =

		0.0819    0.0569
		0.0569    0.1163
	也就是x和y维度虽然不一样，但是元素数目一样，可以进行cov计算，其实是cov(x(:), y(:)) 结果是4个元素
	就是两个向量或者矩阵进行cov 都会先将其转换为列向量(整个矩阵转换为列向量x(:)) 然后计算协方差矩阵
	(结果是4个元素)	
	但是一个矩阵进行cov,计算矩阵列之间的协方差矩阵，结果协方差矩阵维度是 size(x,2) * size(x,2) 大小

计算相关系数矩阵，用法和cov类似 co = corrcoef(x)
计算x的夹角余弦：
	将矩阵转化为单位向量  nx = normc(x)
						cthta = nx' * nx

距离矩阵 一个矩阵，每一行视为一个样本，计算行与行之间的距离的矩阵
	距离矩阵主对角线元素全为0
	系数矩阵主对角线元素全为1
	协方差矩阵主对角线元素全为方差

要明确矩阵的行和列代表的意义 矩阵的每行是一个样本，每列是一个维度
每一行表示一个样品，每一列表示一个指标

MATLAB聚类分析：
M个元素，两两形成关系对，共有M*(M-1) / 2 个关系对

类间距离 按照某种规则计算的类与类之间的距离，例如 类元素之间的最小距离定义为类之间的距离
最长距离，平均距离，重心距离，离差平方和距离

类 包含了很多样本
类之间的距离   样本之间的距离
n个样品的p元观测数据组成一个n*p的矩阵，每一行表示一个样品，每一列表示一个指标
聚类分析的思想就是在样品之间定义距离，在指标之间定义相似系数
每个样品可以看成p元空间的一个点
	
x.'  求矩阵的非共轭转置
x' 	求矩阵的共轭转置
x =
   1.0000 + 2.0000i   3.0000 + 4.0000i   4.0000 + 4.0000i
   
>> x.'
ans =
   1.0000 + 2.0000i
   3.0000 + 4.0000i
   4.0000 + 4.0000i

>> x'
ans =
   1.0000 - 2.0000i
   3.0000 - 4.0000i
   4.0000 - 4.0000i

>> conj(x)   x的共轭
ans =
   1.0000 - 2.0000i   3.0000 - 4.0000i   4.0000 - 4.0000i
   
>> transpose(x)   x的非共轭转置
ans =
   1.0000 + 2.0000i
   3.0000 + 4.0000i
   4.0000 + 4.0000i 

首先,置信水平和置信度应该是一样的,就是变量落在置信区间的可能性,“置信水平”就是相信变量在
设定的置信区间的程度,是个0~1的数,用1-α表示.
置信区间,就是变量的一个范围,变量落在这个范围的可能性是就是1-α.
显著性水平就是变量落在置信区间以外的可能性,“显著”就是与设想的置信区间不一样,用α表示.
显然,显著性水平与置信水平的和为1.
显著性水平为0.05时,α=0.05,1-α=0.95
如果置信区间为（-1,1）,即代表变量x在（-1,1）之间的可能性为0.95.
0.05和0.01是比较常用的,但换个数也是可以的,计算方法还是不变.
总之,置信度越高,显著性水平越低,代表假设的可靠性越高,越好.
   
关于FFT与IFFT：
% 实现对时域信号进行离散傅里叶变换
function Xk = myfft(xn)
	N = length(xn);
	K = N;
	n = 0:N-1;
	k = 0:N-1;
	Wn = exp(-j*2*pi/N);
	base = n'*k; % N*N
	Wnnk = Wn.^base;
	Xk = xn*Wnnk';
end
% 实现对时域信号进行逆离散傅里叶变换
function xn = myifft(Xk)
	N = length(Xk);
	K = N;
	n = 0:N-1;
	k = 0:N-1;
	Wn = exp(-j*2*pi/N);
	base = n'*k; % N*N
	Wnnk = Wn.^(-base);
	xn = Xk*Wnnk' / N;
end

% 自定义的函数与自带函数不一样，复数中间的符号相反，但是模值相等,不论是fft 还是iffft
傅里叶变换对行进行和对列向量进行得到结果对称，这一点要注意

“where n is the size of X in the first nonsingleton dimension”
X的第一个非单一元素维度
若X为一个m1 × m2 × m3 ×...× mk的矩阵   高维矩阵**
如果m1大于1，那么第一个非单一元素维度就是m1
如果m1为1，而m2大于1，那么那么第一个非单一元素维度就是2
如果m1、m2、...、mp都为1，而mp+1大于1，那么那么第一个非单一元素维度就是mp+1
可用函数squeeze()去除矩阵的一维维度
N点长度不够补零，大于截断
Y = fft(X,n) returns the n-point DFT. fft(X) is equivalent to fft(X, n) where n is
the size of X in the first nonsingleton dimension.If the length of X is less than n, 
X is padded with trailing zeros to length n. If the length of X is greater than n,
the sequence X is truncated. When X is a matrix, the length of the columns are 
adjusted in the same manner.(矩阵的每一列都按相同的方式进行调整，即每一列都进行补0或者截断)
MATLAB是“按列进行”的 **

对于二维平面上的数据，我们可以计算出在x方向上的方差δx以及y方向上的方差δy，然而
数据的水平传播和垂直传播不能解释明显的对角线关系，平均而言，如果一个数据点的x值增加，则y值也将增加
这产生了正相关。这种相关性可以通过扩展方差概念到所谓的数据“协方差”捕捉到
协方差是数据相关性的度量，且是二维的，即一个维度和另一个维度之间的相关性的刻画，不是三个维度之间的
如果x与y是正相关的，那么y和x也是正相关的，协方差矩阵是对称阵，其对角线上是方差，非对角线上是协方差
二维正态分布数据由它的均值和2x2协方差矩阵就可以完全解释。同样，一个3x3协方差矩阵用于捕捉三维数据的传播，
一个NxN协方差矩阵捕获N维数据的传播。只是高维数据不具有形象化

A cell array is a collection of containers called cells in which you can store 
different types of data
精华之处就是在可以存储不同类型的数据，可以是Matlab的类型或者自定义的类型

pdist函数：计算矩阵X各行之间的距离，注意第二个参数使求解按照某种算法进行，如欧式距离、名氏距离等等
是矩阵两行之间的距离
d = pdist(X); 得到的是一个矩阵各行之间的一个行向量，还可以添加一个参数，按照某种算法求两个向量之间的距离
dm = squareform(d); 将距离向量变换为距离矩阵的形式，距离矩阵是一个实对称矩阵
dd = tril(dm); 距离矩阵的形式变换为上三角的形式
lk = linkage(d); linkage函数的到系统聚类树，要用到pdist的结果，注意这里也有一个参数，计算的是类间距离
dg = dendrogram(lk, d); 得到谱系聚类图,如果d小于30可以省略
cc = cluster(lk, k); linkage的结果传给cluster函数进行聚类，k是聚类数目
ch = cophenent(lk, d); 利用pdist函数生成的d和linkage函数生成的lk计算cophenet相关系数
选择样本之间(矩阵的某一行)不同的距离算法，不同的类间距离，如何评估聚类的效果呢？
用复合相关系数 越接近1 聚类效果越理想

对linkage函数返回结果的分析：
对于原始矩阵X，共M行(M个样本)，用pdist函数计算，返回一个 M*(M-1)/2的行向量
(每一个元素是各行之间的距离， M*(M-1)/2代表各行之间的组合数)
linkage函数返回结果lk则是(M-1)*3的矩阵,lk矩阵的前两列是索引下标列，
代表矩阵X中哪两行应该聚类到一起，也就是表示哪两个序号的样本可以聚为同一类，第三列为这两个样本之间的距离
除了M个样本以外，对于每次新产生的类，依次用M+1、M+2、…来标识

lk = linkage(d)
lk =
     3.0000     4.0000     0.2228
     2.0000     5.0000     0.5401
     1.0000     7.0000     1.0267
     6.0000     9.0000     1.0581
     8.0000    10.0000     1.3717
上例中表示在产生聚类树的计算过程中，第3和第4点先聚成一类，他们之间的距离是0.2228，
以此类推。要注意的是，为了标记每一个节点，需要给新产生的聚类也安排一个标识，MATLAB中
会将新产生的聚类依次用M+1,M+2,....依次来标识。比如第3和第4点聚成的类以后就用7来标识，
第2和第5点聚成的类用8来标识，依次类推。通过linkage函数计算之后，实际上二叉树式的聚类已经完成了
lk这个数据数组不太好看，可以用dendrogram(lk)来可视化聚类树
X M
pdist M*(M-1)/2
linkage (M-1) * 3

flip翻转矩阵
fliplr(x) 将矩阵左右反转，若x是一个列向量，则x不变，lr代表left、right
flipud(x) 将矩阵上下反转，若x是一个行向量，则x不变，ud代表up、down

MATLAB矩阵都是按列进行的 ****
1、reshape
2、进行fread fwrite进行文件读写，第二个参数[M N] 进行M*N的矩阵操作
3、var、std、cov、corrcoef、按列进行，如果矩阵是N列，得到N*N的矩阵(包含向量得到一个数)
	如果是元素数目相等的两个矩阵或者向量，得到2*2的矩阵
4、db函数 plot函数 按列进行
5、fft ifft n = length(x), fft(X)与fft(X,n)等价，进行n点DFT
	矩阵的每一列都按相同的方式进行调整，即每一列都进行补0或者截断
5、pdist 求M*N矩阵的距离矩阵，按行进行，得到M*(M-1)/2的行向量

sort 非常重要，特别是返回的index矩阵，排序完毕的矩阵SX经过index可以进行逆运算
[Y,I] = sort(X,DIM,MODE)
sort函数默认Mode为'ascend'为升序，sort(X,'descend')为降序排列
若欲保留排列前的索引，则可用[sX,index] = sort(X) ，排序后，sX是排序好的向量
index是 向量sX中对X 的索引，索引使排列逆运算成为可能
事实上，这里X≡sX(index), [X恒等于sX(index)]，这个结论确实很奇妙，而且很有用
就是对矩阵X按行按列进行排序后，返回排好序的矩阵SX以及SX对X的索引矩阵，
SX对索引矩阵index作用返回原来未排序的矩阵X

如何交换矩阵的行和列？
如矩阵x有四列 x = x(:, [4 2 3 1]); x矩阵的第一列和第四列进行交换，对行亦是如此

findpeaks函数的使用：
[pkttof,lcttof]  = findpeaks(PmusicEnvelope_ToF,...
		'SortStr','descend','NPeaks',num_computed_paths);
返回数据的局部最大值，以及最大值出现的下标index，峰值可以按照升序、降序进行排列，
相应的index也会进行相应的调整，还可以设置其他的属性值

关于AOA以及TOF联合估计伪谱峰值搜索的总结：
1、每个角度值theta以及时间值tau都可以确定一个导向矢量，alpha(θ)(与具体的信号无关，
只与aoa以及tof的值有关)，和噪声空间运算可以得到Pmusic矩阵的一个元素Pmusic(i,j)
2、对Pmusic矩阵按行找最大值，找到的是aoa的包络，按列找最大值，找到的是tof的包络
3、对包络进行findpeaks，返回相应包络的峰值以及出现峰值的index
	[pkttof,lcttof]  = findpeaks(PmusicEnvelope_ToF,...
		'SortStr','descend','NPeaks',num_computed_paths);
注意这里面index仅仅是峰值的下标，并不是真正的峰值对应的aoa或者tof，
aoa(index), tof(index)，才是真正的aoa峰值对应aoa，以及tof峰值对应的tof值
4、Pmusic是一个矩阵，如何找矩阵中的某个元素，还是按照index进行查找，
并不是按照真正的角度值或者时间值进行查找 Pmusic(aoa_index, tof_index)
x_aoa = aoa(aoa_index)  y_tof = tof(tof_index)
5、但是对于plot3函数，在Pmusic 3-D图像上标记一个点，则要使用真正的aoa值，以及tof值
plot3(currentAxis, x_aoa, y_tof, z_dB, 'o', 'MarkerSize', 12);
对于矩阵和矩阵代表的图像，都要找同一个点，矩阵中使用下标，绘图中使用下标对应的真实的值
6、对3-D图像某点进行标记，
h = figure('Name', 'figure_name', 'NumberTitle', 'off');
currentAxis = get(h, 'CurrentAxes');
plot3(currentAxis, x_aoa, y_tof, z_dB, 'o', 'MarkerSize', 12);
注意plot3函数的第一个参数是axis，而不是figure

rand以及randn函数
rand产生均匀分布的伪随机数 (0 ~ 1)
randn标准正太分布的伪随机数 均值为0 方差是1

repmat全称是Replicate Matrix ，意思是复制和平铺矩阵，是MATLAB里面的一个函数
repmat函数可以这样来理解，repmat(A, m, n);产生一个m行n列的一个矩阵，
该矩阵的每个元素都用A来代替
x =
     1     2
     3     4
>> repmat(x, 2, 3)
ans =
     1     2     1     2     1     2
     3     4     3     4     3     4
     1     2     1     2     1     2
     3     4     3     4     3     4

>> x = 'matlab ';
>> repmat(x, 2, 3)
ans =
matlab matlab matlab 
matlab matlab matlab	 

save('CSI.mat','CSI', 'A');	% 将矩阵CSI 以及A都保存到CSI.mat中	 
load('CSI.mat'); 将数据加载到workspace中	 


>> x = [2 3 4 1];
>> y = diag(x)
y =
     2     0     0     0
     0     3     0     0
     0     0     4     0
     0     0     0     1
就是将x的元素作为y的主对角线元素，并将对角阵返回





关于CSI数据的处理
csi_trace = read_bf_file('cai_5.dat');  % 返回的是一个N*1的cell，其中每个cell是一个struct
csi_retry = csi_trace{index}; % 每一个cell是一个struct，包含了各种信息
csi = get_scaled_csi(csi_entry);  % 得到struct里面的CSI数据是一个Ntx * Nrx * 30的 complex double矩阵
	csi也就是csi_entry.csi
	csi = csi(1, :, :); 取出发端一天线的数据 1*3*30 	squeeze(csi).' 	30*3
plot(db(abs(squeeze(csi).')))













	 
协方差矩阵、系数矩阵、距离矩阵
非凸函数、
K-means聚类算法
聚类分析 
FFT IFFT
SVM
   
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