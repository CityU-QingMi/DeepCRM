	public boolean hasAlias(String name, String alias) {
		for (Map.Entry<String, String> entry : this.aliasMap.entrySet()) {
			String registeredName = entry.getValue();
			if (registeredName.equals(name)) {
				String registeredAlias = entry.getKey();
				return (registeredAlias.equals(alias) || hasAlias(registeredAlias, alias));
			}
		}
		return false;
	}
