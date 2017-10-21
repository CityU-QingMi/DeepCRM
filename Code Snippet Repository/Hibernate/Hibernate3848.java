	public String toStatementString() {
		StringBuilder buf = new StringBuilder( guesstimatedBufferSize );

		if ( StringHelper.isNotEmpty( comment ) ) {
			buf.append( "/* " ).append( comment ).append( " */ " );
		}

		buf.append( "select " )
				.append( selectClause )
				.append( " from " )
				.append( fromClause );

		if ( StringHelper.isNotEmpty( outerJoinsAfterFrom ) ) {
			buf.append( outerJoinsAfterFrom );
		}

		if ( isNotEmpty( whereClause ) || isNotEmpty( outerJoinsAfterWhere ) ) {
			buf.append( " where " );
			// the outerJoinsAfterWhere needs to come before where clause to properly
			// handle dynamic filters
			if ( StringHelper.isNotEmpty( outerJoinsAfterWhere ) ) {
				buf.append( outerJoinsAfterWhere );
				if ( isNotEmpty( whereClause ) ) {
					buf.append( " and " );
				}
			}
			if ( isNotEmpty( whereClause ) ) {
				buf.append( whereClause );
			}
		}

		if ( orderByClause != null ) {
			buf.append( " order by " ).append( orderByClause );
		}

		if ( lockOptions.getLockMode() != LockMode.NONE ) {
			buf = new StringBuilder(dialect.applyLocksToSql( buf.toString(), lockOptions, null ) );
		}

		return dialect.transformSelectString( buf.toString() );
	}
