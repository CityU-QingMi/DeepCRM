	@SuppressWarnings("")
	public static Criterion SDOWithinDistance(String propertyName, Geometry geom, SDOParameterMap param) {
		return new OracleSpatialCriterion( propertyName, geom, param ) {

			@Override
			public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
				final String[] columns = criteriaQuery.getColumnsUsingProjection( criteria, this.propertyName );
				final StringBuilder sql = new StringBuilder( "SDO_WITHIN_DISTANCE(" );
				sql.append( columns[0] ).append( "," ).append( "?" );
				if ( param != null && !param.isEmpty() ) {
					sql.append( "," ).append( param.toQuotedString() );
				}
				sql.append( ") = 'TRUE'" );
				return sql.toString();
			}
		};
	}
