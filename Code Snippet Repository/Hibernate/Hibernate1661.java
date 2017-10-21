	@Override
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
		final String[] columns = criteriaQuery.getIdentifierColumns( criteria );

		String result = StringHelper.join( " and ", StringHelper.suffix( columns, " = ?" ) );
		if ( columns.length > 1) {
			result = '(' + result + ')';
		}
		return result;
	}
