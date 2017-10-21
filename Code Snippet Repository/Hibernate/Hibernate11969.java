	protected <T> void addQueryResults(Map<Integer, T> result, Query query) {
		List<Object[]> rows = (List<Object[]>) query.list();
		if ( rows.size() == 0 ) {
			getLogger().warn( "No results returned for query!!" );
		}
		for ( Object[] row : rows ) {
			Integer id = (Integer) row[0];
			T val = (T) row[1];
			result.put( id, val );
		}
	}
