	@Override
	public QueryCache getQueryCache(String regionName) throws HibernateException {
		if ( !settings.isQueryCacheEnabled() ) {
			return null;
		}

		if ( regionName == null ) {
			return getDefaultQueryCache();
		}

		QueryCache queryCache = queryCaches.get( regionName );
		if ( queryCache == null ) {
			synchronized (queryCaches) {
				queryCache = queryCaches.get( regionName );
				if ( queryCache == null ) {
					final QueryResultsRegion region = regionFactory.buildQueryResultsRegion(
							qualifyRegionName( regionName ),
							sessionFactory.getProperties()
					);

					queryCache = settings.getQueryCacheFactory().buildQueryCache( region, this );
					queryCaches.put( regionName, queryCache );
				}
			}
		}
		return queryCache;
	}
