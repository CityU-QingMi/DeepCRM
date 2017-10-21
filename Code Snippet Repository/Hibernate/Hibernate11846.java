	public static Projection extent(final String propertyName) {
		return new SimpleProjection() {

			public Type[] getTypes(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
				return new Type[] {
						criteriaQuery.getType( criteria, propertyName )
				};
			}

			public String toSqlString(Criteria criteria, int position, CriteriaQuery criteriaQuery)
					throws HibernateException {
				final StringBuilder stbuf = new StringBuilder();

				final SessionFactoryImplementor factory = criteriaQuery.getFactory();
				final String[] columns = criteriaQuery.getColumnsUsingProjection( criteria, propertyName );
				final Dialect dialect = factory.getDialect();
				if ( dialect instanceof SpatialDialect ) {
					final SpatialDialect seDialect = (SpatialDialect) dialect;
					stbuf.append(
							seDialect.getSpatialAggregateSQL( columns[0], SpatialAggregate.EXTENT )
					);
					stbuf.append( " as y" ).append( position ).append( '_' );
					return stbuf.toString();
				}
				return null;
			}

		};

	}
