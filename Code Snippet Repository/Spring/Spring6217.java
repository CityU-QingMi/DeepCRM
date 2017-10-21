	@Nullable
	public static Object getTypedValue(SqlParameterSource source, String parameterName) {
		int sqlType = source.getSqlType(parameterName);
		if (sqlType != SqlParameterSource.TYPE_UNKNOWN) {
			if (source.getTypeName(parameterName) != null) {
				return new SqlParameterValue(sqlType, source.getTypeName(parameterName), source.getValue(parameterName));
			}
			else {
				return new SqlParameterValue(sqlType, source.getValue(parameterName));
			}
		}
		else {
			return source.getValue(parameterName);
		}
	}
