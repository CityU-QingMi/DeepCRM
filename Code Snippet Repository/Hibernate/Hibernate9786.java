	@Override
	public TabularData getEntityStats() {
		final List<CompositeData> result = new ArrayList<CompositeData>();
		final Statistics statistics = getStatistics();
		for ( String entity : statistics.getEntityNames() ) {
			final EntityStats entityStats = new EntityStats( entity, statistics.getEntityStatistics( entity ) );
			result.add( entityStats.toCompositeData() );
		}
		final TabularData td = EntityStats.newTabularDataInstance();
		td.putAll( result.toArray( new CompositeData[result.size()] ) );
		return td;
	}
