	@Deprecated
	@SuppressWarnings({ "" })
	public static String renderWhereStringTemplate(String sqlWhereString, String placeholder, Dialect dialect) {
		return renderWhereStringTemplate(
				sqlWhereString,
				placeholder,
				dialect,
				new SQLFunctionRegistry( dialect, java.util.Collections.<String, SQLFunction>emptyMap() )
		);
	}
