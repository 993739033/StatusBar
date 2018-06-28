package app.example.com.statusbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by mnkj on 2018/6/28.
 */

public class ToastLayout extends RelativeLayout {
    TextView T_Content;
    Context mContext;
    View view;
    int show_Time = 300;//动画完成时间
     boolean isShow=false;//是否显示中

    public boolean isShow() {
        return isShow;
    }
    public ToastLayout(Context context) {
        super(context);
        initView();
    }

    public ToastLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ToastLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.toastlayout, null);
        addView(view);
        T_Content = view.findViewById(R.id.tv_content);
    }
    public ToastLayout setContent(String content){
        if(T_Content!=null){
            T_Content.setText(content);
        }
        return this;
    }

    public void show(long keepTime){
        AnimationSet set = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,-DisplayUtil.dip2px(getContext(),60),0);
        TranslateAnimation translateAnimation1 = new TranslateAnimation(0, 0, 0, -DisplayUtil.dip2px(getContext(), 60));
        translateAnimation1.setStartOffset(show_Time+keepTime);
        translateAnimation.setDuration(show_Time);
        translateAnimation1.setDuration(show_Time);
        set.addAnimation(translateAnimation);
        set.addAnimation(translateAnimation1);
        this.startAnimation(set);
        this.bringToFront();//添加到最上层
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(VISIBLE);
                isShow = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isShow = false;
                view.setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}
