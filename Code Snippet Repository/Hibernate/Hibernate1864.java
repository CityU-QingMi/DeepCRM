		protected final int determineJdbcTypeCode(Type firstArgumentType, SessionFactoryImplementor factory) throws QueryException {
			try {
				final int[] jdbcTypeCodes = firstArgumentType.sqlTypes( factory );
				if ( jdbcTypeCodes.length != 1 ) {
					throw new QueryException( "multiple-column type in avg()" );
				}
				return jdbcTypeCodes[0];
			}
			catch ( MappingException me ) {
				throw new QueryException( me );
			}
		}
