# Autolayout

### 一、简单说一下我这个AutoUtils适配屏幕的核心原理：  
1、屏幕的实际尺寸与设计图尺寸，计算出一个比例。  
2、然后按这个比例调整控件的size（宽高）、padding、margin。  
3、对于字体大小，则按对角线的比例来调整。  
（其实对于字体大小，我认为比较完美的解决办法是让美工对于每种分辨率设定一个缩放比例。例如设计图是1280x720，这是标准的1倍；对于1920x1080是1.5倍，如此类推；我们按美工给的比例进行缩放就好了）

### 二、使用方法：
1、设计图的数值全部以px（像素）为单位，字体大小也是px。布局按设计图写好，只对设计图的分辨率摆好控件就可以了。   

2、代码里设置设计图的尺寸，例如：  
**AutoUtils.setSize(this, false, 720, 1280);**  
//第一个参数this，是Activity对象。  
//第二个参数false，是boolean变量，表示没有状态栏。如果你的APP要有状态栏，就设置为true。  
//第三个参数720，是int变量，是设计尺寸的宽度。  
//第四个参数1280，是int变量，是设计尺寸的高度。  

注意：  
（1）横屏时互换一下宽高的参数，并且横屏要使用横屏的布局。不要以为互换一下宽高的参数，继续用竖屏的布局就可以作用于横屏了，这样做效果不好的。  
（2）当你的屏幕状态要改变时，记得重新调用一下setSize方法。例如，横屏转回竖屏；全屏转回有状态栏。  

3、在Activity的onCreate里使用：  
在setContentView()之后：  
**AutoUtils.auto(this);**  
//参数this，是Activity对象。  

这样就可以适配Activity的布局了。  

4、对于Fragment、列表（RecyclerView等）、动态加载的控件，使用：  
**AutoUtils.auto(view);**  
//参数view，是View对象。  

凡是需要inflate导入view的地方，或动态生成的控件，用这个方法处理一下就可以。  
RecyclerView在ViewHolder里调用一下这个方法。  

注意：第3和4点的auto方法，对同一个控件或同一个布局，都只能使用一次，即适配一次。再次使用，就再次计算，这样适配结果就不对了。  

5、对于自定义控件：  
主要有两种情况：  
（1）对于那些没涉及到什么尺寸计算的自定义控件，在外面用AutoUtils.auto(this)，随同布局一起适配；或者动态加载时用AutoUtils.auto(view);适配就好了。  
（2）对于那些涉及尺寸计算的自定义控件，建议修改AutoUtils.auto(view)的代码，把它排除掉：if(view instanceof CustomView)return;//不适配CustomView这个类。  
然后自己在CustomView这个类里用getDisplayWidthValue()、getDisplayHeightValue()、getDisplayTextSize()这三个方法处理水平、垂直、字体大小的适配。CustomView类外酌情使用autoMargin()、autoPadding()、autoSize()适配。    

6、平移动画的距离可以用getDisplayWidthValue()、getDisplayHeightValue()来调整。  


### 三、一些常见问题：
1、TabLayout里的字体大小适配不了？因为没办法直接操作它的TextView，要用自定义标签布局，然后你适配它就可以。  
2、注意，设置字体大小要用setTextSize(TypedValue.COMPLEX_UNIT_PX,26);加上TypedValue.COMPLEX_UNIT_PX，因为它默认是用SP的。  
3、现在还不支持圆角的适配，例如CardView的圆角。  
4、某些属性值还没有加入适配，例如TextView的android:lineSpacingExtra,可以自己加上。因为我现在只对控件的size（宽高）、padding、margin、textSize四个方面进行适配。  

  
* 现在AutoUtils有些新调整，Demo里那个是旧的，请直接用这个：https://github.com/zhengjingle/Autolayout/blob/master/src/com/zjl/autolayout/AutoUtils.java
* CSDN上的介绍：http://blog.csdn.net/zhengjingle/article/details/51742839