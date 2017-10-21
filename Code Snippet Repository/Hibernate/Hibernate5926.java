	@Test
	@TestForIssue( jiraKey = "" )
	public void testStaticMetamodel() {
		EntityManagerFactory emf = TestingEntityManagerFactoryGenerator.generateEntityManagerFactory(
				AvailableSettings.LOADED_CLASSES,
				Arrays.asList( Product.class )
		);

		try {
			assertNotNull( "'Product_.description' should not be null)", Product_.description );
			assertNotNull( "'Product_.id' should not be null)", Product_.id );

			assertNotNull( "'AbstractProduct_.id' should not be null)", AbstractProduct_.id );

			assertNotNull( "'ProductId_.id' should not be null)", ProductId_.id );
			assertNotNull( "'ProductId_.code' should not be null)", ProductId_.code );
		}
		finally {
			emf.close();
		}
	}
