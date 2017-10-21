	@Override
	@Nullable
	public String procedureNameToUse(@Nullable String procedureName) {
		if (procedureName == null) {
			return null;
		}
		else if (isStoresUpperCaseIdentifiers()) {
			return procedureName.toUpperCase();
		}
		else if (isStoresLowerCaseIdentifiers()) {
			return procedureName.toLowerCase();
		}
		else {
			return procedureName;
		}
	}
