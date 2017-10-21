	private void process(Vertex<R> v) {
		if ( v.getStartTime() != 0 ) {
			// alread processed
			return;
		}

		v.setStartTime( time++ );

		for ( Vertex<R> n : v.getNeighbours() ) {
			process( n );
		}

		v.setEndTime( time++ );

		sorted.add( v.getRepresentation() );
	}
