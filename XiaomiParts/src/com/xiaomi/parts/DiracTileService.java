package com.xiaomi.parts;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Handler;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import com.xiaomi.parts.R;

public class DiracTileService extends TileService {

    public DiracUtils mDiracUtils;

    @Override
    public void onStartListening() {

        mDiracUtils = new DiracUtils(getApplicationContext());

        boolean enhancerEnabled = mDiracUtils.isDiracEnabled();

        Tile tile = getQsTile();
        if (enhancerEnabled) {
            tile.setState(Tile.STATE_ACTIVE);
        } else {
            tile.setState(Tile.STATE_INACTIVE);
        }

        tile.updateTile();

        super.onStartListening();
    }

    @Override
    public void onClick() {
        Tile tile = getQsTile();
        if (mDiracUtils.isDiracEnabled()) {
            mDiracUtils.setEnabled(false);
            tile.setState(Tile.STATE_INACTIVE);
        } else {
            mDiracUtils.setEnabled(true);
            tile.setState(Tile.STATE_ACTIVE);
        }
        tile.updateTile();
        super.onClick();
    }
}
