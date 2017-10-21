	@SuppressWarnings("")
	public static ParsedPersistenceXmlDescriptor locateIndividualPersistenceUnit(
			URL persistenceXmlUrl,
			PersistenceUnitTransactionType transactionType,
			Map integration) {
		final PersistenceXmlParser parser = new PersistenceXmlParser(
				ClassLoaderServiceImpl.fromConfigSettings( integration ),
				transactionType
		);

		final Map<String,ParsedPersistenceXmlDescriptor> persistenceUnits = parser.parsePersistenceXml( persistenceXmlUrl, integration );
		assert persistenceUnits.size() == 1;

		return persistenceUnits.values().iterator().next();
	}
