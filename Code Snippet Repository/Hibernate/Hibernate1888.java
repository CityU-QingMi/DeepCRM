	@Override
	public String processSql(String sql, RowSelection selection) {
		if ( LimitHelper.useLimit( this, selection ) ) {
			// useLimitOffset: whether "offset" is set or not;
			// if set, use "LIMIT offset, row_count" syntax;
			// if not, use "LIMIT row_count"
			final boolean useLimitOffset = LimitHelper.hasFirstRow( selection );
			return sql + (useLimitOffset ? " limit ?, ?" : " limit ?");
		}
		else {
			// or return unaltered SQL
			return sql;
		}
	}
