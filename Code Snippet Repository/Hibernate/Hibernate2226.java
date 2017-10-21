	public List<FromElement> toFromElements(FromClause fromClause, HqlSqlWalker walker) {
		// If a role already has an explicit fetch in the query, skip it in the graph.
		Map<String, FromElement> explicitFetches = new HashMap<String, FromElement>();
		for ( Object o : fromClause.getFromElements() ) {
			final FromElement fromElement = (FromElement) o;
			if ( fromElement.getRole() != null  && ! (fromElement instanceof ImpliedFromElement) ) {
				explicitFetches.put( fromElement.getRole(), fromElement );
			}
		}

		return getFromElements(
				fromClause.getLevel() == FromClause.ROOT_LEVEL ? originEntityGraph.getAttributeNodes():
					Collections.emptyList(),
				fromClause.getFromElement(),
				fromClause,
				walker,
				explicitFetches
		);
	}
