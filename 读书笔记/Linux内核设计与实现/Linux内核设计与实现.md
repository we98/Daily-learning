## 第1章 Linux内核简介

### 1.1 Unix的历史

- Unix的特点

  - Unix很简洁，不向其他提供数千个系统调用的设计目的不明确的系统，Unix仅仅提供几百个系统调用，并且设计目的明确
  - Unix所有东西都被当作文件，使得对数据和对设备可以使用同一套接口
  - Unix使用c语言编写，移植能力强，更容易被接收
  - Unix进程创建非常迅速
  - Unix也提供了一套简单并且稳定的进程通信原语

### 1.3 操作系统和内核简介

- 库函数和系统调用的关系
  - 对于一些复杂的库函数，系统调用通常只是一个步骤，如printf和write
  - 对于一些简单的库函数，二者一一对应，除了系统调用外，其他几乎啥也不做，如open库函数和open系统调用
  - 一些库函数根本不使用系统调用，如strcpy



