		protected final int determineJdbcTypeCode(Type type, Mapping mapping) throws QueryException {
			try {
				final int[] jdbcTypeCodes = type.sqlTypes( mapping );
				if ( jdbcTypeCodes.length != 1 ) {
					throw new QueryException( "multiple-column type in sum()" );
				}
				return jdbcTypeCodes[0];
			}
			catch ( MappingException me ) {
				throw new QueryException( me );
			}
		}
