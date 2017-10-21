	public static CustomSql buildCustomSql(JaxbHbmCustomSqlDmlType customSqlElement) {
		if ( customSqlElement == null ) {
			return null;
		}
		final ExecuteUpdateResultCheckStyle checkStyle = customSqlElement.getCheck() == null
				? customSqlElement.isCallable()
						? ExecuteUpdateResultCheckStyle.NONE
						: ExecuteUpdateResultCheckStyle.COUNT
				: customSqlElement.getCheck();
		return new CustomSql( customSqlElement.getValue(), customSqlElement.isCallable(), checkStyle );
	}
