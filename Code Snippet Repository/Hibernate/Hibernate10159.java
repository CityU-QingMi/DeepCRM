	public Query toQuery(Session session) {
		final StringBuilder querySb = new StringBuilder();
		final Map<String, Object> queryParamValues = new HashMap<>();

		build( querySb, queryParamValues );

		final Query query = session.createQuery( querySb.toString() );
		for ( Map.Entry<String, Object> paramValue : queryParamValues.entrySet() ) {
			if ( paramValue.getValue() instanceof RevisionType ) {
				// this is needed when the ClassicQueryTranslatorFactory is used
				query.setParameter(
						paramValue.getKey(),
						paramValue.getValue(),
						new CustomType( new RevisionTypeType() )
				);
			}
			else {
				query.setParameter( paramValue.getKey(), paramValue.getValue() );
			}
		}
		return query;
	}
