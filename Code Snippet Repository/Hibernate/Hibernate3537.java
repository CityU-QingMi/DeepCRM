	public void addJoins(JoinFragment outerjoin) throws MappingException {
		outerjoin.addJoin(
				joinable.getTableName(),
				rhsAlias,
				lhsColumns,
				rhsColumns,
				joinType,
				on
		);
		outerjoin.addJoins(
				joinable.fromJoinFragment( rhsAlias, false, true ),
				joinable.whereJoinFragment( rhsAlias, false, true )
		);
	}
