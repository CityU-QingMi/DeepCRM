	private Object loadFromDatasource(
			final LoadEvent event,
			final EntityPersister persister) {
		Object entity = persister.load(
				event.getEntityId(),
				event.getInstanceToLoad(),
				event.getLockOptions(),
				event.getSession()
		);

		if ( event.isAssociationFetch() && event.getSession().getFactory().getStatistics().isStatisticsEnabled() ) {
			event.getSession().getFactory().getStatistics().fetchEntity( event.getEntityClassName() );
		}

		return entity;
	}
