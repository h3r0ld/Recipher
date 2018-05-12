package hu.herold.mobsoft.recipher.interactor.favourites.event;

import hu.herold.mobsoft.recipher.interactor.base.EventBase;

/**
 * Created by herold on 2018. 05. 12..
 */

public class IsValidPasswordEvent extends EventBase {
    private boolean isValid;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
