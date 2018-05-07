package hu.herold.mobsoft.recipher.interactor.base;

/**
 * Created by herold on 2018. 05. 08..
 */

public abstract class EventBase {

    protected int code;
    protected Throwable throwable;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
