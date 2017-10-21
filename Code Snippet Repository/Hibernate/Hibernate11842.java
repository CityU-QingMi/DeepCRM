	@Override
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		final String column = ExpressionUtil.findColumn( propertyName, criteria, criteriaQuery );
		final SpatialDialect spatialDialect = ExpressionUtil.getSpatialDialect(
				criteriaQuery,
				SpatialFunction.dwithin
		);
		return spatialDialect.getDWithinSQL( column );

	}
