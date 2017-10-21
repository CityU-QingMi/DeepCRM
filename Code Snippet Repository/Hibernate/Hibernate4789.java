	public static UniqueConstraintSchemaUpdateStrategy interpret(Object setting) {
		log.tracef( "Interpreting UniqueConstraintSchemaUpdateStrategy from setting : %s", setting );

		if ( setting == null ) {
			// default
			return DROP_RECREATE_QUIETLY;
		}

		if ( UniqueConstraintSchemaUpdateStrategy.class.isInstance( setting ) ) {
			return (UniqueConstraintSchemaUpdateStrategy) setting;
		}

		try {
			final UniqueConstraintSchemaUpdateStrategy byName = byName( setting.toString() );
			if ( byName != null ) {
				return byName;
			}
		}
		catch ( Exception ignore ) {
		}

		log.debugf( "Unable to interpret given setting [%s] as UniqueConstraintSchemaUpdateStrategy", setting );

		// default
		return DROP_RECREATE_QUIETLY;
	}
