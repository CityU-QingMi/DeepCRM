	private FromElement createFromElementInSubselect(
			String path,
			String pathAlias,
			FromElement parentFromElement,
			String classAlias) throws SemanticException {
		LOG.debugf( "createFromElementInSubselect() : path = %s", path );

		// Create an DotNode AST for the path and resolve it.
		FromElement fromElement = evaluateFromElementPath( path, classAlias );
		EntityPersister entityPersister = fromElement.getEntityPersister();

		// If the first identifier in the path refers to the class alias (not the class name), then this
		// is a correlated subselect.  If it's a correlated sub-select, use the existing table alias.  Otherwise
		// generate a new one.
		String tableAlias = null;
		boolean correlatedSubselect = pathAlias.equals( parentFromElement.getClassAlias() );
		if ( correlatedSubselect ) {
			tableAlias = fromElement.getTableAlias();
		}
		else {
			tableAlias = null;
		}

		// If the from element isn't in the same clause, create a new from element.
		if ( fromElement.getFromClause() != fromClause ) {
			LOG.debug( "createFromElementInSubselect() : creating a new FROM element..." );
			fromElement = createFromElement( entityPersister );
			initializeAndAddFromElement(
					fromElement,
					path,
					classAlias,
					entityPersister,
					(EntityType) ( (Queryable) entityPersister ).getType(),
					tableAlias
			);
		}
		LOG.debugf( "createFromElementInSubselect() : %s -> %s", path, fromElement );
		return fromElement;
	}
