	@Override
	public void lock(Serializable id, Object version, Object object, int timeout, SharedSessionContractImplementor session) {
		if ( !lockable.isVersioned() ) {
			throw new HibernateException( "[" + lockMode + "] not supported for non-versioned entities [" + lockable.getEntityName() + "]" );
		}
		final EntityEntry entry = session.getPersistenceContext().getEntry( object );
		final EntityPersister persister = entry.getPersister();
		final Object nextVersion = persister.forceVersionIncrement( entry.getId(), entry.getVersion(), session );
		entry.forceLocked( object, nextVersion );
	}
