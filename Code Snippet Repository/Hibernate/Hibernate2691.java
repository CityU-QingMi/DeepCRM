	public void resolveIndex(AST parent) throws SemanticException {
		// An ident node can represent an index expression if the ident
		// represents a naked property ref
		//      *Note: this makes the assumption (which is currently the case
		//      in the hql-sql grammar) that the ident is first resolved
		//      itself (addrExpr -> resolve()).  The other option, if that
		//      changes, is to call resolve from here; but it is
		//      currently un-needed overhead.
		if (!(isResolved() && nakedPropertyRef)) {
			throw new UnsupportedOperationException();
		}

		String propertyName = getOriginalText();
		if (!getDataType().isCollectionType()) {
			throw new SemanticException("Collection expected; [" + propertyName + "] does not refer to a collection property");
		}

		// TODO : most of below was taken verbatim from DotNode; should either delegate this logic or super-type it
		CollectionType type = (CollectionType) getDataType();
		String role = type.getRole();
		QueryableCollection queryableCollection = getSessionFactoryHelper().requireQueryableCollection(role);

		String alias = null;  // DotNode uses null here...
		String columnTableAlias = getFromElement().getTableAlias();
		JoinType joinType = JoinType.INNER_JOIN;
		boolean fetch = false;

		FromElementFactory factory = new FromElementFactory(
				getWalker().getCurrentFromClause(),
				getFromElement(),
				propertyName,
				alias,
				getFromElement().toColumns(columnTableAlias, propertyName, false),
				true
		);
		FromElement elem = factory.createCollection(queryableCollection, role, joinType, fetch, true);
		setFromElement(elem);
		getWalker().addQuerySpaces(queryableCollection.getCollectionSpaces());	// Always add the collection's query spaces.
	}
