package com.xiaomi.parts;


import java.lang.IllegalArgumentException;
import android.content.Context;

public final class DiracUtils {

public DiracSound mDiracSound;
public static DiracUtils mInstance;
public Context mContext;

    public static DiracUtils getInstance() {
        if (mInstance == null) {
            throw new IllegalArgumentException("Trying to get instance without initializing!");
        }
        return mInstance;
    }


    public DiracUtils(final Context context) {
    mContext = context;
        mDiracSound = new DiracSound(0, 0);
    }


   public void onBootCompleted() {
        setEnabled(mDiracSound.getMusic() == 1);
        mDiracSound.setHeadsetType(mDiracSound.getHeadsetType());
        setLevel(getLevel());
    }

   public  void setEnabled(boolean enable) {
        mDiracSound.setEnabled(enable);
        mDiracSound.setMusic(enable ? 1 : 0);
    }

    public boolean isDiracEnabled() {
        return mDiracSound.getMusic() == 1;
    }

    private String getLevel() {
        StringBuilder selected = new StringBuilder();
        for (int band = 0; band <= 6; band++) {
            int temp = (int) mDiracSound.getLevel(band);
            selected.append(temp);
            if (band != 6) selected.append(",");
        }
        return selected.toString();
    }

    void setLevel(String preset) {
        String[] level = preset.split("\\s*,\\s*");
        for (int band = 0; band <= level.length - 1; band++) {
            mDiracSound.setLevel(band, Float.valueOf(level[band]));
        }
    }

    void setHeadsetType(int paramInt) {
        mDiracSound.setHeadsetType(paramInt);
    }
}
