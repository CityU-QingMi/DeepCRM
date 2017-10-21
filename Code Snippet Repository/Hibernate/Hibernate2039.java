	@Override
	public Object[] getDatabaseSnapshot(Serializable id, EntityPersister persister) throws HibernateException {
		final EntityKey key = session.generateEntityKey( id, persister );
		final Object cached = entitySnapshotsByKey.get( key );
		if ( cached != null ) {
			return cached == NO_ROW ? null : (Object[]) cached;
		}
		else {
			final Object[] snapshot = persister.getDatabaseSnapshot( id, session );
			entitySnapshotsByKey.put( key, snapshot == null ? NO_ROW : snapshot );
			return snapshot;
		}
	}
