package app.example.com.statusbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    protected boolean isTrans;
    RelativeLayout layout_show;
    private int ANIM_TIME = 300;
    private boolean ishow=false;//是否已显示
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initStatusBar(true);
        }
        layout_show = findViewById(R.id.layout_show);

    }

    //状态栏透明
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initStatusBar(boolean isTransparent) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if(isTransparent){
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }else{
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        isTrans = isTransparent;
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    public void switchShow(View view) {
        if (ishow) {
            layoutHide();
        }else{
            layoutShow();
        }
        ishow = !ishow;
        ZSwitchNightUtils.makeView(MainActivity.this).show();

    }

    public void layoutShow() {
        int height=DisplayUtil.dip2px(this, 60);
        TranslateAnimation animation = new TranslateAnimation(0,0,height,0);
        AlphaAnimation animation1 = new AlphaAnimation(0,1);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(animation);
        animationSet.addAnimation(animation1);
        animationSet.setDuration(ANIM_TIME);
        layout_show.setAnimation(animationSet);
        layout_show.setVisibility(View.VISIBLE);
        ZToast.makeText(this, "66666", 2000).show();
    }

    public void layoutHide() {
        int height=DisplayUtil.dip2px(this, 60);
        TranslateAnimation animation = new TranslateAnimation(0,0,0,height);
        AlphaAnimation animation1 = new AlphaAnimation(1,0);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(animation);
        animationSet.addAnimation(animation1);
        animationSet.setDuration(ANIM_TIME);
        layout_show.setAnimation(animationSet);
        layout_show.setVisibility(View.GONE);
        ZToast.makeText(this, "23333", 2000).show();

    }

}
