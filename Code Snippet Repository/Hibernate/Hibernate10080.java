	@Override
	@SuppressWarnings({""})
	public <T> Map<Number, T> findRevisions(Class<T> revisionEntityClass, Set<Number> revisions)
			throws IllegalArgumentException,
			IllegalStateException {
		revisionEntityClass = getTargetClassIfProxied( revisionEntityClass );
		final Map<Number, T> result = new HashMap<>( revisions.size() );

		for ( Number revision : revisions ) {
			checkNotNull( revision, "Entity revision" );
			checkPositive( revision, "Entity revision" );
		}
		checkSession();

		final Query<?> query = enversService.getRevisionInfoQueryCreator().getRevisionsQuery( session, revisions );

		try {
			final List<?> revisionList = query.getResultList();
			for ( Object revision : revisionList ) {
				final Number revNo = enversService.getRevisionInfoNumberReader().getRevisionNumber( revision );
				result.put( revNo, (T) revision );
			}

			return result;
		}
		catch (HibernateException e) {
			throw new AuditException( e );
		}
	}
