	@Override
	@SuppressWarnings({""})
	public <T> T find(
			Class<T> cls,
			String entityName,
			Object primaryKey,
			Number revision,
			boolean includeDeletions) throws IllegalArgumentException, NotAuditedException, IllegalStateException {
		cls = getTargetClassIfProxied( cls );
		checkNotNull( cls, "Entity class" );
		checkNotNull( entityName, "Entity name" );
		checkNotNull( primaryKey, "Primary key" );
		checkNotNull( revision, "Entity revision" );
		checkPositive( revision, "Entity revision" );
		checkSession();

		if ( firstLevelCache.contains( entityName, revision, primaryKey ) ) {
			return (T) firstLevelCache.get( entityName, revision, primaryKey );
		}

		Object result;
		try {
			// The result is put into the cache by the entity instantiator called from the query
			result = createQuery().forEntitiesAtRevision( cls, entityName, revision, includeDeletions )
					.add( AuditEntity.id().eq( primaryKey ) ).getSingleResult();
		}
		catch (NoResultException e) {
			result = null;
		}
		catch (NonUniqueResultException e) {
			throw new AuditException( e );
		}

		return (T) result;
	}
