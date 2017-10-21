	@Test
	@TestForIssue(jiraKey = "")
	@FailureExpected(jiraKey = "")
	public void testRemoval() {
		EntityManager em = getEntityManager();

		final Component comp1 = new Component( null, 11 );
		final Component comp2 = new Component( null, 22 );

		final CompositeCustomTypeSetEntity entity = new CompositeCustomTypeSetEntity();
		entity.setComponents( new HashSet<Component>() );
		entity.getComponents().add( comp1 );
		entity.getComponents().add( comp2 );

		em.getTransaction().begin();
		em.persist( entity );
		em.getTransaction().commit();

		em.getTransaction().begin();
		entity.getComponents().remove( comp1 );
		em.getTransaction().commit();

		CompositeCustomTypeSetEntity rev1 = getAuditReader().find( CompositeCustomTypeSetEntity.class, entity.getId(), 1 );
		CompositeCustomTypeSetEntity rev2 = getAuditReader().find( CompositeCustomTypeSetEntity.class, entity.getId(), 2 );
		assertEquals( "Unexpected components", TestTools.makeSet( comp1, comp2 ), rev1.getComponents() );
		assertEquals( "Unexpected components", TestTools.makeSet( comp2 ), rev2.getComponents() );

	}
