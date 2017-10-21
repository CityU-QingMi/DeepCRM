	@SuppressWarnings("")
	public static ParsedPersistenceXmlDescriptor locateNamedPersistenceUnit(
			URL persistenceXmlUrl,
			String name,
			PersistenceUnitTransactionType transactionType,
			Map integration) {
		assert StringHelper.isNotEmpty( name );

		final PersistenceXmlParser parser = new PersistenceXmlParser(
				ClassLoaderServiceImpl.fromConfigSettings( integration ),
				transactionType
		);

		final Map<String,ParsedPersistenceXmlDescriptor> persistenceUnits = parser.parsePersistenceXml( persistenceXmlUrl, integration );
		assert persistenceUnits.containsKey( name );

		return persistenceUnits.get( name );
	}
