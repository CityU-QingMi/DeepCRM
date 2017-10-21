	@Test
	public void testBooleanHandling() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		// currently, we need the three different binds because they are different underlying types...
		int count = s.createQuery( "update BooleanLiteralEntity set yesNoBoolean = :b1, trueFalseBoolean = :b2, zeroOneBoolean = :b3" )
				.setBoolean( "b1", true )
				.setBoolean( "b2", true )
				.setBoolean( "b3", true )
				.executeUpdate();
		assertEquals( 1, count );
		BooleanLiteralEntity entity = ( BooleanLiteralEntity ) s.createQuery( "from BooleanLiteralEntity" ).uniqueResult();
		assertTrue( entity.isYesNoBoolean() );
		assertTrue( entity.isTrueFalseBoolean() );
		assertTrue( entity.isZeroOneBoolean() );
		s.clear();

		count = s.createQuery( "update BooleanLiteralEntity set yesNoBoolean = true, trueFalseBoolean = true, zeroOneBoolean = true" )
				.executeUpdate();
		assertEquals( 1, count );
		entity = ( BooleanLiteralEntity ) s.createQuery( "from BooleanLiteralEntity" ).uniqueResult();
		assertTrue( entity.isYesNoBoolean() );
		assertTrue( entity.isTrueFalseBoolean() );
		assertTrue( entity.isZeroOneBoolean() );

		t.commit();
		s.close();

		data.cleanup();
	}
