	public FromElement getFromElement(String aliasOrClassName) {
		FromElement fromElement = fromElementByClassAlias.get( aliasOrClassName );
		if ( fromElement == null && getSessionFactoryHelper().isStrictJPAQLComplianceEnabled() ) {
			fromElement = findIntendedAliasedFromElementBasedOnCrazyJPARequirements( aliasOrClassName );
		}
		if ( fromElement == null && parentFromClause != null ) {
			fromElement = parentFromClause.getFromElement( aliasOrClassName );
		}
		return fromElement;
	}
