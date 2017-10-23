C++/Java更强调类型，对类型的检查更严格
JavaScript、Python、PHP不看重类型，甚⾄至不需要事先定义

C语言中的数据类型
整型 char、short、int、long、long long 二进制数（补码）
浮点型 float、double、long double 编码
逻辑 bool
指针
自定义类型

是静态运算符，它的结果在编译时刻就决定了，不要在sizeof的括号里做运算，这些运算不会做的

int a[] = {0};
计算数组元素的个数 sizeof(a) / sizeof(a[0]);  sizeof(a)是数组所占总空间的大小

指针也是一种变量，变量存放的其他变量的地址，指针这种变量本身也有地址
p指向i的含义是变量p中存放的是变量i的地址
指针应用场景之一是，函数的返回值不止一个，可以通过指针修改传入变量的值，通过参数将变量的结果带出来
之二，函数返回执行状态，结果通过指针返回

void getVal(int a[], int len, int *max, int *min);
通过max、min参数将变量的结果带出来
int divide(int p, int q, int *result); 结果通过指针进行操作


void getVal(int a[], int len, int *max, int *min);
函数参数列表中的数组其实就是一个指针，int a[] a就是一个指针，只是样子看起来像一个数组
因此这就是为什么在函数内无法计算数组的元素了
因为a是一个指针，等价写法就是
void getVal(int *a, int len, int *max, int *min);在函数内部依然可以用a[i]的数组访问方式

int a[10];
int *p = a;
[]可以对p用，也可以对a用  p[i] ==> a[i];

数组名是常量指针 
int b[] = a; 是不被允许的，因为，等价于 int *const b 是常量指针，不能被赋值

指针本身不能被修改 常量指针  int * const p = &j; p++; //Error
不能通过指针修改 指向常量的指针 const int * p = &j; 变量j本身可以被修改，指针p本身可以被重新指向，但是不能通过指针p修改变量的值 *p = 1; // Error

const 指针，不能通过指针修改变量的值
void f(const int * x), 总可以将一个非const的值转换为const的
当要传递的参数的类型比地址大的时候，用const指针，既能用较少的字节传递值给参数
又能避免对外界变量的修改

const int a[] = {0};  ===> const int * const a 表明指向常量的常量指针，必须通过初始化赋值
为了保护数组不被破坏，可以修改函数的参数是 void f(const int * x)

if a large structure is to be passed to a function, 
it is generally more efficient to pass a pointer than to copy the whole structure.

传入一个指针，在函数内部对指针所指向的东西进行操作之后，有返回该指针，使得该函数可以被传递给其他函数

struct data *getStruct(struct data * d);

union 可以知道一个整形，float等具体存放形式,得到数据内部的各个字节
typedef union{
	int i;
	char ch[sizeof(int)]; 
}CHI;


定义在函数外部的变量是全局变量，全局变量图任何函数无关，在任何函数内部都能够使用
全局变量会初始化为0，指针类型会初始化为NULL (类比Java 的static )
全局变量，作用域是全局的，只需在一个源文件中定义，在所有源文件总都是可见的，如果源文件中不包含全局变量，要用extern关键字声明
静态全局变量，作用域是全局的，只能在声明的源文件中使用，具有文件作用域，两个源文件静态全局变量相同，他们是不同的变量
局部变量：作用域是局部的，使用完毕，被释放
静态局部变量：作用域是局部的，生存期是整个程序的运行时间
用static修饰全局变量，改变的是作用域，修饰局部变量，改变的是生存期(存储位置发生了改变)
全局变量、静态全局变量、静态局部变量存储在静态存储区，地址比较近似
局部变量存储在栈中

返回本地变量的地址是危险的，因为本地变量在函数调用完毕会被释放

宏定义不出错的原则：整体加括号，每个参数加括号(带参数的宏)
#define MIN(a, b) ((a)>(b)?(b):(a))