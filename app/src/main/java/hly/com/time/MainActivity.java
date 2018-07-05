package hly.com.time;

import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.ArrayMap;
import android.widget.TextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = findViewById(R.id.newtime);

        timerStart();
    }

    /**
     * 倒数计时器
     */
    private CountDownTimer timer = new CountDownTimer(2 * 60 * 60 * 1000, 1000) {
        /**
         * 固定间隔被调用,就是每隔countDownInterval会回调一次方法onTick
         * @param millisUntilFinished
         */
        @Override
        public void onTick(long millisUntilFinished) {
            time.setText(formatTime(millisUntilFinished));
        }

        /**
         * 倒计时完成时被调用
         */
        @Override
        public void onFinish() {
            time.setText("00:00");
        }
    };

    /**
     * 将毫秒转化为 分钟：秒 的格式
     *
     * @param millisecond 毫秒
     * @return
     */
    public String formatTime(long millisecond) {
        int hour;//hour
        int minute;//分钟
        int second;//秒数
        hour = (int) ((millisecond % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        minute = (int) ((millisecond % (1000 * 60 * 60)) / (1000 * 60));
        second = (int) ((millisecond % (1000 * 60)) / 1000);


//        if (minute < 10) {
//            if (second < 10) {
//                return "0" + minute + ":" + "0" + second;
//            } else {
//                return "0" + minute + ":" + second;
//            }
//        } else {
//            if (second < 10) {
//                return minute + ":" + "0" + second;
//            } else {
//                return minute + ":" + second;
//            }
//        }

        return hour + "小时" + minute + "分钟" + second;
    }

    /**
     * 取消倒计时
     */
    public void timerCancel() {
        timer.cancel();
    }

    /**
     * 开始倒计时
     */
    public void timerStart() {
        timer.start();
    }


}
