package app.example.com.statusbar;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by zhhr on 2018/3/27.
 */

public class ZSwitchNightUtils {

    private Activity mActivity;
    private RelativeLayout mToastLayout;
    private SwitchNightRelativeLayout mToast;
    private ViewGroup mView;
    private String text;
    private long times;
    private static ZSwitchNightUtils mToastInstance;
    private static boolean isNight=true;//默认为白天模式

    public ZSwitchNightUtils(Activity mActivity){
        this.mActivity = mActivity;

    }

    public ZSwitchNightUtils(ViewGroup mView){
        this.mView = mView;

    }

    public static ZSwitchNightUtils makeView(Activity mActivity){
        mToastInstance = new ZSwitchNightUtils(mActivity);
        return mToastInstance;
    }

    public static ZSwitchNightUtils makeView(ViewGroup mView){
        mToastInstance = new ZSwitchNightUtils(mView);
        return mToastInstance;
    }



    public void show(){
        if(mActivity!=null){
            mToastLayout = (RelativeLayout) mActivity.findViewById(R.id.rl_Night);
            if(mToastLayout==null){//判断是否已经添加进母VIEW里，没有则添加进去
                mToast = new SwitchNightRelativeLayout(mActivity);
                mActivity.addContentView(mToast,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }else{//如果有，则直接取出
                mToast = (SwitchNightRelativeLayout) mToastLayout.getParent();
            }
        }else if(mView!=null){
            mToastLayout = (RelativeLayout) mView.findViewById(R.id.rl_Night);
            if(mToastLayout==null){
                mToast = new SwitchNightRelativeLayout(mView.getContext());
                mView.addView(mToast,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }else{
                mToast = (SwitchNightRelativeLayout) mToastLayout.getParent();
            }
        }
        mToast.setVisibility(View.GONE,isNight);
        isNight = !isNight;
    }
}
