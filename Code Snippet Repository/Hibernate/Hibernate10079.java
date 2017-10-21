	@Override
	@SuppressWarnings({""})
	public <T> T findRevision(Class<T> revisionEntityClass, Number revision)
			throws IllegalArgumentException, RevisionDoesNotExistException, IllegalStateException {
		revisionEntityClass = getTargetClassIfProxied( revisionEntityClass );
		checkNotNull( revision, "Entity revision" );
		checkPositive( revision, "Entity revision" );
		checkSession();

		final Set<Number> revisions = new HashSet<>( 1 );
		revisions.add( revision );
		final Query<?> query = enversService.getRevisionInfoQueryCreator().getRevisionsQuery( session, revisions );

		try {
			final T revisionData = (T) query.uniqueResult();

			if ( revisionData == null ) {
				throw new RevisionDoesNotExistException( revision );
			}

			return revisionData;
		}
		catch (NonUniqueResultException e) {
			throw new AuditException( e );
		}
	}
