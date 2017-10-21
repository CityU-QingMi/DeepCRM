	@Override
	@SuppressWarnings({""})
	public List<Object> findEntities(Number revision, RevisionType revisionType)
			throws IllegalStateException, IllegalArgumentException {
		final Set<Pair<String, Class>> entityTypes = findEntityTypes( revision );
		final List<Object> result = new ArrayList<>();
		for ( Pair<String, Class> type : entityTypes ) {
			result.addAll(
					auditReaderImplementor.createQuery().forEntitiesModifiedAtRevision(
							type.getSecond(),
							type.getFirst(),
							revision
					)
							.add( new RevisionTypeAuditExpression( null, revisionType, "=" ) ).getResultList()
			);
		}
		return result;
	}
