	public static PersistenceUnitTransactionType interpretTransactionType(Object value) {
		if ( value == null ) {
			return null;
		}

		if ( PersistenceUnitTransactionType.class.isInstance( value ) ) {
			return (PersistenceUnitTransactionType) value;
		}

		final String stringValue = value.toString();
		if ( StringHelper.isEmpty( stringValue ) ) {
			return null;
		}
		else if ( stringValue.equalsIgnoreCase( "JTA" ) ) {
			return PersistenceUnitTransactionType.JTA;
		}
		else if ( stringValue.equalsIgnoreCase( "RESOURCE_LOCAL" ) ) {
			return PersistenceUnitTransactionType.RESOURCE_LOCAL;
		}
		else {
			throw new PersistenceException( "Unknown TransactionType: " + stringValue );
		}
	}
