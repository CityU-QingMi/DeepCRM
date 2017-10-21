	FromElement addFromElement() throws SemanticException {
		final FromClause parentFromClause = fromClause.getParentFromClause();
		if ( parentFromClause != null ) {
			// Look up class name using the first identifier in the path.
			final String pathAlias = PathHelper.getAlias( path );
			final FromElement parentFromElement = parentFromClause.getFromElement( pathAlias );
			if ( parentFromElement != null ) {
				return createFromElementInSubselect( path, pathAlias, parentFromElement, classAlias );
			}
		}

		final EntityPersister entityPersister = fromClause.getSessionFactoryHelper().requireClassPersister( path );

		final FromElement elem = createAndAddFromElement(
				path,
				classAlias,
				entityPersister,
				(EntityType) ( (Queryable) entityPersister ).getType(),
				null
		);

		// Add to the query spaces.
		fromClause.getWalker().addQuerySpaces( entityPersister.getQuerySpaces() );

		return elem;
	}
