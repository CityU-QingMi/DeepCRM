	@Override
	@SuppressWarnings("")
	public T extract(CallableStatement statement) {
		if ( mode == ParameterMode.IN ) {
			throw new ParameterMisuseException( "IN parameter not valid for output extraction" );
		}

		// TODO: sqlTypesToUse.length > 1 does not seem to have a working use case (HHH-10769).
		// For now, if sqlTypes.length > 1 with a named parameter, then extract
		// parameter values by position (since we only have one name).
		final boolean useNamed = sqlTypes.length == 1 &&
				procedureCall.getParameterStrategy() == ParameterStrategy.NAMED &&
				canDoNameParameterBinding();

		try {
			if ( ProcedureParameterExtractionAware.class.isInstance( hibernateType ) ) {
				if ( useNamed ) {
					return (T) ( (ProcedureParameterExtractionAware) hibernateType ).extract(
							statement,
							new String[] { getName() },
							session()
					);
				}
				else {
					return (T) ( (ProcedureParameterExtractionAware) hibernateType ).extract(
							statement,
							startIndex,
							session()
					);
				}
			}
			else {
				if ( useNamed ) {
					return (T) statement.getObject( name );
				}
				else {
					return (T) statement.getObject( startIndex );
				}
			}
		}
		catch (SQLException e) {
			throw procedureCall.getSession().getFactory().getSQLExceptionHelper().convert(
					e,
					"Unable to extract OUT/INOUT parameter value"
			);
		}
	}
