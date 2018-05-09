package hu.herold.mobsoft.recipher.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by herold on 2018. 05. 09..
 */

public class UIExecutor implements Executor {

    private Handler mHandler;

    public UIExecutor() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mHandler.post(command);
    }
}
