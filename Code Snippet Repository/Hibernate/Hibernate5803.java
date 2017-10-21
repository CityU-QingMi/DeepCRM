	@Test
	public void testNamingStrategyFromProperty() {

		// configure NamingStrategy
		{
			PersistenceUnitInfoAdapter adapter = new PersistenceUnitInfoAdapter();
			EntityManagerFactoryBuilderImpl builder = (EntityManagerFactoryBuilderImpl) Bootstrap.getEntityManagerFactoryBuilder(
					adapter,
					Collections.singletonMap( AvailableSettings.PHYSICAL_NAMING_STRATEGY, MyNamingStrategy.class.getName() )
			);
			final EntityManagerFactory emf = builder.build();
			try {
				assertEquals(
						MyNamingStrategy.class.getName(),
						builder.getConfigurationValues().get( AvailableSettings.PHYSICAL_NAMING_STRATEGY )
				);

				assertTyping(
						MyNamingStrategy.class,
						builder.getMetadata().getMetadataBuildingOptions().getPhysicalNamingStrategy()
				);
			}finally {
				if(emf != null){
					emf.close();
				}
			}
		}
	}
