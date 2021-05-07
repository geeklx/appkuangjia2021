# hunheappkuangjia

混合移动应用框架APP-Android13

1.ANROOMCrashActivity：验证APP压力测试

2.AssetsMainActivity：为APP申请访问文件，写入txt存储，copy到assets

3.BannerViewDemoActivity：banner防止OOM

4.BannerDemoActivity：banner支持各种样式

5.BtnActivity：点击按钮按压效果

6.CacheUtil：清除缓存

7.CarAct：加入到购物车动画

8.ChangeLanActivity：切换APP语言

9.BaseApp：APP堆栈管理

10.DownLoadDialog：自定义下载loading

11.EmptyViewNew1,NiubiEmptyViewNew1：EmptyViewMainActivity自定义数据布局切换

12.ExpandableAct：二级tree

13.TagFlowLayout：自定义搜索历史标签

14.handler：handler各种使用

15.ImgOtherToBitmap：图片utils

16.SoftHideKeyBoardUtil：自定义弹出键盘，解决键盘档住输入框

17.LiandongDemoAct：分类列表联动 饿了么

18.LikeButtonActivity：点赞按钮

19.ShowLoadingUtil：自定义loading

20.LobsterPicker：根据颜色变化的进度条
 <com.haier.cellarette.baselibrary.lobsterpicker.sliders.LobsterShadeSlider
                    android:id="@+id/shadeslider"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content" />
shadeslider = rootView.findViewById(R.id.shadeslider);
        shadeslider.setColorAdapter(new BitmapColorAdapter(getActivity(), R.drawable.default_shader_pallete4));
        shadeslider.setShadePosition(5);
//        shadeslider.setColor(R.drawable.default_shader_pallete11);
        shadeslider.addOnColorListener(new OnColorListener() {
            @Override
            public void onColorChanged(@ColorInt int color) {
                MyLogUtil.e("geek111111", "" + color);
                MyLogUtil.e("geek111111", "" + adjustAlpha(color, 1.0f));
//                if (TextUtils.equals(f5_tv11_content, adjustAlpha(color, 1.0f) + "")) {
//                    Toasty.normal(getActivity(), f5_tv11.getText().toString().trim()).show();
//                } else if (TextUtils.equals(f5_tv22_content, adjustAlpha(color, 1.0f) + "")) {
//                    Toasty.normal(getActivity(), f5_tv22.getText().toString().trim()).show();
//                } else if (TextUtils.equals(f5_tv33_content, adjustAlpha(color, 1.0f) + "")) {
//                    Toasty.normal(getActivity(), f5_tv33.getText().toString().trim()).show();
//                } else if (TextUtils.equals(f5_tv44_content, adjustAlpha(color, 1.0f) + "")) {
//                    Toasty.normal(getActivity(), f5_tv44.getText().toString().trim()).show();
//                } else if (TextUtils.equals(f5_tv55_content, adjustAlpha(color, 1.0f) + "")) {
//                    Toasty.normal(getActivity(), f5_tv55.getText().toString().trim()).show();
//                }
            }

            @Override
            public void onColorSelected(@ColorInt int color) {
                MyLogUtil.e("geek111111", "" + color);
                MyLogUtil.e("geek111111", "" + adjustAlpha(color, 1.0f));
                if (TextUtils.equals(f5_tv11_content, adjustAlpha(color, 1.0f) + "")) {
                    ToastUtils.showLong(f5_tv11.getText().toString().trim());
                } else if (TextUtils.equals(f5_tv22_content, adjustAlpha(color, 1.0f) + "")) {
                    ToastUtils.showLong(f5_tv22.getText().toString().trim());
                } else if (TextUtils.equals(f5_tv33_content, adjustAlpha(color, 1.0f) + "")) {
                    ToastUtils.showLong(f5_tv33.getText().toString().trim());
                } else if (TextUtils.equals(f5_tv44_content, adjustAlpha(color, 1.0f) + "")) {
                    ToastUtils.showLong(f5_tv44.getText().toString().trim());
                } else if (TextUtils.equals(f5_tv55_content, adjustAlpha(color, 1.0f) + "")) {
                    ToastUtils.showLong(f5_tv55.getText().toString().trim());
                }
            }
        });

