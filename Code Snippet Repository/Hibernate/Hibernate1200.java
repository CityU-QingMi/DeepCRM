	public static AccessType getAccessStrategy(String externalName) {
		if ( externalName == null ) {
			return DEFAULT;
		}
		else if ( FIELD.getType().equals( externalName ) ) {
			return FIELD;
		}
		else if ( PROPERTY.getType().equals( externalName ) ) {
			return PROPERTY;
		}
		else {
			// TODO historically if the externalName string could not be matched default access was used. Maybe this should be an exception though!?
			return DEFAULT;
		}
	}
