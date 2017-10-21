	@Override
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		final String[] columns = criteriaQuery.findColumns( propertyName, criteria );
		String result = StringHelper.join(
				" or ",
				StringHelper.suffix( columns, " is not null" )
		);
		if ( columns.length > 1 ) {
			result = '(' + result + ')';
		}
		return result;
	}
