package com.swj.prototypealpha.swj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.swj.prototypealpha.R;
import com.swj.prototypealpha.swj.util.ItemBean;
import com.swj.prototypealpha.swj.util.OnItemClickListener;
import com.swj.prototypealpha.swj.util.RecyclerViewHelper.ItemAdapter;
import com.swj.prototypealpha.swj.util.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddLocActivity extends AppCompatActivity implements OnItemClickListener
{

    private Toolbar tlb_checkloc;

    private RecyclerView recvv_checkloc;

    private ItemAdapter adapter;

    private List<ItemBean> itemList = new ArrayList<>();

    private List<String> locList = new ArrayList<>();

    private FloatingActionButton btn_addloc;

    private Spinner spi_add_loc;



    private void initUI()
    {
        recvv_checkloc = findViewById(R.id.recv_addloc);
        tlb_checkloc = findViewById(R.id.tlb_checkloc);
        btn_addloc = findViewById(R.id.lfbtn_add_loc);
        spi_add_loc = findViewById(R.id.spi_add_loc);
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

        locList.add("请选择检查地点");
        locList.add("地点一");
        locList.add("地点二");
        locList.add("地点三");
        SpinnerAdapter spi_adapter = new SpinnerAdapter(this,locList);
        spi_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi_add_loc.setAdapter(spi_adapter);

        spi_add_loc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bitmap leftImage = BitmapFactory.decodeResource(getResources(),R.mipmap.checkperson);
                Bitmap rightArrow = BitmapFactory.decodeResource(getResources(),R.mipmap.add);
                String name = "";
                if(position == 1)   name = "地点一";
                else if(position == 2) name = "地点二";
                else if(position == 3) name = "地点三";

                if(!name.equals("请选择检查地点"))
                {
                    itemList.remove(3);

                    ItemBean item0 = new ItemBean("检查地点",name,leftImage,rightArrow);
                    itemList.add(item0);
                    adapter.notifyItemChanged(3);
                    adapter.notifyItemChanged(0,itemList.size());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void Update()
    {
        itemList.clear();
        Bitmap leftImage = BitmapFactory.decodeResource(getResources(),R.mipmap.detail);
        Bitmap rightArrow = BitmapFactory.decodeResource(getResources(),R.mipmap.right_arrow);
        Bitmap checkperson = BitmapFactory.decodeResource(getResources(),R.mipmap.checkperson);
        ItemBean item0 = new ItemBean("项目名称","橘子洲大桥提质改造工程",leftImage,rightArrow);
        ItemBean item1 = new ItemBean("开工时间","2018.09",leftImage,rightArrow);
        ItemBean item2 = new ItemBean("被检查人","张三",leftImage,checkperson);
        ItemBean item3 = new ItemBean("检查地点","岳麓区",leftImage,rightArrow);

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
