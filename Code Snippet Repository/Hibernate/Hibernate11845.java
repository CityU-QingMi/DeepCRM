	@Override
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		final SessionFactoryImplementor factory = criteriaQuery.getFactory();
		final String[] columns = criteriaQuery.getColumnsUsingProjection( criteria, this.propertyName );
		final Dialect dialect = factory.getDialect();
		if ( dialect instanceof SpatialDialect ) {
			final SpatialDialect seDialect = (SpatialDialect) dialect;
			return seDialect.getSpatialFilterExpression( columns[0] );
		}
		else {
			throw new IllegalStateException( "Dialect must be spatially enabled dialect" );
		}
	}
