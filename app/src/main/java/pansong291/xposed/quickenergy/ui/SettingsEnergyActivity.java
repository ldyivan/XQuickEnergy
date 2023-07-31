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

public class SettingsEnergyActivity extends Activity
{
    CheckBox cb_collectWateringBubble, cb_collectEnergy, cb_helpFriendCollect, cb_receiveForestTaskAward,
            cb_cooperateWater, cb_energyRain, cb_limitCollect, cb_doubleCard,
            cb_ExchangeEnergyDoubleClick, cb_reserve, cb_ancientTree, cb_ancientTreeOnlyWeek;;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_energy);

        Config.shouldReload = true;
        FriendIdMap.shouldReload = true;
        CooperationIdMap.shouldReload = true;

        cb_collectEnergy = findViewById(R.id.cb_collectEnergy);
        cb_collectWateringBubble = findViewById(R.id.cb_collectWateringBubble);
        cb_helpFriendCollect = findViewById(R.id.cb_helpFriendCollect);
        cb_receiveForestTaskAward = findViewById(R.id.cb_receiveForestTaskAward);
        cb_cooperateWater = findViewById(R.id.cb_cooperateWater);
        cb_energyRain = findViewById(R.id.cb_energyRain);
        cb_limitCollect = findViewById(R.id.cb_limitCollect);
        cb_doubleCard = findViewById(R.id.cb_doubleCard);
        cb_ExchangeEnergyDoubleClick = findViewById(R.id.cb_ExchangeEnergyDoubleClick);
        cb_reserve = findViewById(R.id.cb_reserve);
        cb_ancientTree = findViewById(R.id.cb_ancientTree);
        cb_ancientTreeOnlyWeek = findViewById(R.id.cb_ancientTreeOnlyWeek);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        cb_collectEnergy.setChecked(Config.collectEnergy());
        cb_collectWateringBubble.setChecked(Config.collectWateringBubble());
        cb_helpFriendCollect.setChecked(Config.helpFriendCollect());
        cb_receiveForestTaskAward.setChecked(Config.receiveForestTaskAward());
        cb_cooperateWater.setChecked(Config.cooperateWater());
        cb_energyRain.setChecked(Config.energyRain());
        cb_limitCollect.setChecked(Config.isLimitCollect());
        cb_doubleCard.setChecked(Config.doubleCard());
        cb_ExchangeEnergyDoubleClick.setChecked(Config.ExchangeEnergyDoubleClick());
        cb_reserve.setChecked(Config.reserve());
        cb_ancientTree.setChecked(Config.ancientTree());
        cb_ancientTreeOnlyWeek.setChecked(Config.ancientTreeOnlyWeek());
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v)
    {
        if (v instanceof CheckBox) {
            CheckBox cb = (CheckBox)v;
            switch(v.getId())
            {
                case R.id.cb_collectEnergy:
                    Config.setCollectEnergy(cb.isChecked());
                    break;

                case R.id.cb_collectWateringBubble:
                    Config.setCollectWateringBubble(cb.isChecked());
                    break;

                case R.id.cb_limitCollect:
                    Config.setLimitCollect(cb.isChecked());
                    break;

                case R.id.cb_doubleCard:
                    Config.setDoubleCard(cb.isChecked());
                    break;

                case R.id.cb_helpFriendCollect:
                    Config.setHelpFriendCollect(cb.isChecked());
                    break;

                case R.id.cb_receiveForestTaskAward:
                    Config.setReceiveForestTaskAward(cb.isChecked());
                    break;

                case R.id.cb_cooperateWater:
                    Config.setCooperateWater(cb.isChecked());
                    break;

                case R.id.cb_energyRain:
                    Config.setEnergyRain(cb.isChecked());
                    break;

                case R.id.cb_ExchangeEnergyDoubleClick:
                    Config.setExchangeEnergyDoubleClick(cb.isChecked());
                    break;

                case R.id.cb_reserve:
                    Config.setReserve(cb.isChecked());
                    break;

                case R.id.cb_ancientTree:
                    Config.setAncientTree(cb.isChecked());
                    break;

                case R.id.cb_ancientTreeOnlyWeek:
                    Config.setAncientTreeOnlyWeek(cb.isChecked());
                    break;
            }
        } else if (v instanceof Button) {
            Button btn = (Button)v;
            switch(v.getId()) {
                case R.id.btn_checkInterval:
                    EditDialog.showEditDialog(this, btn.getText(), EditDialog.EditMode.CHECK_INTERVAL);
                    break;

                case R.id.btn_advanceTime:
                    EditDialog.showEditDialog(this, btn.getText(), EditDialog.EditMode.ADVANCE_TIME);
                    break;

                case R.id.btn_collectInterval:
                    EditDialog.showEditDialog(this, btn.getText(), EditDialog.EditMode.COLLECT_INTERVAL);
                    break;

                case R.id.btn_collectTimeout:
                    EditDialog.showEditDialog(this, btn.getText(), EditDialog.EditMode.COLLECT_TIMEOUT);
                    break;

                case R.id.btn_limitCount:
                    EditDialog.showEditDialog(this, btn.getText(), EditDialog.EditMode.LIMIT_COUNT);
                    break;

                case R.id.btn_doubleCardTime:
                    EditDialog.showEditDialog(this, btn.getText(), EditDialog.EditMode.DOUBLE_CARD_TIME);
                    break;

                case R.id.btn_returnWater30:
                    EditDialog.showEditDialog(this, btn.getText(), EditDialog.EditMode.RETURN_WATER_30);
                    break;

                case R.id.btn_returnWater20:
                    EditDialog.showEditDialog(this, btn.getText(), EditDialog.EditMode.RETURN_WATER_20);
                    break;

                case R.id.btn_returnWater10:
                    EditDialog.showEditDialog(this, btn.getText(), EditDialog.EditMode.RETURN_WATER_10);
                    break;

                case R.id.btn_dontCollectList:
                    ListDialog.show(this, btn.getText(), AlipayUser.getList(), Config.getDontCollectList(), null);
                    break;

                case R.id.btn_dontHelpCollectList:
                    ListDialog.show(this, btn.getText(), AlipayUser.getList(), Config.getDontHelpCollectList(), null);
                    break;

                case R.id.btn_waterFriendList:
                    ListDialog.show(this, btn.getText(), AlipayUser.getList(), Config.getWaterFriendList(), Config.getWaterCountList());
                    break;

                case R.id.btn_cooperateWaterList:
                    ListDialog.show(this, btn.getText(), CooperateUser.getList(), Config.getCooperateWaterList(), Config.getcooperateWaterNumList());
                    break;

                case R.id.btn_giveEnergyRainList:
                    ListDialog.show(this, btn.getText(), AlipayUser.getList(), Config.getGiveEnergyRainList(), null);
                    break;

                case R.id.btn_ExchangeEnergyDoubleClickCount:
                    EditDialog.showEditDialog(this, btn.getText(),
                            EditDialog.EditMode.EXCHANGE_ENERGY_DOUBLE_CLICK_COUNT);
                    break;

                case R.id.btn_reserveList:
                    ListDialog.show(this, btn.getText(), AlipayReserve.getList(), Config.getReserveList(),
                            Config.getReserveCountList());
                    break;

                case R.id.btn_ancientTreeCityCodeList:
                    ListDialog.show(this, btn.getText(), CityCode.getList(), Config.getAncientTreeCityCodeList(),
                            null);
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

