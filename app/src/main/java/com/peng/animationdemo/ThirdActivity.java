package com.peng.animationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_third)
public class ThirdActivity extends AppCompatActivity {
    @ViewById
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_third);


    }

    @AfterViews
    public void initList() {
        /*
        * 第一步：创建和初始化ListView并设置适配器
        * */
        ArrayList<String> arrayList = new ArrayList<>();

        for (int index = 0; index < 20; index++) {
            arrayList.add("item " + index);
        }
        listView.setAdapter(new ArrayAdapter<String>(ThirdActivity.this, android.R.layout.simple_list_item_1, arrayList));

        /*
        * 第二步：编写Animation实现的效果
        * */
        AnimationSet set = new AnimationSet(true);
        Animation translate = AnimationUtils.loadAnimation(ThirdActivity.this, R.anim.list_translate);
        Animation alpha = new AlphaAnimation(0f, 1f);
        alpha.setDuration(1000);
        set.addAnimation(translate);
        set.addAnimation(alpha);
        //这里使用了插入器：android.R.anim.overshoot_interpolator
        set.setInterpolator(ThirdActivity.this, android.R.anim.overshoot_interpolator);
        // listView.startAnimation(set);

        /*
        * 第三步：创建LayoutAnimationController对象（布局动画控制器），设置item出现的顺序
        * */
        //布局动画控制器
        LayoutAnimationController controller = new LayoutAnimationController(set);
        //item出现的顺序    ORDER_NORMAL:顺序出现    ORDER_RANDOM:随机顺序出现      ORDER_REVERSE:逆序出现
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);

        /*
        * 第四步：为ListView设置布局动画，并开始布局动画
        * */
        //加载布局动画需要使用setLayoutAnimation方法
        listView.setLayoutAnimation(controller);
        //如果不使用布局动画控制器，直接执行这个也是没问题的，但是所有item会一起出现，因为没法设置正常顺序出现
        listView.startLayoutAnimation();
    }

}
