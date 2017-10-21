	protected JoinType getJoinType(
			OuterJoinLoadable persister,
			final PropertyPath path,
			int propertyNumber,
			AssociationType associationType,
			FetchMode metadataFetchMode,
			CascadeStyle metadataCascadeStyle,
			String lhsTable,
			String[] lhsColumns,
			final boolean nullable,
			final int currentDepth) throws MappingException {
		return getJoinType(
				associationType,
				metadataFetchMode,
				path,
				lhsTable,
				lhsColumns,
				nullable,
				currentDepth,
				metadataCascadeStyle
		);
	}
