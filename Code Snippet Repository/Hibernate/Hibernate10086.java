	@Override
	@SuppressWarnings({""})
	public Set<Pair<String, Class>> findEntityTypes(Number revision)
			throws IllegalStateException, IllegalArgumentException {
		checkNotNull( revision, "Entity revision" );
		checkPositive( revision, "Entity revision" );
		checkSession();

		final Session session = auditReaderImplementor.getSession();
		final SessionImplementor sessionImplementor = auditReaderImplementor.getSessionImplementor();

		final Set<Number> revisions = new HashSet<>( 1 );
		revisions.add( revision );
		final Query<?> query = enversService.getRevisionInfoQueryCreator().getRevisionsQuery( session, revisions );
		final Object revisionInfo = query.uniqueResult();

		if ( revisionInfo != null ) {
			// If revision exists.
			final Set<String> entityNames = enversService.getModifiedEntityNamesReader().getModifiedEntityNames( revisionInfo );
			if ( entityNames != null ) {
				// Generate result that contains entity names and corresponding Java classes.
				final Set<Pair<String, Class>> result = new HashSet<>();
				for ( String entityName : entityNames ) {
					result.add(
							Pair.make(
									entityName, EntityTools.getEntityClass(
									sessionImplementor,
									entityName
							)
							)
					);
				}
				return result;
			}
		}

		return Collections.EMPTY_SET;
	}
