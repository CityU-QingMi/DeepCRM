	@Override
	public Object extract(CallableStatement statement, int startIndex, SharedSessionContractImplementor session) throws SQLException {
		Object[] values = new Object[propertySpan];

		int currentIndex = startIndex;
		boolean notNull = false;
		for ( int i = 0; i < propertySpan; i++ ) {
			// we know this cast is safe from canDoExtraction
			final Type propertyType = propertyTypes[i];
			final Object value = ((ProcedureParameterExtractionAware) propertyType).extract(
					statement,
					currentIndex,
					session
			);
			if ( value == null ) {
				if ( isKey ) {
					return null; //different nullability rules for pk/fk
				}
			}
			else {
				notNull = true;
			}
			values[i] = value;
			currentIndex += propertyType.getColumnSpan( session.getFactory() );
		}

		if ( !notNull ) {
			values = null;
		}

		return resolve( values, session, null );
	}
