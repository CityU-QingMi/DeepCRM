	@SuppressWarnings( {""})
	@Test
	public void testBasicOperation() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Entity entity = new Entity( "tester" );
		entity.getValues().add( "value-1" );
		s.persist( entity );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		entity = ( Entity ) s.get( Entity.class, "tester" );
		assertTrue( Hibernate.isInitialized( entity.getValues() ) );
		assertEquals( 1, entity.getValues().size() );
        assertEquals( "Hello", ( ( DefaultableList ) entity.getValues() ).getDefaultValue() );
		s.delete( entity );
		t.commit();
		s.close();
	}
