	protected final EntityEntry reassociate(AbstractEvent event, Object object, Serializable id, EntityPersister persister) {

		if ( log.isTraceEnabled() ) {
			log.tracev(
					"Reassociating transient instance: {0}",
					MessageHelper.infoString( persister, id, event.getSession().getFactory() )
			);
		}

		final EventSource source = event.getSession();
		final EntityKey key = source.generateEntityKey( id, persister );

		source.getPersistenceContext().checkUniqueness( key, object );

		//get a snapshot
		Object[] values = persister.getPropertyValues( object );
		TypeHelper.deepCopy(
				values,
				persister.getPropertyTypes(),
				persister.getPropertyUpdateability(),
				values,
				source
		);
		Object version = Versioning.getVersion( values, persister );

		EntityEntry newEntry = source.getPersistenceContext().addEntity(
				object,
				( persister.isMutable() ? Status.MANAGED : Status.READ_ONLY ),
				values,
				key,
				version,
				LockMode.NONE,
				true,
				persister,
				false
		);

		new OnLockVisitor( source, id, object ).process( object, persister );

		persister.afterReassociate( object, source );

		return newEntry;

	}
