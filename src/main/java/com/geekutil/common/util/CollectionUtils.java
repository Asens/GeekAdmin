package com.geekutil.common.util;

import org.springframework.lang.Nullable;

import java.util.Collection;

/**
 * @author Asens
 */

public class CollectionUtils {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
