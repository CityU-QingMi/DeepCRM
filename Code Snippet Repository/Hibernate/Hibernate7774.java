	@Test
	public void testAllSeparateInOne() {
		Metadata metadata = new MetadataSources( serviceRegistry )
				.addResource( getBaseForMappings() + "extendshbm/allseparateinone.hbm.xml" )
				.buildMetadata();

		assertNotNull( metadata.getEntityBinding( "org.hibernate.test.extendshbm.Customer" ) );
		assertNotNull( metadata.getEntityBinding( "org.hibernate.test.extendshbm.Person" ) );
		assertNotNull( metadata.getEntityBinding( "org.hibernate.test.extendshbm.Employee" ) );
	}
