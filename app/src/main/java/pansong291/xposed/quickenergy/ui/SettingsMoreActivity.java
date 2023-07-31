package pansong291.xposed.quickenergy.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import pansong291.xposed.quickenergy.R;
import pansong291.xposed.quickenergy.util.Config;
import pansong291.xposed.quickenergy.util.CooperationIdMap;
import pansong291.xposed.quickenergy.util.FriendIdMap;

public class SettingsMoreActivity extends Activity
{
    //CheckBox cb_doubleCard;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_more);

        Config.shouldReload = true;
        FriendIdMap.shouldReload = true;
        CooperationIdMap.shouldReload = true;

    }

    @Override
    protected void onResume()
    {
        super.onResume();

    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v)
    {
        if (v instanceof CheckBox) {
            CheckBox cb = (CheckBox)v;
            switch(v.getId())
            {

            }
        } else if (v instanceof Button) {
            Button btn = (Button)v;
            switch(v.getId()) {
                case R.id.btn_donation_developer:
                    new AlertDialog.Builder(this)
                            .setView(R.layout.donation_view)
                            .setPositiveButton("关闭", null)
                            .create().show();
                    break;

                case R.id.btn_donation_xqe_developer:
                    Intent it2 = new Intent(Intent.ACTION_VIEW, Uri.parse("alipays://platformapi/startapp?saId=10000007&qrcode=https%3A%2F%2Fqr.alipay.com%2Ftsx00339eflkuhhtfctcn48"));
                    startActivity(it2);
                    break;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(Config.hasChanged) {
            Config.hasChanged = !Config.saveConfigFile();
            Toast.makeText(this, "Configuration saved", Toast.LENGTH_SHORT).show();
        }
        FriendIdMap.saveIdMap();
        CooperationIdMap.saveIdMap();
    }

}

