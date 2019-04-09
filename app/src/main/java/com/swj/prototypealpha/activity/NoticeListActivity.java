package com.swj.prototypealpha.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.swj.prototypealpha.Enity.NoticeEntity;
import com.swj.prototypealpha.R;
import com.swj.prototypealpha.swj.util.RecyclerViewHelper.NoticeAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoticeListActivity extends AppCompatActivity {

    private static final String TAG = NoticeListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);
        RecyclerView mRecyclerView = findViewById(R.id.notice_list);
        List<NoticeEntity> mData = generateData(2);
        NoticeAdapter mAdapter = new NoticeAdapter(mData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);





    }
    public List<NoticeEntity> generateData(int size)
    {
        ArrayList<NoticeEntity> noticeEntities = new ArrayList<NoticeEntity>();
        noticeEntities.add(new NoticeEntity("关于公布全市房产新政咨询电话的公告","2017-10-16 00:00","市住建委","市住建委","  为方便广大市民咨询有关房地产市场管理政策，现将全市房产新政咨询电话公告如下：\n" +
                "\n" +
                "一、市房产交易管理中心信息科咨询电话：84662152。地址：芙蓉区马王堆中路248号。\n" +
                "\n" +
                "二、驻房产交易大楼政务窗口房产新政咨询电话：84662717。地址：芙蓉区马王堆中路248号。\n" +
                "\n" +
                "三、驻市不动产登记中心大楼政务窗口新政咨询电话：84529160。地址：芙蓉区晚报大道150号。\n" +
                "\n" +
                "四、驻河西市政务中心窗口房产新政咨询电话：88665171。地址：岳麓区岳麓大道218号市政府二办政务大厅\n"));
        noticeEntities.add(new NoticeEntity("住房城乡建设领域涉黑涉恶线索举报平台","时间：2019-03-25 16:32","发布人：市住建委 ","稿件来源：市住建局信访处","一、举报须知。\n" +
                "\n" +
                "    为充分保护举报人的合法权益，并最大限度地发挥网站举报栏目的效能和作用，请您在提交举报信息前仔细阅读以下须知：\n" +
                "\n" +
                "    （一）温馨提示：\n" +
                "    1.本网站只接受住房城乡建设领域涉黑涉恶线索举报。\n" +
                "    2.为方便及时开展核查工作并及时反馈结果，请如实填写内容并留下必要的联系方式，网站将严格保密举报相关信息。对非实名或无实质性内容的信息，网站工作人员有权将其作为无效信息处理。\n" +
                "    3.为提高信息处理质量和效率，请不要重复提交相同内容的信息。\n" +
                "    4.如需提供照片等附件资料，请作为附件上传。\n" +
                "\n" +
                "    （二）举报主要内容：\n" +
                "    1.黑恶势力恶意竞标、强揽工程，在项目建设过程中煽动闹事，在施工渣土运输中阻拦施工、强揽生意等问题；\n" +
                "    2.房屋建筑建设过程中的涉黑涉恶问题；房地产中介公司采取威胁恐吓等暴力手段驱逐租户、强制上涨或恶意克扣租金押金，物业公司勾结社会闲杂人员滋扰业主，以暴力手段阻碍物业项目正常交接等问题；\n" +
                "    3.骗取农村危房改造补助资格和补助资金，诱骗、逼迫农户上缴回扣；\n" +
                "    4.实施违法建设，以暴力手段阻碍行政执法，为违法建设等充当“保护伞”等群众反映强烈、深恶痛绝的涉黑涉恶问题。\n" +
                "\n" +
                "    二、举报方式\n" +
                "\n" +
                "    举报邮箱：cxszjwxfc@163.com\n" +
                "\n" +
                "    举报电话：0731—88665923\n" +
                "\n" +
                "    工作时间：周一至周五 上午9：00―12：00     下午1：00―5：00\n" +
                "\n" +
                "    通讯地址：长沙市岳麓大道218号市政府二办1040办公室\n" +
                "\n" +
                "    邮政编码：410013\n"));
        return noticeEntities;

    }
}
