	@Test
	public void testDeleteWithSubquery() {
		// setup the test data...
		Session s = openSession();
		s.beginTransaction();
		SimpleEntityWithAssociation owner = new SimpleEntityWithAssociation( "myEntity-1" );
		owner.addAssociation( "assoc-1" );
		owner.addAssociation( "assoc-2" );
		owner.addAssociation( "assoc-3" );
		s.save( owner );
		SimpleEntityWithAssociation owner2 = new SimpleEntityWithAssociation( "myEntity-2" );
		owner2.addAssociation( "assoc-1" );
		owner2.addAssociation( "assoc-2" );
		owner2.addAssociation( "assoc-3" );
		owner2.addAssociation( "assoc-4" );
		s.save( owner2 );
		SimpleEntityWithAssociation owner3 = new SimpleEntityWithAssociation( "myEntity-3" );
		s.save( owner3 );
		s.getTransaction().commit();
		s.close();

		// now try the bulk delete
		s = openSession();
		s.beginTransaction();
		int count = s.createQuery( "delete SimpleEntityWithAssociation e where size( e.associatedEntities ) = 0 and e.name like '%'" ).executeUpdate();
		assertEquals( "incorrect delete count", 1, count );
		s.getTransaction().commit();
		s.close();

		// finally, clean up
		s = openSession();
		s.beginTransaction();
		s.createQuery( "delete SimpleAssociatedEntity" ).executeUpdate();
		s.createQuery( "delete SimpleEntityWithAssociation" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
