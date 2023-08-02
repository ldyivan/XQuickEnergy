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

public class SettingsOtherActivity extends Activity
{
    CheckBox cb_receivePoint, cb_openTreasureBox, cb_donateCharityCoin,
            cb_kbSignIn, cb_ecoLifeTick;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_other);

        Config.shouldReload = true;
        FriendIdMap.shouldReload = true;
        CooperationIdMap.shouldReload = true;

        cb_receivePoint = findViewById(R.id.cb_receivePoint);
        cb_openTreasureBox = findViewById(R.id.cb_openTreasureBox);
        cb_donateCharityCoin = findViewById(R.id.cb_donateCharityCoin);
        cb_kbSignIn = findViewById(R.id.cb_kbSignIn);
        cb_ecoLifeTick = findViewById(R.id.cb_ecoLifeTick);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        cb_receivePoint.setChecked(Config.receivePoint());
        cb_openTreasureBox.setChecked(Config.openTreasureBox());
        cb_donateCharityCoin.setChecked(Config.donateCharityCoin());
        cb_kbSignIn.setChecked(Config.kbSginIn());
        cb_ecoLifeTick.setChecked(Config.ecoLifeTick());
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v)
    {
        if (v instanceof CheckBox) {
            CheckBox cb = (CheckBox)v;
            switch(v.getId())
            {
                case R.id.cb_receivePoint:
                    Config.setReceivePoint(cb.isChecked());
                    break;

                case R.id.cb_openTreasureBox:
                    Config.setOpenTreasureBox(cb.isChecked());
                    break;

                case R.id.cb_donateCharityCoin:
                    Config.setDonateCharityCoin(cb.isChecked());
                    break;

                case R.id.cb_kbSignIn:
                    Config.setKbSginIn(cb.isChecked());
                    break;

                case R.id.cb_ecoLifeTick:
                    Config.setEcoLifeTick(cb.isChecked());
                    break;
            }
        } else if (v instanceof Button) {
            Button btn = (Button)v;
            switch(v.getId()) {
                case R.id.btn_minExchangeCount:
                    EditDialog.showEditDialog(this, btn.getText(), EditDialog.EditMode.MIN_EXCHANGE_COUNT);
                    break;

                case R.id.btn_latestExchangeTime:
                    EditDialog.showEditDialog(this, btn.getText(), EditDialog.EditMode.LATEST_EXCHANGE_TIME);
                    break;

                case R.id.btn_syncStepCount:
                    EditDialog.showEditDialog(this, btn.getText(), EditDialog.EditMode.SYNC_STEP_COUNT);
                    break;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(Config.hasChanged) {
            Config.hasChanged = !Config.saveConfigFile();
            Toast.makeText(this, "保存成功!", Toast.LENGTH_SHORT).show();
        }
        FriendIdMap.saveIdMap();
        CooperationIdMap.saveIdMap();
    }

}

