	@Test
	public void testClassComment() {
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder()
				.applySetting("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		MetadataSources metadataSources = new MetadataSources(serviceRegistryBuilder.build());
		metadataSources.addInputStream(new ReaderInputStream(new StringReader(CLASS_COMMENT_HBM_XML)));
		Metadata metadata = metadataSources.buildMetadata();
		PersistentClass pc = metadata.getEntityBinding("org.hibernate.test.hbm.Foo");
		Assert.assertNotNull(pc);
		Table table = pc.getTable();
		Assert.assertNotNull(table);
		Assert.assertEquals("This is class 'Foo' with property 'bar'.", table.getComment());
	}	
