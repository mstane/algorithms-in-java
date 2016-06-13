package org.sm.jdsa.graph.algorithm.mst;

public interface MstStrategy {
	
	Edge[] run(int i);
	
	 class Edge implements Comparable<Edge> {
	    int v1;
	    int v2;
	    int w;
	    
	    public Edge(int v1, int v2, int w) {
	      super();
	      this.v1 = v1;
	      this.v2 = v2;
	      this.w = w;
	    }

	    @Override
	    public int compareTo(Edge e) {
	      if (e != null) {
	        return this.w - e.w;
	      }
	      return 1;
	    }

	    @Override
	    public String toString() {
	      return "Edge [v1=" + v1 + ", v2=" + v2 + ", w=" + w + "]";
	    }

      @Override
      public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + v1;
        result = prime * result + v2;
        result = prime * result + w;
        return result;
      }

      @Override
      public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (getClass() != obj.getClass())
          return false;
        Edge other = (Edge) obj;
        if (v1 != other.v1)
          return false;
        if (v2 != other.v2)
          return false;
        if (w != other.w)
          return false;
        return true;
      }

	    
	  }

}
