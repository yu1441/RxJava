package com.yujing.rxjava.activity;


import android.annotation.SuppressLint;
import android.util.Log;

import com.blankj.rxbus.RxBus;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jakewharton.rxbinding3.view.RxView;
import com.yujing.rxjava.BuildConfig;
import com.yujing.rxjava.R;
import com.yujing.rxjava.contract.RxBusMessage;
import com.yujing.rxjava.databinding.ActivityMainBinding;
import com.yujing.rxjava.model.WangYiNews;
import com.yujing.rxjava.model.Weather;
import com.yujing.rxjava.network.Exception.YResponseTransformer;
import com.yujing.rxjava.network.NetWorkManager;
import com.yujing.rxjava.network.request.YRequest;
import com.yujing.rxjava.network.response.YResponse;
import com.yujing.rxjava.network.response.YResponseNews;
import com.yujing.rxjava.presenter.MainPresenter;
import com.yujing.utils.YShow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.observers.BlockingBaseObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("CheckResult")
public class MainActivity extends BaseActivity<ActivityMainBinding> {
    final String TAG = "MainActivity";
    ObservableEmitter<Integer> oe;
    MainPresenter mainPresenter;
    @Override
    protected Integer getContentLayoutId() {
        return R.layout.activity_main;
    }
    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        observable1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        addDisposable(RxView.clicks(binding.btn1)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(o -> bt1()));
        addDisposable(RxView.longClicks(binding.btn1)
                .subscribe(o -> bt2()));
        binding.btn3.setOnClickListener(v -> bt3());
        binding.btn4.setOnClickListener(v -> bt4());
        binding.btn5.setOnClickListener(v -> bt5());
        binding.btn6.setOnClickListener(v -> bt6());
        binding.btn7.setOnClickListener(v -> bt7());
        binding.btn8.setOnClickListener(v -> bt8());
        binding.btn9.setOnClickListener(v -> bt9());
        binding.btn10.setOnClickListener(v -> bt10());
        binding.btn11.setOnClickListener(v -> bt11());
        binding.btn12.setOnClickListener(v -> bt12());
        binding.btn13.setOnClickListener(v -> bt13());
        binding.btn14.setOnClickListener(v -> bt14());
        binding.btn15.setOnClickListener(v -> bt15());
        binding.btn16.setOnClickListener(v -> bt16());
        binding.btn17.setOnClickListener(v -> bt17());
        binding.btn18.setOnClickListener(v -> bt18());
        binding.btn19.setOnClickListener(v -> bt19());
        binding.btn20.setOnClickListener(v -> bt20());
        binding.btn21.setOnClickListener(v -> bt21());
        binding.btn22.setOnClickListener(v -> bt22());
        binding.btn23.setOnClickListener(v -> bt23());
        binding.btn24.setOnClickListener(v -> bt24());
        binding.btn25.setOnClickListener(v -> bt25());
        binding.btn26.setOnClickListener(v -> bt26());
        binding.btn27.setOnClickListener(v -> bt27());
        binding.btn28.setOnClickListener(v -> bt28());
        binding.btn29.setOnClickListener(v -> bt29());
        binding.btn30.setOnClickListener(v -> bt30());
        binding.btn31.setOnClickListener(v -> bt31());
        binding.btn32.setOnClickListener(v -> bt32());
        binding.btn33.setOnClickListener(v -> bt33());
        binding.btn34.setOnClickListener(v -> bt34());
        binding.btn35.setOnClickListener(v -> bt35());
        binding.btn36.setOnClickListener(v -> bt36());


