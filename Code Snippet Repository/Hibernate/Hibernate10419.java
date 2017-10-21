	@Test
	@TestForIssue(jiraKey = "")
	public void testRemoval() {
		EntityManager em = getEntityManager();

		final Component3 comp1 = new Component3( "comp1", null, null );
		final Component3 comp2 = new Component3( "comp2", null, null );

		EmbeddableSetEntity entity = new EmbeddableSetEntity();

		em.getTransaction().begin();

		entity.getComponentSet().add( comp1 );
		entity.getComponentSet().add( comp2 );

		em.persist( entity );

		em.getTransaction().commit();

		em.getTransaction().begin();

		entity.getComponentSet().remove( comp1 );

		em.getTransaction().commit();

		EmbeddableSetEntity rev1 = getAuditReader().find( EmbeddableSetEntity.class, entity.getId(), 1 );
		EmbeddableSetEntity rev2 = getAuditReader().find( EmbeddableSetEntity.class, entity.getId(), 2 );
		assertEquals( "Unexpected components", TestTools.makeSet( comp1, comp2 ), rev1.getComponentSet() );
		assertEquals( "Unexpected components", TestTools.makeSet( comp2 ), rev2.getComponentSet() );
	}
