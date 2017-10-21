	@Test
	public void testDefaultAnyAssociation() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		PropertySet set1 = new PropertySet( "string" );
		Property property = new StringProperty( "name", "Alex" );
		set1.setSomeProperty( property );
		set1.addGeneratedProperty( property );
		s.save( set1 );

		PropertySet set2 = new PropertySet( "integer" );
		property = new IntegerProperty( "age", 33 );
		set2.setSomeProperty( property );
		set2.addGeneratedProperty( property );
		s.save( set2 );

		s.flush();
		s.clear();

		Query q = s
				.createQuery( "select s from PropertySet s where name = :name" );
		q.setString( "name", "string" );
		PropertySet result = (PropertySet) q.uniqueResult();

		assertNotNull( result );
		assertNotNull( result.getSomeProperty() );
		assertTrue( result.getSomeProperty() instanceof StringProperty );
		assertEquals( "Alex", result.getSomeProperty().asString() );
		assertNotNull( result.getGeneralProperties() );
		assertEquals( 1, result.getGeneralProperties().size() );
		assertEquals( "Alex", result.getGeneralProperties().get( 0 ).asString() );

		q.setString( "name", "integer" );
		result = (PropertySet) q.uniqueResult();
		assertNotNull( result );
		assertNotNull( result.getSomeProperty() );
		assertTrue( result.getSomeProperty() instanceof IntegerProperty );
		assertEquals( "33", result.getSomeProperty().asString() );
		assertNotNull( result.getGeneralProperties() );
		assertEquals( 1, result.getGeneralProperties().size() );
		assertEquals( "33", result.getGeneralProperties().get( 0 ).asString() );

		t.rollback();
		s.close();
	}
