	@Override
	public final void bind(CallableStatement st, J value, String name, WrapperOptions options) throws SQLException {
		final boolean traceEnabled = log.isTraceEnabled();
		if ( value == null ) {
			if ( traceEnabled ) {
				log.trace(
						String.format(
								NULL_BIND_MSG_TEMPLATE,
								name,
								JdbcTypeNameMapper.getTypeName( getSqlDescriptor().getSqlType() )
						)
				);
			}
			st.setNull( name, sqlDescriptor.getSqlType() );
		}
		else {
			if ( traceEnabled ) {
				log.trace(
						String.format(
								BIND_MSG_TEMPLATE,
								name,
								JdbcTypeNameMapper.getTypeName( sqlDescriptor.getSqlType() ),
								getJavaDescriptor().extractLoggableRepresentation( value )
						)
				);
			}
			doBind( st, value, name, options );
		}
	}
