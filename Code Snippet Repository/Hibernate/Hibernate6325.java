	@Test
	public void testEmbedded() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Screen screen = new Screen();
		PowerSupply ps = new PowerSupply();
		screen.setPowerSupply( ps );
		Button button = new Button();
		button.setName( null );
		button.setSize( 3 );
		screen.setStopButton( button );
		try {
			s.persist( screen );
			s.flush();
			fail( "@NotNull on embedded property is not evaluated" );
		}
		catch ( ConstraintViolationException e ) {
			assertEquals( 1, e.getConstraintViolations().size() );
			ConstraintViolation<?> cv = e.getConstraintViolations().iterator().next();
			assertEquals( Screen.class, cv.getRootBeanClass() );
			// toString works since hibernate validator's Path implementation works accordingly. Should do a Path comparison though
			assertEquals( "stopButton.name", cv.getPropertyPath().toString() );
		}

		tx.rollback();
		s.close();
	}
