	private EnumValueMapper interpretParameters(Properties parameters) {
		if ( parameters.containsKey( NAMED ) ) {
			final boolean useNamed = ConfigurationHelper.getBoolean( NAMED, parameters );
			if ( useNamed ) {
				return new NamedEnumValueMapper();
			}
			else {
				return new OrdinalEnumValueMapper();
			}
		}

		if ( parameters.containsKey( TYPE ) ) {
			final int type = Integer.decode( (String) parameters.get( TYPE ) );
			if ( isNumericType( type ) ) {
				return new OrdinalEnumValueMapper();
			}
			else if ( isCharacterType( type ) ) {
				return new NamedEnumValueMapper();
			}
			else {
				throw new HibernateException(
						String.format(
								Locale.ENGLISH,
								"Passed JDBC type code [%s] not recognized as numeric nor character",
								type
						)
				);
			}
		}

		// the fallback
		return new OrdinalEnumValueMapper();
	}
