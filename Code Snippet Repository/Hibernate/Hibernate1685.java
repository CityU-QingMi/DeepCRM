	@Override
	public String toSqlString(Criteria criteria, int position, CriteriaQuery criteriaQuery) throws HibernateException {
		final StringBuilder buf = new StringBuilder();
		final String[] cols = criteriaQuery.getColumns( propertyName, criteria );
		for ( int i=0; i<cols.length; i++ ) {
			buf.append( cols[i] )
					.append( " as y" )
					.append( position + i )
					.append( '_' );
			if (i < cols.length -1) {
				buf.append( ", " );
			}
		}
		return buf.toString();
	}
