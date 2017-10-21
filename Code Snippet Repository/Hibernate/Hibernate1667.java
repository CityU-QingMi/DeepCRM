	@Override
	public String toSqlString(Criteria crit, CriteriaQuery criteriaQuery) throws HibernateException {
		if ( conditions.size()==0 ) {
			return "1=1";
		}

		final StringBuilder buffer = new StringBuilder().append( '(' );
		final Iterator itr = conditions.iterator();
		while ( itr.hasNext() ) {
			buffer.append( ( (Criterion) itr.next() ).toSqlString( crit, criteriaQuery ) );
			if ( itr.hasNext() ) {
				buffer.append( ' ' )
						.append( nature.getOperator() )
						.append( ' ' );
			}
		}

		return buffer.append( ')' ).toString();
	}
