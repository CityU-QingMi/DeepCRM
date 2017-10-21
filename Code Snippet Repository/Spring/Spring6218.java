	public static Map<String, String> extractCaseInsensitiveParameterNames(SqlParameterSource parameterSource) {
		Map<String, String> caseInsensitiveParameterNames = new HashMap<>();
		if (parameterSource instanceof BeanPropertySqlParameterSource) {
			String[] propertyNames = ((BeanPropertySqlParameterSource)parameterSource).getReadablePropertyNames();
			for (String name : propertyNames) {
				caseInsensitiveParameterNames.put(name.toLowerCase(), name);
			}
		}
		else if (parameterSource instanceof MapSqlParameterSource) {
			for (String name : ((MapSqlParameterSource) parameterSource).getValues().keySet()) {
				caseInsensitiveParameterNames.put(name.toLowerCase(), name);
			}
		}
		return caseInsensitiveParameterNames;
	}
