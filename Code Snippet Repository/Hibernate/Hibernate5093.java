	@Override
	public String getOnCondition(
			String alias,
			SessionFactoryImplementor factory,
			Map enabledFilters,
			Set<String> treatAsDeclarations) {
		if ( isReferenceToPrimaryKey() && ( treatAsDeclarations == null || treatAsDeclarations.isEmpty() ) ) {
			return "";
		}
		else {
			return getAssociatedJoinable( factory ).filterFragment( alias, enabledFilters, treatAsDeclarations );
		}
	}
