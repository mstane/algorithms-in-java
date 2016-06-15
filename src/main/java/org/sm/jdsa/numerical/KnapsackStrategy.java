package org.sm.jdsa.numerical;

public interface KnapsackStrategy {
	
	Item[] run(double capacity);
	
	class Item implements Comparable<Item> {
		double price;
		double value;
		double ratio;
		
		public Item(double price, double value) {
			this.price = price;
			this.value = value;
			this.ratio = value / price;
		}

		@Override
		public int compareTo(Item o) {
			if (o == null) return -1;
			if (ratio == o.ratio) return 0;
			else if (ratio < o.ratio) return 1;
			else return -1;
		}
	}


}
