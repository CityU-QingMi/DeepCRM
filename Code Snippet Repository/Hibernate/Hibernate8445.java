	@Test
	public void testRemoveThenSave() {
		Session s = openSession();
		s.beginTransaction();
		Item item = new Item();
		item.setName( "dummy" );
		s.persist( item );
		s.getTransaction().commit();
		s.close();

		Long id = item.getId();

		s = openSession();
		s.beginTransaction();
		item = ( Item ) s.get( Item.class, id );
		String sessionAsString = s.toString();

		s.delete( item );

		Item item2 = ( Item ) s.get( Item.class, id );
		assertNull( "expecting removed entity to be returned as null from get()", item2 );

		s.persist( item );
		assertEquals( "expecting session to be as it was before", sessionAsString, s.toString() );

		item.setName("Rescued");
		item = ( Item ) s.get( Item.class, id );
		assertNotNull( "expecting rescued entity to be returned from get()", item );

		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		item = ( Item ) s.get( Item.class, id );
		s.getTransaction().commit();
		s.close();

		assertNotNull( "expecting removed entity to be returned as null from get()", item );
		assertEquals("Rescued", item.getName());

		// clean up
		s = openSession();
		s.beginTransaction();
		s.delete( item );
		s.getTransaction().commit();
		s.close();
	}
