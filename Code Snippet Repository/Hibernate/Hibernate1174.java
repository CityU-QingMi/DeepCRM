	public void invalidate(Serializable[] spaces, SharedSessionContractImplementor session) throws CacheException {
		final boolean stats = factory != null && factory.getStatistics().isStatisticsEnabled();

		final Long ts = region.nextTimestamp();

		for (Serializable space : spaces) {
			if ( DEBUG_ENABLED ) {
				LOG.debugf( "Invalidating space [%s], timestamp: %s", space, ts );
			}

			try {
				session.getEventListenerManager().cachePutStart();

				//put() has nowait semantics, is this really appropriate?
				//note that it needs to be async replication, never local or sync
				region.put( session, space, ts );
			}
			finally {
				session.getEventListenerManager().cachePutEnd();
			}

			if ( stats ) {
				factory.getStatistics().updateTimestampsCachePut();
			}
		}
	}
