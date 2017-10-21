	@Override
	public ResultSet getResultSet(CallableStatement statement, String name) {
		if ( jdbcServices.getExtractedMetaDataSupport().supportsRefCursors() ) {
			try {
				return (ResultSet) getResultSetByNameMethod().invoke( statement, name, ResultSet.class );
			}
			catch (InvocationTargetException e) {
				if ( e.getTargetException() instanceof SQLException ) {
					throw jdbcServices.getSqlExceptionHelper().convert(
							(SQLException) e.getTargetException(),
							"Error extracting REF_CURSOR parameter [" + name + "]"
					);
				}
				else {
					throw new HibernateException( "Unexpected error extracting REF_CURSOR parameter [" + name + "]", e.getTargetException() );
				}
			}
			catch (Exception e) {
				throw new HibernateException( "Unexpected error extracting REF_CURSOR parameter [" + name + "]", e );
			}
		}
		else {
			try {
				return jdbcServices.getDialect().getResultSet( statement, name );
			}
			catch (SQLException e) {
				throw jdbcServices.getSqlExceptionHelper().convert(
						e,
						"Error asking dialect to extract ResultSet from CallableStatement parameter [" + name + "]"
				);
			}
		}
	}
