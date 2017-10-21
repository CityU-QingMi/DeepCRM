	@Test
	@FailureExpected(jiraKey = "")
	public void testStaticMetamodelOverridden() {
		EntityManagerFactory emf = TestingEntityManagerFactoryGenerator.generateEntityManagerFactory(
				AvailableSettings.LOADED_CLASSES,
				Arrays.asList( Product2.class )
		);
		try {
			assertNotNull(
					"'Product1_.overridenName' should not be null)",
					Product1_.overridenName
			);

			assertNotNull(
					"'Product2_.overridenName' should not be null)",
					Product2_.overridenName
			); // is null
		}
		finally {
			emf.close();
		}
	}
