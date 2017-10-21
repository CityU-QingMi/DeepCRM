	@Test
	public void testTimeStampFunctions() {
		// add an entity just so it can be queried.

		Session s=openSession();
		Transaction tx = s.beginTransaction();
		s.persist( new Entity() );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();

		// current_timestamp(), localtime(), and localtimestamp() are synonyms for now(),
		// which returns the time at which the statement began to execute.
		// the returned values for now(), current_timestamp(), localtime(), and
		// localtimestamp() should be the same.
		// sysdate() is the time at which the function itself is executed, so the
		// value returned for sysdate() should be different.
		Query q=s.createQuery(
				"select now(), current_timestamp(), localtime(), localtimestamp(), sysdate() from MySQL57TimestampFspFunctionTest$Entity"
		);
		Object[] oArray = (Object[]) q.uniqueResult();
		final Timestamp now = (Timestamp) oArray[0];
		assertEquals( now, oArray[1] );
		assertEquals( now, oArray[2] );
		assertEquals( now, oArray[3] );
		final Timestamp sysdate = (Timestamp) oArray[4];
		assertTrue( now.compareTo( sysdate ) < 0 );
		// all should have nanos > 0
		assertTrue( now.getNanos() > 0 );
		assertTrue( sysdate.getNanos() > 0 );

		tx.commit();
		s.close();
	}
