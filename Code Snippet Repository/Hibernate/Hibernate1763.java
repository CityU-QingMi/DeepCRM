	@Override
	public String getLimitString(String sql, boolean hasOffset) {
		if ( hsqldbVersion < 200 ) {
			return new StringBuilder( sql.length() + 10 )
					.append( sql )
					.insert(
							sql.toLowerCase(Locale.ROOT).indexOf( "select" ) + 6,
							hasOffset ? " limit ? ?" : " top ?"
					)
					.toString();
		}
		else {
			return sql + (hasOffset ? " offset ? limit ?" : " limit ?");
		}
	}
