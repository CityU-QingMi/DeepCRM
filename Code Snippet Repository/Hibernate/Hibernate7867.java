	@Test
	public void testIdentityColumnGeneratedIds() {
		Session s = openSession();
		s.beginTransaction();
		MyEntity myEntity = new MyEntity( "test" );
		Long id = ( Long ) s.save( myEntity );
		assertNotNull( "identity column did not force immediate insert", id );
		assertEquals( id, myEntity.getId() );
		s.delete( myEntity );
		s.getTransaction().commit();
		s.close();
	}
