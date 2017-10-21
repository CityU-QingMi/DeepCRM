	private void addAssociationToJoinTreeIfNecessary(
			final AssociationType type,
			final String[] aliasedLhsColumns,
			final String alias,
			final PropertyPath path,
			int currentDepth,
			final JoinType joinType) throws MappingException {
		if ( joinType != JoinType.NONE ) {
			addAssociationToJoinTree(
					type,
					aliasedLhsColumns,
					alias,
					path,
					currentDepth,
					joinType
			);
		}
	}
