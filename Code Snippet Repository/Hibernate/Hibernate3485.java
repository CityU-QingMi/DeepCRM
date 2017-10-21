	protected JoinType getJoinType(boolean nullable, int currentDepth) {
		//TODO: this is too conservative; if all preceding joins were 
		//      also inner joins, we could use an inner join here
		//
		// IMPL NOTE : currentDepth might be less-than zero if this is the
		// 		root of a many-to-many collection initializer 
		return !nullable && currentDepth <= 0
				? JoinType.INNER_JOIN
				: JoinType.LEFT_OUTER_JOIN;
	}
