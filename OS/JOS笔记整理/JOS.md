## Lab1

### 代码结构

#### boot

- boot.S
  - 打开A20地址线
  - 设置临时GDT表，包含内核代码段，内核数据段，都是0x0~0xFFFFFFFF
  - 跳到到main.c中的bootmain，此时调用函数，内核栈顶地址为0x7c00
- main.c
  - 主要函数有bootmain，负责加载内核并跳转到内核入口
  - 首先读取4KB长的内核ELF header至0x10000，ELF header从磁盘的sector 1开始
  - 然后内核文件中每个段的只是信息，将每个段读取到相应的物理地址，第一个段应该从0x100000处开始
  - 读取之后，跳转到内核入口

#### inc

一些头文件，用来被其他文件引用，并不是编译单元

- elf.h定义了elf文件格式
- error.h定义了几种error类型
- kbdreg.h定义了跟键盘交互的一些常量
- memlayout.h定义了跟线性地址空间有关的一些常量
- mmu.h定义了跟分段和分页有关的常量或函数，以及内核线性地址映射到物理地址空间的函数
- stab.h定义了符号表有关数据，如符号类型，代表符号的struct Stab
- stdarg.h定义了有关变长参数列表的相关函数
- stdio.h声明了跟io有关的函数
- string.h声明了跟string相关的函数，如strlen等，并且也声明了memset，memcpy等函数
- type.h定义了一些自定义类型，基本都是和int相关，如uint32_t
- x86.h定义了一些内联汇编语句来写入和读取寄存器，以及写入特定内存地址空间或IO地址空间的函数

#### kern

- entry.S
  - 内核的入口，设置临时页表，映射4M内存
  - 开启分页标志
  - 设置一个长度为KSTKSIZE的内核栈，用来调用i386_init内核初始化程序
- entrypgdir.c定义了entry.S中用到的页目录表和页表

- console.c定义了跟终端IO有关的函数，直接跟终端交互读写的函数有cons_putc和cons_getc，封装了这两个函数实现了cputchar，getcha，cputchar和getchar为更高层的readline和cprintf提供服务
- init.c
  - 内核初始化程序
  - 首先将bss段初始化为0，然后调用cons_init函数，之后进入monitor中
- kdebug.c定义了跟调试有关的函数，以及跟调试有关的符号的struct
- monitor.c循环runcommand，而command则的名称以及对应的函数内置到了该文件中
- printf.c实现了putch，cprintf等

#### lib

- printfmt主要实现了字符串的格式化vprintfmt
- readline.c通过getchar，cputchar实现
- string.c是上文提到的string.h的函数具体实现





### 编译过程

- mk kernel
  - gcc  -c  -o  obj/kern/entry.o  kern/entry.S
  - gcc  -c  -o  obj/kern/entrypgdir.o  entry/entrypgdir.c
  - gcc  -c  -o  obj/kern/init.o  entry/init.c
  - gcc  -c  -o  obj/kern/console.o  entry/console.c
  - gcc  -c  -o  obj/kern/monitor.o  entry/monitor.c
  - gcc  -c  -o  obj/kern/printf.o  entry/printf.c
  - gcc  -c  -o  obj/kern/kdebug.o  entry/kdebug.c
  - gcc  -c  -o  obj/kern/printfmt.o  lib/printfmt.c
  - gcc  -c  -o  obj/kern/readline.o  lib/readline.c
  - gcc  -c  -o  obj/kern/string.o  lib/string.c
  - ld  -o  obj/kern/kernel  obj/kern/entry.o  obj/kern/entrypgdir.o  obj/kern/init.o  obj/kern/console.o  obj/kern/monitor.o  obj/kern/printf.o  obj/kern/kdebug.o  obj/kern/printfmt.o  obj/kern/readline.o   obj/kern/string.o 当然还有GCC自带的库
  - objdump  -S  obj/kern/kernel  obj/kern/kernel.asm
  - nm  -n  obj/kern/kernel  >  obj/kern/kernel.sym
- mk boot
  - gcc  -c  -o  obj/boot/boot.o  boot/boot.S
  - gcc  -c  -o  obj/boot/main.o  boot/main.S
  - ld  -o  obj/boot/boot.out  obj/boot/boot.o  obj/boot/main.o
  - objdump  -S  obj/boot/boot.out  >  obj/boot/boot.asm
  - objcopy  -S  obj/boot/boot.out  obj/boot/boot
- mk kernel.img
  - 使用dd将boot和kernel连接到一起
