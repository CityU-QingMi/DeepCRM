	private static SQLExceptionConverter constructConverter(String converterClassName, ViolatedConstraintNameExtracter violatedConstraintNameExtracter) {
		try {
			LOG.tracev( "Attempting to construct instance of specified SQLExceptionConverter [{0}]", converterClassName );
			final Class converterClass = ReflectHelper.classForName( converterClassName );

			// First, try to find a matching constructor accepting a ViolatedConstraintNameExtracter param...
			final Constructor[] ctors = converterClass.getDeclaredConstructors();
			for ( Constructor ctor : ctors ) {
				if ( ctor.getParameterTypes() != null && ctor.getParameterCount() == 1 ) {
					if ( ViolatedConstraintNameExtracter.class.isAssignableFrom( ctor.getParameterTypes()[0] ) ) {
						try {
							return (SQLExceptionConverter) ctor.newInstance( violatedConstraintNameExtracter );
						}
						catch (Throwable ignore) {
							// eat it and try next
						}
					}
				}
			}

			// Otherwise, try to use the no-arg constructor
			return (SQLExceptionConverter) converterClass.newInstance();

		}
		catch (Throwable t) {
			LOG.unableToConstructSqlExceptionConverter( t );
		}

		return null;
	}
