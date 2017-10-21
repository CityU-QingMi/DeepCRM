	@Override
	public String processSql(String sql, RowSelection selection) {
		// SQLServer mandates the following rules to use OFFSET/LIMIT
		//  * An 'ORDER BY' is required
		//  * The 'OFFSET ...' clause is mandatory, cannot use 'FETCH ...' by itself.
		//  * The 'TOP' clause isn't permitted with LIMIT/OFFSET.
		if ( hasOrderBy( sql ) ) {
			if ( !LimitHelper.useLimit( this, selection ) ) {
				return sql;
			}
			return applyOffsetFetch( selection, sql, getInsertPosition( sql ) );
		}
		return super.processSql( sql, selection );
	}
