	public SimpleAuxiliaryDatabaseObject(
			Namespace namespace,
			String createString,
			String dropString,
			Set<String> dialectScopes) {
		this(
				namespace,
				new String[] { createString },
				new String[] { dropString },
				dialectScopes
		);
	}
