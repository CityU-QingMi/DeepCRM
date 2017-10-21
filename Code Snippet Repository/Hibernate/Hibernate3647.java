	protected JoinType getJoinType(
			OuterJoinLoadable persister,
			PropertyPath path,
			int propertyNumber,
			AssociationType associationType,
			FetchMode metadataFetchMode,
			CascadeStyle metadataCascadeStyle,
			String lhsTable,
			String[] lhsColumns,
			boolean nullable,
			int currentDepth) throws MappingException {
		// NOTE : we override this form here specifically to account for
		// fetch profiles.
		// TODO : how to best handle criteria queries?
		if ( lockOptions.getLockMode().greaterThan( LockMode.READ ) ) {
			return JoinType.NONE;
		}
		if ( isTooDeep( currentDepth )
				|| ( associationType.isCollectionType() && isTooManyCollections() ) ) {
			return JoinType.NONE;
		}
		if ( !isJoinedFetchEnabledInMapping( metadataFetchMode, associationType )
				&& !isJoinFetchEnabledByProfile( persister, path, propertyNumber ) ) {
			return JoinType.NONE;
		}
		if ( isDuplicateAssociation( lhsTable, lhsColumns, associationType ) ) {
			return JoinType.NONE;
		}
		return getJoinType( nullable, currentDepth );
	}
