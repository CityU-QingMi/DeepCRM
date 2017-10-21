	@Test
	public void testOrm1Support() {
		Triggerable triggerable = logInspection.watchForLogMessages( "HHH00196" );

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Light light = new Light();
		light.name = "the light at the end of the tunnel";
		s.persist( light );
		s.flush();
		s.clear();

		assertEquals( 1, s.getNamedQuery( "find.the.light" ).list().size() );
		tx.rollback();
		s.close();

		assertFalse( triggerable.wasTriggered() );
	}
