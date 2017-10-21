	@Override
	public String toSqlString(Criteria criteria, int loc, CriteriaQuery criteriaQuery) throws HibernateException {
		final StringBuilder buf = new StringBuilder();
		String separator = "";

		for ( Projection projection : elements ) {
			buf.append( separator ).append( projection.toSqlString( criteria, loc, criteriaQuery ) );
			loc += getColumnAliases( loc, criteria, criteriaQuery, projection ).length;
			separator = ", ";
		}
		return buf.toString();
	}
