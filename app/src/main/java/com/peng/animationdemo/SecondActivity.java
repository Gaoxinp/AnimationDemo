package com.peng.animationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private int[] src = {R.id.sucai0, R.id.sucai1, R.id.sucai2, R.id.sucai3, R.id.sucai4, R.id.sucai5, R.id.sucai6, R.id.sucai7};
    private List<ImageView> imageViewList = new ArrayList<ImageView>();

    //    菜单图片是否展开了
    private boolean flag = false;

    //    每个菜单图片移动的总距离
    private final float TRANSLATIONLENGTH = 300f;
    //    可以起作用的菜单图片的总个数
    private final int MENUSUM = src.length - 1;
    //    每个菜单图片需要旋转的角度
    private final double ROTAION = 90f / (MENUSUM - 1);
    //    菜单图片相对于垂直方向的角度
    private double[] radians;
    //    菜单图片在水平方向上移动的距离
    private double[] translationXFloat;
    //    菜单图片在垂直方向上移动的距离
    private double[] translationYFloat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        initParameter();
        for (int i = 0; i < src.length; i++) {
            ImageView imageView = (ImageView) findViewById(src[i]);
            imageViewList.add(imageView);
            imageView.setOnClickListener(this);
        }


    }

    //    初始化参数
    private void initParameter() {
        translationXFloat = new double[MENUSUM];
        translationYFloat = new double[MENUSUM];
        radians = new double[MENUSUM];

        for (int i = 0; i < MENUSUM; i++) {
            radians[i] = Math.toRadians(i * ROTAION);
            translationXFloat[i] = Math.sin(radians[i]) * TRANSLATIONLENGTH;
            translationYFloat[i] = -Math.cos(radians[i]) * TRANSLATIONLENGTH;
        }
    }

    //    ImageView的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sucai0:
                if (flag == false) {
                    openMenu();
                    flag = true;
                } else {
                    closeMenu();
                    flag = false;
                }
                break;
            default:
                break;
        }
    }

    //    关闭菜单
    private void closeMenu() {
//        for (int i = 1; i < imageViewList.size(); i++) {
////            -----------------------------------按照打开的顺序关闭------------------------------------------------------------
////            ObjectAnimator translatorXAnimation = ObjectAnimator.ofFloat(imageViewList.get(i), "translationX", (float) translationXFloat[i - 1], 0f);
////            ObjectAnimator translatorYAnimation = ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", (float) translationYFloat[i - 1], 0f);
////            -----------------------------------------结束--------------------------------------------------------
//
////           ----------------------------------按照打开时的反顺序第一种方法------------------------------------------------
//            /*
//            * 这里反顺序的方式是通过修改数组的下标，且更改地方比较多，需要更改操作对象的顺序和X、Y轴偏移的取值
//            * 还有一种方法较为简单，可以直接在for的三个表达式中进行修改，从而达到效果
//            * */
//            ObjectAnimator translatorXAnimation = ObjectAnimator.ofFloat(imageViewList.get(imageViewList.size()- i), "translationX", (float) translationXFloat[imageViewList.size()- i -1], 0f);
//            ObjectAnimator translatorYAnimation = ObjectAnimator.ofFloat(imageViewList.get(imageViewList.size()- i), "translationY", (float) translationYFloat[imageViewList.size()- i -1], 0f);
////           ---------------------------------------------结束------------------------------------------------------------
//
//            AnimatorSet set = new AnimatorSet();
//            set.playTogether(translatorXAnimation, translatorYAnimation);
//            set.setDuration(500);
//            ----------------------------按照打开顺序的反顺序收起菜单的第三种方法--------------------------------
            /*
            * 第三种方法仅适用于菜单不同时打開时的情况（因为是反序，所以一般都是适用的）
            * 我们最开始打开时设置了动画的延时时间，通常都是操作的第一个对象延时时间最少，
            * 即：第一个菜单图片动画开始的时间点最早
            * 由此我们可以想到第三种反序的方法，那就是增大前面的操作对象的延时时间，减小后面操作对象的延时时间
            * 即：将第一个操作对象的延时时间设置为最大，第二个次之......最后一个最小
            * 因此在视觉上是反序的，虽然操作时并不是反序的，
            * 这恐怕是最简单的设置反序收起菜单的方法了
            * */
//            set.setStartDelay(100 * i);
////            set.setStartDelay(100 * (MENUSUM - i));
//        ---------------------------------------------结束------------------------------------------------------
////            Interpolator interpolator = new AnticipateInterpolator();
//            Interpolator interpolator = new AnticipateOvershootInterpolator();
//            set.setInterpolator(interpolator);
//            set.start();
//        }



//        --------------------------------按照打开时的反顺序关闭第二种方法---------------------------------
        /*
        * 第二种方法只需要在for循环的是是三个表达式中修改，使得按相反的顺序操作对象即可
        * 需要注意的是，程序中设置了延时时间，因为for循环的i从7到1，因此延时也必须修改，以使操作对象在时间上跟操作顺序上保持一致
        * 有注意事项这里我们可以想到第三种方法，那就是在第一种方法的代码中，我们不改变操作对象的顺序，但是改变每个对象开始动画的延时，
        * 让第一个操作对象的开始动画的时间点最晚，这样就能达到反序收起菜单的视觉效果
        * */
        for (int i = MENUSUM; i > 0; i--) {
            ObjectAnimator translatorXAnimation = ObjectAnimator.ofFloat(imageViewList.get(i), "translationX", (float) translationXFloat[i - 1], 0f);
            ObjectAnimator translatorYAnimation = ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", (float) translationYFloat[i - 1], 0f);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(translatorXAnimation, translatorYAnimation);
            set.setDuration(500);
            set.setStartDelay(100 * (MENUSUM - i));
//            Interpolator interpolator = new AnticipateInterpolator();
            Interpolator interpolator = new AnticipateOvershootInterpolator();
            set.setInterpolator(interpolator);
            set.start();
        }
//        ----------------------------------------结束-------------------------------------------------
    }

    //    展开菜单
    private void openMenu() {
        for (int i = 1; i < imageViewList.size(); i++) {
            ObjectAnimator translatorXAnimation = ObjectAnimator.ofFloat(imageViewList.get(i), "translationX", 0f, (float) translationXFloat[i - 1]);
            ObjectAnimator translatorYAnimation = ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", 0f, (float) translationYFloat[i - 1]);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(translatorXAnimation, translatorYAnimation);
            set.setDuration(500);
            set.setStartDelay(100 * i);
//            Interpolator interpolator = new AnticipateInterpolator();
            Interpolator interpolator = new AnticipateOvershootInterpolator();
            set.setInterpolator(interpolator);
            set.start();
        }
    }
}
