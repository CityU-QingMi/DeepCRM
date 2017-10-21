	@Test
	public void testSubSelectAsArithmeticOperand() {
		Session s = openSession();
		s.beginTransaction();

		// first a control
		s.createQuery( "from Zoo z where ( select count(*) from Zoo ) = 0" ).list();

		// now as operands singly:
		s.createQuery( "from Zoo z where ( select count(*) from Zoo ) + 0 = 0" ).list();
		s.createQuery( "from Zoo z where 0 + ( select count(*) from Zoo ) = 0" ).list();

		// and doubly:
		s.createQuery( "from Zoo z where ( select count(*) from Zoo ) + ( select count(*) from Zoo ) = 0" ).list();

		s.getTransaction().commit();
		s.close();
	}
