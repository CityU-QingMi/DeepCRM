	public static SpatialDialect getSpatialDialect(CriteriaQuery criteriaQuery, SpatialFunction function) {
		final Dialect dialect = criteriaQuery.getFactory().getDialect();
		if ( !( dialect instanceof SpatialDialect ) ) {
			throw new HibernateException( "A spatial expression requires a spatial dialect." );
		}
		final SpatialDialect spatialDialect = (SpatialDialect) dialect;
		if ( !spatialDialect.supports( function ) ) {
			throw new HibernateException( function + " function not supported by this dialect" );
		}
		return spatialDialect;
	}
