	@Test
	public void testSimpleCaseStatementWithParamAllResults() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		try {
			s.createQuery( "select case p.name when 'Steve' then :opt1 else :opt2 end from Person p" )
					.setString( "opt1", "x" )
					.setString( "opt2", "y" )
					.list();
			fail( "was expecting an exception" );
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch (QueryException expected) {
			// expected
		}

		s.createQuery( "select case p.name when 'Steve' then cast( :opt1 as string ) else cast( :opt2 as string) end from Person p" )
				.setString( "opt1", "x" )
				.setString( "opt2", "y" )
				.list();

		t.commit();
		s.close();
	}
