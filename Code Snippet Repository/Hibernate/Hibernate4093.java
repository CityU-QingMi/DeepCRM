	@Override
	public String whereJoinFragment(
			String alias,
			boolean innerJoin,
			boolean includeSubclasses,
			Set<String> treatAsDeclarations) {
		return ( (Joinable) getElementPersister() ).whereJoinFragment(
				alias,
				innerJoin,
				includeSubclasses,
				treatAsDeclarations
		);
	}
