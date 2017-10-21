	@Override
	public J extract(CallableStatement statement, String[] paramNames, WrapperOptions options) throws SQLException {
		if ( paramNames.length > 1 ) {
			throw new IllegalArgumentException( "Basic value extraction cannot handle multiple output parameters" );
		}
		final String paramName = paramNames[0];
		final J value = doExtract( statement, paramName, options );
		final boolean traceEnabled = log.isTraceEnabled();
		if ( value == null || statement.wasNull() ) {
			if ( traceEnabled ) {
				log.tracef(
						"extracted named procedure output  parameter ([%s] : [%s]) - [null]",
						paramName,
						JdbcTypeNameMapper.getTypeName( getSqlDescriptor().getSqlType() )
				);
			}
			return null;
		}
		else {
			if ( traceEnabled ) {
				log.tracef(
						"extracted named procedure output  parameter ([%s] : [%s]) - [%s]",
						paramName,
						JdbcTypeNameMapper.getTypeName( getSqlDescriptor().getSqlType() ),
						getJavaDescriptor().extractLoggableRepresentation( value )
				);
			}
			return value;
		}
	}
