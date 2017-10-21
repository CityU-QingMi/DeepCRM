	@Test
	public void testToOneAssocNotValidated() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Screen screen = new Screen();
		PowerSupply ps = new PowerSupply();
		ps.setPosition( "1" );
		ps.setPower( new BigDecimal( 350 ) );
		screen.setPowerSupply( ps );
		try {
			s.persist( screen );
			s.flush();
			fail( "Associated objects should not be validated" );
		}
		catch ( ConstraintViolationException e ) {
			assertEquals( 1, e.getConstraintViolations().size() );
			final ConstraintViolation constraintViolation = e.getConstraintViolations().iterator().next();
			assertEquals( PowerSupply.class, constraintViolation.getRootBeanClass() );
		}

		tx.rollback();
		s.close();
	}
