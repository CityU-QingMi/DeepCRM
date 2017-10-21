	@Override
	public String processSql(String sql, RowSelection selection) {
		final boolean hasOffset = LimitHelper.hasFirstRow( selection );
		String sqlOffset = hasOffset ? " SKIP " + selection.getFirstRow() : "";
		String sqlLimit = " FIRST " + getMaxOrLimit( selection );
		String sqlOffsetLimit = sqlOffset + sqlLimit;
		String result = new StringBuilder( sql.length() + 10 )
				.append( sql )
				.insert( sql.toLowerCase( Locale.ROOT ).indexOf( "select" ) + 6, sqlOffsetLimit ).toString();
		return result;
	}
