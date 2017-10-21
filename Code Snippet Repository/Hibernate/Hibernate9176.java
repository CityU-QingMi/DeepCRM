	@Test
	@TestForIssue(jiraKey = "")
	public void testUnionSubclassEntityQuoting() {
		Session s = openSession();
		s.beginTransaction();
		Container container1 = new Container();
		Container container2 = new Container();
		SimpleItem simpleItem = new SimpleItem();
		
		container1.items.add( container2 );
		container1.items.add( simpleItem );
		container2.parent = container1;
		simpleItem.parent = container1;
		
		s.persist( simpleItem );
		s.persist( container2 );
		s.persist( container1 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Container result = s.get( Container.class, container1.id );
		assertNotNull( result );
		assertNotNull( result.items );
		assertEquals( 2, result.items.size() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		container1 = s.get( Container.class, container1.id );
		for ( Item item : container1.items ) {
			item.parent = null;
		}
		container1.items.clear();
		s.flush();
		s.createQuery( "delete Item" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
