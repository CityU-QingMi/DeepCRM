	public static String createSubselectFetchQueryFragment(QueryParameters queryParameters) {
		//TODO: ugly here:
		final String queryString = queryParameters.getFilteredSQL();
		final int fromIndex = getFromIndex( queryString );
		final int orderByIndex = queryString.lastIndexOf( "order by" );
		final String subselectQueryFragment =  orderByIndex > 0
				? queryString.substring( fromIndex, orderByIndex )
				: queryString.substring( fromIndex );
		if ( LOG.isTraceEnabled() ) {
			LOG.tracef( "SubselectFetch query fragment: %s", subselectQueryFragment );
		}
		return subselectQueryFragment;
	}
