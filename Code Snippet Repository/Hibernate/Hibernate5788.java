	@Test
	public void testContainerBootstrapValidationMode() {
		// first, via the integration vars
		PersistenceUnitInfoAdapter empty = new PersistenceUnitInfoAdapter();
		{
			// as object
			EntityManagerFactoryBuilderImpl builder = (EntityManagerFactoryBuilderImpl) Bootstrap.getEntityManagerFactoryBuilder(
					empty,
					Collections.singletonMap( AvailableSettings.VALIDATION_MODE, ValidationMode.CALLBACK )
			);
			assertEquals( ValidationMode.CALLBACK, builder.getConfigurationValues().get( AvailableSettings.VALIDATION_MODE ) );
		}
		{
			// as string
			EntityManagerFactoryBuilderImpl builder = (EntityManagerFactoryBuilderImpl) Bootstrap.getEntityManagerFactoryBuilder(
					empty,
					Collections.singletonMap( AvailableSettings.VALIDATION_MODE, ValidationMode.CALLBACK.name() )
			);
			assertEquals( ValidationMode.CALLBACK.name(), builder.getConfigurationValues().get( AvailableSettings.VALIDATION_MODE ) );
		}

		// next, via the PUI
		PersistenceUnitInfoAdapter adapter = new PersistenceUnitInfoAdapter() {
			@Override
			public ValidationMode getValidationMode() {
				return ValidationMode.CALLBACK;
			}
		};
		{
			EntityManagerFactoryBuilderImpl builder = (EntityManagerFactoryBuilderImpl) Bootstrap.getEntityManagerFactoryBuilder(
					adapter,
					null
			);
			assertEquals( ValidationMode.CALLBACK, builder.getConfigurationValues().get( AvailableSettings.VALIDATION_MODE ) );
		}

		// via both, integration vars should take precedence
		{
			EntityManagerFactoryBuilderImpl builder = (EntityManagerFactoryBuilderImpl) Bootstrap.getEntityManagerFactoryBuilder(
					adapter,
					Collections.singletonMap( AvailableSettings.VALIDATION_MODE, ValidationMode.NONE )
			);
			assertEquals( ValidationMode.NONE, builder.getConfigurationValues().get( AvailableSettings.VALIDATION_MODE ) );
		}
	}
