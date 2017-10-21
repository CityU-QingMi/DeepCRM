	@Override
	public String toGroupSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		final StringBuilder buf = new StringBuilder();
		String separator = "";
		for ( Projection projection : elements ) {
			if ( ! projection.isGrouped() ) {
				continue;
			}

			buf.append( separator ).append( projection.toGroupSqlString( criteria, criteriaQuery ) );
			separator = ", ";
		}
		return buf.toString();
	}
