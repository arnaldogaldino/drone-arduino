package com.ardudrone.arnaldo.ctrldrone;

import android.app.Application;
import com.ardudrone.arnaldo.ctrldrone.database.dao.DataManager;

public class DroneApp extends Application {
    private DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        setDataManager(new DataManager(this));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

}
