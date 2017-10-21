	@Test
	public void testMappedSuperclassAndSecondaryTable() throws Exception {
		Session s = openSession( );
		s.getTransaction().begin();
		C c = new C();
		c.setAge( 12 );
		c.setCreateDate( new Date() );
		c.setName( "Bob" );
		s.persist( c );
		s.flush();
		s.clear();
		c= (C) s.get( C.class, c.getId() );
		assertNotNull( c.getCreateDate() );
		assertNotNull( c.getName() );
		s.getTransaction().rollback();
		s.close();
	}
