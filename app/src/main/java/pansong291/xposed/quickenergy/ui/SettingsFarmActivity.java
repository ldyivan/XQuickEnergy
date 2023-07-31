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

public class SettingsFarmActivity extends Activity
{
    CheckBox cb_limitCollect, cb_rewardFriend, cb_sendBackAnimal,
            cb_receiveFarmToolReward,  cb_useNewEggTool,  cb_harvestProduce,
            cb_donation, cb_answerQuestion, cb_receiveFarmTaskAward,
            cb_feedAnimal, cb_useAccelerateTool, cb_notifyFriend;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_farm);

        Config.shouldReload = true;
        FriendIdMap.shouldReload = true;
        CooperationIdMap.shouldReload = true;

        cb_rewardFriend = findViewById(R.id.cb_rewardFriend);
        cb_sendBackAnimal = findViewById(R.id.cb_sendBackAnimal);
        cb_receiveFarmToolReward = findViewById(R.id.cb_receiveFarmToolReward);
        cb_useNewEggTool = findViewById(R.id.cb_useNewEggTool);
        cb_harvestProduce = findViewById(R.id.cb_harvestProduce);
        cb_donation = findViewById(R.id.cb_donation);
        cb_answerQuestion = findViewById(R.id.cb_answerQuestion);
        cb_receiveFarmTaskAward = findViewById(R.id.cb_receiveFarmTaskAward);
        cb_feedAnimal = findViewById(R.id.cb_feedAnimal);
        cb_useAccelerateTool = findViewById(R.id.cb_useAccelerateTool);
        cb_notifyFriend = findViewById(R.id.cb_notifyFriend);
        cb_limitCollect = findViewById(R.id.cb_limitCollect);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        cb_rewardFriend.setChecked(Config.rewardFriend());
        cb_sendBackAnimal.setChecked(Config.sendBackAnimal());
        cb_receiveFarmToolReward.setChecked(Config.receiveFarmToolReward());
        cb_useNewEggTool.setChecked(Config.useNewEggTool());
        cb_harvestProduce.setChecked(Config.harvestProduce());
        cb_donation.setChecked(Config.donation());
        cb_answerQuestion.setChecked(Config.answerQuestion());
        cb_receiveFarmTaskAward.setChecked(Config.receiveFarmTaskAward());
        cb_feedAnimal.setChecked(Config.feedAnimal());
        cb_useAccelerateTool.setChecked(Config.useAccelerateTool());
        cb_notifyFriend.setChecked(Config.notifyFriend());
        cb_limitCollect.setChecked(Config.isLimitCollect());
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v)
    {
        if (v instanceof CheckBox) {
            CheckBox cb = (CheckBox)v;
            switch(v.getId())
            {
                case R.id.cb_rewardFriend:
                    Config.setRewardFriend(cb.isChecked());
                    break;

                case R.id.cb_sendBackAnimal:
                    Config.setSendBackAnimal(cb.isChecked());
                    break;

                case R.id.cb_receiveFarmToolReward:
                    Config.setReceiveFarmToolReward(cb.isChecked());
                    break;

                case R.id.cb_useNewEggTool:
                    Config.setUseNewEggTool(cb.isChecked());
                    break;

                case R.id.cb_harvestProduce:
                    Config.setHarvestProduce(cb.isChecked());
                    break;

                case R.id.cb_donation:
                    Config.setDonation(cb.isChecked());
                    break;

                case R.id.cb_answerQuestion:
                    Config.setAnswerQuestion(cb.isChecked());
                    break;

                case R.id.cb_receiveFarmTaskAward:
                    Config.setReceiveFarmTaskAward(cb.isChecked());
                    break;

                case R.id.cb_feedAnimal:
                    Config.setFeedAnimal(cb.isChecked());
                    break;

                case R.id.cb_useAccelerateTool:
                    Config.setUseAccelerateTool(cb.isChecked());
                    break;

                case R.id.cb_notifyFriend:
                    Config.setNotifyFriend(cb.isChecked());
                    break;

                case R.id.cb_donateCharityCoin:
                    Config.setDonateCharityCoin(cb.isChecked());
                    break;
            }
        } else if (v instanceof Button) {
            Button btn = (Button)v;
            switch(v.getId()) {
                case R.id.btn_sendType:
                    ChoiceDialog.showSendType(this, btn.getText());
                    break;

                case R.id.btn_dontSendFriendList:
                    ListDialog.show(this, btn.getText(), AlipayUser.getList(), Config.getDontSendFriendList(), null);
                    break;

                case R.id.btn_recallAnimalType:
                    ChoiceDialog.showRecallAnimalType(this, btn.getText());
                    break;

                case R.id.btn_feedFriendAnimalList:
                    ListDialog.show(this, btn.getText(), AlipayUser.getList(), Config.getFeedFriendAnimalList(), Config.getFeedFriendCountList());
                    break;

                case R.id.btn_dontNotifyFriendList:
                    ListDialog.show(this, btn.getText(), AlipayUser.getList(), Config.getDontNotifyFriendList(), null);
                    break;

                case R.id.btn_animalSleepTime:
                    EditDialog.showEditDialog(this, btn.getText(), EditDialog.EditMode.ANIMAL_SLEEP_TIME);
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

