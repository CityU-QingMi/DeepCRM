	@Test
	@TestForIssue( jiraKey = "" )
	public void testStaticMetamodel() {
		EntityManagerFactory emf = TestingEntityManagerFactoryGenerator.generateEntityManagerFactory(
				AvailableSettings.LOADED_CLASSES,
				Arrays.asList( ProductAttribute.class )
		);

		try {
			assertNotNull( "'ProductAttribute_.value' should not be null)", ProductAttribute_.value );
			assertNotNull( "'ProductAttribute_.owner' should not be null)", ProductAttribute_.owner );
			assertNotNull( "'ProductAttribute_.key' should not be null)", ProductAttribute_.key );

			assertNotNull( "'AbstractAttribute_.value' should not be null)", AbstractAttribute_.value );
		}
		finally {
			emf.close();
		}
	}
