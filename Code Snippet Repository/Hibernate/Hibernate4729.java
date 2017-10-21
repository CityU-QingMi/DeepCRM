	public ConcurrentSecondLevelCacheStatisticsImpl getSecondLevelCacheStatistics(String regionName) {
		ConcurrentSecondLevelCacheStatisticsImpl stat = secondLevelCacheStatistics.get( regionName );
		if ( stat == null ) {
			if ( sessionFactory == null ) {
				return null;
			}

			final EntityRegionAccessStrategy entityRegionAccess = sessionFactory.getCache().getEntityRegionAccess( regionName );
			final CollectionRegionAccessStrategy collectionRegionAccess = sessionFactory.getCache().getCollectionRegionAccess( regionName );

			if ( entityRegionAccess == null && collectionRegionAccess == null ) {
				final QueryCache queryCache = sessionFactory.getCache().getQueryCache( regionName );
				if ( queryCache == null ) {
					return null;
				}
				final Region region = queryCache.getRegion();
				if ( region == null ) {
					throw new IllegalArgumentException( "Could not resolve region name [" + regionName + "]" );
				}
				stat = new ConcurrentSecondLevelCacheStatisticsImpl( region, null, null );
			}
			else {

				final Region region = entityRegionAccess != null
						? entityRegionAccess.getRegion()
						: collectionRegionAccess.getRegion();

				stat = new ConcurrentSecondLevelCacheStatisticsImpl(
						region,
						entityRegionAccess,
						collectionRegionAccess
				);
			}

			ConcurrentSecondLevelCacheStatisticsImpl previous;
			if ( ( previous = secondLevelCacheStatistics.putIfAbsent( regionName, stat ) ) != null ) {
				stat = previous;
			}
		}

		return stat;
	}
