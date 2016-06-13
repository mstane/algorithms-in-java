package org.sm.jdsa.hash;

import java.util.Arrays;

public class ClosedHashTable<K, V> implements HashTable<K, V> {
  
  private static int DEFAULT_INITIAL_CAPACITY = 11;
  
  private Entry<K, V>[] entries;
  private int size;
  
  public ClosedHashTable() {
    this(DEFAULT_INITIAL_CAPACITY);
  }
  
  @SuppressWarnings("unchecked")
  public ClosedHashTable(int initialCapacity) {
    size = 0;
    entries = new Entry[initialCapacity];
  }

  @Override
  public V put(K key, V value) {
    int idx = locate(key);
    entries[idx] = new EntryImpl(key, value);
    if (++size > entries.length / 2) rehash();
    return value;
  }

  @Override
  public V get(K key) {
    int idx = locate(key);
    Entry<K, V> entry = entries[idx];
    return entry == null ? null : entry.getValue();
  }

  @Override
  public V remove(K key) {
    int idx = locate(key);
    Entry<K, V> entry = entries[idx];
    V valueToRemove = entry.getValue();
    entries[idx] = null;
    return valueToRemove;
  }
  
  private int locate(K key) {
    int idx = hash(key);
    while (entries[idx] != null && !entries[idx].getKey().equals(key)) {
      idx++;
    };
    return idx;
  }
  
  private int hash(K key) {
    int hashValue = key.hashCode();
    
    hashValue %= entries.length;
    if (hashValue < 0) hashValue += entries.length;
    
    return hashValue;
  }
  
  @SuppressWarnings("unchecked")
  private void rehash() {
    Entry<K, V>[] oldEntries = entries;
    entries = new Entry[oldEntries.length * 2];
    Arrays.stream(oldEntries)
        .filter(entry -> entry != null)
        .forEach(entry -> entries[locate(entry.getKey())] = entry);
  }
  
  private class EntryImpl implements Entry<K, V> {
    
    K key;
    V value;
    
    public EntryImpl(K key, V value) {
      super();
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
    public V setValue(V value) {
      return this.value = value;
    }
    
  }

}
