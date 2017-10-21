	private String renderJoinType(JoinType joinType) {
		switch ( joinType ) {
			case INNER: {
				return " inner join ";
			}
			case LEFT: {
				return " left join ";
			}
			case RIGHT: {
				return " right join ";
			}
		}
		throw new IllegalStateException( "Unknown join type " + joinType );
	}