        binding.btn1.setText("1.防抖");
        binding.btn2.setText("2.长按");
        binding.btn3.setText("3.创建new");
        binding.btn4.setText("4.创建create");
        binding.btn5.setText("5.创建just");
        binding.btn6.setText("6.多线程");
        binding.btn7.setText("7.提前注册");
        binding.btn8.setText("8.遍历集合fromIterable");
        binding.btn9.setText("9.延迟创建defer");
        binding.btn10.setText("10.定时timer");
        binding.btn11.setText("11.延迟递增interval");
        binding.btn12.setText("12.指定递增intervalRange");
        binding.btn13.setText("13.快速递增range");
        binding.btn14.setText("14.转换map");
        binding.btn15.setText("15.拆分flatMap");
        binding.btn16.setText("16.顺序拆分concatMap");
        binding.btn17.setText("17.缓存事件buffer");
        binding.btn18.setText("18.延迟delay");
        binding.btn19.setText("19.事件do");
        binding.btn20.setText("20.错误重发retry");
        binding.btn21.setText("21.重复发送repeat");
        binding.btn22.setText("22.条件重发repeatWhen");
        binding.btn23.setText("23.网络请求Retrofit");
        binding.btn24.setText("24.Retrofit+Rxjava");
        binding.btn25.setText("25.封装NetWork");
        binding.btn26.setText("26.封装异常");
        binding.btn27.setText("27.高度封装");
        binding.btn28.setText("28.网络RxBus回调");
        binding.btn29.setText("29.合并事件merge");
        binding.btn30.setText("30.合并网络请求zip");
        binding.btn31.setText("31.模拟缓存");
        binding.btn32.setText("32.RxBus广播");
        binding.btn33.setText("33.网络请求字符串");
        binding.btn34.setText("34.获取网易新闻");
        binding.btn35.setText("35.发送粘性消息");
        binding.btn36.setText("36.RxBus跳转");

