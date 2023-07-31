package pansong291.xposed.quickenergy.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import pansong291.xposed.quickenergy.R;
import java.util.ArrayList;

public class SettingsListActivity extends Activity {

    private ListView listView;
    private ArrayList<String> dataList;
    private int[] iconList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.include_settings_list);

        // 初始化ListView和数据源
        listView = findViewById(R.id.setting_listView);
        dataList = new ArrayList<>();
        iconList = new int[] {R.drawable.icon_setting,
                R.drawable.icon_energy,
                R.drawable.icon_form,
                R.drawable.icon_more,
                R.drawable.icon_other,

                R.drawable.icon_help,
                R.drawable.icon_like,
                R.drawable.icon_z1,
                R.drawable.icon_z2,
                R.drawable.icon_qqchanel,
                R.drawable.icon_github};

        // 添加数据到数据源
        dataList.add(getResources().getString(R.string.base_configuration));
        dataList.add(getResources().getString(R.string.forest_configuration));
        dataList.add(getResources().getString(R.string.farm_configuration));
        dataList.add(getResources().getString(R.string.other_configuration));
        dataList.add(getResources().getString(R.string.more));

        dataList.add(getResources().getString(R.string.help_wiki)); //文档
        dataList.add(getResources().getString(R.string.support_developer)); //支持当前开发者
        dataList.add(getResources().getString(R.string.support_xqe_developer)); //支持修复开发者
        dataList.add(getResources().getString(R.string.support_first_developer)); //支持XQE原作者
        dataList.add(getResources().getString(R.string.visit_qqGround)); //加入QQ频道
        dataList.add(getResources().getString(R.string.visit_github));

        // 创建适配器并设置给ListView
        CustomListAdapter adapter = new CustomListAdapter(this, dataList, iconList);
        listView.setAdapter(adapter);

        // 设置ListView的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                //String itemName = dataList.get(position);
                //Toast.makeText(SettingsListActivity.this, itemName, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        startActivity(new Intent(SettingsListActivity.this, SettingsNormalActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(SettingsListActivity.this, SettingsEnergyActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(SettingsListActivity.this, SettingsFarmActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(SettingsListActivity.this, SettingsOtherActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(SettingsListActivity.this, SettingsMoreActivity.class));
                        break;

                    case 5:
                        Intent help = new Intent(SettingsListActivity.this, HtmlViewerActivity.class);
                        help.setData(Uri.parse("https://github.com/pansong291/XQuickEnergy/wiki"));
                        startActivity(help);
                        break;
                    case 6:
                        Intent dev = new Intent(SettingsListActivity.this, HtmlViewerActivity.class);
                        dev.setData(Uri.parse("https://dz.hzv5.cn/z"));
                        startActivity(dev);
                        break;
                    case 7:
                        new AlertDialog.Builder(SettingsListActivity.this)
                                .setView(R.layout.donation_view)
                                .setPositiveButton("关闭", null)
                                .create().show();
                        break;
                    case 8:
                        Intent it2 = new Intent(Intent.ACTION_VIEW, Uri.parse("alipays://platformapi/startapp?saId=10000007&qrcode=https%3A%2F%2Fqr.alipay.com%2Ftsx00339eflkuhhtfctcn48"));
                        startActivity(it2);
                        break;

                    case 9:
                        Intent qqpd = new Intent(SettingsListActivity.this, HtmlViewerActivity.class);
                        qqpd.setData(Uri.parse("https://pd.qq.com/s/de0m20a0q"));
                        startActivity(qqpd);
                        break;

                    case 10:
                        Intent it = new Intent(SettingsListActivity.this, HtmlViewerActivity.class);
                        it.setData(Uri.parse("https://github.com/constanline/XQuickEnergy"));
                        startActivity(it);
                        break;
                }
            }
        });
    }



}
