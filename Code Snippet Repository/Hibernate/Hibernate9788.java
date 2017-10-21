	@Override
	public TabularData getQueryStats() {
		final List<CompositeData> result = new ArrayList<CompositeData>();
		final Statistics statistics = getStatistics();
		for ( String query : statistics.getQueries() ) {
			final QueryStats queryStats = new QueryStats( query, statistics.getQueryStatistics( query ) );
			result.add( queryStats.toCompositeData() );
		}
		final TabularData td = QueryStats.newTabularDataInstance();
		td.putAll( result.toArray( new CompositeData[result.size()] ) );
		return td;
	}
