	public String getOrderBy() {
		StringBuilder orderBy = new StringBuilder( 30 );
		Iterator<CriteriaImpl.OrderEntry> criterionIterator = rootCriteria.iterateOrderings();
		while ( criterionIterator.hasNext() ) {
			CriteriaImpl.OrderEntry oe = criterionIterator.next();
			orderBy.append( oe.getOrder().toSqlString( oe.getCriteria(), this ) );
			if ( criterionIterator.hasNext() ) {
				orderBy.append( ", " );
			}
		}
		return orderBy.toString();
	}
