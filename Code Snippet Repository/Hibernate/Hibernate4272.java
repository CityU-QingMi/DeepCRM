	public static BuiltInPropertyAccessStrategies interpret(String name) {
		if ( BASIC.externalName.equals( name ) ) {
			return BASIC;
		}
		else if ( FIELD.externalName.equals( name ) ) {
			return FIELD;
		}
		else if ( MAP.externalName.equals( name ) ) {
			return MAP;
		}
		else if ( EMBEDDED.externalName.equals( name ) ) {
			return EMBEDDED;
		}
		else if ( NOOP.externalName.equals( name ) ) {
			return NOOP;
		}

		return null;
	}
