	@Test
	public void testDuplicatedDiscriminatorValueSameHierarchy() {
		try {
			tryBuildingSessionFactory( Building.class, Building1.class, Building2.class );
			Assert.fail( MappingException.class.getName() + " expected when two subclasses are mapped with the same discriminator value." );
		}
		catch ( MappingException e ) {
			final String errorMsg = e.getCause().getMessage();
			// Check if error message contains descriptive information.
			Assert.assertTrue( errorMsg.contains( Building1.class.getName() ) );
			Assert.assertTrue( errorMsg.contains( Building2.class.getName() ) );
			Assert.assertTrue( errorMsg.contains( "discriminator value '" + DISCRIMINATOR_VALUE + "'." ) );
		}

		assertFalse( SessionFactoryRegistry.INSTANCE.hasRegistrations() );
	}
