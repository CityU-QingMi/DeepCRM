	@Test
	@TestForIssue(jiraKey = "")
	@WithClasses(TestEntity.class)
	@WithProcessorOption(key = JPAMetaModelEntityProcessor.ADD_SUPPRESS_WARNINGS_ANNOTATION, value = "")
	public void testSuppressedWarningsAnnotationGenerated() {
		assertMetamodelClassGeneratedFor( TestEntity.class );

		// need to check the source because @SuppressWarnings is not a runtime annotation
		String metaModelSource = getMetaModelSourceAsString( TestEntity.class );
		assertTrue(
				"@SuppressWarnings should be added to the metamodel.",
				metaModelSource.contains( "@SuppressWarnings(\"all\")" )
		);
	}
