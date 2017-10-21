	public String toStatementString() {
		StringBuilder buf = new StringBuilder(
				columns.size() * 10 +
						tableName.length() +
						whereTokens.size() * 10 +
						10
		);

		if ( comment != null ) {
			buf.append( "/* " ).append( comment ).append( " */ " );
		}

		buf.append( "select " );
		Set<String> uniqueColumns = new HashSet<String>();
		Iterator<String> iter = columns.iterator();
		boolean appendComma = false;
		while ( iter.hasNext() ) {
			String col = iter.next();
			String alias = aliases.get( col );
			if ( uniqueColumns.add( alias == null ? col : alias ) ) {
				if ( appendComma ) {
					buf.append( ", " );
				}
				buf.append( col );
				if ( alias != null && !alias.equals( col ) ) {
					buf.append( " as " )
							.append( alias );
				}
				appendComma = true;
			}
		}

		buf.append( " from " )
				.append( dialect.appendLockHint( lockOptions, tableName ) );

		if ( whereTokens.size() > 0 ) {
			buf.append( " where " )
					.append( toWhereClause() );
		}

		if ( orderBy != null ) {
			buf.append( orderBy );
		}

		if ( lockOptions != null ) {
			buf = new StringBuilder(dialect.applyLocksToSql( buf.toString(), lockOptions, null ) );
		}

		return dialect.transformSelectString( buf.toString() );
	}
