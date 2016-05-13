package com.salyo.data;

import java.util.UUID;

/**
 * Created by daniel.blum on 13.05.2016.
 */
public interface ForeignSystemIdItem {
    String getForeignSystemId();

    void setForeignSystemId(String foreignSystemId);

    UUID getId();

    void setId(UUID id);
}
