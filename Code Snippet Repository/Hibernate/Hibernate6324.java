	@Test
	public void testNonLazyAssocFieldWithConstraintsFailureExpected() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Screen screen = new Screen();
		screen.setPowerSupply( null );
		try {
			s.persist( screen );
			s.flush();
			fail( "@NotNull on a non lazy association is not evaluated" );
		}
		catch ( ConstraintViolationException e ) {
			assertEquals( 1, e.getConstraintViolations().size() );
		}

		tx.rollback();
		s.close();
	}
