	private String findAlias(String varName) {

		if (aliases == null)
			return varName;

		String alias = (String) aliases.get(varName);
		if (alias == null) {
			return varName;
		}
		return alias;
	}
