	private Map<String,ParsedPersistenceXmlDescriptor> parsePersistenceXml(URL xmlUrl, Map integration) {
		LOG.tracef( "Attempting to parse persistence.xml file : %s", xmlUrl.toExternalForm() );

		final Document doc = loadUrl( xmlUrl );
		final Element top = doc.getDocumentElement();

		final Map<String,ParsedPersistenceXmlDescriptor> persistenceUnits = new ConcurrentHashMap<>();

		final NodeList children = top.getChildNodes();
		for ( int i = 0; i < children.getLength() ; i++ ) {
			if ( children.item( i ).getNodeType() == Node.ELEMENT_NODE ) {
				final Element element = (Element) children.item( i );
				final String tag = element.getTagName();
				if ( tag.equals( "persistence-unit" ) ) {
					final URL puRootUrl = ArchiveHelper.getJarURLFromURLEntry( xmlUrl, "/META-INF/persistence.xml" );
					ParsedPersistenceXmlDescriptor persistenceUnit = new ParsedPersistenceXmlDescriptor( puRootUrl );
					bindPersistenceUnit( persistenceUnit, element );

					if ( persistenceUnits.containsKey( persistenceUnit.getName() ) ) {
						LOG.duplicatedPersistenceUnitName( persistenceUnit.getName() );
						continue;
					}

					// per JPA spec, any settings passed in to PersistenceProvider bootstrap methods should override
					// values found in persistence.xml
					if ( integration.containsKey( AvailableSettings.JPA_PERSISTENCE_PROVIDER ) ) {
						persistenceUnit.setProviderClassName( (String) integration.get( AvailableSettings.JPA_PERSISTENCE_PROVIDER ) );
					}
					if ( integration.containsKey( AvailableSettings.JPA_TRANSACTION_TYPE ) ) {
						String transactionType = (String) integration.get( AvailableSettings.JPA_TRANSACTION_TYPE );
						persistenceUnit.setTransactionType( parseTransactionType( transactionType ) );
					}
					if ( integration.containsKey( AvailableSettings.JPA_JTA_DATASOURCE ) ) {
						persistenceUnit.setJtaDataSource( integration.get( AvailableSettings.JPA_JTA_DATASOURCE ) );
					}
					if ( integration.containsKey( AvailableSettings.JPA_NON_JTA_DATASOURCE ) ) {
						persistenceUnit.setNonJtaDataSource( integration.get( AvailableSettings.JPA_NON_JTA_DATASOURCE ) );
					}

					decodeTransactionType( persistenceUnit );

					Properties properties = persistenceUnit.getProperties();
					ConfigurationHelper.overrideProperties( properties, integration );

					persistenceUnits.put( persistenceUnit.getName(), persistenceUnit );
				}
			}
		}
		return persistenceUnits;
	}
