	public String getWhereCondition() {
		StringBuilder condition = new StringBuilder( 30 );
		Iterator<CriteriaImpl.CriterionEntry> criterionIterator = rootCriteria.iterateExpressionEntries();
		while ( criterionIterator.hasNext() ) {
			CriteriaImpl.CriterionEntry entry = criterionIterator.next();
			String sqlString = entry.getCriterion().toSqlString( entry.getCriteria(), this );
			condition.append( sqlString );
			if ( criterionIterator.hasNext() ) {
				condition.append( " and " );
			}
		}
		return condition.toString();
	}
