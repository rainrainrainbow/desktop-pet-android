# 🐾 桌面宠物安卓版 (Desktop Pet Android)

在手机桌面上养一只可爱宠物的 Android 应用！

## ✨ 特性

- 🖼️ **悬浮窗宠物** — 小宠物悬浮在手机桌面上，随时陪伴
- 👆 **拖拽移动** — 可以自由拖动宠物到任何位置
- 😺 **互动表情** — 点击宠物切换心情（开心😺 / 瞌睡😴）
- 🎨 **Material You** — 适配深色/浅色主题
- 💨 **轻量高效** — 基于 Jetpack Compose，极致流畅

## 📱 效果预览

| 开心模式 | 瞌睡模式 |
|:--------:|:--------:|
| 😺 | 😴 |

## 🛠️ 技术栈

| 组件 | 选用方案 |
|------|----------|
| 语言 | Kotlin 2.1 |
| UI 框架 | Jetpack Compose + Material3 |
| 动画引擎 | Compose Animation + Lottie |
| 悬浮窗 | WindowManager + Foreground Service |
| 存储 | DataStore Preferences |
| 图片加载 | Coil |
| 最低 SDK | Android 8.0 (API 26) |

## 🏗️ 项目结构

```
desktop-pet-android/
├── app/
│   ├── src/main/
│   │   ├── kotlin/com/deskpet/
│   │   │   ├── PetApplication.kt        # Application 入口
│   │   │   ├── MainActivity.kt           # 权限申请与启动
│   │   │   ├── overlay/
│   │   │   │   └── PetOverlayService.kt  # 悬浮窗服务
│   │   │   └── ui/theme/
│   │   │       └── Theme.kt             # Compose 主题
│   │   ├── res/                         # 资源文件
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── build.gradle.kts                     # 根构建配置
├── settings.gradle.kts
└── .gitignore
```

## 🚀 快速开始

1. 克隆仓库
```bash
git clone https://github.com/rainrainrainbow/desktop-pet-android.git
```

2. 用 Android Studio 打开项目

3. 同步 Gradle -> 构建运行

4. 授予悬浮窗权限 -> 开始养宠物！

## 📋 待办功能

- [ ] 多个宠物形象切换（猫/狗/兔子...）
- [ ] Lottie 动画（跑步、跳跃、打滚）
- [ ] 宠物饥饿/心情系统
- [ ] 宠物自定义装扮
- [ ] 定时提醒与互动
- [ ] 桌面小部件

## 📄 协议

本项目基于 MIT 协议开源。

---

**Made with ❤️ by rainrainrainbow**
