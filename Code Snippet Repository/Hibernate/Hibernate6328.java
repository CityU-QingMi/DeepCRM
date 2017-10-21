	@Test
	public void testEmbeddedCollection() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Screen screen = new Screen();
		PowerSupply ps = new PowerSupply();
		screen.setPowerSupply( ps );
		DisplayConnector conn = new DisplayConnector();
		conn.setNumber( 0 );
		screen.getConnectors().add( conn );
		try {
			s.persist( screen );
			s.flush();
			fail( "Collection of embedded objects should be validated" );
		}
		catch ( ConstraintViolationException e ) {
			assertEquals( 1, e.getConstraintViolations().size() );
			final ConstraintViolation constraintViolation = e.getConstraintViolations().iterator().next();
			assertEquals( Screen.class, constraintViolation.getRootBeanClass() );
			// toString works since hibernate validator's Path implementation works accordingly. Should do a Path comparison though
			assertEquals( "connectors[].number", constraintViolation.getPropertyPath().toString() );
		}

		tx.rollback();
		s.close();
	}
