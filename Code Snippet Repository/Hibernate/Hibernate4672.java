	public static JoinType parse(int joinType) {
		if ( joinType < 0 ) {
			return NONE;
		}
		switch ( joinType ) {
			case 0:
				return INNER_JOIN;
			case 1:
				return LEFT_OUTER_JOIN;
			case 2:
				return RIGHT_OUTER_JOIN;
			case 4:
				return FULL_JOIN;
			default:
				throw new HibernateException( "unknown join type: " + joinType );
		}
	}
