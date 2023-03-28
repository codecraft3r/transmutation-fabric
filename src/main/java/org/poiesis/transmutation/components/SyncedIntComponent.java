package org.poiesis.transmutation.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;

public interface SyncedIntComponent extends PlayerComponent, AutoSyncedComponent {
    int getValue();
    void setValue(int value);
}
