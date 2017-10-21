	public static SQLExceptionConverter buildSQLExceptionConverter(Dialect dialect, Properties properties) throws HibernateException {
		SQLExceptionConverter converter = null;

		String converterClassName = (String) properties.get( Environment.SQL_EXCEPTION_CONVERTER );
		if ( StringHelper.isNotEmpty( converterClassName ) ) {
			converter = constructConverter( converterClassName, dialect.getViolatedConstraintNameExtracter() );
		}

		if ( converter == null ) {
			LOG.trace( "Using dialect defined converter" );
			converter = dialect.buildSQLExceptionConverter();
		}

		if ( converter instanceof Configurable ) {
			try {
				( (Configurable) converter ).configure( properties );
			}
			catch (HibernateException e) {
				LOG.unableToConfigureSqlExceptionConverter( e );
				throw e;
			}
		}

		return converter;
	}
