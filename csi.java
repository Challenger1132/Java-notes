The 30 groups of CSI represent the channel response in frequency
domain, which is about one group per two subcarriers

CSI value is the channel matrix from RX baseband to TX baseband which is needed for
channel equalization. Therefore, there is no extra processing overhead when obtaining the CSI information.

Based on OFDM, channel measurement at the subcarrier level becomes available. Nowadays, 
adaptive transmission systems in wireless communication always improve the throughput by utilizing 
some knowledge of the channel state to adapt or allocate transmitter resources [9].
Channel state information or channel status information (CSI) is information that estimates the channel by representing
the channel properties of a communication link. To be more specifically, CSI describes how a signal propagates from the
transmitter(s) to the receiver(s) and reveals the combined effect of, for instance, scattering, fading, and power decay with
distance. In summary, the accuracy of CSI greatly influences the overall OFDM system performance.
In a narrowband flat-fading channel, the OFDM system in the frequency domain is modeled as

CSI is a fine-grained value from the PHY layer which describes the amplitude and phase on each
subcarrier in the frequency domain
CSI数据的统计稳定性，可以直接用蒙娜丽莎的试验结论

 Temporal Stability of CSI: Temporal stability is a fundamental criteria in validating the robustness of the localization
systems. We thus set out to examine the stability of the
proposed new metric 𝐵𝐵𝐵 and RSSI value in time series. It
is well-known that RSSI is a fickle measurement of the channel
gain because of its coarse packet-level estimation and easily
varied by multipath effect. As CSI is fine-grained PHY layer
information that provides detailed channel state information in
subcarrier level

in comparison with RSSI, CSI is more temporally stable in
different environments and helps maintain the performance
over time. 


贡献之一：在商用AP节点上面实现，没有硬件修改易于广泛部署
We exploit PHY layer channel state information to
identify the availability of the LOS component in
multipath-dense indoor scenarios. As far as we are
aware of, this is the first LOS identification scheme
built upon merely commodity WiFi infrastructure
without hardware modification leveraging PHY layer
information, which allows pervasive adoption.

Nevertheless,
commodity wireless infrastructure often fails to support precise
CIR estimation 

PHY layer information. Leveraging the off-the-shelf Intel 5300
NIC and a modified driver, a sampled version of Channel
Frequency Response (CFR) within WiFi bandwidth is revealed
to upper layers in the format of Channel State Information
(CSI) [19]. Each CSI depicts the amplitude and phase of a
subcarrier:

Compared with the conventional MAC layer RSSI, CSI
portrays a finer-grained temporal and spectral structure of
wireless links.、、

Given an operating bandwidth of 20MHz, commodity WiFi yields a time
resolution of 50ns. Therefore paths with length difference smaller than 15m might be mixed in one CIR sample
CSI provides a time resolution of multipath-clusters there is an uncertain time lag at the start
of measured CIR samples. In case of low time resolution and
lack of synchronization, it is rather error-prone to align the
CIR samples with respect to the first arriving path.

Conventional MAC layer RSSI usually contains enormous noise, thus inducing irrelevant interference to
the LOS path, making it less deterministic

Since typical indoor maximum excess delay is smaller
than 500ns [23], given a time resolution of 50ns, only
the first 10 out of the 30 accessible CIR samples are
relevant to multipath propagation  分割时间范围

In this paper, based on the assumption that the signal on the reflected paths is generally with large
variation of the AOA and TOA measurements comparing with the one on the direct path,
诸多论文中都有这么一句话描述
LOS路径服从莱斯分布，非视距路径相对于LOS来说表现出更多的随机性，但是实际操作的过程中，并不是这样的
特定的室内环境造成、收发端之间的距离很近，LOS路径以及NLOS路径的边界很模糊，因此很难提取Los能量

各种误差的来源，以及占据主导型的误差分析
不同的数据包的alpha和β是不一样的，因此要每个数据包单独进行相位剔除，每天线进行相位的剔除

However, it is well-known that the
radiation power decreases with propagation distance in free space

即使再细纹的问题，也要用数学术语进行描述

