	@Test
	public void testStaticMetamodel() {
		EntityManagerFactory emf = TestingEntityManagerFactoryGenerator.generateEntityManagerFactory(
				AvailableSettings.LOADED_CLASSES,
				Arrays.asList( Product.class )
		);
		try {
			assertNotNull( "'Product_.id' should not be null)", Product_.id );
			assertNotNull( "'Product_.name' should not be null)", Product_.name );

			assertNotNull( "'AbstractNameable_.name' should not be null)", AbstractNameable_.name );
		}
		finally {
			emf.close();
		}
	}
