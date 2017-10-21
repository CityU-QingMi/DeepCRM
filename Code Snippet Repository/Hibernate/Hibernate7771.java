	@Test
	public void testOutOfOrder() {
		Metadata metadata = new MetadataSources( serviceRegistry )
				.addResource( getBaseForMappings() + "extendshbm/Customer.hbm.xml" )
				.addResource( getBaseForMappings() + "extendshbm/Person.hbm.xml" )
				.addResource( getBaseForMappings() + "extendshbm/Employee.hbm.xml" )
				.buildMetadata();

		assertNotNull( metadata.getEntityBinding( "org.hibernate.test.extendshbm.Customer" ) );
		assertNotNull( metadata.getEntityBinding( "org.hibernate.test.extendshbm.Person" ) );
		assertNotNull( metadata.getEntityBinding( "org.hibernate.test.extendshbm.Employee" ) );
	}
