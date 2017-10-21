	public void addJoin(
			String tableName,
			String alias,
			String[] fkColumns,
			String[] pkColumns,
			JoinType joinType,
			String on) {
		addJoin( tableName, alias, fkColumns, pkColumns, joinType );
		addCondition( on );
	}
