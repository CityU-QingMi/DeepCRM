	@Override
	public Object extract(CallableStatement statement, String[] paramNames, SharedSessionContractImplementor session)
			throws SQLException {
		// for this form to work all sub-property spans must be one (1)...

		Object[] values = new Object[propertySpan];

		int indx = 0;
		boolean notNull = false;
		for ( String paramName : paramNames ) {
			// we know this cast is safe from canDoExtraction
			final ProcedureParameterExtractionAware propertyType = (ProcedureParameterExtractionAware) propertyTypes[indx];
			final Object value = propertyType.extract( statement, new String[] {paramName}, session );
			if ( value == null ) {
				if ( isKey ) {
					return null; //different nullability rules for pk/fk
				}
			}
			else {
				notNull = true;
			}
			values[indx] = value;
		}

		if ( !notNull ) {
			values = null;
		}

		return resolve( values, session, null );
	}
