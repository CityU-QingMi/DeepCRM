	private void decodeTransactionType(ParsedPersistenceXmlDescriptor persistenceUnit) {
		// if transaction type is set already
		// 		use that value
		// else
		//		if JTA DS
		//			use JTA
		//		else if NOT JTA DS
		//			use RESOURCE_LOCAL
		//		else
		//			use defaultTransactionType
		if ( persistenceUnit.getTransactionType() != null ) {
			return;
		}

		if ( persistenceUnit.getJtaDataSource() != null ) {
			persistenceUnit.setTransactionType( PersistenceUnitTransactionType.JTA );
		}
		else if ( persistenceUnit.getNonJtaDataSource() != null ) {
			persistenceUnit.setTransactionType( PersistenceUnitTransactionType.RESOURCE_LOCAL );
		}
		else {
			persistenceUnit.setTransactionType( defaultTransactionType );
		}
	}
