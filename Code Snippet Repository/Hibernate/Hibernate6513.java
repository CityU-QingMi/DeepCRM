	@Test
	@TestForIssue(jiraKey="")
	public void testAttributeOverride() {
		EmbeddableB embedB = new EmbeddableB();
		embedB.setEmbedAttrB( "B" );

		EmbeddableA embedA = new EmbeddableA();
		embedA.setEmbedAttrA("A");
		embedA.setEmbedB(embedB);
		
		EntityWithNestedEmbeddables entity = new EntityWithNestedEmbeddables();
		entity.setEmbedA(embedA);
		
		Session s = openSession();
		s.beginTransaction();
		s.persist( entity );
		s.getTransaction().commit();
		s.close();
	}
