	public String toStatementString() {
		StringBuilder buf = new StringBuilder(guesstimatedBufferSize);
		if ( StringHelper.isNotEmpty(comment) ) {
			buf.append("/* ").append(comment).append(" */ ");
		}
		
		buf.append("select ").append(selectClause)
				.append(" from ").append(fromClause);
		
		if ( StringHelper.isNotEmpty(outerJoinsAfterFrom) ) {
			buf.append(outerJoinsAfterFrom);
		}
		
		if ( StringHelper.isNotEmpty(whereClause) || StringHelper.isNotEmpty(outerJoinsAfterWhere) ) {
			buf.append(" where " );
			// the outerJoinsAfterWhere needs to come before where clause to properly
			// handle dynamic filters
			if ( StringHelper.isNotEmpty(outerJoinsAfterWhere) ) {
				buf.append(outerJoinsAfterWhere);
				if ( StringHelper.isNotEmpty(whereClause) ) {
					buf.append( " and " );
				}
			}
			if ( StringHelper.isNotEmpty( whereClause ) ) {
				buf.append(whereClause);
			}
		}
		
		if ( StringHelper.isNotEmpty(groupByClause) ) {
			buf.append(" group by ").append(groupByClause);
		}
		
		if ( StringHelper.isNotEmpty(orderByClause) ) {
			buf.append(" order by ").append(orderByClause);
		}
		
		if (lockOptions.getLockMode() != LockMode.NONE) {
			buf.append(dialect.getForUpdateString(lockOptions));
		}
		
		return dialect.transformSelectString( buf.toString() );
	}
