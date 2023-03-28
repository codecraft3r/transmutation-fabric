package org.poiesis.transmutation.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import java.util.ArrayList;

public interface SyncedStringArrayListComponent extends PlayerComponent, AutoSyncedComponent {
    ArrayList<String> getList();
    void addToList(String value);
    void removeFromList(String value);

}
