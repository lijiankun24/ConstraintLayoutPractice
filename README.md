## ConstraintLayoutPractice

ConstraintLayout 是在 2016 年 Google 大会上推出的一个新的布局控件，众所周知，ConstraintLayout 有两个优势：使用 ConstraintLayout 编写布局性能更高，使用 ConstraintLayout 可以通过拖拽的形式更方便地编写布局文件。之前一直知道这个布局控件，但是一直没有详细地学习过，最近学习了它的使用，现在简单记录一下。

本文主要分为以下几个部分：
<div align=center>
      <img src="/screenshot/catalog.png" width="701" height="345"/>
</div>

## 1. 背景介绍
在面试的时候经常会被问到有没有做过布局优化，都通过什么方式优化布局界面的？优化布局界面其中一种方式就是通过 ConstraintLayout 降低布局层级，从而避免过度测量和绘制。本篇文章重点讲解 ConstraintLayout 的用法，关于 ConstraintLayout 性能方面的优势，可以参考这篇文章：[解析 ConstraintLayout 的性能优势](https://mp.weixin.qq.com/s/gGR2itbY7hh9fo61SxaMQQ)。

通过可视化拖拽的方式编写 ConstraintLayout 布局界面，个人是不太推崇的，虽然它确实很方便，但是即使通过可视化拖拽的方式编写布局之后，还是需要看懂 xml 文件中关于 ConstraintLayout 的属性，才可以更灵活的修改布局界面，所以本文主要讲解通过 xml 属性的方式编写 ConstraintLayout 布局界面，关于可视化拖拽的方式编写 ConstraintLayout 布局界面，可以参考郭霖写的这篇博客：[ Android 新特性介绍，ConstraintLayout 完全解析](http://blog.csdn.net/guolin_blog/article/details/53122387)

好了，上面介绍了 ConstraintLayout 的两个优点，下面开始进入本文的正题，通过 xml 布局属性的方式编写 ConstraintLayout 布局。使用之前，需要在 `build.gradle` 文件中添加对 ConstraintLayout 的依赖：
``` grovvy
dependencies {
    ...
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    ...
}
```

## 2. 详细使用
本节主要分为 7 点介绍 ConstraintLayout 的详细使用，那就开始吧~

### 2.1 相对位置
ConstraintLayout 和 RelativeLayout 是非常类似的布局控件，它们之间最大的相似之处在于都可以编写一个控件相对于其他控件或父控件的相对位置，比如下面这个样式的布局既可以通过
RelativeLayout 实现，也可以通过 ConstraintLayout 实现
<div align=center>
      <img src="/screenshot/relativepositioning.png" width="521" height="407"/>
</div>

通过 ConstraintLayout 实现的代码如下所示：
``` xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.lijiankun24.constraintlayoutpractice.RelativePositionActivity">

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="@color/colorPrimary"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
        app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
        app:title="@string/relative_positioning"
        app:titleTextColor="@android:color/white"/>

    <TextView
        android:id="@+id/tv_a"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:background="@color/colorPrimary"
        android:padding="30dp"
        android:text="A"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toLeftOf="@+id/tv_b"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"/>

    <TextView
        android:id="@+id/tv_b"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:background="@color/colorPrimary"
        android:padding="30dp"
        android:text="B"
        app:layout_constraintLeft_toRightOf="@+id/tv_a"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"/>
</android.support.constraint.ConstraintLayout>
```
通过上面的代码，可以看到几个陌生的 xml 属性，它们都是 ConstraintLayout 的 xml 属性，比如：
``` xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    ...
    >

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        ...
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"/>

    <TextView
        android:id="@+id/tv_a"
        ...
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toLeftOf="@+id/tv_b"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"/>

    <TextView
        android:id="@+id/tv_b"
        ...
        app:layout_constraintLeft_toRightOf="@+id/tv_a"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"/>
</android.support.constraint.ConstraintLayout>
```
通过属性的名字就可以猜测出它们大概的意思，比如：
  * `app:layout_constraintLeft_toLeftOf` 是指该控件的左边缘和某个控件的左边缘对齐
  * `app:layout_constraintRight_toRightOf` 是指该控件的右边缘和某个控件的右边缘对齐
  * `app:layout_constraintTop_toBottomOf` 是指该控件的上边缘和某个控件的下边缘对齐

依次类推，同样含义的属性还有：
  * `layout_constraintLeft_toLeftOf`
  * `layout_constraintLeft_toRightOf`
  * `layout_constraintRight_toLeftOf`
  * `layout_constraintRight_toRightOf`
  * `layout_constraintTop_toTopOf`
  * `layout_constraintTop_toBottomOf`
  * `layout_constraintBottom_toTopOf`
  * `layout_constraintBottom_toBottomOf`
  * `layout_constraintStart_toEndOf`
  * `layout_constraintStart_toStartOf`
  * `layout_constraintEnd_toStartOf`
  * `layout_constraintEnd_toEndOf`
  * `layout_constraintBaseline_toBaselineOf`
  > 上述属性的属性值可以是某个控件的 id，也可以是 `parent`，比如：

  > app:layout_constraintLeft_toRightOf="@+id/tv_a"

  > app:layout_constraintRight_toRightOf="parent"

  对于相对位置，官方给出的示意图如下所示：
  <div align=center>
        <img src="/screenshot/relativepositioning1.png" width="560" height="148"/>
  </div>

### 2.2 边距（Margins）
假如现在有如下这样一个界面：TextViewA 上边距离 Toolbar 30dp，在屏幕最左边，TextViewB 上边距离 Toolbar 30dp，左边距离 TextView A 也是 30dp，该如何编写布局文件呢？
<div align=center>
      <img src="/screenshot/margin.png" width="523" height="431"/>
</div>

该布局 xml 关键代码如下所示：
``` xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    ...
    >

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        ...
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"/>

    <TextView
        android:id="@+id/tv_a"
        ...
        android:layout_marginTop="30dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toLeftOf="@+id/tv_b"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"/>

    <TextView
        android:id="@+id/tv_b"
        ...
        android:layout_marginLeft="30dp"
        android:layout_marginTop="30dp"
        app:layout_constraintLeft_toRightOf="@+id/tv_a"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"/>
</android.support.constraint.ConstraintLayout>
```
在 ConstraintLayout 布局中，下面几个 Margin 相关的属性依然是有效的
* `android:layout_marginStart`
* `android:layout_marginEnd`
* `android:layout_marginLeft`
* `android:layout_marginTop`
* `android:layout_marginRight`
* `android:layout_marginBottom`
> 注意：在 ConstraintLayout 中，上述属性值只可以是正数或者0，不可以是负数。


假如现在有这样的一个需求，当 TextViewA 显示的时候，TextViewB 距离左边是 20dp；当 TextViewA 可见性为 `Gone` 的时候，TextViewB 距离左边是 30dp，使用 ConstraintLayout 可以很容易的实现这样的需求。

在 ConstraintLayout 中如下这些边距属性就是当依据的控件变为 Gone 的时候就会生效
* `layout_goneMarginStart`
* `layout_goneMarginEnd`
* `layout_goneMarginLeft`
* `layout_goneMarginTop`
* `layout_goneMarginRight`
* `layout_goneMarginBottom`

### 2.3 居中显示
假如说有如下图所示的一个界面，TextViewA 在布局的正中间，如果用 RelativeLayout 和 LinearLayout 实现是很方便的，但是用 ConstraintLayout 怎么实现呢？
<div align=center>
      <img src="/screenshot/centerposition.png" width="460" height="764"/>
</div>

其实也很简单，代码如下：
``` xml
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.lijiankun24.constraintlayoutpractice.RelativePositionActivity">

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="@color/colorPrimary"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
        app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
        app:title="@string/centering_positioning"
        app:titleTextColor="@android:color/white"/>

    <TextView
        android:id="@+id/tv_a"
        android:layout_width="100dp"
        android:layout_height="wrap_content"
        android:background="@color/colorPrimary"
        android:gravity="center"
        android:paddingBottom="10dp"
        android:paddingTop="10dp"
        android:text="A"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"/>
</android.support.constraint.ConstraintLayout>
```

在 ConstraintLayout 中并不像 LinearLayout 和 RelativeLayout 是通过 `android:gravity="center"`、`android:layout_gravity="center"`、`android:layout_centerVertical="true"` 和 `android:layout_centerHorizontal="true"` 设置居中位置的，而是通过 `app:layout_constraintBottom_toBottomOf` 、`app:layout_constraintLeft_toLeftOf`、`app:layout_constraintRight_toRightOf` 和 `app:layout_constraintTop_toBottomOf` 设置该控件上下左右分别依附于指定的控件即可。

在 ConstraintLayout 中有一个非常重要的概念 ---- constraint(约束)：当控件有自己的大小时，例如：`wrap_content` 和具体的数值时，我们为控件添加的都是 constraint (约束)，这个约束有点像橡皮筋一样会有一个拉力拉着控件，但是并不会改变控件的大小。

例如上例，在居中显示中，TextViewA 宽度为 100dp，左边有 `app:layout_constraintLeft_toLeftOf="parent"` 约束，右边有 `app:layout_constraintRight_toRightOf="parent"` 约束，左边和右边分别有一个同样大小的力拉着 TextViewA 控件，所以 TextViewA 会水平居中，竖直方向也是一样的。

如果想让上述例子中的 TextViewA 水平方向撑满整个父控件，即如下图所示，那该怎么做呢？
<div align=center>
      <img src="/screenshot/centerposition1.png" width="460" height="764"/>
</div>

首先我们想到的是使用 `android:layout_width="match_parent"` 实现，这样设置之后确实也会实现这样的效果，但是查看 ConstraintLayout 的官方文档发现，在 ConstraintLayout 中 `match_parent` 已经被 `match_constraint` 所取代，所以使用 `android:layout_width="0dp"` 更为合适（在 xml 中并没有 `match_constraint` 这个属性值，0dp 即代码 `match_constraint` 值）。

**Bias属性**

可以在上下和左右分别有约束的时候加上偏移率，属性如下所示：
``` xml
app:layout_constraintHorizontal_bias="0.1"
app:layout_constraintVertical_bias="0.8"
```
* `layout_constraintHorizontal_bias` 取值范围是[0.0 ~ 1.0]，从左向右
* `layout_constraintVertical_bias` 取值范围是[0.0 ~ 1.0]，从上到下
<div align=center>
      <img src="/screenshot/centerposition2.png" width="380" height="120"/>
</div>

### 2.4 可见性对布局的影响
在 2.1 节中提到过几个特殊的属性：
* `layout_goneMarginStart`
* `layout_goneMarginEnd`
* `layout_goneMarginLeft`
* `layout_goneMarginTop`
* `layout_goneMarginRight`
* `layout_goneMarginBottom`

这些属性是配合着 `layout_marginStart` 等属性使用的，当控件的约束所依附的 target 控件的 visibility 不为 GONE 的时候，`layout_marginStart` 等属性起作用，当 target 控件的 visibility 为 GONE 的时候，`layout_goneMarginStart` 属性起作用

### 2.5 尺寸约束
#### ConstraintLayout 的最小尺寸
当 ConstraintLayout 的宽度或者高度为 `wrap_content` 时，可以通过如下属性为 `ConstraintLayout` 设置最小尺寸或最大尺寸
* `android:minWidth`：最小宽度
* `android:minHeight`：最小高度
* `android:maxWidth`：最大宽度
* `android:maxHeight`：最大高度

#### 控件的尺寸约束
在 `ConstraintLayout` 中，设置控件的大小总共有三种方式，分别是：
* 一个具体的属性值，比如：`123dp` 或者一个 Dimension 引用
* 使用 `wrap_content`，根据自身大小决定
* `0dp` 即 `match_constraint`

#### 控件按宽高比设置大小
在 `ConstraintLayout` 中，其中的控件可以按照宽高比设置其大小，前提是控件的宽或者高的尺寸至少有一个是 `0dp`，然后通过 `layout_constraintDimensionRatio` 设置控件的宽高比，显示出来的控件的宽和高即是设置的比例的大小，如下所示：
```xml
<!-- 此 Button 的宽和高相等-->
<Button
  android:layout_width="wrap_content"
  android:layout_height="0dp"
  app:layout_constraintDimensionRatio="1:1" />
```
>在上面 xml 代码中，Button 的宽度是一个特定值：`wrap_content`，高度是一个可变值：`0dp`，宽：高 = 1 ：1，则高度也就是和宽度相同的值

通过 `layout_constraintDimensionRatio` 设置的参数可以是：
* 一个浮点数，代表 `宽/高` 的比例
* 也可以是上述例子中的形式：`宽：高`

当控件宽和高的值都是 `0dp` 时，也可以通过 `layout_constraintDimensionRatio` 设置宽和高的比例，比如：
```xml
<Button
  android:layout_width="0dp"
  android:layout_height="0dp"
  app:layout_constraintDimensionRatio="H,16:9"
  app:layout_constraintBottom_toBottomOf="parent"
  app:layout_constraintTop_toTopOf="parent"/>
```
> 在上面 xml 代码中，Button 的宽是可变值：`0dp`，高也是可变值：`0dp`，高：宽 = 16：9，但是 Button 有上下两个约束：高顶到父控件的上边缘，底顶到父控件的下边缘，这样 Button 的高度就固定了，再通过比例，即可得到宽 = 高 * 9/16.

### 2.5 链（Chains）
在 ConstraintLayout 中有一个非常重要的概念：**链（Chains）**

那什么才是链呢？在下图所示的界面中即存在一个链：
<div align=center>
      <img src="/screenshot/chains.png" width="460" height="762"/>
</div>

上图中 TextViewA、TextViewB 和 TextViewC 形成了一个链，上图对应的 xml 代码是：
```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.lijiankun24.constraintlayoutpractice.RelativePositionActivity">

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="@color/colorPrimary"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
        app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
        app:title="@string/chains"
        app:titleTextColor="@android:color/white"/>

    <TextView
        android:id="@+id/tv_a"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:background="@color/colorPrimary"
        android:padding="20dp"
        android:text="A"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toLeftOf="@+id/tv_b"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"/>

    <TextView
        android:id="@+id/tv_b"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:background="@color/colorPrimary"
        android:padding="20dp"
        android:text="B"
        app:layout_constraintLeft_toRightOf="@+id/tv_a"
        app:layout_constraintRight_toLeftOf="@+id/tv_c"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"/>


    <TextView
        android:id="@+id/tv_c"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:background="@color/colorPrimary"
        android:padding="20dp"
        android:text="C"
        app:layout_constraintLeft_toRightOf="@+id/tv_b"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"/>
</android.support.constraint.ConstraintLayout>
```

从代码中可以看到形成链的三个控件有以下特点：
* TextViewA 和 TextViewB 通过 `app:layout_constraintRight_toLeftOf="@+id/tv_b"` 和 `app:layout_constraintLeft_toRightOf="@+id/tv_a"` 相互依赖
* TextViewB 和 TextViewC 通过 `app:layout_constraintRight_toLeftOf="@+id/tv_c"` 和 `app:layout_constraintLeft_toRightOf="@+id/tv_b"` 相互依赖

这样便形成了一个链，在此链中最左边的控件称为链头。同样的也可以通过上下相互依赖形成上下的链，最上面的控件称为链头。

在链头中通过 `layout_constraintHorizontal_chainStyle` 或 `layout_constraintVertical_chainStyle` 可以设置链的样式。
链的样式总共有三种：
* CHAIN_SPREAD：默认样式
* CHAIN_SPREAD_INSIDE
* CHAIN_PACKED

用一张官方的图解释上面几个属性的含义，如下图所示：
<div align=center>
      <img src="/screenshot/chainsStyle.png" width="642" height="325"/>
</div>

**这里需要强调下比重链（Weighted chain）样式**：在 `CHAIN_SPREAD` 样式的链中，如果其中的控件的宽度或高度为 0dp，即可以通过 `layout_constraintHorizontal_weight` 或 `layout_constraintVertical_weight` 设置该控件在水平或者竖直方向的比例，类似于 LinearLayout 中的 weight 属性的作用

### 2.6 Guideline
在 ConstraintLayout 中有一个特殊的辅助类：`android.support.constraint.Guideline`，主要用于辅助布局，可以把它看做是一个辅助线，但是不会绘制到界面上，有水平的和垂直的。

`Guideline` 有以下几个属性：
* `android:orientation`：`vertical` 或 `horizontal`，表示此 Guideline 是水平的还是垂直的
* `app:layout_constraintGuide_begin`：表示 Guideline 的距离左边或上边的距离，根据方向决定是距离哪边的
* `app:layout_constraintGuide_begin`：表示 Guideline 的距离右边或下边的距离，根据方向决定是距离哪边的
* `app:layout_constraintGuide_percent`：表示 Guideline 距离左边或上边的百分比，根据方向决定是距离哪边的

用一张效果图展示 Guideline 的作用，一个 TextView 在中线的左边 32dp 位置处，另一个 TextView 在中线右边 32dp 处
<div align=center>
      <img src="/screenshot/guideline.png" width="230" height="388"/>
</div>

上图代码如下：
``` xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.lijiankun24.constraintlayoutpractice.VirtualHelpersActivity">

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="@color/colorPrimary"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
        app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
        app:title="@string/virtual_helpers_objects"
        app:titleTextColor="@android:color/white"/>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginRight="32dp"
        android:background="@android:color/holo_blue_light"
        android:text="@string/virtual_helpers_objects"
        android:textColor="@android:color/white"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintRight_toLeftOf="@+id/guide_line"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"
        app:layout_constraintVertical_bias="0.502"/>

    <android.support.constraint.Guideline
        android:id="@+id/guide_line"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_percent="0.5"/>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="32dp"
        android:background="@android:color/holo_blue_light"
        android:text="@string/virtual_helpers_objects"
        android:textColor="@android:color/white"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toRightOf="@+id/guide_line"
        app:layout_constraintTop_toBottomOf="@+id/toolbar"
        app:layout_constraintVertical_bias="0.502"/>
</android.support.constraint.ConstraintLayout>
```

好啦，至此关于 ConstraintLayout 的使用就基本全部介绍完毕，是不是觉得很好用呢？那就赶快在项目中使用起来的！文中涉及的代码都在这儿：[ConstraintLayoutPractic](https://github.com/lijiankun24/ConstraintLayoutPractice)。


------------
**参考资料：**

[ConstraintLayout 官方文档](https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html#DimensionConstraints)

[ConstraintLayout 完全解析 快来优化你的布局吧](http://blog.csdn.net/lmj623565791/article/details/78011599)  -- [Hongyang](http://blog.csdn.net/lmj623565791)

[Android ConstraintLayout 约束布局](http://blog.csdn.net/jjwwmlp456/article/details/72912807)  --  [打鱼还是晒网 —— stone](http://blog.csdn.net/jjwwmlp456)

[ConstraintLayout约束布局使用教程难点理解](http://blog.csdn.net/yueding_/article/details/78628086) -- [yueding](http://blog.csdn.net/yueding_)
