	public String getSqlAssignmentFragment() {
		if ( sqlAssignmentString == null ) {
			try {
				SqlGenerator sqlGenerator = new SqlGenerator( factory );
				sqlGenerator.comparisonExpr(
						eq,
						false
				);  // false indicates to not generate parens around the assignment
				sqlAssignmentString = sqlGenerator.getSQL();
			}
			catch (Throwable t) {
				throw new QueryException( "cannot interpret set-clause assignment" );
			}
		}
		return sqlAssignmentString;
	}
