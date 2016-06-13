package org.sm.jdsa.hash;

public interface HashTable<K, V> {

  V put(K key, V value);
  
  V get(K key);

  V remove(K key);
  
  interface Entry<K, V> {
    
    K getKey();
    
    V getValue();
    
    V setValue(V value);
    
  }

}
