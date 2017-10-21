	@Override
	protected AST postProcessSortSpecification(AST sortSpec) {
		assert SORT_SPEC == sortSpec.getType();
		SortSpecification sortSpecification = (SortSpecification) sortSpec;
		AST sortKey = sortSpecification.getSortKey();
		if ( IDENT_LIST == sortKey.getFirstChild().getType() ) {
			AST identList = sortKey.getFirstChild();
			AST ident = identList.getFirstChild();
			AST holder = new CommonAST();
			do {
				holder.addChild(
						createSortSpecification(
								ident,
								sortSpecification.getCollation(),
								sortSpecification.getOrdering()
						)
				);
				ident = ident.getNextSibling();
			} while ( ident != null );
			sortSpec = holder.getFirstChild();
		}
		return sortSpec;
	}
