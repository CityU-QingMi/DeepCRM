    @Test
	public void testMixPackageAndResourceOrdering() throws Exception {
		try {
			Configuration config = new Configuration();
			config.addResource( "org/hibernate/test/annotations/configuration/orm.xml" );
			config.addPackage( "org.hibernate.test.annotations.configuration" );
		}
		catch( Exception e ) {
            Assert.fail( "Processing package first when ORM.xml is used should not fail" );
		}
	}
