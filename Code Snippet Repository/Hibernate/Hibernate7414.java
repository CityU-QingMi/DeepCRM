	@Test
	public void testSerializationOnAfterStatementAggressiveRelease() throws Throwable {
		prepare();
		try {
			Session s = getSessionUnderTest();
			Silly silly = new Silly( "silly" );
			s.save( silly );

			// this should cause the CM to obtain a connection, and then release it
			s.flush();

			// We should be able to serialize the session at this point...
			SerializationHelper.serialize( s );

			s.delete( silly );
			s.flush();

			release( s );
		}
		finally {
			done();
		}
	}
