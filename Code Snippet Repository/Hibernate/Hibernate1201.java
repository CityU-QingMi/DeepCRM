	public static AccessType getAccessStrategy(javax.persistence.AccessType type) {
		if ( javax.persistence.AccessType.PROPERTY.equals( type ) ) {
			return PROPERTY;
		}
		else if ( javax.persistence.AccessType.FIELD.equals( type ) ) {
			return FIELD;
		}
		else {
			return DEFAULT;
		}
	}
