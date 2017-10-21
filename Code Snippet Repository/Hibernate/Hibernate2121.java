	@Override
	public ResultSet getResultSet(CallableStatement statement, int position) {
		if ( jdbcServices.getExtractedMetaDataSupport().supportsRefCursors() ) {
			try {
				return (ResultSet) getResultSetByPositionMethod().invoke( statement, position, ResultSet.class );
			}
			catch (InvocationTargetException e) {
				if ( e.getTargetException() instanceof SQLException ) {
					throw jdbcServices.getSqlExceptionHelper().convert(
							(SQLException) e.getTargetException(),
							"Error extracting REF_CURSOR parameter [" + position + "]"
					);
				}
				else {
					throw new HibernateException( "Unexpected error extracting REF_CURSOR parameter [" + position + "]", e.getTargetException() );
				}
			}
			catch (Exception e) {
				throw new HibernateException( "Unexpected error extracting REF_CURSOR parameter [" + position + "]", e );
			}
		}
		else {
			try {
				return jdbcServices.getDialect().getResultSet( statement, position );
			}
			catch (SQLException e) {
				throw jdbcServices.getSqlExceptionHelper().convert(
						e,
						"Error asking dialect to extract ResultSet from CallableStatement parameter [" + position + "]"
				);
			}
		}
	}
