# AndroidBaseProject

### 添加依赖
#### 通过Gradle抓取

在`Project`的`build.gradle`中添加。
```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
        ...
    }
}
```
在`app`的`build.gradle`中添加，latestVersion为下方版本号，请自行替换。

[![](https://jitpack.io/v/Kiuber/AndroidBaseProject.svg)](https://jitpack.io/#Kiuber/AndroidBaseProject)
```
dependencies {
          ...
	   compile 'com.github.Kiuber:AndroidBaseProject:latestVersion'
          ...
  }
```

### 使用方法
#### 工具类

Activity工具类，为每一个Activity覆写`onCreate`及`onDestroy`方法，可以在一些需要传Activity类型参数直接调用` ActivityUtil.get().getTopActivity()`。
```
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    StatusBarUtil.setStatusBarColor(this, true, R.color.colorPrimary, false);
    ActivityUtil.get().addActivity(this);
}

@Override
protected void onDestroy() {
    super.onDestroy();
    ActivityUtil.get().removeActivity(this);
}
```

Toast
```
ToastUtil.get().showShortToast("I am a short Toast!");
ToastUtil.get().showLongToast("I am a short Toast!");
ToastUtil.get().showSuccessToast();
ToastUtil.get().showSuccessToast("上传成功");
```

首选项
```
PreferenceUtil.get().putOneValue("user_info","nickname","Kiuber");
PreferenceUtil.get().getOneValue("user_info","nickname");
```

操作权限
对于一次请求一个权限且子类本应继承Activity类，首先继承PermissionUtil，然后调用父类compatibleResolver方法，这个方法兼容6.0以下及以上权限问题。
```
compatibleResolver(Manifest.permission.CAMERA, new CompatibleResolverListener() {
            @Override
            public void onGranted() {
                // do something
            }
        });
```

打印日志
```
LogUtil.d("Kiuber");
LogUtil.d("Nickname","Kiuber");
```

对话框
```
DialogUtil.get().showNormalTipDialog(true, "这是显示一般提示对话框（点击对话框外可以取消）");
DialogUtil.get().showHandleTipDialog(true, DialogUtil.DIALOG_TITLE_TIP, "内容", "确定", new DialogUtil.MyOneButtonDialogOnClickListener() {
            @Override
            public void onPositive() {
                ToastUtil.get().showShortToast("点击了确定");
            }
        });
DialogUtil.get().showLoadingWindow(this, true, "正在进行~");
```

### 更多使用方法请参考apk及源代码

测试Demo下载

![image](http://qr.api.cli.im/qr?data=https%253A%252F%252Fgithub.com%252FKiuber%252FAndroidBaseProject%252Fraw%252Fmaster%252Fapp-debug.apk&level=H&transparent=false&bgcolor=%23ffffff&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&size=280&kid=cliim&key=e4d404736523b0d08f65bee38b14b942)
