	@Override
	public Query getQuery(AuditReaderImplementor versionsReader, Object primaryKey, Number revision, boolean removed) {
		final Query query = versionsReader.getSession().createQuery( removed ? getQueryRemovedString() : getQueryString() );
		query.setParameter( DEL_REVISION_TYPE_PARAMETER, RevisionType.DEL );
		query.setParameter( REVISION_PARAMETER, revision );
		for ( QueryParameterData paramData : referencingIdData.getPrefixedMapper().mapToQueryParametersFromId(
				primaryKey
		) ) {
			paramData.setParameterValue( query );
		}
		return query;
	}
