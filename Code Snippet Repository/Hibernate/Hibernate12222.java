	@Test
	@TestForIssue(jiraKey = "")
	@WithClasses(TestEntity.class)
	public void testGeneratedAnnotationNotGenerated() {
		assertMetamodelClassGeneratedFor( TestEntity.class );

		// need to check the source because @Generated is not a runtime annotation
		String metaModelSource = getMetaModelSourceAsString( TestEntity.class );
		String generatedString = "@Generated(value = \"org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor\")";
		assertTrue( "@Generated should be added to the metamodel.", metaModelSource.contains( generatedString ) );
	}
