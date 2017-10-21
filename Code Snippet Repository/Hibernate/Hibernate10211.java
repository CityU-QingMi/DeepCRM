	private void addNonIdentifierWhereConditions(QueryBuilder qb, Map<String, Object> data, String originalIdPropertyName) {
		final Parameters parameters = qb.getRootParameters();
		for ( Map.Entry<String, Object> entry : data.entrySet() ) {
			if ( !originalIdPropertyName.equals( entry.getKey() ) ) {
				if ( entry.getValue() != null ) {
					parameters.addWhereWithParam( entry.getKey(), true, "=", entry.getValue() );
				}
				else {
					parameters.addNullRestriction( entry.getKey(), true );
				}
			}
		}
	}
