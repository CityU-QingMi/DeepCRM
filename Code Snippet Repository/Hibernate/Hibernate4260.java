	@Override
	public String renderCallableStatement(
			String procedureName,
			ParameterStrategy parameterStrategy,
			List<ParameterRegistrationImplementor<?>> parameterRegistrations,
			SharedSessionContractImplementor session) {
		final StringBuilder buffer = new StringBuilder().append( "{call " )
				.append( procedureName )
				.append( "(" );
		String sep = "";
		for ( ParameterRegistrationImplementor parameter : parameterRegistrations ) {
			if ( parameter == null ) {
				throw new QueryException( "Parameter registrations had gaps" );
			}

			if ( parameter.getMode() == ParameterMode.REF_CURSOR ) {
				verifyRefCursorSupport( session.getJdbcServices().getJdbcEnvironment().getDialect() );
				buffer.append( sep ).append( "?" );
				sep = ",";
			}
			else {
				for ( int i = 0; i < parameter.getSqlTypes().length; i++ ) {
					buffer.append( sep ).append( "?" );
					sep = ",";
				}
			}
		}

		return buffer.append( ")}" ).toString();
	}
