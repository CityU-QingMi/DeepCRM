	@Override
	public String processSql(String sql, RowSelection selection) {
		final boolean useLimitOffset = supportsLimit()
				&& supportsLimitOffset()
				&& LimitHelper.hasFirstRow( selection )
				&& LimitHelper.hasMaxRows( selection );
		return dialect.getLimitString(
				sql,
				useLimitOffset ? LimitHelper.getFirstRow( selection ) : 0,
				getMaxOrLimit( selection )
		);
	}
