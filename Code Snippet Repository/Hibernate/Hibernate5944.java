	@Test
	@TestForIssue(jiraKey = "")
	public void ensureAttributeForEmbeddableIsGeneratedInMappedSuperClass() {
		EmbeddableType<EmbeddableEntity> embeddableType = entityManagerFactory().getMetamodel()
				.embeddable( EmbeddableEntity.class );

		Attribute<?, ?> attribute = embeddableType.getAttribute( "foo" );
		assertNotNull( attribute );

		ManagedType<AbstractEntity> managedType = entityManagerFactory().getMetamodel().managedType( AbstractEntity.class );
		assertNotNull( managedType );

		attribute = managedType.getAttribute( "embedded" );
		assertNotNull( attribute );
	}
