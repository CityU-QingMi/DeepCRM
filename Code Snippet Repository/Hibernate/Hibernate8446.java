	@Test
	public void testRemoveThenSaveWithCascades() {
		Session s = openSession();
		s.beginTransaction();

		Item item = new Item();
		item.setName( "dummy" );
		Part part = new Part(item, "child", "1234", BigDecimal.ONE);

		// persist cascades to part
		s.persist( item );

		// delete cascades to part also
		s.delete( item );
		assertFalse( "the item is contained in the session after deletion", s.contains( item ) );
		assertFalse( "the part is contained in the session after deletion", s.contains( part ) );

		// now try to persist again as a "unschedule removal" operation
		s.persist( item );
		assertTrue( "the item is contained in the session after deletion", s.contains( item ) );
		assertTrue( "the part is contained in the session after deletion", s.contains( part ) );

		s.getTransaction().commit();
		s.close();

		// clean up
		s = openSession();
		s.beginTransaction();
		s.delete( item );
		s.getTransaction().commit();
		s.close();
	}
