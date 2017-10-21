	public Query<?> getRevisionNumberForDateQuery(Session session, Date date) {
		return session.createQuery(
				String.format(
						REVISION_NUMBER_FOR_DATE_QUERY,
						revisionInfoIdName,
						revisionInfoEntityName,
						revisionInfoTimestampName
				)
		).setParameter( REVISION_NUMBER_FOR_DATE_QUERY_PARAMETER, timestampAsDate ? date : date.getTime() );
	}
