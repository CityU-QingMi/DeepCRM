	@Override
	public final void bind(PreparedStatement st, J value, int index, WrapperOptions options) throws SQLException {
		final boolean traceEnabled = log.isTraceEnabled();
		if ( value == null ) {
			if ( traceEnabled ) {
				log.trace(
						String.format(
								NULL_BIND_MSG_TEMPLATE,
								index,
								JdbcTypeNameMapper.getTypeName( getSqlDescriptor().getSqlType() )
						)
				);
			}
			st.setNull( index, sqlDescriptor.getSqlType() );
		}
		else {
			if ( traceEnabled ) {
				log.trace(
						String.format(
								BIND_MSG_TEMPLATE,
								index,
								JdbcTypeNameMapper.getTypeName( sqlDescriptor.getSqlType() ),
								getJavaDescriptor().extractLoggableRepresentation( value )
						)
				);
			}
			doBind( st, value, index, options );
		}
	}
