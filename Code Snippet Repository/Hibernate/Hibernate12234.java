	@Test
	@TestForIssue(jiraKey = "")
	@WithClasses(TestEntity.class)
	public void testSuppressedWarningsAnnotationNotGenerated() {
		assertMetamodelClassGeneratedFor( TestEntity.class );

		// need to check the source because @SuppressWarnings is not a runtime annotation
		String metaModelSource = getMetaModelSourceAsString( TestEntity.class );
		assertFalse(
				"@SuppressWarnings should not be added to the metamodel.",
				metaModelSource.contains( "@SuppressWarnings(\"all\")" )
		);
	}
