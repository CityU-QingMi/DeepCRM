	@Test
	public void test_bootstrap_bootstrap_native_EntityManagerFactory_example() {

		try {
			//tag::bootstrap-native-EntityManagerFactory-example[]
			String persistenceUnitName = "CRM";
			List<String> entityClassNames = new ArrayList<>(  );
			Properties properties = new Properties(  );

			PersistenceUnitInfoImpl persistenceUnitInfo = new PersistenceUnitInfoImpl(
				persistenceUnitName,
				entityClassNames,
				properties
			);

			Map<String, Object> integrationSettings = new HashMap<>();
			integrationSettings.put(
				AvailableSettings.INTERCEPTOR,
				new CustomSessionFactoryInterceptor()
			);

			EntityManagerFactoryBuilderImpl entityManagerFactoryBuilder =
				new EntityManagerFactoryBuilderImpl(
					new PersistenceUnitInfoDescriptor( persistenceUnitInfo ),
					integrationSettings
				);

			EntityManagerFactory emf = entityManagerFactoryBuilder.build();
			//end::bootstrap-native-EntityManagerFactory-example[]
		}
		catch (Exception ignore) {
		}
	}
