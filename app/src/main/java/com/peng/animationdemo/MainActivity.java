package com.peng.animationdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Property;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewById
    ImageView img;
    private int width, height;

    @Click(R.id.img)
    public void imgClick() {
        Toast.makeText(MainActivity.this, img.getId() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        WindowManager wm = this.getWindowManager();
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

    }

    public void alphaAnimation(View view) {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
        img.startAnimation(animation);
    }

    public void scaleAnimation(View view) {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
        img.startAnimation(animation);
    }

    public void translateAnimation(View view) {
        Animation translate = new TranslateAnimation(30f, 150f, 30f, 150f);
        translate.setDuration(2000);
//        translate.setFillAfter(true);         //是否停在动画结束时的状态
        translate.setRepeatCount(5);            // 一共播放6次
//        translate.setRepeatMode(Animation.REVERSE);  //倒序重复
        translate.setInterpolator(new OvershootInterpolator());
        img.startAnimation(translate);
    }

    public void rotateAnimation(View view) {
        Animation rotate = new RotateAnimation(0f, 360f, img.getWidth() / 2, img.getHeight() / 2);  //围绕中心点旋转
        rotate.setDuration(1000);
        img.startAnimation(rotate);

//        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
//        img.startAnimation(animation);
    }

    public void xubo1Animation(View view) {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.xubo1);
        img.startAnimation(animation);

//-----------------------------------方法二----------------------------------------------------------
//        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
//        img.startAnimation(animation);
//        final Animation rotate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                img.startAnimation(rotate);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        ---------------------------------方法二结束----------------------------------------
    }

    public void xubo2Animation(View view) {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.xubo2);
        img.startAnimation(animation);
    }

    public void shanshuoAnimation(View view) {
        Animation alpha = new AlphaAnimation(0f, 1.0f);
//      如果倒序：reverse，如果正序：RESTART
        alpha.setRepeatMode(Animation.RESTART);
        alpha.setRepeatCount(5);
        alpha.setDuration(100);
        img.startAnimation(alpha);
    }

    public void doudongAnimation(View view) {
        Animation translate = new TranslateAnimation(0f, 5f, 0f, -8f);
        translate.setDuration(100);
        translate.setRepeatMode(Animation.RESTART);
        translate.setRepeatCount(5);
        img.startAnimation(translate);
    }

    public void qiehuanAnimation(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.in_activity, R.anim.out_activity);
