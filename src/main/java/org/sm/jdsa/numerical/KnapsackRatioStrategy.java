package org.sm.jdsa.numerical;

import java.util.Arrays;

public class KnapsackRatioStrategy implements KnapsackStrategy {
	
	private final Item[] items;
	
	public KnapsackRatioStrategy(Item[] items) {
		this.items = items;
	}
	
	@Override
	public Item[] run(double capacity) {
		Arrays.sort(items);
		
		double currentCapacity = 0;
		
		int i = 0;
		while (i < items.length && currentCapacity + items[i].price <= capacity) {
			currentCapacity += items[i++].price;
		}
		
		return Arrays.copyOf(items, i - 1);
	}
	

}
