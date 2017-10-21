	public Query toQuery(Session session) {
		final StringBuilder querySb = new StringBuilder();
		final Map<String, Object> queryParamValues = new HashMap<>();

		build( querySb, queryParamValues );

		final Query query = session.createQuery( querySb.toString() );
		for ( Map.Entry<String, Object> paramValue : queryParamValues.entrySet() ) {
			query.setParameter( paramValue.getKey(), paramValue.getValue() );
		}

		return query;
	}
