	@Test
	public void testDecimalLiterals() {
		// note: simply performing syntax checking in the db
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( "from Animal where bodyWeight > 100.0e-10" ).list();
		s.createQuery( "from Animal where bodyWeight > 100.0E-10" ).list();
		s.createQuery( "from Animal where bodyWeight > 100.001f" ).list();
		s.createQuery( "from Animal where bodyWeight > 100.001F" ).list();
		s.createQuery( "from Animal where bodyWeight > 100.001d" ).list();
		s.createQuery( "from Animal where bodyWeight > 100.001D" ).list();
		s.createQuery( "from Animal where bodyWeight > .001f" ).list();
		s.createQuery( "from Animal where bodyWeight > 100e-10" ).list();
		s.createQuery( "from Animal where bodyWeight > .01E-10" ).list();
		s.createQuery( "from Animal where bodyWeight > 1e-38" ).list();
		s.getTransaction().commit();
		s.close();
	}
