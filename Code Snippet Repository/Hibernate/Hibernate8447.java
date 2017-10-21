	@Test
	public void testRemoveChildThenFlushWithCascadePersist() {
		Session s = openSession();
		s.beginTransaction();

		Item item = new Item();
		item.setName( "dummy" );
		Part child = new Part(item, "child", "1234", BigDecimal.ONE);

		// persist cascades to part
		s.persist( item );

		// delete the part
		s.delete( child );
		assertFalse("the child is contained in the session, since it is deleted", s.contains(child) );

		// now try to flush, which will attempt to cascade persist again to child.
		s.flush();
		assertTrue("Now it is consistent again since if was cascade-persisted by the flush()", s.contains(child));

		s.getTransaction().commit();
		s.close();

		// clean up
		s = openSession();
		s.beginTransaction();
		s.delete( item );
		s.getTransaction().commit();
		s.close();
	}
