	@Test
	public void doTest() {
		final MetadataSources metadataSources = new MetadataSources();
		try {
			applySources( metadataSources );

			final Metadata metadata = metadataSources.getMetadataBuilder()
					.applyImplicitNamingStrategy( getImplicitNamingStrategyToUse() )
					.build();

			validateCustomer( metadata );
			validateOrder( metadata );
			validateZipCode( metadata );

			validateCustomerRegisteredTrademarks( metadata );
			validateCustomerAddresses( metadata );
			validateCustomerOrders( metadata );
			validateCustomerIndustries( metadata );
		}
		finally {
			ServiceRegistry metaServiceRegistry = metadataSources.getServiceRegistry();
			if(metaServiceRegistry instanceof BootstrapServiceRegistry ) {
				BootstrapServiceRegistryBuilder.destroy( metaServiceRegistry );
			}
		}
	}
