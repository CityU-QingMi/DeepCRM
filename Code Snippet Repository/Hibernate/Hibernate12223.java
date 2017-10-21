	@Test
	@TestForIssue(jiraKey = "")
	@WithClasses(TestEntity.class)
	@WithProcessorOption(key = JPAMetaModelEntityProcessor.ADD_GENERATION_DATE, value = "")
	public void testGeneratedAnnotationGenerated() {
		assertMetamodelClassGeneratedFor( TestEntity.class );

		// need to check the source because @Generated is not a runtime annotation
		String metaModelSource = getMetaModelSourceAsString( TestEntity.class );

		dumpMetaModelSourceFor( TestEntity.class );
		String generatedString = "@Generated(value = \"org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor\", date = \"";

		assertTrue( "@Generated should also contain the date parameter.", metaModelSource.contains( generatedString ) );
	}
