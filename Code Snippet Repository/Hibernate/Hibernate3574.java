	@Override
	protected JoinType getJoinType(
			AssociationType associationType,
			FetchMode config,
			PropertyPath path,
			String lhsTable,
			String[] lhsColumns,
			boolean nullable,
			int currentDepth,
			CascadeStyle cascadeStyle) throws MappingException {
		return getJoinType(
				null,
				path,
				-1,
				associationType,
				config,
				cascadeStyle,
				lhsTable,
				lhsColumns,
				nullable,
				currentDepth
		);
	}
