	@Test
	public void testUpdateSetNullUnionSubclass() {
		TestData data = new TestData();
		data.prepare();

		// These should reach out into *all* subclass tables...
		Session s = openSession();
		Transaction t = s.beginTransaction();

		int count = s.createQuery( "update Vehicle set owner = 'Steve'" ).executeUpdate();
		assertEquals( "incorrect restricted update count", 4, count );
		count = s.createQuery( "update Vehicle set owner = null where owner = 'Steve'" ).executeUpdate();
		assertEquals( "incorrect restricted update count", 4, count );

		try {
			count = s.createQuery( "delete Vehicle where owner is null" ).executeUpdate();
			assertEquals( "incorrect restricted delete count", 4, count );
		}
		catch ( AssertionFailedError afe ) {
			if ( H2Dialect.class.isInstance( getDialect() ) ) {
				// http://groups.google.com/group/h2-database/t/5548ff9fd3abdb7
				// this is fixed in H2 1.2.140
				count = s.createQuery( "delete Vehicle" ).executeUpdate();
				assertEquals( "incorrect count", 4, count );
			}
			else {
				throw afe;
			}
		}

		t.commit();
		s.close();

		data.cleanup();
	}
