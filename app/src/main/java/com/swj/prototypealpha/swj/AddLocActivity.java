package com.swj.prototypealpha.swj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.swj.prototypealpha.R;
import com.swj.prototypealpha.swj.util.ItemBean;
import com.swj.prototypealpha.swj.util.OnItemClickListener;
import com.swj.prototypealpha.swj.util.RecyclerViewHelper.ItemAdapter;
import com.swj.prototypealpha.swj.util.SpinnerAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class AddLocActivity extends AppCompatActivity implements OnItemClickListener
{

    private WebView webView;

    String mUrl = "https://apis.map.qq.com/tools/locpicker?search=1&type=0&backurl=https2://callback&key=QULBZ-6M6KO-5YZWR-SEYTJ-GNNS5-O6B3L&referer=myapp";

    private Toolbar tlb_checkloc;

    private RecyclerView recvv_checkloc;

    private ItemAdapter adapter;

    private List<ItemBean> itemList = new ArrayList<>();

    private TextView text_takephoto;

    private FloatingActionButton btn_addloc;

    private void initUI()
    {
        recvv_checkloc = findViewById(R.id.recv_addloc);
        tlb_checkloc = findViewById(R.id.tlb_checkloc);
        btn_addloc = findViewById(R.id.lfbtn_add_loc);
        text_takephoto = findViewById(R.id.text_loc_takephoto);
        webView = findViewById(R.id.webView);
        setSupportActionBar(tlb_checkloc);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loc);

        initUI();
        WebSettings settings = webView.getSettings();
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        settings.setAllowFileAccess(false);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        webView.setVerticalScrollbarOverlay(true);
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                if (!url.startsWith("https://callback")) {
                    view.loadUrl(url);
                }
                else {
                    try {

                        //转utf-8编码
                        String decode = URLDecoder.decode(url, "UTF-8");

                        //转uri，然后根据key取值
                        Uri uri = Uri.parse(decode);
                        String latng = uri.getQueryParameter("latng");//纬度在前，经度在后，以逗号分隔
                        String[] split = latng.split(",");
                        String lat = split[0];//纬度
                        String lng = split[1];//经度
                        String address = uri.getQueryParameter("addr");//地址


                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });
        webView.loadUrl(mUrl);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recvv_checkloc.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(itemList);

        recvv_checkloc.setAdapter(adapter);
        adapter.setItemClickListener(this);
        Update();
        btn_addloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddLocActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });
        text_takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddLocActivity.this,TakePhotoActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Update()
    {
        itemList.clear();
        Bitmap proj_name = BitmapFactory.decodeResource(getResources(),R.mipmap.project_name);
        Bitmap startingtime = BitmapFactory.decodeResource(getResources(),R.mipmap.startingtime);
        Bitmap checkedperson = BitmapFactory.decodeResource(getResources(),R.mipmap.checkedperson);
        Bitmap checkedaddr = BitmapFactory.decodeResource(getResources(),R.mipmap.proj_addr);
        Bitmap rightArrow = BitmapFactory.decodeResource(getResources(),R.mipmap.right_arrow);
        Bitmap checkperson = BitmapFactory.decodeResource(getResources(),R.mipmap.checkperson);
        ItemBean item0 = new ItemBean("项目名称","橘子洲大桥提质改造工程",proj_name,rightArrow);
        ItemBean item1 = new ItemBean("开工时间","2018.09",startingtime,rightArrow);
        ItemBean item2 = new ItemBean("被检查人","张三",checkedperson,rightArrow);
        ItemBean item3 = new ItemBean("检查地点","岳麓区",checkedaddr,rightArrow);

        itemList.add(item0);
        adapter.notifyItemChanged(0);
        itemList.add(item1);
        adapter.notifyItemChanged(1);
        itemList.add(item2);
        adapter.notifyItemChanged(2);
        itemList.add(item3);
        adapter.notifyItemChanged(3);

        adapter.notifyItemChanged(0,itemList.size());
    }

    @Override
    public void onItemClick(int position) {
    }

    @Override
    public void onDeleteClick(int position) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
