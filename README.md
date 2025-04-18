# IP路由模拟系统 🌐

[![Java Version](https://img.shields.io/badge/Java-21%2B-blue)](https://openjdk.org/)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)
![GitHub last commit](https://img.shields.io/github/last-commit/xuan0331/NetworkIP)

> 一个基于Java Swing的轻量级路由模拟器，可视化演示IP路由匹配过程

## ✨ 功能特性

- 🎯 **智能路由查询**  
  支持ipv4格式输入，采用最长前缀匹配算法
- 📋 **路由表管理**  
  可视化展示网络地址/子网掩码/下一跳/前缀长度
- 🖥️ **教学友好界面**  
  - 深色主题按钮设计
  - 中文格式化结果输出
- 🧩 **模块化设计**  
  清晰的MVC架构，便于扩展

## 🚀 快速开始

### 运行环境
- JDK 21
- 系统支持：Windows/macOS/Linux

### 启动方式
```bash
# 克隆项目
git clone https://github.com/xuan0331/NetworkIP.git

# 进入项目目录
cd NetworkIP/src/NetworkIP

# 编译运行
直接导入IDE（如IntelliJ IDEA）运行MainApp.java


🏗️ 项目结构
NetworkIP/
├── src/
│   └── NetworkIP/                # 主包
│       ├── Main.java             # 主程序入口
│       ├── Router.java           # 路由核心逻辑
│       ├── IPAddress.java        # IP地址处理类
│       ├── Route.java            # 路由条目实体
│       └── RouterFrame.java      # 主窗口框架
        └── RouterController.java # 控制核心
        └── InputPanel.java       # 输入面板组件
        └── ResultPanel.java      # 结果展示组件
└── README.md                     # 本文件

🎮 使用演示
启动应用
启动界面
![image](https://github.com/user-attachments/assets/672f2680-8be7-4a59-afdc-110a11c203eb)




查询路由
