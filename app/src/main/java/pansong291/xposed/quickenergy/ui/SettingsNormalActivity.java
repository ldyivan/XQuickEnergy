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

public class SettingsNormalActivity extends Activity
{
    CheckBox cb_immediateEffect, cb_recordLog, cb_showToast,
            cb_stayAwake, cb_timeoutRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_normal);

        Config.shouldReload = true;
        FriendIdMap.shouldReload = true;
        CooperationIdMap.shouldReload = true;

        cb_immediateEffect = findViewById(R.id.cb_immediateEffect);
        cb_recordLog = findViewById(R.id.cb_recordLog);
        cb_showToast = findViewById(R.id.cb_showToast);
        cb_stayAwake = findViewById(R.id.cb_stayAwake);
        cb_timeoutRestart = findViewById(R.id.cb_timeoutRestart);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        cb_immediateEffect.setChecked(Config.immediateEffect());
        cb_recordLog.setChecked(Config.recordLog());
        cb_showToast.setChecked(Config.showToast());
        cb_stayAwake.setChecked(Config.stayAwake());
        cb_timeoutRestart.setChecked(Config.timeoutRestart());
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v)
    {
        if (v instanceof CheckBox) {
            CheckBox cb = (CheckBox)v;
            switch(v.getId())
            {
                case R.id.cb_immediateEffect:
                    Config.setImmediateEffect(cb.isChecked());
                    break;

                case R.id.cb_recordLog:
                    Config.setRecordLog(cb.isChecked());
                    break;

                case R.id.cb_showToast:
                    Config.setShowToast(cb.isChecked());
                    break;

                case R.id.cb_stayAwake:
                    Config.setStayAwake(cb.isChecked());
                    break;

                case R.id.cb_timeoutRestart:
                    Config.setTimeoutRestart(cb.isChecked());
                    break;
            }
        } else if (v instanceof Button) {
            Button btn = (Button)v;
            switch(v.getId()) {
                case R.id.btn_stayAwakeType:
                    ChoiceDialog.showStayAwakeType(this, btn.getText());
                    break;

                case R.id.btn_stayAwakeTarget:
                    ChoiceDialog.showStayAwakeTarget(this, btn.getText());
                    break;

                case R.id.btn_waitWhenException:
                    EditDialog.showEditDialog(this, btn.getText(), EditDialog.EditMode.WAIT_WHEN_EXCEPTION);
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

