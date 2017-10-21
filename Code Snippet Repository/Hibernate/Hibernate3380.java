	private static PersistenceUnitTransactionType parseTransactionType(String value) {
		if ( StringHelper.isEmpty( value ) ) {
			return null;
		}
		else if ( value.equalsIgnoreCase( "JTA" ) ) {
			return PersistenceUnitTransactionType.JTA;
		}
		else if ( value.equalsIgnoreCase( "RESOURCE_LOCAL" ) ) {
			return PersistenceUnitTransactionType.RESOURCE_LOCAL;
		}
		else {
			throw new PersistenceException( "Unknown persistence unit transaction type : " + value );
		}
	}
