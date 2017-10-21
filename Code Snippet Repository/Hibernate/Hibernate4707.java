	private SortSpecification createSortSpecification(
			AST ident,
			CollationSpecification collationSpecification,
			OrderingSpecification orderingSpecification) {
		AST sortSpecification = getASTFactory().create( SORT_SPEC, "{{sort specification}}" );
		AST sortKey = getASTFactory().create( SORT_KEY, "{{sort key}}" );
		AST newIdent = getASTFactory().create( ident.getType(), ident.getText() );
		sortKey.setFirstChild( newIdent );
		sortSpecification.setFirstChild( sortKey );
		if ( collationSpecification != null ) {
			sortSpecification.addChild( collationSpecification );
		}
		if ( orderingSpecification != null ) {
			sortSpecification.addChild( orderingSpecification );
		}
		return (SortSpecification) sortSpecification;
	}
