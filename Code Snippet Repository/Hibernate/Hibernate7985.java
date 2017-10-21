	@Test
	public void testInsertWithMismatchedTypes() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();
		try {
			s.createQuery( "insert into Pickup (owner, vin, id) select id, vin, owner from Car" ).executeUpdate();
			fail( "mismatched types did not error" );
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch( QueryException e ) {
			// expected result
		}

		t.commit();
		t = s.beginTransaction();

		s.createQuery( "delete Vehicle" ).executeUpdate();

		t.commit();
		s.close();

		data.cleanup();
	}
