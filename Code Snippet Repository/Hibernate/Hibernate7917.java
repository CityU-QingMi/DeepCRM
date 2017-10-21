	@Test
	@FailureExpected( jiraKey = "" )
	public void testParameterTypeMismatch() {
		Session s = openSession();
		s.beginTransaction();

		Query query = s.createQuery( "from Animal a where a.description = :nonstring" )
				.setParameter( "nonstring", Integer.valueOf( 1 ) );
		try {
			query.list();
			fail( "query execution should have failed" );
		}
		catch (IllegalArgumentException e) {
			assertTyping( TypeMismatchException.class, e.getCause() );
		}
		catch( TypeMismatchException tme ) {
			// expected behavior
		}

		s.getTransaction().commit();
		s.close();
	}
