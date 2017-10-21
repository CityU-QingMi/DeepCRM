	@Test
	public void testNakedPropertyRef() {
		// note: simply performing syntax and column/table resolution checking in the db
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( "from Animal where bodyWeight = bodyWeight" ).list();
		s.createQuery( "select bodyWeight from Animal" ).list();
		s.createQuery( "select max(bodyWeight) from Animal" ).list();
		s.getTransaction().commit();
		s.close();
	}
