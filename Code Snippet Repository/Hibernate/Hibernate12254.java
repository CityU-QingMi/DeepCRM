	@Test
	@TestForIssue(jiraKey = "")
	@WithClasses(value = { Foo.class, BusinessEntity.class }, preCompile = BusinessId.class)
	@WithMappingFiles("")
	@IgnoreCompilationErrors
	public void testAttributeForEmbeddableConfiguredInXmlExists() {
		// need to work with the source file. BusinessEntity_.class won't get generated, because the business id won't
		// be on the classpath
		String entityMetaModel = getMetaModelSourceAsString( BusinessEntity.class );
		assertTrue( entityMetaModel.contains( "SingularAttribute<BusinessEntity, BusinessId<T>> businessId" ) );
	}
