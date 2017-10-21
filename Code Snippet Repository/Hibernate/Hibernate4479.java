	@Override
	@SuppressWarnings("")
	public QueryImplementor setMaxResults(int maxResult) {
		if ( maxResult < 0 ) {
			// treat zero and negatives specially as meaning no limit...
			queryOptions.setMaxRows( null );
		}
		else {
			queryOptions.setMaxRows( maxResult );
		}
		return this;
	}
