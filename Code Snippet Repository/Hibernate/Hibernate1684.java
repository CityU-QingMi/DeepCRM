	@Override
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		final String[] lhsColumns = criteriaQuery.findColumns( propertyName, criteria );
		final String[] rhsColumns = criteriaQuery.findColumns( otherPropertyName, criteria );

		final String[] comparisons = StringHelper.add( lhsColumns, getOp(), rhsColumns );
		if ( comparisons.length > 1 ) {
			return '(' + StringHelper.join( " and ", comparisons ) + ')';
		}
		else {
			return comparisons[0];
		}
	}
