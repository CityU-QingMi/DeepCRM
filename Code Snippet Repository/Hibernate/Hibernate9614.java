	@Test
	public void testInsert() {
		Session s = openSession();
		s.getTransaction().begin();
		Entity e = new Entity( "name" );
		s.save( e );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		e = ( Entity ) s.get( Entity.class, e.getId() );
		assertFalse( e.getName().startsWith( StoredPrefixedStringType.PREFIX ) );
		assertEquals( "name", e.getName() );
		s.delete( e );
		s.getTransaction().commit();
		s.close();
	}
