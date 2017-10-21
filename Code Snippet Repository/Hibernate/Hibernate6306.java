	@Test
	public void testManyToAnyWithMap() throws Exception {

		Session s = openSession();
		Transaction t = s.beginTransaction();

		PropertyMap map = new PropertyMap( "sample" );
		map.getProperties().put( "name", new StringProperty( "name", "Alex" ) );
		map.getProperties().put( "age", new IntegerProperty( "age", 33 ) );

		s.save( map );

		s.flush();
		s.clear();

		Query q = s
				.createQuery( "SELECT map FROM PropertyMap map WHERE map.name = :name" );
		q.setString( "name", "sample" );
		PropertyMap actualMap = (PropertyMap) q.uniqueResult();

		assertNotNull( actualMap );
		assertNotNull( actualMap.getProperties() );

		Property property = actualMap.getProperties().get( "name" );
		assertNotNull( property );
		assertTrue( property instanceof StringProperty );
		assertEquals( "Alex", property.asString() );

		property = actualMap.getProperties().get( "age" );
		assertNotNull( property );
		assertTrue( property instanceof IntegerProperty );
		assertEquals( "33", property.asString() );

		t.rollback();
		s.close();

	}
