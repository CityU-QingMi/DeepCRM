	public Query<?> getRevisionDateQuery(Session session, Number revision) {
		return session.createQuery(
				String.format(
						REVISION_DATE_QUERY,
						revisionInfoTimestampName,
						revisionInfoEntityName,
						revisionInfoIdName
				)
		).setParameter( REVISION_DATE_QUERY_PARAMETER, revision );
	}
