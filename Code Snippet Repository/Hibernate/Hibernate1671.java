	@Override
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
		return '('
				+ lhs.toSqlString( criteria, criteriaQuery )
				+ ' '
				+ getOp()
				+ ' '
				+ rhs.toSqlString( criteria, criteriaQuery )
				+ ')';
	}
