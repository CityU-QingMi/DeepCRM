	@Override
	public String processSql(String sql, RowSelection selection) {
		if (LimitHelper.useLimit( this, selection )) {
			return sql + (LimitHelper.hasFirstRow( selection ) ?
					" offset ? rows fetch next ? rows only" : " fetch first ? rows only");
		}
		else {
			// or return unaltered SQL
			return sql;
		}
	}