21.MusicActivity：播放本地文件

22.NetEmptyViewMainActivity：联网是否布局动态

23.ExpandViewRect：点击范围

24.MohuReUtil：背景磨砂

25.QrCodeUtil：二维码生成

26.QuanxianActivity：获取权限

27.BaeRecycleViewActivity：recycleview使用大全

28.RecycleViewMainActivity：recycleview使用自定义

29.AdvertiseLinearLayoutManager：recycleview吸附效果

30.JackSnapHelper：recycleview按照item滚动

31.RingActivity：系统声音自定义

32.LightOnOffUtils：亮息屏

33.ScreenListenerUtils：屏幕监听

34.ScrollViewAct：用户须知滚动布局

35.ShadowLayout:呼吸布局
<com.gigamole.library.ShadowLayout
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       app:sl_shadow_angle="2"
       app:sl_shadow_color="#000"
       android:id="@+id/sl"
       app:sl_shadow_distance="5dp"
       app:sl_shadow_radius="1dp"
       app:sl_shadowed="true">
       <ImageView
           android:layout_width="100dp"
           android:id="@+id/ls"
           android:layout_height="100dp"
           android:src="@mipmap/menu_stick" />
   </com.gigamole.library.ShadowLayout>
   final ShadowLayout shadowLayout = (ShadowLayout) findViewById(R.id.sl);
   shadowLayout.setIsShadowed(true);
   shadowLayout.setShadowAngle(45);//阴影角度
   shadowLayout.setShadowRadius(20);//阴影半径
   shadowLayout.setShadowDistance(30);//阴影距离
   shadowLayout.setShadowColor(Color.DKGRAY);//阴影颜色

36.ShoppingCarActivity：添加购物车动画

37.LxShimmerLayout：布局加载动画

38.SizeUtils：尺寸相关工具类

39.SmartBar：返回|主页 工具类 一行解决你的问题

40.AlphaView：自定义导航页

41.StatusBarUtil，StatusBarUtilV7：透明栏

42.UnAnimTabLayout：自定义UnAnimTabLayout，ViewPagerSlide：自定义是否滚动viewpage

43.Toasty：自定义toast

44.MToast：自定义toast

45.UpdateManager：更新APP通过xml

46.YanzhengUtil：验证util

47.LocalBroadcastManagers：本地内部广播

48.DemoZuniScrollViewActivity：阻尼效果

49.AlertView：仿IOS的弹出框

50.AutofitTextView：自定义缩小textview

