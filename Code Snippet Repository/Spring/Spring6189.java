	@Override
	@Nullable
	public String parameterNameToUse(@Nullable String parameterName) {
		if (parameterName == null) {
			return null;
		}
		else if (isStoresUpperCaseIdentifiers()) {
			return parameterName.toUpperCase();
		}
		else if (isStoresLowerCaseIdentifiers()) {
			return parameterName.toLowerCase();
		}
		else {
			return parameterName;
		}
	}
