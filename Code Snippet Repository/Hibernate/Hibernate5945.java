	@Test
	@TestForIssue(jiraKey = "")
	public void ensureAttributeForEmbeddableIsGeneratedInMappedSuperClass() {
		EmbeddableType<WorkOrderComponentId> woci = entityManagerFactory().getMetamodel()
				.embeddable( WorkOrderComponentId.class );
		assertThat( woci, notNullValue() );
		assertThat( woci.getAttribute( "workOrder" ), notNullValue() );
		assertThat( woci.getAttribute( "plantId" ), notNullValue() );
		assertThat( woci.getAttribute( "lineNumber" ), notNullValue() );
	}
