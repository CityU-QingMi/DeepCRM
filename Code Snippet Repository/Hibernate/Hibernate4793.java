	public static SourceType interpret(Object value, SourceType defaultValue) {
		if ( value == null ) {
			return defaultValue;
		}

		if ( SourceType.class.isInstance( value ) ) {
			return (SourceType) value;
		}

		final String name = value.toString();
		if ( StringHelper.isEmpty( name ) ) {
			// empty is in fact valid as means to interpret default value based on other settings
			return defaultValue;
		}

		if ( METADATA.externalName.equals( value ) ) {
			return METADATA;
		}
		else if ( SCRIPT.externalName.equals( value ) ) {
			return SCRIPT;
		}
		else if ( METADATA_THEN_SCRIPT.externalName.equals( value ) ) {
			return METADATA_THEN_SCRIPT;
		}
		else if ( SCRIPT_THEN_METADATA.externalName.equals( value ) ) {
			return SCRIPT_THEN_METADATA;
		}

		throw new IllegalArgumentException( "Unrecognized schema generation source-type value : " + value );
	}
