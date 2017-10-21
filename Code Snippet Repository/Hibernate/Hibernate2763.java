	public static JoinType toHibernateJoinType(int astJoinType) {
		switch ( astJoinType ) {
			case LEFT_OUTER: {
				return JoinType.LEFT_OUTER_JOIN;
			}
			case INNER: {
				return JoinType.INNER_JOIN;
			}
			case RIGHT_OUTER: {
				return JoinType.RIGHT_OUTER_JOIN;
			}
			case FULL: {
				return JoinType.FULL_JOIN;
			}
			default: {
				throw new AssertionFailure( "undefined join type " + astJoinType );
			}
		}
	}
