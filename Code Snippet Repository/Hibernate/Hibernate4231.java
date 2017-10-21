	@Override
	public String renderCallableStatement(
			String procedureName,
			ParameterStrategy parameterStrategy,
			List<ParameterRegistrationImplementor<?>> parameterRegistrations,
			SharedSessionContractImplementor session) {
		// if there are any parameters, see if the first is REF_CURSOR
		final boolean firstParamIsRefCursor = ! parameterRegistrations.isEmpty()
				&& parameterRegistrations.get( 0 ).getMode() == ParameterMode.REF_CURSOR;

		if ( firstParamIsRefCursor ) {
			// validate that the parameter strategy is positional (cannot mix, and REF_CURSOR is inherently positional)
			if ( parameterStrategy == ParameterStrategy.NAMED ) {
				throw new HibernateException( "Cannot mix named parameters and REF_CURSOR parameter on PostgreSQL" );
			}
		}

		final StringBuilder buffer;
		if ( firstParamIsRefCursor ) {
			buffer = new StringBuilder().append( "{? = call " );
		}
		else {
			buffer = new StringBuilder().append( "{call " );
		}

		buffer.append( procedureName ).append( "(" );

		String sep = "";

		// skip the first registration if it was a REF_CURSOR
		final int startIndex = firstParamIsRefCursor ? 1 : 0;
		for ( int i = startIndex; i < parameterRegistrations.size(); i++ ) {
			final ParameterRegistrationImplementor parameter = parameterRegistrations.get( i );

			// any additional REF_CURSOR parameter registrations are an error
			if ( parameter.getMode() == ParameterMode.REF_CURSOR ) {
				throw new HibernateException( "PostgreSQL supports only one REF_CURSOR parameter, but multiple were registered" );
			}

			for ( int ignored : parameter.getSqlTypes() ) {
				buffer.append( sep ).append( "?" );
				sep = ",";
			}
		}

		return buffer.append( ")}" ).toString();
	}
