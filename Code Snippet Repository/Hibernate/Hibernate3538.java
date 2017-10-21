	public void addManyToManyJoin(JoinFragment outerjoin, QueryableCollection collection) throws MappingException {
		String manyToManyFilter = collection.getManyToManyFilterFragment( rhsAlias, enabledFilters );
		String condition = "".equals( manyToManyFilter )
				? on
				: "".equals( on )
				? manyToManyFilter
				: on + " and " + manyToManyFilter;
		outerjoin.addJoin(
				joinable.getTableName(),
				rhsAlias,
				lhsColumns,
				rhsColumns,
				joinType,
				condition
		);
		outerjoin.addJoins(
				joinable.fromJoinFragment( rhsAlias, false, true ),
				joinable.whereJoinFragment( rhsAlias, false, true )
		);
	}
