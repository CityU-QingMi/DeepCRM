	@Test
	public void testNakedEntityAssociationReference() {
		// note: simply performing syntax and column/table resolution checking in the db
		Session s = openSession();
		s.beginTransaction();
		if ( getDialect() instanceof AbstractHANADialect ) {
			s.createQuery( "from Animal where mother is null" ).list();
		}
		else {
			s.createQuery( "from Animal where mother = :mother" ).setParameter( "mother", null ).list();
		}

		s.getTransaction().commit();
		s.close();
	}
