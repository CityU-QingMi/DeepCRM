	@Override
	public String fromJoinFragment(String alias, boolean innerJoin, boolean includeSubclasses) {
		// NOTE : Not calling createJoin here is just a performance optimization
		return getSubclassTableSpan() == 1
				? ""
				: createJoin(
				alias,
				innerJoin,
				includeSubclasses,
				Collections.<String>emptySet()
		).toFromFragmentString();
	}
