	@Test
	@TestForIssue( jiraKey = "" )
	public void testStaticMetamodel() {
		EntityManagerFactory emf = TestingEntityManagerFactoryGenerator.generateEntityManagerFactory(
				AvailableSettings.LOADED_CLASSES,
				Arrays.asList( Company.class )
		);
		try {
			assertNotNull( "'Company_.id' should not be null)", Company_.id );
			assertNotNull( "'Company_.address' should not be null)", Company_.address );

			assertNotNull( "'AbstractAddressable_.address' should not be null)", AbstractAddressable_.address );
		}
		finally {
			emf.close();
		}
	}
