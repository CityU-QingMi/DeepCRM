	protected final int bindLimitParameters(RowSelection selection, PreparedStatement statement, int index)
			throws SQLException {
		if ( !supportsVariableLimit() || !LimitHelper.hasMaxRows( selection ) ) {
			return 0;
		}
		final int firstRow = convertToFirstRowValue( LimitHelper.getFirstRow( selection ) );
		final int lastRow = getMaxOrLimit( selection );
		final boolean hasFirstRow = supportsLimitOffset() && ( firstRow > 0 || forceLimitUsage() );
		final boolean reverse = bindLimitParametersInReverseOrder();
		if ( hasFirstRow ) {
			statement.setInt( index + ( reverse ? 1 : 0 ), firstRow );
		}
		statement.setInt( index + ( reverse || !hasFirstRow ? 0 : 1 ), lastRow );
		return hasFirstRow ? 2 : 1;
	}
