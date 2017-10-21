	public FromElement createEntityJoin(
			String entityClass,
			String tableAlias,
			JoinSequence joinSequence,
			boolean fetchFlag,
			boolean inFrom,
			EntityType type,
			String role,
			String joinPath) throws SemanticException {
		FromElement elem = createJoin( entityClass, tableAlias, joinSequence, type, false );
		elem.setFetch( fetchFlag );

		if ( joinPath != null ) {
			elem.applyTreatAsDeclarations( fromClause.getWalker().getTreatAsDeclarationsByPath( joinPath ) );
		}

		EntityPersister entityPersister = elem.getEntityPersister();
		int numberOfTables = entityPersister.getQuerySpaces().length;
		if ( numberOfTables > 1 && implied && !elem.useFromFragment() ) {
			LOG.debug( "createEntityJoin() : Implied multi-table entity join" );
			elem.setUseFromFragment( true );
		}

		// If this is an implied join in a FROM clause, then use ANSI-style joining, and set the
		// flag on the FromElement that indicates that it was implied in the FROM clause itself.
		if ( implied && inFrom ) {
			joinSequence.setUseThetaStyle( false );
			elem.setUseFromFragment( true );
			elem.setImpliedInFromClause( true );
		}
		if ( elem.getWalker().isSubQuery() ) {
			// two conditions where we need to transform this to a theta-join syntax:
			//      1) 'elem' is the "root from-element" in correlated subqueries
			//      2) The DotNode.useThetaStyleImplicitJoins has been set to true
			//          and 'elem' represents an implicit join
			if ( elem.getFromClause() != elem.getOrigin().getFromClause() ||
//			        ( implied && DotNode.useThetaStyleImplicitJoins ) ) {
					DotNode.useThetaStyleImplicitJoins ) {
				// the "root from-element" in correlated subqueries do need this piece
				elem.setType( FROM_FRAGMENT );
				joinSequence.setUseThetaStyle( true );
				elem.setUseFromFragment( false );
			}
		}

		elem.setRole( role );

		return elem;
	}
