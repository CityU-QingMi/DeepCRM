	@Override
	@SuppressWarnings({""})
	public Map<RevisionType, List<Object>> findEntitiesGroupByRevisionType(Number revision)
			throws IllegalStateException, IllegalArgumentException {
		final Set<Pair<String, Class>> entityTypes = findEntityTypes( revision );
		final Map<RevisionType, List<Object>> result = new HashMap<>();
		for ( RevisionType revisionType : RevisionType.values() ) {
			result.put( revisionType, new ArrayList<>() );
			for ( Pair<String, Class> type : entityTypes ) {
				final List<Object> list = auditReaderImplementor.createQuery()
						.forEntitiesModifiedAtRevision( type.getSecond(), type.getFirst(), revision )
						.add( new RevisionTypeAuditExpression( null, revisionType, "=" ) )
						.getResultList();
				result.get( revisionType ).addAll( list );
			}
		}
		return result;
	}
