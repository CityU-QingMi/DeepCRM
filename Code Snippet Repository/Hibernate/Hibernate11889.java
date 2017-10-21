	@Override
	public String toSqlString(Criteria criteria, int position, CriteriaQuery criteriaQuery) throws HibernateException {

		final SessionFactoryImplementor factory = criteriaQuery.getFactory();
		final String[] columns = criteriaQuery.getColumnsUsingProjection(
				criteria,
				this.propertyName
		);
		final Dialect dialect = factory.getDialect();
		if ( dialect instanceof SpatialDialect ) {
			final SpatialDialect seDialect = (SpatialDialect) dialect;

			return new StringBuffer(
					seDialect.getSpatialAggregateSQL(
							columns[0], this.aggregate
					)
			).append( " y" ).append( position )
					.append( "_" ).toString();
		}
		else {
			throw new IllegalStateException(
					"Dialect must be spatially enabled dialect"
			);
		}

	}
