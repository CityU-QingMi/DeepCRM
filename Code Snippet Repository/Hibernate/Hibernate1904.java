	@Override
	public String processSql(String sql, RowSelection selection) {
		if (LimitHelper.hasFirstRow( selection )) {
			throw new UnsupportedOperationException( "query result offset is not supported" );
		}

		final int selectIndex = sql.toLowerCase(Locale.ROOT).indexOf( "select" );
		final int selectDistinctIndex = sql.toLowerCase(Locale.ROOT).indexOf( "select distinct" );
		final int insertionPoint = selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);

		StringBuilder sb = new StringBuilder( sql.length() + 8 )
				.append( sql );

		if ( supportsVariableLimit ) {
			sb.insert( insertionPoint, " TOP ? " );
		}
		else {
			sb.insert( insertionPoint, " TOP " + getMaxOrLimit( selection ) + " " );
		}

		return sb.toString();
	}