        mainPresenter = new MainPresenter(this);
        //注册带 tag 为 " 类型事件
        RxBus.getDefault().subscribe(this, "32", AndroidSchedulers.mainThread(), new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                show(s);
                Log.e("eventTag", s);
            }
        });
        RxBus.getDefault().subscribe(this, AndroidSchedulers.mainThread(), callback);

        RxBus.getDefault().subscribeSticky(this, "newActivity", AndroidSchedulers.mainThread(), callback2);
    }

    Observable<Integer> observable1 = Observable.create(emitter -> {
        oe = emitter;
    });

    Observer<Integer> observer = new Observer<Integer>() {
        @Override
        public void onSubscribe(Disposable d) {
            addDisposable(d);
            Log.d(TAG, "开始采用subscribe连接");
        }

        @Override
        public void onNext(Integer value) {
            Log.d(TAG, "对Next事件" + value + "作出响应");
            if (value == 999) {
                YShow.show(MainActivity.this, "" + value);
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "对Error事件作出响应");
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "对Complete事件作出响应");
        }
    };

    @Override
    public void onEvent(RxBusMessage<?> rxBusMessage) {

    }



    RxBus.Callback callback2 = new RxBus.Callback<String>() {
        @Override
        public void onEvent(String str) {
            binding.textView.setText(str);
        }
    };

    RxBus.Callback callback = new RxBus.Callback<Weather>() {
        @Override
        public void onEvent(Weather weather) {
            Log.e("■RxBus", weather.toString());
        }
    };

    private void bt1() {
        show("clicks:防抖");
        Log.e("btn1", "clicks:防抖");
    }

    private void bt2() {
        show("longClicks:长点击了按钮");
        Log.e("btn1", "longClicks:长点击了按钮");
    }

    private void bt3() {
        Observable<Integer> observable = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
        });
        observable.subscribe(observer);
    }

    private void bt4() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onNext(999);
        }).subscribe(observer);
    }

    private void bt5() {
        // 1. 创建时传入整型1、2、3、4
        // 在创建后就会发送这些对象，相当于执行了onNext(1)、onNext(2)、onNext(3)、onNext(4)
        Observable.just(1, 2, 3, 4).subscribe(observer);
    }

    private void bt6() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            Thread.sleep(2000);
            emitter.onNext(999);
            emitter.onComplete();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //提前
    private void bt7() {
        new Thread(() -> {
            oe.onNext(999);
        }).start();
    }

    private void bt8() {
        /*
         * 集合遍历
         **/
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Observable.fromIterable(list)
                .subscribe(integer -> {
                    Log.d(TAG, "集合中的数据元素 = " + integer);
                });
    }

    /**
     * 延迟创建，调用subscribe后才调用defer创建Observable
     * 定时操作：在经过了x秒后，需要自动执行y操作
     * 周期性操作：每隔x秒后，需要自动执行y操作
     */
    Integer i = 10;

    private void bt9() {

        // 2. 通过defer 定义被观察者对象
        // 注：此时被观察者对象还没创建
        Observable<Integer> observable = Observable.defer(() -> Observable.just(i));
        i = 15;
        // 注：此时，才会调用defer（）创建被观察者对象（Observable）
        observable.subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "接收到的整数是" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        });
    }

    //定时
    private void bt10() {
        // 该例子 = 延迟2s后，发送一个long类型数值
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> {
                    Log.d(TAG, "接收到了事件" + value);
                    YShow.show(MainActivity.this, "" + value);
                });
    }

    /**
     * 从0开始、无限递增1的的整数序列
     */

    private void bt11() {
        // 该例子发送的事件序列特点：延迟2s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
        Observable.interval(2, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> {
                    Log.d(TAG, "接收到了事件" + value);
                    binding.textView.setText("接收到了事件" + value);
                });
    }

    private void bt12() {
        // 参数说明：
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 参数3 = 第1次事件延迟发送时间；
        // 参数4 = 间隔时间数字；
        // 参数5 = 时间单位
        Observable.intervalRange(100, 10, 2, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // 该例子发送的事件序列特点：
                // 1. 从3开始，一共发送10个事件；
                // 2. 第1次延迟2s发送，之后每隔2秒产生1个数字（从0开始递增1，无限个）
                .subscribe(new BlockingBaseObserver<Long>() {
                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                        binding.textView.setText("接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "错误", e);
                    }
                });
    }

    private void bt13() {
        // 参数说明：
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 注：若设置为负数，则会抛出异常
        Observable.range(3, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // 该例子发送的事件序列特点：从3开始发送，每次发送事件递增1，一共发送10个事件
                .subscribe(new BlockingBaseObserver<Integer>() {
                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                        binding.textView.setText("接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "错误", e);
                    }
                });

    }

    private void bt14() {
        // 采用RxJava基于事件流的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            // 1. 被观察者发送事件 = 参数为整型 = 1、2、3
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);

            }
            // 2. 使用Map变换操作符中的Function函数对被观察者发送的事件进行统一变换：整型变换成字符串类型
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "使用 Map变换操作符 将事件" + integer + "的参数从 整型" + integer + " 变换成 字符串类型" + integer;
            }
        }).subscribe(new Consumer<String>() {

            // 3. 观察者接收事件时，是接收到变换后的事件 = 字符串类型
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

    /**
     * 我是事件1 拆分后的子事件0
     * 我是事件1 拆分后的子事件1
     * 我是事件1 拆分后的子事件2
     * 我是事件2 拆分后的子事件0
     * 我是事件2 拆分后的子事件1
     * 我是事件2 拆分后的子事件2
     * 我是事件3 拆分后的子事件0
     * 我是事件3 拆分后的子事件1
     * 我是事件3 拆分后的子事件2
     */
    private void bt15() {
        // 采用RxJava基于事件流的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }

            // 采用flatMap（）变换操作符
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件" + integer + " 拆分后的子事件" + i);
                    // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                    // 最终合并，再发送给被观察者
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

    /**
     * 新合并生成的事件序列顺序是有序的，即 严格按照旧序列发送事件的顺序
     * 我是事件1 拆分后的子事件0
     * 我是事件1 拆分后的子事件1
     * 我是事件1 拆分后的子事件2
     * 我是事件2 拆分后的子事件0
     * 我是事件2 拆分后的子事件1
     * 我是事件2 拆分后的子事件2
     * 我是事件3 拆分后的子事件0
     * 我是事件3 拆分后的子事件1
     * 我是事件3 拆分后的子事件2
     */
    private void bt16() {
        // 采用RxJava基于事件流的链式操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }

            // 采用concatMap（）变换操作符
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件 " + integer + "拆分后的子事件" + i);
                    // 通过concatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                    // 最终合并，再发送给被观察者
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

    /**
     * 缓存被观察者发送的事件
     */
    private void bt17() {
        // 被观察者 需要发送5个数字
        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 1) // 设置缓存区大小 & 步长
                // 缓存区大小 = 每次从被观察者中获取的事件数量
                // 步长 = 每次获取新事件的数量
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> stringList) {
                        //
                        Log.d(TAG, " 缓存区里的事件数量 = " + stringList.size());
                        for (Integer value : stringList) {
                            Log.d(TAG, " 事件 = " + value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    /**
     * 延迟
     * // 1. 指定延迟时间
     * // 参数1 = 时间；参数2 = 时间单位
     * delay(long delay,TimeUnit unit)
     * <p>
     * // 2. 指定延迟时间 & 调度器
     * // 参数1 = 时间；参数2 = 时间单位；参数3 = 线程调度器
     * delay(long delay,TimeUnit unit,mScheduler scheduler)
     * <p>
     * // 3. 指定延迟时间  & 错误延迟
     * // 错误延迟，即：若存在Error事件，则如常执行，执行后再抛出错误异常
     * // 参数1 = 时间；参数2 = 时间单位；参数3 = 错误延迟参数
     * delay(long delay,TimeUnit unit,boolean delayError)
     * <p>
     * // 4. 指定延迟时间 & 调度器 & 错误延迟
     * // 参数1 = 时间；参数2 = 时间单位；参数3 = 线程调度器；参数4 = 错误延迟参数
     * delay(long delay,TimeUnit unit,mScheduler scheduler,boolean delayError): 指定延迟多长时间并添加调度器，错误通知可以设置是否延迟
     */
    private void bt18() {
        Observable.just(1, 2, 3)
                .delay(3, TimeUnit.SECONDS) // 延迟3s再发送，由于使用类似，所以此处不作全部展示
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer value) throws Exception {
                        Log.d(TAG, "接收到了事件" + value);
                    }
                });
    }

    /**
     * do使用
     * 某个事件的生命周期中调用
     */
    private void bt19() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new Throwable("发生错误了"));
            }
        })
                // 1. 当Observable每发送1次数据事件就会调用1次
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        Log.d(TAG, "doOnEach: " + integerNotification.getValue());
                    }
                })
                // 2. 执行Next事件前调用
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "doOnNext: " + integer);
                    }
                })
                // 3. 执行Next事件后调用
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "doAfterNext: " + integer);
                    }
                })
                // 4. Observable正常发送事件完毕后调用
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doOnComplete: ");
                    }
                })
                // 5. Observable发送错误事件时调用
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "doOnError: " + throwable.getMessage());
                    }
                })
                // 6. 观察者订阅时调用
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e(TAG, "doOnSubscribe: ");
                    }
                })
                // 7. Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doAfterTerminate: ");
                    }
                })
                // 8. 最后执行
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doFinally: ");
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    /**
     * 错误重新发送
     * <-- 1. retry（） -->
     * // 作用：出现错误时，让被观察者重新发送数据
     * // 注：若一直错误，则一直重新发送
     * <p>
     * <-- 2. retry（long time） -->
     * // 作用：出现错误时，让被观察者重新发送数据（具备重试次数限制
     * // 参数 = 重试次数
     * <p>
     * <-- 3. retry（Predicate predicate） -->
     * // 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送& 持续遇到错误，则持续重试）
     * // 参数 = 判断逻辑
     * <p>
     * <--  4. retry（new BiPredicate<Integer, Throwable>） -->
     * // 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试
     * // 参数 =  判断逻辑（传入当前重试次数 & 异常错误信息）
     * <p>
     * <-- 5. retry（long time,Predicate predicate） -->
     * // 作用：出现错误后，判断是否需要重新发送数据（具备重试次数限制
     * // 参数 = 设置重试次数 & 判断逻辑
     */
    private void bt20() {
        /*
         2019-10-24 10:53:28.370 20235-20235/com.yujing.rxjava D/MainActivity: 接收到了事件1
         2019-10-24 10:53:28.370 20235-20235/com.yujing.rxjava D/MainActivity: 接收到了事件2
         2019-10-24 10:53:28.370 20235-20235/com.yujing.rxjava D/MainActivity: 接收到了事件1
         2019-10-24 10:53:28.371 20235-20235/com.yujing.rxjava D/MainActivity: 接收到了事件2
         2019-10-24 10:53:28.371 20235-20235/com.yujing.rxjava D/MainActivity: 接收到了事件1
         2019-10-24 10:53:28.371 20235-20235/com.yujing.rxjava D/MainActivity: 接收到了事件2
         2019-10-24 10:53:28.371 20235-20235/com.yujing.rxjava D/MainActivity: 接收到了事件1
         2019-10-24 10:53:28.371 20235-20235/com.yujing.rxjava D/MainActivity: 接收到了事件2
         2019-10-24 10:53:28.371 20235-20235/com.yujing.rxjava D/MainActivity: 对Error事件作出响应
         */
        // 作用：出现错误时，让被观察者重新发送数据（具备重试次数限制
        // 参数 = 重试次数
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })
                .retry(3) // 设置重试次数 = 3次
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    /**
     * 无条件重复发送
     * // 不传入参数 = 重复发送次数 = 无限次
     * repeat（）；
     * // 传入参数 = 重复发送次数有限
     * repeatWhen（Integer int ）；
     */
    private void bt21() {
        // 1. 接收到.onCompleted()事件后，触发重新订阅 & 发送
        // 2. 默认运行在一个新的线程上
        // 具体使用
        Observable.just(1, 2, 3, 4)
                .repeat(3) // 重复创建次数 =- 3次
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });
    }

    /**
     * 作用
     * 有条件地、重复发送 被观察者事件
     * <p>
     * 原理
     * 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable），以此决定是否重新订阅 & 发送原来的 Observable
     * <p>
     * 若新被观察者（Observable）返回1个Complete / Error事件，则不重新订阅 & 发送原来的 Observable
     * 若新被观察者（Observable）返回其余事件时，则重新订阅 & 发送原来的 Observable
     */
    private void bt22() {
        Observable.just(1, 2, 3, 4, 5).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            // 在Function函数中，必须对输入的 Observable<Object>进行处理，这里我们使用的是flatMap操作符接收上游的数据
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
                // 以此决定是否重新订阅 & 发送原来的 Observable
                // 此处有2种情况：
                // 1. 若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                // 2. 若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object throwable) throws Exception {

                        // 情况1：若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                        return Observable.empty();
                        // Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）

                        //return Observable.error(new Throwable("不再重新订阅事件"));
                        // 返回Error事件 = 回调onError（）事件，并接收传过去的错误信息。

                        // 情况2：若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                        //return Observable.just(1);
                        // 仅仅是作为1个触发重新订阅被观察者的通知，发送的是什么数据并不重要，只要不是Complete（） /  Error（）事件
                    }
                });

            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应：" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        });
    }

    private void bt23() {
        //        YnetAndroid.create()
        //                .get("https://www.apiopen.top/weatherApi?city=成都")
        //                .setYnetListener(new Ynet.YnetListener() {
        //                    @Override
        //                    public void success(String value) {
        //                        Log.d(TAG, value);
        //                    }
        //
        //                    @Override
        //                    public void fail(String value) {
        //
        //                    }
        //                }).start();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        YRequest request = retrofit.create(YRequest.class);
        Call<YResponse<Weather>> call = request.getCall("成都");
        call.enqueue(new Callback<YResponse<Weather>>() {
            @Override
            public void onResponse(Call<YResponse<Weather>> call, Response<YResponse<Weather>> response) {
                Log.d(TAG, response.body().getData().toString());
            }

            @Override
            public void onFailure(Call<YResponse<Weather>> call, Throwable t) {
                Log.e(TAG, "错误", t);
            }
        });
    }

    private void bt24() {
        //https://www.apiopen.top/weatherApi?city=成都
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)// 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();
        YRequest request = retrofit.create(YRequest.class);
        Observable<YResponse<Weather>> observable = request.get2();
        observable.subscribeOn(Schedulers.io())               // 切换到IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  // 切换回到主线程 处理请求结果
                .subscribe(new Consumer<YResponse<Weather>>() {
                    @Override
                    public void accept(YResponse<Weather> weatherYResponse) throws Exception {
                        Log.d(TAG, weatherYResponse.getData().toString());
                    }
                });
    }

    /**
     * 封装NetWorkManager
     */
    private void bt25() {
        //https://www.apiopen.top/weatherApi?city=成都
        Observable<YResponse<Weather>> observable = NetWorkManager.getRequest().get2();
        observable.subscribeOn(Schedulers.io())               // 切换到IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  // 切换回到主线程 处理请求结果
                .subscribe(weatherYResponse -> {
                    show(weatherYResponse.getData().toString());
                    Log.d(TAG, weatherYResponse.getData().toString());
                });
    }

    /**
     * 封装异常
     */
    private void bt26() {
        //https://www.apiopen.top/weatherApi?city=成都
        Disposable disposable = NetWorkManager.getRequest().get2()
                .compose(YResponseTransformer.handleResult())//封装异常
                .subscribeOn(Schedulers.io())               // 切换到IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  // 切换回到主线程 处理请求结果
                .subscribe(obj -> {
                    show(obj.toString());
                    Log.d(TAG, obj.toString());
                }, throwable -> {
                    Log.e(TAG, "错误原因：" + throwable.getMessage(), throwable);
                    show(throwable.getMessage());
                });
        addDisposable(disposable);
    }

    /**
     * 高度封装
     */
    private void bt27() {
        mainPresenter.get("南部");
    }

    private void bt28() {
        mainPresenter.getCity("南充");
    }

    String result = "数据源来自 = ";

    private void bt29() {
        //设置第1个Observable：通过网络获取数据
        Observable<String> network = Observable.just("网络");
        //设置第2个Observable：通过本地文件获取数据，此处仅作本地文件请求的模拟
        Observable<String> file = Observable.just("本地文件");
        Observable.merge(network, file)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String value) {
                        Log.d(TAG, "数据源有： " + value);
                        result += value + "+";
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    // 接收合并事件后，统一展示
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "获取数据完成");
                        Log.d(TAG, result);
                    }
                });
    }

    /**
     * 对象合并
     */
    private void bt30() {
        Observable<YResponse<Weather>> disposable1 = NetWorkManager.getRequest().get("成都").subscribeOn(Schedulers.io());
        Observable<YResponse<Weather>> disposable2 = NetWorkManager.getRequest().get("广州").subscribeOn(Schedulers.io());
        //网络请求合并事件
        Observable.zip(disposable1, disposable2, new BiFunction<YResponse<Weather>, YResponse<Weather>, String>() {
            @Override
            public String apply(YResponse<Weather> weatherYResponse, YResponse<Weather> weatherYResponse2) throws Exception {
                Log.e(TAG, "收到两个对象");
                return weatherYResponse.getData().getCity() + ":" + weatherYResponse.getData().getGanmao() +
                        "\n" + weatherYResponse2.getData().getCity() + ":" + weatherYResponse2.getData().getGanmao();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> {
                    binding.textView.setText(value);
                    Log.d(TAG, "最终接收到的数据是：" + value);
                }, throwable -> System.out.println("失败"));
    }

    // 该2变量用于模拟内存缓存 & 磁盘缓存中的数据
    String memoryCache = null;
    String diskCache = "从磁盘缓存中获取数据";

    private void bt31() {
        //设置第1个Observable：检查内存缓存是否有该数据的缓存
        Observable<String> memory = Observable.create(emitter -> {
            // 先判断内存缓存有无数据,若有该数据，则发送，若无该数据，则直接发送结束事件
            if (memoryCache != null)
                emitter.onNext(memoryCache);
            else
                emitter.onComplete();
        });
        //设置第2个Observable：检查磁盘缓存是否有该数据的缓存
        Observable<String> disk = Observable.create(emitter -> {
            // 先判断磁盘缓存有无数据,若有该数据，则发送，若无该数据，则直接发送结束事件
            if (diskCache != null)
                emitter.onNext(diskCache);
            else
                emitter.onComplete();
        });

        //设置第3个Observable：通过网络获取数据
        Observable<String> network = Observable.just("从网络中获取数据");
        // 1. 通过concat（）合并memory、disk、network 3个被观察者的事件（即检查内存缓存、磁盘缓存 & 发送网络请求）并将它们按顺序串联成队列
        // 2. 通过firstElement()，从串联队列中取出并发送第1个有效事件（Next事件），即依次判断检查memory、disk、network
        // 即本例的逻辑为：
        // a. firstElement()取出第1个事件 = memory，即先判断内存缓存中有无数据缓存；由于memoryCache = null，即内存缓存中无数据，所以发送结束事件（视为无效事件）
        // b. firstElement()继续取出第2个事件 = disk，即判断磁盘缓存中有无数据缓存：由于diskCache ≠ null，即磁盘缓存中有数据，所以发送Next事件（有效事件）
        // c. 即firstElement()已发出第1个有效事件（disk事件），所以停止判断。
        Observable.concat(memory, disk, network)
                .firstElement()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "最终获取的数据来源 =  " + s);
                    }
                });
    }

    private void bt32() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RxBus.getDefault().post("32号按钮发来的事件，我是余静", "32");
            }
        }).start();


    }

    /**
     * 请求字符串
     */
    private void bt33() {
        YRequest request = NetWorkManager.getRequest();
        Observable<ResponseBody> observable = request.newsResponseBody();
        observable
                .subscribeOn(Schedulers.io())
                .compose(YResponseTransformer.handle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(obj -> {
                    show(obj.string());
                }, throwable -> {
                    onFail(throwable.getMessage());
                });
    }

    private void bt34() {
        YRequest request = NetWorkManager.getRequest();
        Observable<YResponseNews<WangYiNews>> observable = request.news();
        observable.subscribeOn(Schedulers.io())
                .compose(YResponseTransformer.handle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(obj -> {
                    binding.textView.setText(obj.getResult().get(0).toString());
                }, throwable -> {
                    onFail(throwable.getMessage());
                });
    }

    private void bt35() {
        RxBus.getDefault().postSticky("跳转信息", "newActivity");
    }


    private void bt36() {
        startActivity(MainActivity2.class);
    }


    @Override
    protected void onDestroy() {
        mainPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onFail(String errorMsg) {
        show(errorMsg);
    }

    @Override
    public void onSuccess(String type, Object object) {
        binding.textView.setText(object.toString());
    }
}

