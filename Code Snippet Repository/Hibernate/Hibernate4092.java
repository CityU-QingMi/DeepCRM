	@Override
	public String fromJoinFragment(
			String alias,
			boolean innerJoin,
			boolean includeSubclasses,
			Set<String> treatAsDeclarations) {
		return ( (Joinable) getElementPersister() ).fromJoinFragment(
				alias,
				innerJoin,
				includeSubclasses,
				treatAsDeclarations
		);
	}
