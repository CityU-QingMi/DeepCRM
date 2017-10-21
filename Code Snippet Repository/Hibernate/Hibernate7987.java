	@Test
	public void testInsertAcrossMappedJoinFails() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		try {
			s.createQuery( "insert into Joiner (name, joinedName) select vin, owner from Car" ).executeUpdate();
			fail( "mapped-join insertion did not error" );
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch( QueryException e ) {
			// expected result
		}

		t.commit();
		t = s.beginTransaction();

		s.createQuery( "delete Joiner" ).executeUpdate();
		s.createQuery( "delete Vehicle" ).executeUpdate();

		t.commit();
		s.close();

		data.cleanup();
	}
