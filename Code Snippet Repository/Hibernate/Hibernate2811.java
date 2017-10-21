	public String getAliasName(String alias) {
		String name = (String) aliasNames.get( alias );
		if ( name == null ) {
			if ( superQuery != null ) {
				name = superQuery.getAliasName( alias );
			}
			else {
				name = alias;
			}
		}
		return name;
	}
