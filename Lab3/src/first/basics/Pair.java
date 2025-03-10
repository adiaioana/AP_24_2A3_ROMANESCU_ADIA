package first.basics;

import java.time.LocalTime;
import java.util.Comparator;

public class Pair<K, V> {
    private K key;
    private V value;
    public Pair(K first, V second) {
        this.key = first;
        this.value = second;
    }
    public void setKey(K key) {
        this.key = key;
    }
    public void setValue(V value) {
        this.value = value;
    }
    public K getKey() {
        return key;
    }
    public V getValue() {
        return value;
    }
    public String toString(){
        return String.valueOf(key)+" "+String.valueOf(value);
    }

}