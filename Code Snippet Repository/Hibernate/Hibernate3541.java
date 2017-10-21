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
		JoinType joinType = super.getJoinType(
				persister,
				path,
				propertyNumber,
				associationType,
				metadataFetchMode,
				metadataCascadeStyle,
				lhsTable,
				lhsColumns,
				nullable,
				currentDepth
		);
		//we can use an inner join for the many-to-many
		if ( joinType==JoinType.LEFT_OUTER_JOIN && path.isRoot() ) {
			joinType=JoinType.INNER_JOIN;
		}
		return joinType;
	}
