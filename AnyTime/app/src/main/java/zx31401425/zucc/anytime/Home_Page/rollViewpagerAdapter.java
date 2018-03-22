package zx31401425.zucc.anytime.Home_Page;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import zx31401425.zucc.anytime.R;

/**
 * Created by 赵轩 on 2018/3/22.
 */

public class rollViewpagerAdapter extends StaticPagerAdapter {

    private int[] res={R.drawable.sp1_1
            ,R.drawable.sp1_2,
            R.drawable.sp1_3,
            R.drawable.sp1_4};

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView imageView=new ImageView(container.getContext());
        imageView.setImageResource(res[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return imageView;
    }

    @Override
    public int getCount() {
        return res.length;
    }
}
