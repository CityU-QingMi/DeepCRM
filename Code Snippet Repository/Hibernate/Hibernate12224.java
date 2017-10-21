	@Test
	@TestForIssue(jiraKey = "")
	@WithClasses(TestEntity.class)
	@WithProcessorOption(key = JPAMetaModelEntityProcessor.ADD_GENERATED_ANNOTATION, value = "")
	public void testGeneratedAnnotationGenerated() {
		assertMetamodelClassGeneratedFor( TestEntity.class );

		// need to check the source because @Generated is not a runtime annotation
		String metaModelSource = getMetaModelSourceAsString( TestEntity.class );
		assertFalse( "@Generated should not be added to the metamodel.", metaModelSource.contains( "@Generated" ) );
	}
