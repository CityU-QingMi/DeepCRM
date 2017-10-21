	@Test
	public void testImportFile() throws Exception {
		Session s = openSession();
		final Transaction tx = s.beginTransaction();
		final List<?> humans = s.createQuery( "from " + Human.class.getName() ).list();
		assertEquals( "humans.sql not imported", 3, humans.size() );

		final List<?> dogs = s.createQuery( "from " + Dog.class.getName() ).list();
		assertEquals( "dogs.sql not imported", 3, dogs.size() );
		for ( Object entity : dogs ) {
			s.delete( entity );
		}
		for ( Object entity : humans ) {
			s.delete( entity );
		}
		tx.commit();
		s.close();
	}
