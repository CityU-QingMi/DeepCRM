	@Nullable
	private static SqlParameter findParameter(
			@Nullable List<SqlParameter> declaredParams, String paramName, int paramIndex) {

		if (declaredParams != null) {
			// First pass: Look for named parameter match.
			for (SqlParameter declaredParam : declaredParams) {
				if (paramName.equals(declaredParam.getName())) {
					return declaredParam;
				}
			}
			// Second pass: Look for parameter index match.
			if (paramIndex < declaredParams.size()) {
				SqlParameter declaredParam = declaredParams.get(paramIndex);
				// Only accept unnamed parameters for index matches.
				if (declaredParam.getName() == null) {
					return declaredParam;
				}
			}
		}
		return null;
	}
