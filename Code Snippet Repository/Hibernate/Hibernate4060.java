	@Override
	public String filterFragment(
			String alias,
			Map enabledFilters,
			Set<String> treatAsDeclarations) {
		StringBuilder sessionFilterFragment = new StringBuilder();
		filterHelper.render( sessionFilterFragment, getFilterAliasGenerator(alias), enabledFilters );

		return sessionFilterFragment.append( filterFragment( alias, treatAsDeclarations ) ).toString();
	}