//        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
//        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

    public void layoutAnimation(View view) {
        Intent intent = new Intent(MainActivity.this, ThirdActivity_.class);
        startActivity(intent);
    }

    public void frameAnimationOn(View view) {
//        img.setImageDrawable(getResources().getDrawable(R.drawable.frame_animation));
        img.setImageResource(R.drawable.frame_animation);
        AnimationDrawable ad = (AnimationDrawable) img.getDrawable();
        ad.start();
    }

    public void frameAnimationOff(View view) {
//        img.setImageDrawable(getResources().getDrawable(R.drawable.frame_animation));
//        img.setImageResource(R.drawable.frame_animation);
        AnimationDrawable ad = (AnimationDrawable) img.getDrawable();
        ad.stop();
//----------------------------完全用代码实现帧动画----------------------------
//        AnimationDrawable anim = new AnimationDrawable();
//        for (int i = 1; i <= 4; i++) {
//            int id = getResources().getIdentifier("img" + i, "mipmap", getPackageName());
//            Drawable drawable = getResources().getDrawable(id);
//            anim.addFrame(drawable, 100);
//        }
//        anim.setOneShot(false);
//        img.setImageDrawable(anim);
//        anim.start();
//----------------------------------结束---------------------------------------

    }

    //  按钮点击事件
    public void propertyAnimator(final View view) {


//----------------------------使用基本的ObjectAnimator-----------------------------

        /*
        * 通过这种方法，所有定义的动画会同时播放
        * ofFloat方法的第二个参数是要操作的属性，只要谷歌对某个对象提供了get和set方法，就可以操作
        * 常见的可操作属性有：translationX，translationY，X，Y，rotation，alpha,scale
        * 当是rotation的时候，默认围绕图片中心点旋转，除非自己制定旋转中心，
        * 可以使用img.setPivotX(300);和img.setPivotY(300);设置旋转中心
        * */
//        -------------------------------方法一--------------------------------------
//        ObjectAnimator.ofFloat(img, "translationX", 0f, 200f).setDuration(1000).start();
//        ObjectAnimator.ofFloat(img, "translationY", 0f, 200f).setDuration(1000).start();
//        ObjectAnimator.ofFloat(img, "rotation", 0f, 360f).setDuration(1000).start();
//        ------------------------------方法一结束------------------------------------

//        -------------------------------对rotation的举例------------------------------------
//        ObjectAnimator animator = ObjectAnimator.ofFloat(img,"rotation",0f,360f);
//        img.setPivotX(300);
//        img.setPivotY(300);
//        ------------------------------------结束----------------------------------------------

        /*
        * 第二种方法使用PropertyValuesHolder对象来操作对象进行动画，这种方式看起来跟方法一很相似，
        * 其优势是：谷歌对PropertyValuesHolder进行了优化，使得在调用多个动画时更加节省系统资源和更加有效率
        * */
//        ------------------------------方法二-----------------------------------------
//        PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX", 0f, 200f);
//        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationY", 0f, 200f);
//        PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
//        ObjectAnimator.ofPropertyValuesHolder(img,p1,p2,p3).setDuration(1000).start();
//        ----------------------------方法二结束---------------------------------------

        /*
        * 方法三使用AnimatorSet实现多动画操作
        * AnimatorSet的playTogether方法可以使所有动画同时播放
        * AnimatorSet的playSequentially方法可以使动画按照写入参数的顺序播放
        * AnimatorSet可以调用play方法执行00一个动画，with方法：同时执行
        * after方法：在参数动画之后执行   before方法：在参数动画之前执行
        * */
//        ------------------------------方法三-----------------------------------------------
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(img, "translationX", 0f, 200f);
//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(img, "translationY", 0f, 200f);
//        ObjectAnimator animator3 = ObjectAnimator.ofFloat(img, "rotation", 0f, 360f);
//        AnimatorSet set = new AnimatorSet();
//        方法1：动画1和2同时播放，动画3在播放完动画1后播放
//        set.play(animator1).with(animator2);
//        set.play(animator3).after(animator1);

//        方法2：同时播放动画
//        set.playTogether(animator1,animator2,animator3);

//        方法3：按顺序播放动画
//        set.playSequentially(animator1,animator2,animator3);

//        set.setDuration(1000);
//        set.start();
//        -----------------------------方法三结束--------------------------------------------
//---------------------------------结束--------------------------------------------


//---------------------------------------对按钮的动画进行监听----------------------------------
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        animator.setDuration(1000);

//        -----------------------------------方法一-------------------------------------------
//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                Button bt = (Button) view;
//                Toast.makeText(MainActivity.this,bt.getText().toString(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        -------------------------------方法一结束-------------------------------------------
//        ---------------------------------方法二----------------------------------------------
        /*
        * 通常情况下我们不需要重写AnimatorListener里面的所有方法，例如我们只想在动画结束时进行其他操作
        * 这时候我们可以选择使用AnimatorListenerAdapter来代替AnimatorListener，然后选择合适的方法进行重写
        * 这样可以对代码进行优化
        * ObjectAnimation.setStartDelay方法是为动画设置延时
        * */
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Button bt = (Button) view;
                Toast.makeText(MainActivity.this, bt.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        animator.setStartDelay(500);
//          -------------------------------方法二结束-------------------------------------------
        animator.start();
//-------------------------------------------结束----------------------------------------------
    }
}