51.BookPaddingDecoration：自定义recycleview书架线
        recycleViewDivider = new BookPaddingDecoration(this, OrientationHelper.VERTICAL, R.drawable.bg_book_shelf_charge9);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));
                mRecyclerView.removeItemDecoration(recycleViewDivider);// 书架bufen
            } else {
                mRecyclerView.addItemDecoration(recycleViewDivider);// 书架bufen

52.CircleImageView：自义定圆形图片

53.CircularSeekBar：

54.CustomImageView：自定义View，实现圆角，圆形等效果

55.LxLinearLayout，LxRelativeLayout：自定义点击拦截layout
   rl1.setTouch(true);

56.NoScrollView：自定义不滚动view

57.RecycleViewDivider：自定义添加默认分割线

58.SmoothCheckBox：自定义checkbox

59.SwitchButton：自义定开关按钮

60.AnnotateUtils：自定义butternife

61.AutoHideInputMethodFrameLayout：自定义键盘弹起layout

62.DaojishiUtil：时间转换util

63.NavigationBarUtil：虚拟键util

64.NoDoubleClickListener：两次误点击

65.SimpleTagImageView：图片标签

66.SpannableStringUtils：

67.StartHiddenManager：测试入口util

68.libagentweb-core：自定义webview

69.libappjpush：极光推送

70.libappjpushshare：极光分享

71.libappjpushstatistics：极光统计

72.libappmob：mob分享推送

73.libapppay：支付宝，微信，银联支付

74.libappumengstatistics：友盟统计

75.libglide47：glide4.10.0

76.libmvp：接口设计模式

77.librararyforfileprovdider：动态权限申请

78.libretrofit：网络请求

79.libupdateapputilsold：升级APP

80.libutils：无敌的工具类

81.libwebview：HIOS协议

82.libZRecyclerView：自定义recycleview支持分页

83.smartrefreshlayout：下拉刷新上拉加载

84.slbbluetooth：蓝牙

85.slbcitypickerview：自定义城市选择

86.slbdachshundtablayout：自定义tablayout

87.slbimagepicker：图片上传九宫格

88.slbjiaozivideoplayer：饺子视频播放器

89.slbncalendar：自定义日历

90.slbota：OTA升级

91.slbswipebacklayout：返回IOS

92.slbwifi：自定义WiFi

93.zxinglibs：扫码二维码

94.zuser_adapter+xml：自定义xml布局util

95.baselibrary

// https://github.com/teprinciple/UpdateAppUtils  升级
    api 'com.teprinciple:updateapputilsx:2.3.0'
    // https://github.com/jd-alexander/LikeButton  点击效果
    api 'com.mikepenz:iconics-core:2.9.1@aar'
    api 'com.mikepenz:community-material-typeface:1.8.36.1@aar'
    // https://github.com/drakeet/MultiType   列表
    api 'me.drakeet.multitype:multitype:3.4.4'
    // https://github.com/CymChad/BaseRecyclerViewAdapterHelper   列表
    api 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.40'
    // https://github.com/jaredrummler/MaterialSpinner  下拉框
    api 'com.jaredrummler:material-spinner:1.2.5'
    // https://github.com/kyleduo/SwitchButton  按钮
    api 'com.kyleduo.switchbutton:library:2.0.3'
    // https://github.com/drawthink/ExpandableRecyclerView  二级列表
    api 'com.drawthink:expandable-recyclerview:0.0.3'
    // https://github.com/BolexLiu/AutoEx    日志上报
    api 'com.github.BolexLiu.AutoEx:AutoEx-Lib-No-Op:v1.0.8'
    // https://github.com/Jzvd/JZVideo   播放器
//    api 'cn.jzvd:jiaozivideoplayer:7.6.0'
    api project(':commonlibs:yewulibs:slbjiaozivideoplayer')
    // https://github.com/HuanHaiLiuXin/CoolViewPager   viewpager
    api 'com.github.HuanHaiLiuXin:CoolViewPager:v1.0.0'
    // https://github.com/airbnb/lottie-android   动画
    api 'com.airbnb.android:lottie:2.5.5'
    // https://github.com/greenrobot/EventBus   EventBus
    api 'org.greenrobot:eventbus:3.1.1'
    // https://github.com/Meituan-Dianping/walle    walle
    api 'com.meituan.android.walle:library:1.1.7'
    // https://github.com/BolexLiu/PressScanCode    一个可实现长按扫描 View 上的二维码的工具库。webView 也可以。 集成后您仅需三行代码就可以实现解析。
    api 'com.github.BolexLiu:PressScanCode:v1.0.0'
    // https://github.com/ethanhua/Skeleton     加载页面过度动画
    api 'com.ethanhua:skeleton:1.1.2'
    // https://github.com/team-supercharge/ShimmerLayout   加载页面过度动画      
    api 'io.supercharge:shimmerlayout:2.1.0'
//    api 'com.larswerkman:lobsterpicker:1.0.1' 
    // https://github.com/square/leakcanary     内存溢出
    api librarys.leakcanaryAndroid
    // https://github.com/JessYanCoding/AndroidAutoSize     今日头条适配
    api 'me.jessyan:autosize:1.2.1'
    // https://github.com/gongwen/MarqueeViewLibrary        滚动信息
    api 'com.gongwen:marqueelibrary:1.1.3'
    //
    api 'com.tencent.tinker:tinker-android-lib:1.9.14.3'
    api 'com.tencent.bugly:crashreport_upgrade:1.5.2'//
    api 'com.tencent.bugly:nativecrashreport:3.9.1' //
    // https://github.com/KunMinX/Linkage-RecyclerView      Linkage-RecyclerView 的目标是：一行代码即可接入二级联动列表。
    api 'com.kunminx.linkage:linkage-recyclerview:2.3.5'
    // https://github.com/LuckSiege/PictureSelector         图片选择器
    api 'com.github.LuckSiege.PictureSelector:picture_library:v2.6.1'
    // https://github.com/dongjunkun/DropDownMenu           下拉框
    api 'com.github.dongjunkun:DropDownMenu:1.0.4'
    // https://github.com/jgilfelt/SystemBarTint            系统通知栏
    api 'com.readystatesoftware.systembartint:systembartint:1.0.3'
    // https://github.com/koral--/android-gif-drawable      加载GIF
    api 'pl.droidsonroids.gif:android-gif-drawable:1.2.22'
    // https://github.com/hongyangAndroid/FlowLayout        FlowLayout
    api 'com.hyman:flowlayout-lib:1.1.2'
    // https://github.com/AigeStudio/WheelPicker            滚轮选择器
    api 'cn.aigestudio.wheelpicker:WheelPicker:1.1.2'
    // https://github.com/shaohui10086/BottomDialog         弹出框
    api 'me.shaohui:bottomdialog:1.1.9'
    // https://github.com/yhaolpz/FloatWindow               悬浮框
    api 'com.github.yhaolpz:FloatWindow:1.0.9'
    // https://github.com/dfqin/PermissionGrantor           权限
    api 'com.github.dfqin:grantor:2.5'
    // For developers using AndroidX in their applications
//    api 'pub.devrel:easypermissions:3.0.0'                权限        
    // For developers using the Android Support Library
    api 'pub.devrel:easypermissions:2.0.1'
    // https://github.com/getActivity/XXPermissions         权限
    api 'com.hjq:xxpermissions:9.6'
    // https://github.com/yanzhenjie/AndPermission          权限
    api 'com.yanzhenjie:permission:2.0.3'
    // https://github.com/yshrsmz/KeyboardVisibilityEvent
    api 'net.yslibrary.keyboardvisibilityevent:keyboardvisibilityevent:2.3.0'       监听键盘弹出过程
    // https://github.com/getActivity/ToastUtils
    api 'com.hjq:toast:5.8'
    // https://github.com/everhad/AndroidRatingStar         评论星星
    api 'com.github.everhad:AndroidRatingStar:v1.0.4'
    // https://github.com/li-xiaojun/XPopup                 弹出框
    api 'com.lxj:xpopup:1.8.4'
    // https://github.com/meetsl/SCardView-master           一个可以配置阴影方向和颜色的类 CardView 控件 SCardView
    api 'io.github.meetsl:SCardView:1.1'
    // https://github.com/nanchen2251/BankCardUtils         判断
    api 'com.github.nanchen2251:BankCardUtils:1.0.2'
    // https://github.com/SherlockGougou/BigImageViewPager      大图预览
    api 'com.github.SherlockGougou:BigImageViewPager:v4_2.1.0'
    // https://github.com/zzhoujay/RichText/                富文本
    api 'com.zzhoujay.richtext:richtext:3.0.7'
    // https://github.com/maning0303/MNProgressHUD          一个常用的自定义弹框封装
    api 'com.github.maning0303:MNProgressHUD:V1.1.8X'
    // https://github.com/MZCretin/ExpandableTextView       实现类似微博内容，@用户，链接高亮，@用户和链接可点击跳转，可展开和收回的TextView
    api 'com.github.MZCretin:ExpandableTextView:v1.6.1-x'
    // https://github.com/Manabu-GT/ExpandableTextView      实现类似微博内容，@用户，链接高亮，@用户和链接可点击跳转，可展开和收回的TextView
    api 'com.ms-square:expandableTextView:0.1.4'
    // https://github.com/yanzhenjie/NoFragment             Fragment的封装，启动Fragment只需要调用startFragment(XXFragment.class);
    api 'com.yanzhenjie:fragment:1.0.1'

96.yewulibs1-appcomm

 //TODO common libs
    implementation project(':commonlibs:baselibrary')
    // 支持分版本网络请求
    bxnt_nationImplementation project(':yewulibs1:yewulibs1-libvariants')
    bxny_nationImplementation project(':yewulibs1:yewulibs1-libvariants')
    bxn_nationImplementation project(':yewulibs1:yewulibs1-libvariants')
    // jpush推送
//    api project(':commonlibs:hunheku:libappjpush')
    // jpush分享
    api project(':commonlibs:hunheku:libappjpushshare')
    // jpush统计
    api project(':commonlibs:hunheku:libappjpushstatistics')
    // Umeng统计
    api project(':commonlibs:hunheku:libappumengstatistics')
    // mob分享
    api project(':commonlibs:hunheku:libappmob')
    // 支付
    api project(':commonlibs:hunheku:libapppay')
    // Glide 4.0以上
    api librarys.glide47
    api project(':commonlibs:hunheku:libglide47')
    //TODO other libs
// https://github.com/crazyandcoder/citypicker                      城市选择器
// pi 'liji.library.dev:citypickerview:4.3.2'
    api project(':commonlibs:yewulibs:slbcitypickerview')
    // slbbluetooth                                                  蓝牙               
    api project(':commonlibs:yewulibs:slbbluetooth')
    // jiaozivideoplayer                                            播放器
    api project(':commonlibs:yewulibs:slbjiaozivideoplayer')
    // ncalendar                                                    小米日历
    api project(':commonlibs:yewulibs:slbncalendar')
    // slbota                                                       OTA升级
    api project(':commonlibs:yewulibs:slbota')
// https://github.com/ikew0ng/SwipeBackLayout                       
// api 'me.imid.swipebacklayout.lib:library:1.1.0'                  关闭页面IOS效果
    api project(':commonlibs:yewulibs:slbswipebacklayout')
    // slbwifi                                                      WIFI选择器            
    api project(':commonlibs:yewulibs:slbwifi') 
    // https://github.com/scwang90/SmartRefreshLayout               下拉刷新上拉加载
    api project(':commonlibs:smartrefreshlayout:refresh-footer')
    api project(':commonlibs:smartrefreshlayout:refresh-header')
    api project(':commonlibs:smartrefreshlayout:refresh-layout')
    //
    api 'com.shuyu:GSYVideoPlayer:7.1.5'                            播放器
//    api 'com.shuyu:gsyVideoPlayer-java:7.0.1'
    //是否需要ExoPlayer模式
//    api 'com.shuyu:GSYVideoPlayer-exo2:7.0.1'
    //更多ijk的编码支持
//    api 'com.shuyu:gsyVideoPlayer-ex_so:7.0.1'
//    api('com.shuyu:GSYVideoPlayer:7.0.1') {
////        exclude module: 'support-v4'
//        exclude(group: 'com.android.support', module: 'support-annotations')
//    }
    // uploadpic                                                    图片选择器old
    api project(':commonlibs:yewulibs:slbimagepicker')
    // https://github.com/jenly1314/ZXingLite                       扫码
//    api 'com.king.zxing:zxing-lite:1.1.4-androidx'
    api project(':commonlibs:zxinglibs:zxinglibs1')
    api project(':commonlibs:zxinglibs:zxinglibs2')
    api project(':commonlibs:zxinglibs:zxinglibs3')
    //                                                              recycleview
    api project(':commonlibs:hunheku:libZRecyclerView')
    // https://github.com/wangyiwy/PDF_Preview                      PDF
    api project(':commonlibs:hunheku:libpdf')
    // 高德地图                                                     地图marker    
    api project(':commonlibs:hunheku:liblocations')












