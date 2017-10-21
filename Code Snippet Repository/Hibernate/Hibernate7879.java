	@Test
	public void testQueryReturn() {
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder()
			.applySetting("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		StandardServiceRegistry standardServiceRegistry = serviceRegistryBuilder.build();
		MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
		try {
			metadataSources.addInputStream(new ReaderInputStream(new StringReader(QUERY_RETURN_HBM_XML)));
			Metadata metadata = metadataSources.buildMetadata();
			NamedSQLQueryDefinition myQuery = metadata.getNamedNativeQueryDefinition("myQuery");
			Assert.assertNotNull(myQuery);
			NativeSQLQueryReturn[] myQueryReturns = myQuery.getQueryReturns();
			Assert.assertNotNull(myQueryReturns);
			Assert.assertEquals(1, myQueryReturns.length);
			Assert.assertTrue(NativeSQLQueryRootReturn.class.isInstance(myQueryReturns[0]));
			NativeSQLQueryRootReturn myQueryRootReturn = (NativeSQLQueryRootReturn)myQueryReturns[0];
			Assert.assertEquals("e", myQueryRootReturn.getAlias());
			Assert.assertEquals("org.hibernate.test.hbm.query.QueryReturnTest$Bar", myQueryRootReturn.getReturnEntityName());
		}
		finally {
			if ( standardServiceRegistry instanceof StandardServiceRegistryImpl ) {
				( (StandardServiceRegistryImpl) standardServiceRegistry ).destroy();
			}
		}
	}
