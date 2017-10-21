	@Test
	public void testCollectionAssocNotValidated() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Screen screen = new Screen();
		screen.setStopButton( new Button() );
		screen.getStopButton().setName( "STOOOOOP" );
		PowerSupply ps = new PowerSupply();
		screen.setPowerSupply( ps );
		Color c = new Color();
		c.setName( "Blue" );
		s.persist( c );
		c.setName( null );
		screen.getDisplayColors().add( c );
		try {
			s.persist( screen );
			s.flush();
			fail( "Associated objects should not be validated" );
		}
		catch ( ConstraintViolationException e ) {
			assertEquals( 1, e.getConstraintViolations().size() );
			final ConstraintViolation constraintViolation = e.getConstraintViolations().iterator().next();
			assertEquals( Color.class, constraintViolation.getRootBeanClass() );
		}

		tx.rollback();
		s.close();
	}
