	protected Serializable resolveNaturalId(final ResolveNaturalIdEvent event) {
		final EntityPersister persister = event.getEntityPersister();

		final boolean traceEnabled = LOG.isTraceEnabled();
		if ( traceEnabled ) {
			LOG.tracev(
					"Attempting to resolve: {0}",
					MessageHelper.infoString( persister, event.getNaturalIdValues(), event.getSession().getFactory() )
			);
		}

		Serializable entityId = resolveFromCache( event );
		if ( entityId != null ) {
			if ( traceEnabled ) {
				LOG.tracev(
						"Resolved object in cache: {0}",
						MessageHelper.infoString( persister, event.getNaturalIdValues(), event.getSession().getFactory() )
				);
			}
			return entityId;
		}

		if ( traceEnabled ) {
			LOG.tracev(
					"Object not resolved in any cache: {0}",
					MessageHelper.infoString( persister, event.getNaturalIdValues(), event.getSession().getFactory() )
			);
		}

		return loadFromDatasource( event );
	}
