	@Override
	public TabularData getCollectionStats() {
		final List<CompositeData> result = new ArrayList<CompositeData>();
		final Statistics statistics = getStatistics();
		for ( String roleName : statistics.getCollectionRoleNames() ) {
			final CollectionStats collectionStats = new CollectionStats(
					roleName,
					statistics.getCollectionStatistics( roleName )
			);
			result.add( collectionStats.toCompositeData() );
		}
		final TabularData td = CollectionStats.newTabularDataInstance();
		td.putAll( result.toArray( new CompositeData[result.size()] ) );
		return td;
	}
