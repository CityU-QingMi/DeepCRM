	@Test
	public void testMetaDataUseWithManyToAny() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		PropertyList list = new PropertyList( "sample" );
		StringProperty stringProperty = new StringProperty( "name", "Alex" );
		IntegerProperty integerProperty = new IntegerProperty( "age", 33 );
		LongProperty longProperty = new LongProperty( "distance", 121L );
		CharProperty charProp = new CharProperty( "Est", 'E' );

		list.setSomeProperty( longProperty );

		list.addGeneratedProperty( stringProperty );
		list.addGeneratedProperty( integerProperty );
		list.addGeneratedProperty( longProperty );
		list.addGeneratedProperty( charProp );

		s.save( list );

		s.flush();
		s.clear();

		Query q = s
				.createQuery( "SELECT list FROM PropertyList list WHERE list.name = :name" );
		q.setString( "name", "sample" );
		PropertyList<Property> actualList = (PropertyList<Property>) q
				.uniqueResult();

		assertNotNull( actualList );
		assertNotNull( actualList.getGeneralProperties() );
		assertEquals( 4, actualList.getGeneralProperties().size() );

		Property property = actualList.getSomeProperty();
		assertNotNull( property );
		assertTrue( property instanceof LongProperty );
		assertEquals( "121", property.asString() );

		assertEquals( "Alex", actualList.getGeneralProperties().get( 0 )
				.asString() );
		assertEquals( "33", actualList.getGeneralProperties().get( 1 ).asString() );
		assertEquals( "121", actualList.getGeneralProperties().get( 2 ).asString() );
		assertEquals( "E", actualList.getGeneralProperties().get( 3 ).asString() );

		t.rollback();
		s.close();
	}
