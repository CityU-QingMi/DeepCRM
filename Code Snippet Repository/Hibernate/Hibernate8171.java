	@Test
	@TestForIssue(jiraKey = "")
	public void testWithJoinRHS() {
		Session s = openSession();
		s.beginTransaction();

		SimpleEntityWithAssociation entity1 = new SimpleEntityWithAssociation();
		entity1.setName( "entity1" );
		SimpleEntityWithAssociation entity2 = new SimpleEntityWithAssociation();
		entity2.setName( "entity2" );
		
		SimpleAssociatedEntity associatedEntity1 = new SimpleAssociatedEntity();
		associatedEntity1.setName( "associatedEntity1" );
		SimpleAssociatedEntity associatedEntity2 = new SimpleAssociatedEntity();
		associatedEntity2.setName( "associatedEntity2" );
		
		entity1.addAssociation( associatedEntity1 );
		entity2.addAssociation( associatedEntity2 );
		
		s.persist( entity1 );
		s.persist( entity2 );
		
		s.getTransaction().commit();
		s.clear();
		
		s.beginTransaction();
		
		Query query = s.createQuery( "select a from SimpleEntityWithAssociation as e INNER JOIN e.associatedEntities as a WITH e.name=?" );
		query.setParameter( 0, "entity1" );
		List list = query.list();
		assertEquals( list.size(), 1 );
		
		SimpleAssociatedEntity associatedEntity = (SimpleAssociatedEntity) query.list().get( 0 );
		assertNotNull( associatedEntity );
		assertEquals( associatedEntity.getName(), "associatedEntity1" );
		assertEquals( associatedEntity.getOwner().getName(), "entity1" );
		
		s.getTransaction().commit();
		s.close();
	}
