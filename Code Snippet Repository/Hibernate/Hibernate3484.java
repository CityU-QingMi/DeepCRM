	protected JoinType getJoinType(
			AssociationType associationType,
			FetchMode config,
			PropertyPath path,
			String lhsTable,
			String[] lhsColumns,
			boolean nullable,
			int currentDepth,
			CascadeStyle cascadeStyle) throws MappingException {
		if ( !isJoinedFetchEnabled( associationType, config, cascadeStyle ) ) {
			return JoinType.NONE;
		}
		if ( isTooDeep( currentDepth ) || ( associationType.isCollectionType() && isTooManyCollections() ) ) {
			return JoinType.NONE;
		}
		if ( isDuplicateAssociation( lhsTable, lhsColumns, associationType ) ) {
			return JoinType.NONE;
		}
		return getJoinType( nullable, currentDepth );
	}
