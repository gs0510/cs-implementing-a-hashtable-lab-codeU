/**
 * 
 */
package com.flatironschool.javacs;

import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
/**
 * Implementation of a HashMap using a collection of MyLinearMap and
 * resizing when there are too many entries.
 * 
 * @author downey
 * @param <K>
 * @param <V>
 *
 */
public class MyHashMap<K, V> extends MyBetterMap<K, V> implements Map<K, V> {
	
	// average number of entries per map before we rehash
	protected static final double FACTOR = 1.0;

	@Override
	public V put(K key, V value) {
		V oldValue = super.put(key, value);
		
		//System.out.println("Put " + key + " in " + map + " size now " + map.size());
		
		// check if the number of elements per map exceeds the threshold
		if (size() > maps.size() * FACTOR) {
			rehash();
		}
		return oldValue;
	}

	/**
	 * Doubles the number of maps and rehashes the existing entries.
	 */
	/**
	 * 
	 */
	protected void rehash() {
        // TODO: fill this in.
        ArrayList<MyLinearMap<K, V>> maps1 = new ArrayList<MyLinearMap<K, V>>(2*maps.size());
        for(int i=0;i<2*maps.size();i++)
        {
        	maps1.add(new MyLinearMap<K, V>());
        }
        for(MyLinearMap<K,V> map : maps){
        	Set<K> traverse = map.keySet();
        	for(K entry:traverse) 
        	{
        		int new_index = entry==null ? 0 : Math.abs(entry.hashCode()) % (2*maps.size());
        		MyLinearMap<K,V> map_new =maps1.get(new_index);
        		map_new.put(entry,map.get(entry));
        	}
        }
        maps = maps1;
    //    throw new UnsupportedOperationException();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> map = new MyHashMap<String, Integer>();
		for (int i=0; i<10; i++) {
			map.put(new Integer(i).toString(), i);
		}
		Integer value = map.get("3");
		System.out.println(value);
	}
}
