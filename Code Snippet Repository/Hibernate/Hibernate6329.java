	@Test
	public void testAssocInEmbeddedNotValidated() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Screen screen = new Screen();
		screen.setStopButton( new Button() );
		screen.getStopButton().setName( "STOOOOOP" );
		PowerSupply ps = new PowerSupply();
		screen.setPowerSupply( ps );
		DisplayConnector conn = new DisplayConnector();
		conn.setNumber( 1 );
		screen.getConnectors().add( conn );
		final Display display = new Display();
		display.setBrand( "dell" );
		conn.setDisplay( display );
		s.persist( display );
		s.flush();
		try {
			display.setBrand( null );
			s.persist( screen );
			s.flush();
			fail( "Collection of embedded objects should be validated" );
		}
		catch ( ConstraintViolationException e ) {
			assertEquals( 1, e.getConstraintViolations().size() );
			final ConstraintViolation constraintViolation = e.getConstraintViolations().iterator().next();
			assertEquals( Display.class, constraintViolation.getRootBeanClass() );
		}

		tx.rollback();
		s.close();
	}
