	public static Map<String,ParsedPersistenceXmlDescriptor> parse(
			URL persistenceXmlUrl,
			PersistenceUnitTransactionType transactionType,
			Map integration) {
		PersistenceXmlParser parser = new PersistenceXmlParser(
				ClassLoaderServiceImpl.fromConfigSettings( integration ),
				transactionType
		);

		return parser.doResolve( integration );
	}
