	private void addSubclassJoins(
			JoinFragment joinFragment,
			String alias,
			Joinable joinable,
			boolean innerJoin,
			boolean includeSubclassJoins,
			Set<String> treatAsDeclarations) {
		final boolean include = includeSubclassJoins && isIncluded( alias );
		joinFragment.addJoins(
				joinable.fromJoinFragment( alias, innerJoin, include, treatAsDeclarations ),
				joinable.whereJoinFragment( alias, innerJoin, include, treatAsDeclarations )
		);
	}
