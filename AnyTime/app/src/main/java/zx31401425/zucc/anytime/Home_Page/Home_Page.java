package zx31401425.zucc.anytime.Home_Page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;

import zx31401425.zucc.anytime.MainActivity;
import zx31401425.zucc.anytime.R;
import zx31401425.zucc.anytime.Task_All.AllTaskActivity;
import zx31401425.zucc.anytime.Task_All.ReleaseTaskActivity;


public class Home_Page extends AppCompatActivity implements View.OnClickListener{
    private RollPagerView rollPagerView;
    private TextView rollText;
    private CardView cardview1;
    private CardView cardview2;
    private CardView cardview3;
    private CardView cardview4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        getSupportActionBar().hide();// 隐藏ActionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//remove notification bar  即全屏
        setContentView(R.layout.activity_home__page);
        rollPagerView= (RollPagerView) findViewById(R.id.rollViewpager);
        rollPagerViewSet();
        rollText = (TextView) findViewById(R.id.text_marquee);
        setTextMarquee(rollText);
        cardview1 = (CardView)  findViewById(R.id.cardView1);   //cardView1  为我的任务
        setCardView(cardview1);
        cardview1.setOnClickListener(this);
        cardview2 = (CardView)  findViewById(R.id.cardView2);  //cardView2   为发布任务
        setCardView(cardview2);
        cardview2.setOnClickListener(this);
        cardview3 = (CardView)  findViewById(R.id.cardView3);  //cardView2   为发布任务
        setCardView(cardview3);
        cardview3.setOnClickListener(this);
        cardview4 = (CardView)  findViewById(R.id.cardView4);  //cardView2   为发布任务
        setCardView(cardview4);
        cardview4.setOnClickListener(this);
    }

    private  void setCardView(CardView cardView){
        cardView.setRadius(8);//设置图片圆角的半径大小
        cardView.setCardElevation(8);//设置阴影部分大小
        cardView.setContentPadding(5,5,5,5);//设置图片距离阴影大小
    }

    public static void setTextMarquee(TextView textView) {   //设置TEXT走马灯的设置
        if (textView != null) {
            textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textView.setSingleLine(true);
            textView.setSelected(true);
            textView.setFocusable(true);
            textView.setFocusableInTouchMode(true);
        }
    }

    private void rollPagerViewSet() {    //设置图片滚动的设置
        rollPagerView.setPlayDelay(3000);//*播放间隔
        rollPagerView.setAnimationDurtion(500);//透明度
        rollPagerView.setAdapter(new rollViewpagerAdapter());//配置适配器
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.cardView1){

        }else if(view.getId() == R.id.cardView2){
            Intent intent =new Intent(Home_Page.this,ReleaseTaskActivity.class);
            startActivity(intent);
        }else if(view.getId() == R.id.cardView3){
            Intent intent =new Intent(Home_Page.this,AllTaskActivity.class);
            startActivity(intent);
        }else if(view.getId() == R.id.cardView4){

        }
    }
}
