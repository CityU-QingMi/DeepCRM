	@Test
	public void testMissingSuper() {
		try {
			Metadata metadata = new MetadataSources( serviceRegistry )
					.addResource( getBaseForMappings() + "extendshbm/Customer.hbm.xml" )
					.addResource( getBaseForMappings() + "extendshbm/Employee.hbm.xml" )
					.buildMetadata();
			fail( "Should not be able to build sessionFactory without a Person" );
		}
		catch ( HibernateException e ) {
		}
	}
