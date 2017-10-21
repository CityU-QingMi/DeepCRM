	public void addJoin(String tableName, String alias, String[] fkColumns, String[] pkColumns, JoinType joinType, String on) {
		//arbitrary on clause ignored!!
		addJoin( tableName, alias, fkColumns, pkColumns, joinType );
		if ( joinType == JoinType.INNER_JOIN ) {
			addCondition( on );
		}
		else if ( joinType == JoinType.LEFT_OUTER_JOIN ) {
			addLeftOuterJoinCondition( on );
		}
		else {
			throw new UnsupportedOperationException( "join type not supported by OracleJoinFragment (use Oracle9iDialect/Oracle10gDialect)" );
		}
	}
