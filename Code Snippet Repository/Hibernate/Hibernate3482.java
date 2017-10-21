	private void walkEntityAssociationTree(
			final AssociationType associationType,
			final OuterJoinLoadable persister,
			final int propertyNumber,
			final String alias,
			final PropertyPath path,
			final boolean nullable,
			final int currentDepth) throws MappingException {
		String[] aliasedLhsColumns = JoinHelper.getAliasedLHSColumnNames(
				associationType, alias, propertyNumber, persister, getFactory()
		);
		String[] lhsColumns = JoinHelper.getLHSColumnNames(
				associationType, propertyNumber, persister, getFactory()
		);
		String lhsTable = JoinHelper.getLHSTableName( associationType, propertyNumber, persister );

		PropertyPath subPath = path.append( persister.getSubclassPropertyName( propertyNumber ) );
		JoinType joinType = getJoinType(
				persister,
				subPath,
				propertyNumber,
				associationType,
				persister.getFetchMode( propertyNumber ),
				persister.getCascadeStyle( propertyNumber ),
				lhsTable,
				lhsColumns,
				nullable,
				currentDepth
		);
		addAssociationToJoinTreeIfNecessary(
				associationType,
				aliasedLhsColumns,
				alias,
				subPath,
				currentDepth,
				joinType
		);
	}
