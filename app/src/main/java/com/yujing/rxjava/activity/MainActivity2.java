package com.yujing.rxjava.activity;


import android.annotation.SuppressLint;

import com.blankj.rxbus.RxBus;
import com.blankj.rxbus.RxBus.Callback;
import com.yujing.rxjava.R;
import com.yujing.rxjava.contract.RxBusMessage;
import com.yujing.rxjava.databinding.ActivityMain2Binding;
import com.yujing.utils.YShow;

import io.reactivex.android.schedulers.AndroidSchedulers;

@SuppressLint("CheckResult")
public class MainActivity2 extends BaseActivity<ActivityMain2Binding> {

    final String TAG = "MainActivity2";

    @Override
    protected Integer getContentLayoutId() {
        return R.layout.activity_main2;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        RxBus.getDefault().subscribeSticky(this, "newActivity", AndroidSchedulers.mainThread(), newActivity);
        binding.btn1.setOnClickListener(v -> bt1());
        binding.btn2.setOnClickListener(v -> bt2());
        binding.btn3.setOnClickListener(v -> bt3());
        binding.btn4.setOnClickListener(v -> bt4());
        binding.btn1.setText("1.封装RxBus");
        binding.btn2.setText("2.发送doublue");
        binding.btn3.setText("3.延迟关闭");
        binding.btn4.setText("4.");
    }

    private void bt1() {
        RxBus.getDefault().post(new RxBusMessage<>("1", "发送文字"));
    }

    private void bt2() {
        RxBus.getDefault().post(new RxBusMessage<>("2", 123.45));
    }

    private void bt3() {
        YShow.show(this, "延迟5秒关闭");
        delayedShowFinish(5);
    }

    private void bt4() {

    }

    Callback newActivity = new Callback<String>() {
        @Override
        public void onEvent(String s) {
            binding.textView.setText(binding.textView.getText() + s);
        }
    };


    @Override
    public void onFail(String errorMsg) {

    }

    @Override
    public void onSuccess(String type, Object object) {

    }

    @Override
    public void onEvent(RxBusMessage<?> rxBusMessage) {
        switch (rxBusMessage.getType()) {
            case "1":
                binding.textView.setText((String) rxBusMessage.getObject());
                break;
            case "2":
                binding.textView.setText(rxBusMessage.getObject().toString());
                break;
        }
    }
}