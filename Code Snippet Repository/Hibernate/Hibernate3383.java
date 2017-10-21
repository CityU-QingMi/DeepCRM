	public static EntityManagerFactoryBuilder getEntityManagerFactoryBuilder(
			URL persistenceXmlUrl,
			String persistenceUnitName,
			PersistenceUnitTransactionType transactionType,
			Map integration) {
		;
		return new EntityManagerFactoryBuilderImpl(
				PersistenceXmlParser.parse( persistenceXmlUrl, transactionType, integration ).get( persistenceUnitName ),
				integration
		);
	}
