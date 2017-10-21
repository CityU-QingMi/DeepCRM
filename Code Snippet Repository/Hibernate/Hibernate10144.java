	public List<R> sort(Collection<Vertex<R>> vertices) {
		sorted = new ArrayList<>( vertices.size() );

		time = 1;

		for ( Vertex<R> v : vertices ) {
			if ( v.getEndTime() == 0 ) {
				process( v );
			}
		}

		Collections.reverse( sorted );

		return sorted;
	}
