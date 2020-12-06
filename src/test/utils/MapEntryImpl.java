package unittests.utils;

import java.util.Map;

public class MapEntryImpl<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    public MapEntryImpl(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V v) {
        V old = value;
        value = v;
        return old;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
