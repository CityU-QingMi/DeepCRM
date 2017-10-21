	@Test
	public void testInsertIntoSuperclassPropertiesFails() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		try {
			s.createQuery( "insert into Human (id, bodyWeight) select id, bodyWeight from Lizard" ).executeUpdate();
			fail( "superclass prop insertion did not error" );
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch( QueryException e ) {
			// expected result
		}

		t.commit();
		t = s.beginTransaction();

		s.createQuery( "delete Animal where mother is not null" ).executeUpdate();
		s.createQuery( "delete Animal where father is not null" ).executeUpdate();
		s.createQuery( "delete Animal" ).executeUpdate();

		t.commit();
		s.close();

		data.cleanup();
	}