The intuition is that NLOS paths often
involve large numbers of obstacles that reflect, refract and
diffract wireless signals. Consequently, signals travelling along
NLOS paths tend to behave more randomly compared with
those along a clear LOS path

相位误差的描述是 k*x + b的形式
power control uncertainty causes a CSI amplitude offset; 2) packet detection
delay and SFO, essentially equivalent to a time delay, cause
CSI phase rotation errors; 3) the rest would respectively cause
an identical CSI phase offset error on each measured subcarriers. Consequently, these sources can only introduce linear
phase errors expressed as a rotation error proportional to the
sub-carrier index plus an offset in the measured CSI phases.

不确定性的功控导致CSI幅度偏移
PDD、SFO导致时延，CSI相位旋转误差
其他的导致对每个子载波相同的相位偏移误差
这些源只能引入线性相位误差，其表示为与子载波索引成比例的
旋转误差加上所测量的CSI相位中的偏移

解释为什么用能量进行距离发的估计，因为不能捕获到真正的tof，原因在于收发端之间的各种不同步
采样时间潘奕，采样间隔偏移，t = (Ts + delta_Ts)*n + ty
得到的时间中包含由于采样时间偏移、采样频率偏移等等造成的时延

STO adds a constant offset to the ToF estimates of all the paths
这种时延等效为频域的线性相位偏移

OFDM方案采集CSI数据的优缺点	引入时钟同步误差等各种相位误差和信道状态无关，因此使CSI数据的相位
不能真实反映信道的状态
优点，计算方便，802.11协议广泛用于商用AP等

峰值检测算法：不能滤除和LOS同一个采样周期到达的NLOS信号
异常值的滤除

commodity WiFi devices 对于精确的室内定位有两点限制，有限的天线数目以及各种相位误差的影响
大多数商用无线AP安装的天线数目是有限的，且很不容易扩展

整个信号收发过程、OFDM

CSI vs RSSI、OFDM、CSI被计算产生的地方
CSI采集描述、CSI矩阵
CSI各种相位误差的描述、原因的分析
MUSIC算法的一大块东西、平滑(采用和论文中不一样的方法，双向空间平滑)、信源数目的估计可以作为一个模块、聚类、LOS确定
CSI幅度更加稳定，


展望：
3-D定位 水平天线阵列、垂直天线阵列
探究不同天线排列方式、线阵、圆阵
LOS阻挡，多个AP能在概率上提高定位精度

802.11n defines a channel sounding mechanism where the transmitter can trigger CSI estimation at the receiver by setting an appropriate flag in the transmitted packet. The receiver can thereafter feedback the estimated CSI to the transmitter for the purpose of calibration, or beamforming. We use this mechanism to
extract CSI. We modify the device driver of Atheros 9390 cards to
appropriately set the sounding bit of a transmitted packet. On
the receiver side, the CSI is estimated for each receive and transmit antenna pair. The Atheros 9390 reports one complex number
per subcarrier for 56 out of the 64 available subcarriers, and we
only use these for our scheme.

[13] J. Xiong et al. SecureAngle: improving wireless security
using angle-of-arrival information. In HotNets, 2010.
[14] J. Xiong and K. Jamieson. Towards fine-grained radio-based
indoor location. In HotMobile. ACM, 2012.

1、箱图 filter之前分布范围很大，filter之后分布范围很小
cir峰值能量变化略有规律，不稳定，通过线性变换，线性拟合可以解决，使多个数据包的CIR峰值稳定
2、详细研究了AGC，AGC对同一个位置的不同的数据包变化很小(稳定环境下几乎不变化)，不同位置处AGC变化比较大(线性变化)
不同天线的Rssi_a、Rssi_b、Rssi_c是以db形式暴露的，使用包前导来计算的
(correspond to RSSI measured by the receiving NIC at the input to each antenna port)
为了得到接受信号强度，必须结合AGC，并减去常量魔数
3天线得到的Rssi_a、Rssi_b、Rssi_c是没有规律的，但是结合AGC就变得有规律了，且变化趋势和Total_RSS变化趋势一样