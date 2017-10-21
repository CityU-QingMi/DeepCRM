	@Override
	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		final SpatialDialect spatialDialect = ExpressionUtil.getSpatialDialect( criteriaQuery, SpatialFunction.dwithin );
		TypedValue typedDistanceValue = new TypedValue( StandardBasicTypes.DOUBLE, distance );
		if ( spatialDialect instanceof OracleSpatial10gDialect) {
			typedDistanceValue = new TypedValue( StandardBasicTypes.STRING, "distance=" + distance );
		}
		return new TypedValue[] {
				criteriaQuery.getTypedValue( criteria, propertyName, geometry ),
				typedDistanceValue
		};
	}
